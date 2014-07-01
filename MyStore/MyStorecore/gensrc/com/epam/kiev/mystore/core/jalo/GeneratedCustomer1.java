/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at May 27, 2014 12:23:14 PM                    ---
 * ----------------------------------------------------------------
 */
package com.epam.kiev.mystore.core.jalo;

import com.epam.kiev.mystore.core.constants.MyStoreCoreConstants;
import com.epam.kiev.mystore.core.jalo.Organization;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.util.Utilities;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.user.Customer Customer1}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomer1 extends Customer
{
	/** Qualifier of the <code>Customer1.organizations</code> attribute **/
	public static final String ORGANIZATIONS = "organizations";
	/** Relation ordering override parameter constants for Customer12Organization from ((MyStorecore))*/
	protected static String CUSTOMER12ORGANIZATION_SRC_ORDERED = "relation.Customer12Organization.source.ordered";
	protected static String CUSTOMER12ORGANIZATION_TGT_ORDERED = "relation.Customer12Organization.target.ordered";
	/** Relation disable markmodifed parameter constants for Customer12Organization from ((MyStorecore))*/
	protected static String CUSTOMER12ORGANIZATION_MARKMODIFIED = "relation.Customer12Organization.markmodified";
	/** Qualifier of the <code>Customer1.attemptCount</code> attribute **/
	public static final String ATTEMPTCOUNT = "attemptCount";
	/** Qualifier of the <code>Customer1.status</code> attribute **/
	public static final String STATUS = "status";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Customer.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ATTEMPTCOUNT, AttributeMode.INITIAL);
		tmp.put(STATUS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.attemptCount</code> attribute.
	 * @return the attemptCount
	 */
	public Integer getAttemptCount(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ATTEMPTCOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.attemptCount</code> attribute.
	 * @return the attemptCount
	 */
	public Integer getAttemptCount()
	{
		return getAttemptCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.attemptCount</code> attribute. 
	 * @return the attemptCount
	 */
	public int getAttemptCountAsPrimitive(final SessionContext ctx)
	{
		Integer value = getAttemptCount( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.attemptCount</code> attribute. 
	 * @return the attemptCount
	 */
	public int getAttemptCountAsPrimitive()
	{
		return getAttemptCountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.attemptCount</code> attribute. 
	 * @param value the attemptCount
	 */
	public void setAttemptCount(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ATTEMPTCOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.attemptCount</code> attribute. 
	 * @param value the attemptCount
	 */
	public void setAttemptCount(final Integer value)
	{
		setAttemptCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.attemptCount</code> attribute. 
	 * @param value the attemptCount
	 */
	public void setAttemptCount(final SessionContext ctx, final int value)
	{
		setAttemptCount( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.attemptCount</code> attribute. 
	 * @param value the attemptCount
	 */
	public void setAttemptCount(final int value)
	{
		setAttemptCount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.organizations</code> attribute.
	 * @return the organizations
	 */
	public Collection<Organization> getOrganizations(final SessionContext ctx)
	{
		final List<Organization> items = getLinkedItems( 
			ctx,
			true,
			MyStoreCoreConstants.Relations.CUSTOMER12ORGANIZATION,
			null,
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_TGT_ORDERED, true)
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.organizations</code> attribute.
	 * @return the organizations
	 */
	public Collection<Organization> getOrganizations()
	{
		return getOrganizations( getSession().getSessionContext() );
	}
	
	public long getOrganizationsCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			MyStoreCoreConstants.Relations.CUSTOMER12ORGANIZATION,
			null
		);
	}
	
	public long getOrganizationsCount()
	{
		return getOrganizationsCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.organizations</code> attribute. 
	 * @param value the organizations
	 */
	public void setOrganizations(final SessionContext ctx, final Collection<Organization> value)
	{
		setLinkedItems( 
			ctx,
			true,
			MyStoreCoreConstants.Relations.CUSTOMER12ORGANIZATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(CUSTOMER12ORGANIZATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.organizations</code> attribute. 
	 * @param value the organizations
	 */
	public void setOrganizations(final Collection<Organization> value)
	{
		setOrganizations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to organizations. 
	 * @param value the item to add to organizations
	 */
	public void addToOrganizations(final SessionContext ctx, final Organization value)
	{
		addLinkedItems( 
			ctx,
			true,
			MyStoreCoreConstants.Relations.CUSTOMER12ORGANIZATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(CUSTOMER12ORGANIZATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to organizations. 
	 * @param value the item to add to organizations
	 */
	public void addToOrganizations(final Organization value)
	{
		addToOrganizations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from organizations. 
	 * @param value the item to remove from organizations
	 */
	public void removeFromOrganizations(final SessionContext ctx, final Organization value)
	{
		removeLinkedItems( 
			ctx,
			true,
			MyStoreCoreConstants.Relations.CUSTOMER12ORGANIZATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(CUSTOMER12ORGANIZATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(CUSTOMER12ORGANIZATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from organizations. 
	 * @param value the item to remove from organizations
	 */
	public void removeFromOrganizations(final Organization value)
	{
		removeFromOrganizations( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.status</code> attribute.
	 * @return the status
	 */
	public Boolean isStatus(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, STATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.status</code> attribute.
	 * @return the status
	 */
	public Boolean isStatus()
	{
		return isStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.status</code> attribute. 
	 * @return the status
	 */
	public boolean isStatusAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isStatus( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer1.status</code> attribute. 
	 * @return the status
	 */
	public boolean isStatusAsPrimitive()
	{
		return isStatusAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.status</code> attribute. 
	 * @param value the status
	 */
	public void setStatus(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, STATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.status</code> attribute. 
	 * @param value the status
	 */
	public void setStatus(final Boolean value)
	{
		setStatus( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.status</code> attribute. 
	 * @param value the status
	 */
	public void setStatus(final SessionContext ctx, final boolean value)
	{
		setStatus( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer1.status</code> attribute. 
	 * @param value the status
	 */
	public void setStatus(final boolean value)
	{
		setStatus( getSession().getSessionContext(), value );
	}
	
}
