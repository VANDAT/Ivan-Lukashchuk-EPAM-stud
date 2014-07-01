/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.epam.kiev.mystore.storefront.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


/**
 * Form object for updating profile.
 */
public class OrganizationForm
{
	private String id;
	private String email;
	private String name;
	private String phoneNumber;

	@Size(min = 1, max = 10, message = "{text.account.organizations.id.invalid}")
	@NotBlank(message = "{text.account.organizations.id.invalid}")
	@Digits(fraction = 0, integer = 10, message = "{text.account.organizations.id.digitsInvalid}")
	public String getId()
	{
		return id;
	}


	public void setId(final String id)
	{
		this.id = id;
	}

	@Size(min = 1, max = 255, message = "{text.account.organizations.name.invalid}")
	@NotBlank(message = "{text.account.organizations.name.invalid}")
	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	@Size(min = 1, max = 20, message = "{text.account.organizations.phoneNumber.invalid}")
	@NotBlank(message = "{text.account.organizations.phoneNumber.invalid}")
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}


	public String getEmail()
	{
		return email;
	}


	public void setEmail(final String email)
	{
		this.email = email;
	}
}
