/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jun 27, 2014 12:44:37 PM                    ---
 * ----------------------------------------------------------------
 */
package com.epam.kiev.mystore.core.jalo;

import com.epam.kiev.mystore.core.constants.MyStoreCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.validation.jalo.constraints.TypeConstraint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.epam.kiev.mystore.core.jalo.ClassificationNotBlankConstraint ClassificationNotBlankConstraint}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedClassificationNotBlankConstraint extends TypeConstraint
{
	/** Qualifier of the <code>ClassificationNotBlankConstraint.classification</code> attribute **/
	public static final String CLASSIFICATION = "classification";
	/** Qualifier of the <code>ClassificationNotBlankConstraint.category</code> attribute **/
	public static final String CATEGORY = "category";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(TypeConstraint.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CLASSIFICATION, AttributeMode.INITIAL);
		tmp.put(CATEGORY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ClassificationNotBlankConstraint.category</code> attribute.
	 * @return the category - Category code
	 */
	public String getCategory(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CATEGORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ClassificationNotBlankConstraint.category</code> attribute.
	 * @return the category - Category code
	 */
	public String getCategory()
	{
		return getCategory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ClassificationNotBlankConstraint.category</code> attribute. 
	 * @param value the category - Category code
	 */
	public void setCategory(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CATEGORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ClassificationNotBlankConstraint.category</code> attribute. 
	 * @param value the category - Category code
	 */
	public void setCategory(final String value)
	{
		setCategory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ClassificationNotBlankConstraint.classification</code> attribute.
	 * @return the classification - Classification class code
	 */
	public String getClassification(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLASSIFICATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ClassificationNotBlankConstraint.classification</code> attribute.
	 * @return the classification - Classification class code
	 */
	public String getClassification()
	{
		return getClassification( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ClassificationNotBlankConstraint.classification</code> attribute. 
	 * @param value the classification - Classification class code
	 */
	public void setClassification(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLASSIFICATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ClassificationNotBlankConstraint.classification</code> attribute. 
	 * @param value the classification - Classification class code
	 */
	public void setClassification(final String value)
	{
		setClassification( getSession().getSessionContext(), value );
	}
	
}
