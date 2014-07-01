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

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.acceleratorservices.order.impl.DefaultCartServiceForAccelerator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartFactory;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionservices.model.BillingTimeModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.kiev.mystore.core.services.MyStoreCartService;


public class MyStoreCartServiceForAccelerator extends DefaultCartServiceForAccelerator implements MyStoreCartService
{
	@Autowired
	private UserService userService;

	@Autowired
	private CartFactory cartFactory;

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCartService#cartLoginOperation()
	 */
	@Override
	public void cartLoginOperation()
	{
		if (hasSessionCart() && getSessionCart().getLastModifiedEntries().size() != 0)
		{
			final List<CartModel> cartModels = (List) getUserService().getCurrentUser().getCarts();
			CartModel sessionCartModel = getSessionCart();
			if (cartModels.size() > 1)
			{
				CartModel oldCartModel = cartModels.get(cartModels.size() - 2);
				oldCartModel = getTopLevelCart(oldCartModel);
				sessionCartModel = getTopLevelCart(sessionCartModel);
				mergeCarts(sessionCartModel, oldCartModel);
				getModelService().remove(sessionCartModel);
				setSessionCart(oldCartModel);
				changeCurrentCartUser(getUserService().getCurrentUser());
			}
		}
	}

	/**
	 * @param sourceCart
	 * @param targetCart
	 */
	private void mergeCarts(final CartModel sourceCart, final CartModel targetCart)
	{
		incrementBundleNoInSourceCart(sourceCart, targetCart);
		final Map<AbstractOrderEntryModel, CartEntryModel> logicCartEntryModels = new HashMap<>();
		appendToCart(sourceCart, targetCart, logicCartEntryModels);
		final Map<BillingTimeModel, AbstractOrderModel> sessionChildrenCartModelMap = new HashMap<>();
		for (final AbstractOrderModel child : sourceCart.getChildren())
		{
			final BillingTimeModel billingTimeModel = child.getBillingTime();
			sessionChildrenCartModelMap.put(billingTimeModel, child);
			addChildCartIfNotExists(targetCart, billingTimeModel);
		}
		getModelService().save(targetCart);
		for (final AbstractOrderModel child : targetCart.getChildren())
		{
			final BillingTimeModel billingTimeModel = child.getBillingTime();
			final CartModel sessionildCartModel = (CartModel) sessionChildrenCartModelMap.get(billingTimeModel);
			if (sessionildCartModel != null)
			{
				appendToCart(sessionildCartModel, (CartModel) child, logicCartEntryModels);
				getModelService().save(child);
				sessionChildrenCartModelMap.remove(billingTimeModel);
			}
		}
		getModelService().save(targetCart);
	}

	/**
	 * @param sourceCart
	 * @param targetCart
	 */
	private void incrementBundleNoInSourceCart(final CartModel sourceCart, final CartModel targetCart)
	{
		int bundleNo = 1;
		int newBundleNo = 1;
		for (final AbstractOrderEntryModel oldEntryModel : targetCart.getEntries())
		{
			bundleNo = oldEntryModel.getBundleNo().intValue();
			if (bundleNo > newBundleNo)
			{
				newBundleNo = bundleNo;
			}
		}
		for (final AbstractOrderEntryModel sessionEntryModel : sourceCart.getEntries())
		{
			final int currentBundleNo = sessionEntryModel.getBundleNo().intValue();
			if (currentBundleNo != 0)
			{
				sessionEntryModel.setBundleNo(currentBundleNo + newBundleNo);
			}
		}
	}

	/**
	 * @param oldCartModel
	 * @return
	 */
	private CartModel getTopLevelCart(CartModel oldCartModel)
	{
		while (oldCartModel != null && oldCartModel.getParent() != null)
		{
			oldCartModel = (CartModel) oldCartModel.getParent();
		}
		return oldCartModel;
	}

	protected void appendToCart(final CartModel sourceCart, final CartModel targetCart,
			final Map<AbstractOrderEntryModel, CartEntryModel> logicCartEntryModels)
	{
		validateParameterNotNull(targetCart, "targetCart must not be null!");
		final int lastEntryNo = getNextEntryNumber(targetCart, null);
		//clone source entries

		final Collection<CartEntryModel> sourceClones = getCloneAbstractOrderStrategy().cloneEntries(
				getAbstractOrderEntryTypeService().getAbstractOrderEntryType(targetCart), sourceCart);

		postProcessClonedEntries(sourceClones, lastEntryNo, targetCart, logicCartEntryModels);
		if (targetCart.getParent() == null)
		{
			final Iterator<AbstractOrderEntryModel> sourceCartEntries = sourceCart.getEntries().iterator();
			final Iterator<CartEntryModel> sourceCloneCartEntries = sourceClones.iterator();
			while (sourceCartEntries.hasNext() && sourceCloneCartEntries.hasNext())
			{
				logicCartEntryModels.put(sourceCartEntries.next(), sourceCloneCartEntries.next());
			}
		}
		//add clones to target cart
		final List<AbstractOrderEntryModel> targetEntries = new ArrayList<AbstractOrderEntryModel>(targetCart.getEntries());
		targetEntries.addAll(sourceClones);
		targetCart.setEntries(targetEntries);
	}

	protected void postProcessClonedEntries(final Collection<CartEntryModel> sourceClones, final int lastEntryNo,
			final CartModel targetCart, final Map<AbstractOrderEntryModel, CartEntryModel> logicCartEntryModels)
	{
		final boolean isParent = (targetCart.getParent() == null);
		int entryNumber = lastEntryNo;
		for (final CartEntryModel entry : sourceClones)
		{
			entry.setEntryNumber(Integer.valueOf(entryNumber++));
			entry.setOrder(targetCart);
			if (!isParent)
			{
				entry.setMasterEntry(logicCartEntryModels.get(entry.getMasterEntry()));
			}
		}
	}

	private void addChildCartIfNotExists(final CartModel cartModel, final BillingTimeModel billingTimeModel)
	{
		for (final AbstractOrderModel child : cartModel.getChildren())
		{
			if (child.getBillingTime().equals(billingTimeModel))
			{
				return;
			}
		}
		final CartModel childCartModel = cartFactory.createCart();
		childCartModel.setParent(cartModel);
		childCartModel.setBillingTime(billingTimeModel);
		getModelService().save(childCartModel);
	}

}
