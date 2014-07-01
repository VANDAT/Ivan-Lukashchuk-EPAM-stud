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
package com.epam.kiev.mystore.facades.converters.populator;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;

import org.springframework.util.Assert;

import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.facades.data.OrganizationData;


public class OrganizationPopulator extends AbstractPopulatingConverter<OrganizationModel, OrganizationData>
{
	@Override
	protected OrganizationData createTarget()
	{
		return new OrganizationData();
	}

	@Override
	public void populate(final OrganizationModel source, final OrganizationData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setId(source.getId().toString());
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setPhoneNumber(source.getPhoneNumber());

		super.populate(source, target);
	}
}