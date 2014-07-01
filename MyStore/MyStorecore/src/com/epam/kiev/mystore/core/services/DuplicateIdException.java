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

public class DuplicateIdException extends Exception
{

	public DuplicateIdException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	public DuplicateIdException(final String message)
	{
		super(message);
	}

	public DuplicateIdException(final Throwable cause)
	{
		super(cause);
	}
}
