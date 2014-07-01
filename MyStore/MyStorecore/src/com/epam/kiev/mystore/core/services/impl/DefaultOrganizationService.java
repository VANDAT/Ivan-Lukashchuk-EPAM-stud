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

import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.kiev.mystore.core.daos.OrganizationDAO;
import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.core.services.DuplicateIdException;
import com.epam.kiev.mystore.core.services.OrganizationService;
import com.epam.kiev.mystore.facades.data.OrganizationData;


@Service
public class DefaultOrganizationService implements OrganizationService
{
	@Autowired
	private OrganizationDAO organizationDAO;
	@Autowired
	private ModelService modelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.OrganizationService#findAll()
	 */
	@Override
	public List<OrganizationModel> findAll()
	{
		return organizationDAO.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.OrganizationService#findById(int)
	 */
	@Override
	public OrganizationModel findById(final int id)
	{
		final List<OrganizationModel> organizationModels = organizationDAO.findById(id);
		if (organizationModels != null && !organizationModels.isEmpty())
		{
			return organizationModels.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.OrganizationService#createOrganization(int, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean createOrganization(final OrganizationData organizationData) throws DuplicateIdException
	{
		final OrganizationModel organizationModel = modelService.create(OrganizationModel.class);
		organizationModel.setId(new Integer(organizationData.getId()));
		organizationModel.setEmail(organizationData.getEmail());
		organizationModel.setName(organizationData.getName());
		organizationModel.setPhoneNumber(organizationData.getPhoneNumber());
		try
		{
			modelService.save(organizationModel);
		}
		catch (final ModelSavingException exception)
		{
			throw new DuplicateIdException(exception);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.OrganizationService#updateOrganization(int, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateOrganization(final OrganizationData organizationData)
	{
		final OrganizationModel organizationModel = findById(Integer.parseInt(organizationData.getId()));
		organizationModel.setName(organizationData.getName());
		organizationModel.setPhoneNumber(organizationData.getPhoneNumber());

		modelService.save(organizationModel);

		return false;
	}
}
