/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jun 27, 2014 12:44:37 PM                    ---
 * ----------------------------------------------------------------
 */
package com.epam.kiev.mystore.core.jalo;

import com.epam.kiev.mystore.core.constants.MyStoreCoreConstants;
import com.epam.kiev.mystore.core.jalo.Accessory;
import com.epam.kiev.mystore.core.jalo.ClassificationNotBlankConstraint;
import com.epam.kiev.mystore.core.jalo.Device;
import com.epam.kiev.mystore.core.jalo.MyStoreCustomer;
import com.epam.kiev.mystore.core.jalo.Organization;
import com.epam.kiev.mystore.core.jalo.ProductReferencesAndClassificationsComponent;
import com.epam.kiev.mystore.core.jalo.ProductReferencesAndClassificationsForDevicesComponent;
import com.epam.kiev.mystore.core.jalo.ServiceAddOn;
import com.epam.kiev.mystore.core.jalo.ServicePlan;
import de.hybris.platform.catalog.jalo.classification.ClassAttributeAssignment;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.link.Link;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.type.Type;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>MyStoreCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMyStoreCoreManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<ProductReferencesAndClassificationsComponent> PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT2TARGETITEMTYPERELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER = new OneToManyHandler<ProductReferencesAndClassificationsComponent>(
	MyStoreCoreConstants.TC.PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT,
	false,
	"targetItemType",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<ProductReferencesAndClassificationsComponent> PRODUCTACCESSORYCOMPONENT2CLASSATTRIBUTEASSIGNRELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER = new OneToManyHandler<ProductReferencesAndClassificationsComponent>(
	MyStoreCoreConstants.TC.PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT,
	false,
	"classAttributeAssignment",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public Accessory createAccessory(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.ACCESSORY );
			return (Accessory)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Accessory : "+e.getMessage(), 0 );
		}
	}
	
	public Accessory createAccessory(final Map attributeValues)
	{
		return createAccessory( getSession().getSessionContext(), attributeValues );
	}
	
	public ClassificationNotBlankConstraint createClassificationNotBlankConstraint(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.CLASSIFICATIONNOTBLANKCONSTRAINT );
			return (ClassificationNotBlankConstraint)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ClassificationNotBlankConstraint : "+e.getMessage(), 0 );
		}
	}
	
	public ClassificationNotBlankConstraint createClassificationNotBlankConstraint(final Map attributeValues)
	{
		return createClassificationNotBlankConstraint( getSession().getSessionContext(), attributeValues );
	}
	
	public Device createDevice(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.DEVICE );
			return (Device)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Device : "+e.getMessage(), 0 );
		}
	}
	
	public Device createDevice(final Map attributeValues)
	{
		return createDevice( getSession().getSessionContext(), attributeValues );
	}
	
	public MyStoreCustomer createMyStoreCustomer(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.MYSTORECUSTOMER );
			return (MyStoreCustomer)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating MyStoreCustomer : "+e.getMessage(), 0 );
		}
	}
	
	public MyStoreCustomer createMyStoreCustomer(final Map attributeValues)
	{
		return createMyStoreCustomer( getSession().getSessionContext(), attributeValues );
	}
	
	public Organization createOrganization(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.ORGANIZATION );
			return (Organization)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Organization : "+e.getMessage(), 0 );
		}
	}
	
	public Organization createOrganization(final Map attributeValues)
	{
		return createOrganization( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductReferencesAndClassificationsComponent createProductReferencesAndClassificationsComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT );
			return (ProductReferencesAndClassificationsComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductReferencesAndClassificationsComponent : "+e.getMessage(), 0 );
		}
	}
	
	public ProductReferencesAndClassificationsComponent createProductReferencesAndClassificationsComponent(final Map attributeValues)
	{
		return createProductReferencesAndClassificationsComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductReferencesAndClassificationsForDevicesComponent createProductReferencesAndClassificationsForDevicesComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.PRODUCTREFERENCESANDCLASSIFICATIONSFORDEVICESCOMPONENT );
			return (ProductReferencesAndClassificationsForDevicesComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductReferencesAndClassificationsForDevicesComponent : "+e.getMessage(), 0 );
		}
	}
	
	public ProductReferencesAndClassificationsForDevicesComponent createProductReferencesAndClassificationsForDevicesComponent(final Map attributeValues)
	{
		return createProductReferencesAndClassificationsForDevicesComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public ServiceAddOn createServiceAddOn(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.SERVICEADDON );
			return (ServiceAddOn)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ServiceAddOn : "+e.getMessage(), 0 );
		}
	}
	
	public ServiceAddOn createServiceAddOn(final Map attributeValues)
	{
		return createServiceAddOn( getSession().getSessionContext(), attributeValues );
	}
	
	public ServicePlan createServicePlan(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MyStoreCoreConstants.TC.SERVICEPLAN );
			return (ServicePlan)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ServicePlan : "+e.getMessage(), 0 );
		}
	}
	
	public ServicePlan createServicePlan(final Map attributeValues)
	{
		return createServicePlan( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return MyStoreCoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ComposedType.productReferencesAndClassificationsComponents</code> attribute.
	 * @return the productReferencesAndClassificationsComponents
	 */
	public Collection<ProductReferencesAndClassificationsComponent> getProductReferencesAndClassificationsComponents(final SessionContext ctx, final ComposedType item)
	{
		return PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT2TARGETITEMTYPERELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ComposedType.productReferencesAndClassificationsComponents</code> attribute.
	 * @return the productReferencesAndClassificationsComponents
	 */
	public Collection<ProductReferencesAndClassificationsComponent> getProductReferencesAndClassificationsComponents(final ComposedType item)
	{
		return getProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ComposedType.productReferencesAndClassificationsComponents</code> attribute. 
	 * @param value the productReferencesAndClassificationsComponents
	 */
	public void setProductReferencesAndClassificationsComponents(final SessionContext ctx, final ComposedType item, final Collection<ProductReferencesAndClassificationsComponent> value)
	{
		PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT2TARGETITEMTYPERELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ComposedType.productReferencesAndClassificationsComponents</code> attribute. 
	 * @param value the productReferencesAndClassificationsComponents
	 */
	public void setProductReferencesAndClassificationsComponents(final ComposedType item, final Collection<ProductReferencesAndClassificationsComponent> value)
	{
		setProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productReferencesAndClassificationsComponents. 
	 * @param value the item to add to productReferencesAndClassificationsComponents
	 */
	public void addToProductReferencesAndClassificationsComponents(final SessionContext ctx, final ComposedType item, final ProductReferencesAndClassificationsComponent value)
	{
		PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT2TARGETITEMTYPERELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productReferencesAndClassificationsComponents. 
	 * @param value the item to add to productReferencesAndClassificationsComponents
	 */
	public void addToProductReferencesAndClassificationsComponents(final ComposedType item, final ProductReferencesAndClassificationsComponent value)
	{
		addToProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productReferencesAndClassificationsComponents. 
	 * @param value the item to remove from productReferencesAndClassificationsComponents
	 */
	public void removeFromProductReferencesAndClassificationsComponents(final SessionContext ctx, final ComposedType item, final ProductReferencesAndClassificationsComponent value)
	{
		PRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENT2TARGETITEMTYPERELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productReferencesAndClassificationsComponents. 
	 * @param value the item to remove from productReferencesAndClassificationsComponents
	 */
	public void removeFromProductReferencesAndClassificationsComponents(final ComposedType item, final ProductReferencesAndClassificationsComponent value)
	{
		removeFromProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ClassAttributeAssignment.productReferencesAndClassificationsComponents</code> attribute.
	 * @return the productReferencesAndClassificationsComponents
	 */
	public Collection<ProductReferencesAndClassificationsComponent> getProductReferencesAndClassificationsComponents(final SessionContext ctx, final ClassAttributeAssignment item)
	{
		return PRODUCTACCESSORYCOMPONENT2CLASSATTRIBUTEASSIGNRELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ClassAttributeAssignment.productReferencesAndClassificationsComponents</code> attribute.
	 * @return the productReferencesAndClassificationsComponents
	 */
	public Collection<ProductReferencesAndClassificationsComponent> getProductReferencesAndClassificationsComponents(final ClassAttributeAssignment item)
	{
		return getProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ClassAttributeAssignment.productReferencesAndClassificationsComponents</code> attribute. 
	 * @param value the productReferencesAndClassificationsComponents
	 */
	public void setProductReferencesAndClassificationsComponents(final SessionContext ctx, final ClassAttributeAssignment item, final Collection<ProductReferencesAndClassificationsComponent> value)
	{
		PRODUCTACCESSORYCOMPONENT2CLASSATTRIBUTEASSIGNRELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ClassAttributeAssignment.productReferencesAndClassificationsComponents</code> attribute. 
	 * @param value the productReferencesAndClassificationsComponents
	 */
	public void setProductReferencesAndClassificationsComponents(final ClassAttributeAssignment item, final Collection<ProductReferencesAndClassificationsComponent> value)
	{
		setProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productReferencesAndClassificationsComponents. 
	 * @param value the item to add to productReferencesAndClassificationsComponents
	 */
	public void addToProductReferencesAndClassificationsComponents(final SessionContext ctx, final ClassAttributeAssignment item, final ProductReferencesAndClassificationsComponent value)
	{
		PRODUCTACCESSORYCOMPONENT2CLASSATTRIBUTEASSIGNRELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to productReferencesAndClassificationsComponents. 
	 * @param value the item to add to productReferencesAndClassificationsComponents
	 */
	public void addToProductReferencesAndClassificationsComponents(final ClassAttributeAssignment item, final ProductReferencesAndClassificationsComponent value)
	{
		addToProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productReferencesAndClassificationsComponents. 
	 * @param value the item to remove from productReferencesAndClassificationsComponents
	 */
	public void removeFromProductReferencesAndClassificationsComponents(final SessionContext ctx, final ClassAttributeAssignment item, final ProductReferencesAndClassificationsComponent value)
	{
		PRODUCTACCESSORYCOMPONENT2CLASSATTRIBUTEASSIGNRELPRODUCTREFERENCESANDCLASSIFICATIONSCOMPONENTSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from productReferencesAndClassificationsComponents. 
	 * @param value the item to remove from productReferencesAndClassificationsComponents
	 */
	public void removeFromProductReferencesAndClassificationsComponents(final ClassAttributeAssignment item, final ProductReferencesAndClassificationsComponent value)
	{
		removeFromProductReferencesAndClassificationsComponents( getSession().getSessionContext(), item, value );
	}
	
}
