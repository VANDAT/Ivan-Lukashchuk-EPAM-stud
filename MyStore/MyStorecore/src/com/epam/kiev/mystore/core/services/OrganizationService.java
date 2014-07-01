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
package com.epam.kiev.mystore.core.services;

import java.util.List;

import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.facades.data.OrganizationData;


public interface OrganizationService
{
	List<OrganizationModel> findAll();

	OrganizationModel findById(int id);

	boolean createOrganization(OrganizationData organizationData) throws DuplicateIdException;

	boolean updateOrganization(OrganizationData organizationData);
}
