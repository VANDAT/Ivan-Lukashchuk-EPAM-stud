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
package com.epam.kiev.mystore.core.interceptors;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.kiev.mystore.core.model.MyStoreCustomerModel;


public class MyStoreCustomerStatusInterceptor implements ValidateInterceptor<MyStoreCustomerModel>
{

	private static final String MAX_ATTEMPT_COUNT_KEY = "max.attempt.count";

	@Autowired
	private ConfigurationService configurationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final MyStoreCustomerModel model, final InterceptorContext context) throws InterceptorException
	{
		if (model.getAttemptCount() >= configurationService.getConfiguration().getInt(MAX_ATTEMPT_COUNT_KEY) && !model.getStatus())
		{
			model.setAttemptCount(0);
		}
	}
}
