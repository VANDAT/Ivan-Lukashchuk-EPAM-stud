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
package com.epam.kiev.mystore.core.bundle.impl;

import de.hybris.platform.configurablebundleservices.bundle.impl.DefaultCartBundleComponentEditableChecker;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.CartModel;


public class MyStoreCartBundleComponentEditableChecker extends DefaultCartBundleComponentEditableChecker
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.configurablebundleservices.bundle.impl.DefaultAbstractBundleComponentEditableChecker#canEdit
	 * (de.hybris.platform.core.model.order.AbstractOrderModel,
	 * de.hybris.platform.configurablebundleservices.model.BundleTemplateModel, int)
	 */
	@Override
	public boolean canEdit(final CartModel masterAbstractOrder, final BundleTemplateModel bundleTemplate, final int bundleNo)
	{
		return true;
	}
}
