/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.epam.kiev.mystore.core.jobs;


import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import com.epam.kiev.mystore.core.services.MyMailService;


public class SendCustomerCountJob extends AbstractJobPerformable<CronJobModel>
{

	private MyMailService myMailService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		myMailService.sendCustomerList();
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public void setMyMailService(final MyMailService mailService)
	{
		this.myMailService = mailService;
	}
}