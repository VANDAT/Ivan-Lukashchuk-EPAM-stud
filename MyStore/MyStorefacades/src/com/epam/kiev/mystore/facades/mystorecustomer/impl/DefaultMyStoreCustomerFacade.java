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
package com.epam.kiev.mystore.facades.mystorecustomer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.epam.kiev.mystore.core.model.MyStoreCustomerModel;
import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.core.services.MyStoreCartService;
import com.epam.kiev.mystore.core.services.MyStoreCustomerService;
import com.epam.kiev.mystore.core.services.OrganizationService;
import com.epam.kiev.mystore.facades.data.MyStoreRegisterData;
import com.epam.kiev.mystore.facades.data.OrganizationData;
import com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade;


@Component
public class DefaultMyStoreCustomerFacade extends DefaultCustomerFacade implements MyStoreCustomerFacade
{
	protected static final Logger LOG = Logger.getLogger(DefaultMyStoreCustomerFacade.class);

	@Autowired
	private MyStoreCustomerService myStoreCustomerService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private BaseSiteService baseSiteService;

	@Autowired
	private MyStoreCartService myStoreCartService;

	@Resource(name = "organizationConverter")
	private Converter<OrganizationModel, OrganizationData> organizationConverter;

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#loginSuccess()
	 */
	@Override
	public void loginSuccess(final Authentication authentication)
	{
		myStoreCustomerService.refreshAttemptCountAndStatus(authentication.getName());

		final CustomerData userData = getCurrentCustomer();

		myStoreCartService.cartLoginOperation();

		// Update the session currency (which might change the cart currency)
		if (!updateSessionCurrency(userData.getCurrency(), getStoreSessionFacade().getDefaultCurrency()))
		{
			// Update the user
			getUserFacade().syncSessionCurrency();
		}

		// Update the user
		getUserFacade().syncSessionLanguage();

		// Calculate the cart after setting everything up
		if (getCartService().hasSessionCart())
		{
			final CartModel sessionCart = getCartService().getSessionCart();
			try
			{
				getCommerceCartService().recalculateCart(sessionCart);
			}
			catch (final CalculationException ex)
			{
				LOG.error("Failed to recalculate order [" + sessionCart.getCode() + "]", ex);
			}
		}
	}

	@Override
	public void register(final MyStoreRegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

		final MyStoreCustomerModel newCustomer = getModelService().create(MyStoreCustomerModel.class);
		newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}

		final TitleModel title = getUserService().getTitleForCode(registerData.getTitleCode());
		newCustomer.setTitle(title);
		final List<OrganizationModel> organizationModels = new ArrayList<OrganizationModel>();
		for (final String organizationId : registerData.getOrganizationIds())
		{
			final OrganizationModel organizationModel = organizationService.findById(Integer.parseInt(organizationId));
			organizationModels.add(organizationModel);
		}

		newCustomer.setOrganizations(organizationModels);
		setUidForRegister(registerData, newCustomer);
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		getCustomerAccountService().register(newCustomer, registerData.getPassword());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade#getOrganizations()
	 */
	@Override
	public List<OrganizationData> getOrganizations()
	{
		return Converters.convertAll(((MyStoreCustomerModel) getUserService().getCurrentUser()).getOrganizations(),
				organizationConverter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade#getNotMyOrganizations()
	 */
	@Override
	public List<OrganizationData> getNotMyOrganizations()
	{
		return Converters.convertAll(myStoreCustomerService.getNotMyOrganizations(), organizationConverter);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade#removeOrganization(com.epam.kiev.mystore.facades
	 * .data.OrganizationData)
	 */
	@Override
	public boolean removeOrganization(final OrganizationData organizationData)
	{
		validateParameterNotNullStandardMessage("organizationData", organizationData);
		return myStoreCustomerService.removeOrganization(Integer.parseInt(organizationData.getId()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade#addOrganization(com.epam.kiev.mystore.facades
	 * .data.OrganizationData)
	 */
	@Override
	public boolean addOrganization(final OrganizationData organizationData)
	{
		validateParameterNotNullStandardMessage("organizationData", organizationData);
		return myStoreCustomerService.addOrganization(Integer.parseInt(organizationData.getId()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade#incrementAttemptCount(java.lang.String)
	 */
	@Override
	public void incrementAttemptCount(final String uid)
	{
		myStoreCustomerService.incrementAttemptCount(uid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade#cheackUserStatus(java.lang.String)
	 */
	@Override
	public boolean checkUserStatus(final String uid)
	{
		return myStoreCustomerService.checkUserStatus(uid);
	}


}