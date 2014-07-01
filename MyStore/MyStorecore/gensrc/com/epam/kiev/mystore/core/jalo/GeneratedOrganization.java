/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jun 27, 2014 12:44:37 PM                    ---
 * ----------------------------------------------------------------
 */
package com.epam.kiev.mystore.core.jalo;

import com.epam.kiev.mystore.core.constants.MyStoreCoreConstants;
import com.epam.kiev.mystore.core.jalo.MyStoreCustomer;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Organization}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrganization extends GenericItem
{
	/** Qualifier of the <code>Organization.phoneNumber</code> attribute **/
	public static final String PHONENUMBER = "phoneNumber";
	/** Qualifier of the <code>Organization.customers</code> attribute **/
	public static final String CUSTOMERS = "customers";
	/** Relation ordering override parameter constants for MyStoreCustomer2Organization from ((MyStorecore))*/
	protected static String MYSTORECUSTOMER2ORGANIZATION_SRC_ORDERED = "relation.MyStoreCustomer2Organization.source.ordered";
	protected static String MYSTORECUSTOMER2ORGANIZATION_TGT_ORDERED = "relation.MyStoreCustomer2Organization.target.ordered";
	/** Relation disable markmodifed parameter constants for MyStoreCustomer2Organization from ((MyStorecore))*/
	protected static String MYSTORECUSTOMER2ORGANIZATION_MARKMODIFIED = "relation.MyStoreCustomer2Organization.markmodified";
	/** Qualifier of the <code>Organization.email</code> attribute **/
	public static final String EMAIL = "email";
	/** Qualifier of the <code>Organization.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>Organization.id</code> attribute **/
	public static final String ID = "id";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PHONENUMBER, AttributeMode.INITIAL);
		tmp.put(EMAIL, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(ID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.customers</code> attribute.
	 * @return the customers
	 */
	public List<MyStoreCustomer> getCustomers(final SessionContext ctx)
	{
		final List<MyStoreCustomer> items = getLinkedItems( 
			ctx,
			false,
			MyStoreCoreConstants.Relations.MYSTORECUSTOMER2ORGANIZATION,
			null,
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_TGT_ORDERED, true)
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.customers</code> attribute.
	 * @return the customers
	 */
	public List<MyStoreCustomer> getCustomers()
	{
		return getCustomers( getSession().getSessionContext() );
	}
	
	public long getCustomersCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			false,
			MyStoreCoreConstants.Relations.MYSTORECUSTOMER2ORGANIZATION,
			null
		);
	}
	
	public long getCustomersCount()
	{
		return getCustomersCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.customers</code> attribute. 
	 * @param value the customers
	 */
	public void setCustomers(final SessionContext ctx, final List<MyStoreCustomer> value)
	{
		setLinkedItems( 
			ctx,
			false,
			MyStoreCoreConstants.Relations.MYSTORECUSTOMER2ORGANIZATION,
			null,
			value,
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(MYSTORECUSTOMER2ORGANIZATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.customers</code> attribute. 
	 * @param value the customers
	 */
	public void setCustomers(final List<MyStoreCustomer> value)
	{
		setCustomers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to customers. 
	 * @param value the item to add to customers
	 */
	public void addToCustomers(final SessionContext ctx, final MyStoreCustomer value)
	{
		addLinkedItems( 
			ctx,
			false,
			MyStoreCoreConstants.Relations.MYSTORECUSTOMER2ORGANIZATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(MYSTORECUSTOMER2ORGANIZATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to customers. 
	 * @param value the item to add to customers
	 */
	public void addToCustomers(final MyStoreCustomer value)
	{
		addToCustomers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from customers. 
	 * @param value the item to remove from customers
	 */
	public void removeFromCustomers(final SessionContext ctx, final MyStoreCustomer value)
	{
		removeLinkedItems( 
			ctx,
			false,
			MyStoreCoreConstants.Relations.MYSTORECUSTOMER2ORGANIZATION,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(MYSTORECUSTOMER2ORGANIZATION_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(MYSTORECUSTOMER2ORGANIZATION_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from customers. 
	 * @param value the item to remove from customers
	 */
	public void removeFromCustomers(final MyStoreCustomer value)
	{
		removeFromCustomers( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.email</code> attribute.
	 * @return the email
	 */
	public String getEmail(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.email</code> attribute.
	 * @return the email
	 */
	public String getEmail()
	{
		return getEmail( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final String value)
	{
		setEmail( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.id</code> attribute.
	 * @return the id
	 */
	public Integer getId(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.id</code> attribute.
	 * @return the id
	 */
	public Integer getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.id</code> attribute. 
	 * @return the id
	 */
	public int getIdAsPrimitive(final SessionContext ctx)
	{
		Integer value = getId( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.id</code> attribute. 
	 * @return the id
	 */
	public int getIdAsPrimitive()
	{
		return getIdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final Integer value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final int value)
	{
		setId( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final int value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrganization.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrganization.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.phoneNumber</code> attribute.
	 * @return the phoneNumber
	 */
	public String getPhoneNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PHONENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Organization.phoneNumber</code> attribute.
	 * @return the phoneNumber
	 */
	public String getPhoneNumber()
	{
		return getPhoneNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.phoneNumber</code> attribute. 
	 * @param value the phoneNumber
	 */
	public void setPhoneNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PHONENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Organization.phoneNumber</code> attribute. 
	 * @param value the phoneNumber
	 */
	public void setPhoneNumber(final String value)
	{
		setPhoneNumber( getSession().getSessionContext(), value );
	}
	
}
