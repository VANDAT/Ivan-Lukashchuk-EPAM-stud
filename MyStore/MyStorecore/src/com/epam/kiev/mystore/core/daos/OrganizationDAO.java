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
package com.epam.kiev.mystore.core.daos;

import java.util.List;

import com.epam.kiev.mystore.core.model.OrganizationModel;


public interface OrganizationDAO
{
	List<OrganizationModel> findAll();

	List<OrganizationModel> findById(int id);
}
