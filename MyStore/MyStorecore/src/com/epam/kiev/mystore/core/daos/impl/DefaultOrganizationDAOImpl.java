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
package com.epam.kiev.mystore.core.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.kiev.mystore.core.daos.OrganizationDAO;
import com.epam.kiev.mystore.core.model.OrganizationModel;


@Repository(value = "organizationDAO")
public class DefaultOrganizationDAOImpl implements OrganizationDAO
{
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.daos.OrganizationDAO#findAll()
	 */
	@Override
	public List<OrganizationModel> findAll()
	{
		final String queryString = "SELECT {pk} FROM {" + OrganizationModel._TYPECODE + "}";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		return flexibleSearchService.<OrganizationModel> search(query).getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.daos.OrganizationDAO#findById(int)
	 */
	@Override
	public List<OrganizationModel> findById(final int id)
	{
		final String queryString = "SELECT {pk} FROM {" + OrganizationModel._TYPECODE + "} WHERE {" + OrganizationModel.ID
				+ "}=?id";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("id", id);
		return flexibleSearchService.<OrganizationModel> search(query).getResult();
	}

}
