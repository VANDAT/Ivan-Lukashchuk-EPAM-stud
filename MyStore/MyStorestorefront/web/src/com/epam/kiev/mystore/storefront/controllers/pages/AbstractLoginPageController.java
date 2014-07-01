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
package com.epam.kiev.mystore.storefront.controllers.pages;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.epam.kiev.mystore.facades.mystorecustomer.MyStoreCustomerFacade;
import com.epam.kiev.mystore.storefront.breadcrumb.Breadcrumb;
import com.epam.kiev.mystore.storefront.controllers.util.GlobalMessages;
import com.epam.kiev.mystore.storefront.forms.LoginForm;
import com.epam.kiev.mystore.storefront.forms.RegisterForm;


/**
 * Abstract base class for login page controllers
 */
@Component
public abstract class AbstractLoginPageController extends AbstractRegisterPageController
{
	private static final Logger LOG = Logger.getLogger(AbstractLoginPageController.class);

	@Autowired
	private MyStoreCustomerFacade myStoreCustomerFacade;

	protected static final String SPRING_SECURITY_LAST_USERNAME = "SPRING_SECURITY_LAST_USERNAME";

	protected String getDefaultLoginPage(final boolean loginError, final HttpSession session, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{

		final LoginForm loginForm = new LoginForm();
		model.addAttribute(loginForm);
		model.addAttribute(new RegisterForm());

		final String username = (String) session.getAttribute(SPRING_SECURITY_LAST_USERNAME);
		if (username != null)
		{
			session.removeAttribute(SPRING_SECURITY_LAST_USERNAME);
		}

		loginForm.setJ_username(username);
		storeCmsPageInModel(model, getCmsPage());
		setUpMetaDataForContentPage(model, (ContentPageModel) getCmsPage());
		model.addAttribute("metaRobots", "index,no-follow");


		final Breadcrumb loginBreadcrumbEntry = new Breadcrumb("#", getMessageSource().getMessage("header.link.login", null,
				getI18nService().getCurrentLocale()), null);
		model.addAttribute("breadcrumbs", Collections.singletonList(loginBreadcrumbEntry));

		if (loginError)
		{
			if (myStoreCustomerFacade.checkUserStatus(((LoginForm) model.asMap().get("loginForm")).getJ_username()))
			{
				GlobalMessages.addErrorMessage(model, "login.error.account.status.true");
			}
			else
			{
				GlobalMessages.addErrorMessage(model, "login.error.account.not.found.title");
			}
		}
		return getView();
	}
}
