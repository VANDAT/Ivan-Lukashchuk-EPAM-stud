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

import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.kiev.mystore.core.daos.MyStoreCustomerDAO;
import com.epam.kiev.mystore.core.model.MyStoreCustomerModel;
import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.core.services.MyStoreCustomerService;
import com.epam.kiev.mystore.core.services.OrganizationService;


public class DefaultMyStoreCustomerServiceImpl extends DefaultCustomerAccountService implements MyStoreCustomerService
{

	private static final String MAX_ATTEMPT_COUNT_KEY = "max.attempt.count";
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private MyStoreCustomerDAO myStoreCustomerDAO;
	@Autowired
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#incrementAttemptCount(java.lang.String)
	 */
	@Override
	public boolean incrementAttemptCount(final String uid)
	{
		final MyStoreCustomerModel myStoreCustomerModel = myStoreCustomerDAO.findByUID(uid);
		if (myStoreCustomerModel != null)
		{
			final int attemptCounter = myStoreCustomerModel.getAttemptCount() + 1;
			myStoreCustomerModel.setAttemptCount(attemptCounter);
			if (attemptCounter >= configurationService.getConfiguration().getInt(MAX_ATTEMPT_COUNT_KEY))
			{
				myStoreCustomerModel.setStatus(true);
			}
			modelService.save(myStoreCustomerModel);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#refreshAttemptCountAndStatus(java.lang.String)
	 */
	@Override
	public boolean refreshAttemptCountAndStatus(final String uid)
	{
		final MyStoreCustomerModel myStoreCustomerModel = myStoreCustomerDAO.findByUID(uid);
		if (myStoreCustomerModel != null)
		{
			myStoreCustomerModel.setAttemptCount(0);
			myStoreCustomerModel.setStatus(false);
			modelService.save(myStoreCustomerModel);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#getByUID(java.lang.String)
	 */
	@Override
	public MyStoreCustomerModel getByUID(final String uid)
	{
		return myStoreCustomerDAO.findByUID(uid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#cheackUserStatus(java.lang.String)
	 */
	@Override
	public boolean checkUserStatus(final String uid)
	{
		final MyStoreCustomerModel myStoreCustomerModel = getByUID(uid);
		return (myStoreCustomerModel != null && myStoreCustomerModel.getStatus().booleanValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#getNotMyOrganizations()
	 */
	@Override
	public List<OrganizationModel> getNotMyOrganizations()
	{
		final List<OrganizationModel> myOrganizationModels = ((MyStoreCustomerModel) getUserService().getCurrentUser())
				.getOrganizations();
		final List<OrganizationModel> organizationModels = new ArrayList<>(organizationService.findAll());
		organizationModels.removeAll(myOrganizationModels);
		return organizationModels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#removeOrganization(int)
	 */
	@Override
	public boolean removeOrganization(final int id)
	{
		final MyStoreCustomerModel myStoreCurrentCustomer = (MyStoreCustomerModel) getUserService().getCurrentUser();
		final List<OrganizationModel> organizationModels = myStoreCurrentCustomer.getOrganizations();
		for (final OrganizationModel organizationModel : organizationModels)
		{
			if (id == organizationModel.getId().intValue())
			{
				final List<OrganizationModel> buf = new ArrayList<>(organizationModels);
				buf.remove(organizationModel);
				myStoreCurrentCustomer.setOrganizations(buf);
				getModelService().save(myStoreCurrentCustomer);
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MyStoreCustomerService#addOrganization(int)
	 */
	@Override
	public boolean addOrganization(final int id)
	{
		final MyStoreCustomerModel myStoreCurrentCustomer = (MyStoreCustomerModel) getUserService().getCurrentUser();
		final List<OrganizationModel> organizationModels = myStoreCurrentCustomer.getOrganizations();
		final List<OrganizationModel> buf = new ArrayList<>(organizationModels);
		final boolean result = buf.add(organizationService.findById(id));
		if (result)
		{
			myStoreCurrentCustomer.setOrganizations(buf);
			getModelService().save(myStoreCurrentCustomer);
		}
		return result;
	}

}
