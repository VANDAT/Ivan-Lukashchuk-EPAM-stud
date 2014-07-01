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
package com.epam.kiev.mystore.facades.mystorecustomer;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.epam.kiev.mystore.facades.data.MyStoreRegisterData;
import com.epam.kiev.mystore.facades.data.OrganizationData;


public interface MyStoreCustomerFacade extends CustomerFacade
{
	void register(MyStoreRegisterData registerData) throws DuplicateUidException;

	List<OrganizationData> getOrganizations();

	List<OrganizationData> getNotMyOrganizations();

	boolean removeOrganization(OrganizationData organizationData);

	boolean addOrganization(OrganizationData organizationData);

	void loginSuccess(Authentication authentication);

	void incrementAttemptCount(String uid);

	boolean checkUserStatus(String uid);

}
