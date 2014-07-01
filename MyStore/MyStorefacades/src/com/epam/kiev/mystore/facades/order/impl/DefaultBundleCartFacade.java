/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.epam.kiev.mystore.facades.order.impl;


import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.configurablebundleservices.bundle.BundleCommerceCartService;
import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.subscriptionfacades.order.impl.DefaultSubscriptionCartFacade;
import de.hybris.platform.subscriptionservices.subscription.BillingTimeService;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.epam.kiev.mystore.facades.order.BundleCartFacade;


/**
 * Default implementation for {@link BundleCartFacade}
 */
public class DefaultBundleCartFacade extends DefaultSubscriptionCartFacade implements BundleCartFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultBundleCartFacade.class);

	private BillingTimeService billingTimeService;
	private BundleCommerceCartService bundleCommerceCartService;
	private BundleTemplateService bundleTemplateService;
	private Converter<CartModel, CartData> bundleCartConverter;

	@Override
	public List<CartModificationData> addToCart(final String productCode, final long quantity, final int bundleNo,
			final String bundleTemplateId, final boolean removeCurrentProducts) throws CommerceCartModificationException
	{
		final CartModel cartModel = getCartService().getSessionCart();
		final ProductModel product = getProductService().getProductForCode(productCode);

		final String xml = getProductAsXML(product);

		BundleTemplateModel bundleTemplate = null;
		if (StringUtils.isNotEmpty(bundleTemplateId))
		{
			if (bundleNo > 0)
			{
				final List<CartEntryModel> entries = getBundleCommerceCartService().getCartEntriesForBundle(cartModel, bundleNo);
				final BundleTemplateModel parentModel = entries.get(0).getBundleTemplate().getParentTemplate();
				bundleTemplate = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId, parentModel.getVersion());
			}
			else
			{
				bundleTemplate = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
			}
		}

		final List<CommerceCartModification> modifications = getBundleCommerceCartService().addToCart(cartModel, product, quantity,
				product.getUnit(), false, bundleNo, bundleTemplate, removeCurrentProducts, xml);
		return Converters.convertAll(modifications, getCartModificationConverter());
	}

	@Override
	public List<CartModificationData> addToCart(final String productCode1, final int bundleNo, final String bundleTemplateId1,
			final String productCode2, final String bundleTemplateId2) throws CommerceCartModificationException
	{
		final CartModel cartModel = getCartService().getSessionCart();
		final ProductModel product1 = getProductService().getProductForCode(productCode1);
		final ProductModel product2 = getProductService().getProductForCode(productCode2);

		final String xml1 = getProductAsXML(product1);
		final String xml2 = getProductAsXML(product2);

		BundleTemplateModel bundleTemplateModel1 = null;
		BundleTemplateModel bundleTemplateModel2 = null;
		if (StringUtils.isNotEmpty(bundleTemplateId1))
		{
			bundleTemplateModel1 = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId1);
		}
		if (StringUtils.isNotEmpty(bundleTemplateId2))
		{
			bundleTemplateModel2 = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId2);
		}

		final List<CommerceCartModification> modifications = getBundleCommerceCartService().addToCart(cartModel,
				product1.getUnit(), bundleNo, product1, bundleTemplateModel1, product2, bundleTemplateModel2, xml1, xml2);
		return Converters.convertAll(modifications, getCartModificationConverter());
	}

	@Override
	public void deleteCartBundle(final int bundleNo) throws CommerceCartModificationException
	{
		getBundleCommerceCartService().removeAllEntries(getCartService().getSessionCart(), bundleNo);
	}

	@Override
	public boolean isCartValid()
	{
		final BundleTemplateModel bundleTemplate = getBundleCommerceCartService().getFirstInvalidComponentInCart(
				getCartService().getSessionCart());

		if (bundleTemplate == null)
		{
			return true;
		}
		return false;
	}

	protected BillingTimeService getBillingTimeService()
	{
		return billingTimeService;
	}

	@Required
	public void setBillingTimeService(final BillingTimeService billingTimeService)
	{
		this.billingTimeService = billingTimeService;
	}

	protected BundleCommerceCartService getBundleCommerceCartService()
	{
		return bundleCommerceCartService;
	}

	@Required
	public void setBundleCommerceCartService(final BundleCommerceCartService bundleCommerceCartService)
	{
		this.bundleCommerceCartService = bundleCommerceCartService;
	}

	protected BundleTemplateService getBundleTemplateService()
	{
		return bundleTemplateService;
	}

	@Required
	public void setBundleTemplateService(final BundleTemplateService bundleTemplateService)
	{
		this.bundleTemplateService = bundleTemplateService;
	}

	protected Converter<CartModel, CartData> getBundleCartConverter()
	{
		return bundleCartConverter;
	}

	@Required
	public void setBundleCartConverter(final Converter<CartModel, CartData> bundleCartConverter)
	{
		this.bundleCartConverter = bundleCartConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCartFacade#updateCartEntry(long, long)
	 */
	@Override
	public CartModificationData updateCartEntry(final long entryNumber, long quantity) throws CommerceCartModificationException
	{
		final CartModel cartModel = getCartService().getSessionCart();

		final AbstractOrderEntryModel cartEntryModel = getEntryForNumber(cartModel, (int) entryNumber);
		final int bundleNo = cartEntryModel.getBundleNo().intValue();
		CommerceCartModification modification = null;
		if (bundleNo == 0)
		{
			modification = getCommerceCartService().updateQuantityForCartEntry(cartModel, entryNumber, quantity);
		}
		else
		{
			boolean logic = false;
			long deviceQuantity = 0;
			boolean isDevice = false;
			for (final AbstractOrderEntryModel cartEntry : cartModel.getEntries())
			{
				isDevice = cartEntry.getProduct().getItemtype().equals("Device");
				if (isDevice && cartEntry.getBundleNo().intValue() == bundleNo)
				{
					deviceQuantity = cartEntry.getQuantity().longValue();
					if (cartEntry.getEntryNumber().longValue() == entryNumber)
					{
						logic = true;
						modification = getCommerceCartService().updateQuantityForCartEntry(cartModel,
								cartEntry.getEntryNumber().longValue(), quantity);
						quantity = modification.getQuantity();
					}
				}
			}
			for (final AbstractOrderEntryModel cartEntry : cartModel.getEntries())
			{
				if (cartEntry.getBundleNo().intValue() == bundleNo)
				{
					isDevice = cartEntry.getProduct().getItemtype().equals("Device");
					if (logic && (cartEntry.getQuantity().longValue() <= quantity) || !logic
							&& (isDevice && deviceQuantity >= quantity || !isDevice))
					{
						modification = getCommerceCartService().updateQuantityForCartEntry(cartModel,
								cartEntry.getEntryNumber().longValue(), quantity);
						quantity = modification.getQuantity();
					}
				}
			}
		}
		return getCartModificationConverter().convert(modification);
	}

	protected AbstractOrderEntryModel getEntryForNumber(final AbstractOrderModel order, final int number)
	{
		final List entries = order.getEntries();
		if (entries != null && !entries.isEmpty())
		{
			final Integer requestedEntryNumber = Integer.valueOf(number);
			for (final Iterator iterator = entries.iterator(); iterator.hasNext();)
			{
				final AbstractOrderEntryModel entry = (AbstractOrderEntryModel) iterator.next();
				if (entry != null && requestedEntryNumber.equals(entry.getEntryNumber()))
				{
					return entry;
				}
			}
		}
		return null;
	}
}
