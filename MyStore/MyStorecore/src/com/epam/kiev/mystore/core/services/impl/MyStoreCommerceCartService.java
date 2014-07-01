package com.epam.kiev.mystore.core.services.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.configurablebundleservices.bundle.impl.DefaultBundleCommerceCartService;
import de.hybris.platform.core.GenericSearchConstants.LOG;
import de.hybris.platform.core.model.order.CartModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;


public class MyStoreCommerceCartService extends DefaultBundleCommerceCartService
{

	private static final Logger LOG = Logger.getLogger(MyStoreCommerceCartService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService#restoreCart(de.hybris.platform.core.
	 * model.order.CartModel)
	 */
	@Override
	public CommerceCartRestoration restoreCart(CartModel oldCart) throws CommerceCartRestorationException
	{
		CommerceCartRestoration restoration = new CommerceCartRestoration();
		List modifications = new ArrayList();
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
				catch (CommerceCartModificationException e)
				{
					throw new CommerceCartRestorationException(e.getMessage(), e);
				}
			}
		}
		restoration.setModifications(modifications);
		return restoration;
	}
}
