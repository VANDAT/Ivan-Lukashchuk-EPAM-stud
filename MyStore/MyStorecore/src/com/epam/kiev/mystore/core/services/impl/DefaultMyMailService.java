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
package com.epam.kiev.mystore.core.services.impl;

import de.hybris.platform.servicelayer.i18n.L10NService;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.epam.kiev.mystore.core.daos.OrganizationDAO;
import com.epam.kiev.mystore.core.model.MyStoreCustomerModel;
import com.epam.kiev.mystore.core.model.OrganizationModel;
import com.epam.kiev.mystore.core.services.MyMailService;


public class DefaultMyMailService implements MyMailService
{

	private static final Logger LOG = Logger.getLogger(DefaultMyMailService.class);

	private JavaMailSender mailSender;

	private OrganizationDAO organizationDAO;

	@Autowired
	private L10NService l10NService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.kiev.mystore.core.services.MailService#sendCustomerQty()
	 */
	@Override
	public void sendCustomerList()
	{
		for (final OrganizationModel organization : organizationDAO.findAll())
		{
			final List<MyStoreCustomerModel> customers = organization.getCustomers();
			final MimeMessage message = mailSender.createMimeMessage();
			try
			{
				LOG.info(customers.toString());
				final MimeMessageHelper helper = new MimeMessageHelper(message, true);
				//				helper.setFrom("hybrisemailtest@gmail.com");
				helper.setTo(organization.getEmail());
				helper.setSubject(l10NService.getLocalizedString("mail.customerlist.subject"));
				String str = "";
				if (!customers.isEmpty())
				{
					str = createListOfCustomerMessage(customers);
				}
				else
				{
					str = l10NService.getLocalizedString("mail.customerlist.emptylist");
				}
				helper.setText(str);

			}
			catch (final MessagingException e)
			{
				throw new MailParseException(e);
			}
			mailSender.send(message);
		}
	}

	/**
	 * @param customers
	 * @return
	 */
	private String createListOfCustomerMessage(final List<MyStoreCustomerModel> customers)
	{
		final String delimitor = l10NService.getLocalizedString("mail.customerlist.delimiter");
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(l10NService.getLocalizedString("mail.customerlist.text"));
		for (final MyStoreCustomerModel myStoreCustomerModel : customers)
		{
			stringBuilder.append(myStoreCustomerModel.getName()).append(delimitor);
		}
		final int endingIndex = stringBuilder.length();
		stringBuilder.delete(endingIndex - delimitor.length(), endingIndex);
		stringBuilder.append(l10NService.getLocalizedString("mail.customerlist.end"));
		return stringBuilder.toString();
	}

	public void setOrganizationDAO(final OrganizationDAO organizationDAO)
	{
		this.organizationDAO = organizationDAO;
	}

	public void setMailSender(final JavaMailSender javaMailSender)
	{
		this.mailSender = javaMailSender;
	}

}