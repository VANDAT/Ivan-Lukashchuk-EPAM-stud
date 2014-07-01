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
package com.epam.kiev.mystore.facades.organization.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.core.services.DuplicateIdException;
import com.epam.kiev.mystore.core.services.OrganizationService;
import com.epam.kiev.mystore.facades.data.OrganizationData;
import com.epam.kiev.mystore.facades.organization.OrganizationFacade;


@Component
public class DefaultOrganizationFacade implements OrganizationFacade
{

	private Converter<OrganizationModel, OrganizationData> organizationConverter;

	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.organization.OrganizationFacade#getOrganizations()
	 */
	@Override
	public List<OrganizationData> getOrganizations()
	{
		return Converters.convertAll(organizationService.findAll(), organizationConverter);
	}

	public Converter<OrganizationModel, OrganizationData> getOrganizationConverter()
	{
		return organizationConverter;
	}

	@Required
	public void setOrganizationConverter(final Converter<OrganizationModel, OrganizationData> organizationConverter)
	{
		this.organizationConverter = organizationConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.organization.OrganizationFacade#createOrganization(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean createOrganization(final OrganizationData organizationData) throws DuplicateIdException
	{
		organizationData.setEmail(userService.getCurrentUser().getUid());
		return organizationService.createOrganization(organizationData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.organization.OrganizationFacade#getOrganization(java.lang.String)
	 */
	@Override
	public OrganizationData getOrganization(final String id)
	{
		return organizationConverter.convert(organizationService.findById(Integer.parseInt(id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.facades.organization.OrganizationFacade#updateOrganization(int, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean updateOrganization(final OrganizationData organizationData)
	{
		return organizationService.updateOrganization(organizationData);
	}
}
