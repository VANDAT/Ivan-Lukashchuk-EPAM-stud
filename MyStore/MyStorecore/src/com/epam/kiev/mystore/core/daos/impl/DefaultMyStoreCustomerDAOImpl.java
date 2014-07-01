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

import com.epam.kiev.mystore.core.daos.MyStoreCustomerDAO;
import com.epam.kiev.mystore.core.model.MyStoreCustomerModel;


@Repository(value = "myStoreCustomerDAO")
public class DefaultMyStoreCustomerDAOImpl implements MyStoreCustomerDAO
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.daos.MyStoreCustomerDAO#findByUID(java.lang.String)
	 */
	@Override
	public MyStoreCustomerModel findByUID(String uid)
	{
		if (uid == null)
		{
			uid = "";
		}
		final String queryString = "SELECT {c:" + MyStoreCustomerModel.PK + "} FROM {" + MyStoreCustomerModel._TYPECODE + " AS c}"
				+ "WHERE {c:" + MyStoreCustomerModel.UID + "} =?uid";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("uid", uid);
		final List<MyStoreCustomerModel> customerModels = flexibleSearchService.<MyStoreCustomerModel> search(query).getResult();
		if (customerModels.isEmpty())
		{
			return null;
		}
		return customerModels.get(0);
	}
}
