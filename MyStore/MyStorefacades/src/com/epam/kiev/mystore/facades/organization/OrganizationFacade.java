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
package com.epam.kiev.mystore.facades.organization;

import java.util.List;

import com.epam.kiev.mystore.core.services.DuplicateIdException;
import com.epam.kiev.mystore.facades.data.OrganizationData;


public interface OrganizationFacade
{
	List<OrganizationData> getOrganizations();

	boolean createOrganization(OrganizationData name) throws DuplicateIdException;

	OrganizationData getOrganization(String id);

	boolean updateOrganization(OrganizationData data);
}
