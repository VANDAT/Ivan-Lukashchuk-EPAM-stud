/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.epam.kiev.mystore.core.services.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.configurablebundleservices.bundle.impl.DefaultBundleCommerceCartService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;


public class MyStoreBundleCommerceCartService extends DefaultBundleCommerceCartService
{

	private static final Logger LOG = Logger.getLogger(MyStoreBundleCommerceCartService.class);


	@Override
	public CommerceCartModification updateQuantityForCartEntry(final CartModel masterCartModel, final long entryNumber,
			final long newQuantity) throws CommerceCartModificationException
	{
		ServicesUtil.validateParameterNotNullStandardMessage("masterCartModel", masterCartModel);
		if (!isMasterCart(masterCartModel))
		{
			throw new IllegalArgumentException((new StringBuilder("Provided cart '")).append(masterCartModel.getCode())
					.append("' is not a master cart").toString());
		}
		int bundleNo = 0;
		final AbstractOrderEntryModel cartEntryModel = getCartEntryToBeUpdated(masterCartModel, entryNumber);
		final BundleTemplateModel bundleTemplate = cartEntryModel.getBundleTemplate();
		if (bundleTemplate != null)
		{
			bundleNo = cartEntryModel.getBundleNo().intValue();

			checkAutoPickRemoval((CartEntryModel) cartEntryModel);
			//checkSelectionCriteriaNotUnderThreshold((CartEntryModel) cartEntryModel);
			//checkAndRemoveDependentComponents(masterCartModel, bundleNo, bundleTemplate);
			//checkIsComponentDependencyMetAfterRemoval(masterCartModel, cartEntryModel.getBundleTemplate(), bundleNo);
			setCartEntriesInSameBundleToNotCalculated(cartEntryModel);
		}
		final CommerceCartModification commerceCartModification = superUpdateQuantityForCartEntry(masterCartModel, entryNumber,
				newQuantity);
		if (bundleNo != 0)
		{
			calculateCart(masterCartModel);
		}
		updateLastModifiedEntriesList(masterCartModel, Collections.singletonList(commerceCartModification));
		return commerceCartModification;
	}

	private CommerceCartModification superUpdateQuantityForCartEntry(final CartModel cartModel, final long entryNumber,
			final long newQuantity) throws CommerceCartModificationException
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		if (!isMasterCart(cartModel))
		{
			throw new IllegalArgumentException((new StringBuilder("Provided cart '")).append(cartModel.getCode())
					.append("' is not a master cart").toString());
		}
		final AbstractOrderEntryModel masterEntry = getCartEntryToBeUpdated(cartModel, entryNumber);
		final ProductModel productModel = masterEntry.getProduct();
		CommerceCartModification modification;
		if (productModel instanceof SubscriptionProductModel)
		{
			//			if (newQuantity < 0L || newQuantity > 1L)
			//			{
			//				throw new CommerceCartModificationException((new StringBuilder("Subscription product '"))
			//						.append(productModel.getCode()).append("' must have a new quantity of 0 or 1, quantity given:")
			//						.append(newQuantity).toString());
			//			}
		}
		else
		{
			modification = superSuperUpdateQuantityForCartEntry(cartModel, entryNumber, newQuantity);
			return modification;
		}
		AbstractOrderEntryModel childEntry;
		CartModel childCart;
		for (final Iterator iterator = masterEntry.getChildEntries().iterator(); iterator.hasNext(); superSuperUpdateQuantityForCartEntry(
				childCart, childEntry.getEntryNumber().intValue(), newQuantity))
		{
			childEntry = (AbstractOrderEntryModel) iterator.next();
			childCart = (CartModel) childEntry.getOrder();
		}

		modification = superSuperUpdateQuantityForCartEntry(cartModel, masterEntry.getEntryNumber().intValue(), newQuantity);
		removeEmptyChildCarts(cartModel);
		return modification;
	}

	private CommerceCartModification superSuperUpdateQuantityForCartEntry(final CartModel cartModel, final long entryNumber,
			final long newQuantity) throws CommerceCartModificationException
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		final AbstractOrderEntryModel entryToUpdate = getEntryForNumber(cartModel, (int) entryNumber);
		validateEntryBeforeModification(newQuantity, entryToUpdate);
		final Integer maxOrderQuantity = entryToUpdate.getProduct().getMaxOrderQuantity();
		final long quantityToAdd = newQuantity - entryToUpdate.getQuantity().longValue();
		if (entryToUpdate.getDeliveryPointOfService() != null)
		{
			final long actualAllowedQuantityChange = getAllowedCartAdjustmentForProduct(cartModel, entryToUpdate.getProduct(),
					quantityToAdd, entryToUpdate.getDeliveryPointOfService());
			return modifyEntry(cartModel, entryToUpdate, actualAllowedQuantityChange, newQuantity, maxOrderQuantity);
		}
		else
		{
			final long actualAllowedQuantityChange = getAllowedCartAdjustmentForProduct(cartModel, entryToUpdate.getProduct(),
					quantityToAdd, null);
			return modifyEntry(cartModel, entryToUpdate, actualAllowedQuantityChange, newQuantity, maxOrderQuantity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService#restoreCart(de.hybris.platform.core.
	 * model.order.CartModel)
	 */
	@Override
	public CommerceCartRestoration restoreCart(final CartModel oldCart) throws CommerceCartRestorationException
	{
		final CommerceCartRestoration restoration = new CommerceCartRestoration();
		final List modifications = new ArrayList();
		if (oldCart != null && getBaseSiteService().getCurrentBaseSite().equals(oldCart.getSite()))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug((new StringBuilder("Restoring from cart ")).append(oldCart.getCode()).append(".").toString());
			}
			if ((new DateTime(oldCart.getModifiedtime())).isAfter((new DateTime(getTimeService().getCurrentTime()))
					.minusSeconds(getCartValidityPeriod())))
			{

				oldCart.setCalculated(Boolean.FALSE);
				getModelService().removeAll(oldCart.getPaymentTransactions());
				oldCart.setPaymentTransactions(Collections.EMPTY_LIST);
				oldCart.setGuid(getGuidKeyGenerator().generate().toString());
				getModelService().save(oldCart);
				calculateCart(oldCart);
				getCartService().setSessionCart(oldCart);
				if (LOG.isDebugEnabled())
				{
					LOG.debug((new StringBuilder("Cart ")).append(oldCart.getCode())
							.append(" was found to be valid and was restored to the session.").toString());
				}
			}
			else
			{
				try
				{
					modifications.addAll(rebuildSessionCart(oldCart));
				}
				catch (final CommerceCartModificationException e)
				{
					throw new CommerceCartRestorationException(e.getMessage(), e);
				}
			}
		}
		restoration.setModifications(modifications);
		return restoration;
	}

}
