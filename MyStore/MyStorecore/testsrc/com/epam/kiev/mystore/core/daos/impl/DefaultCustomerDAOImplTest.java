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

import static org.junit.Assert.assertTrue;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.epam.kiev.mystore.core.daos.MyStoreCustomerDAO;
import com.epam.kiev.mystore.core.model.MyStoreCustomerModel;
import com.epam.kiev.mystore.core.model.OrganizationModel;


public class DefaultCustomerDAOImplTest extends ServicelayerTransactionalTest
{

	@Resource
	private ModelService modelService;

	@Resource
	private MyStoreCustomerDAO myStorecustomerDAO;

	private OrganizationModel organizationModel;
	private MyStoreCustomerModel myStoreCustomerModel;

	@Before
	public void before()
	{
		organizationModel = new OrganizationModel();
		organizationModel.setId(1);
		organizationModel.setEmail("email@test.dd");
		organizationModel.setName("test");
		organizationModel.setPhoneNumber("23452");
		modelService.save(organizationModel);

		myStoreCustomerModel = new MyStoreCustomerModel();
	}

	@Test
	public void customerDAOTest()
	{

//		final List<MyStoreCustomerModel> customers = myStorecustomerDAO.findByOrdanization(organizationModel);
//		assertTrue("No Stadium should be returned", customers.isEmpty());


		//		final StadiumModel stadiumModel = new StadiumModel();
		//		stadiumModel.setCode(STADIUM_NAME);
		//		stadiumModel.setCapacity(STADIUM_CAPACITY);
		//		modelService.save(stadiumModel);
		//
		//		allStadiums = customerDAO.findStadiums();
		//		assertEquals(size + 1, allStadiums.size());
		//		assertEquals("Unexpected stadium found", stadiumModel, allStadiums.get(allStadiums.size() - 1));
		//
		//		stadiumsByCode = customerDAO.findStadiumsByCode(STADIUM_NAME);
		//		assertEquals("Find the one we just saved", 1, stadiumsByCode.size());
		//		assertEquals("Check the names", STADIUM_NAME, stadiumsByCode.get(0).getCode());
		//		assertEquals("Check the capacity", STADIUM_CAPACITY, stadiumsByCode.get(0).getCapacity());

	}
}
