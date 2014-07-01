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
package com.epam.kiev.mystore.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.epam.kiev.mystore.core.constants.MyStoreCoreConstants;
import com.epam.kiev.mystore.core.setup.CoreSystemSetup;



/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
@SuppressWarnings("PMD")
public class MyStoreCoreManager extends GeneratedMyStoreCoreManager
{
	public static final MyStoreCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (MyStoreCoreManager) em.getExtension(MyStoreCoreConstants.EXTENSIONNAME);
	}
}
