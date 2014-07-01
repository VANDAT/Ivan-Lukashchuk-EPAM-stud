<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="true" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="inputCSS" required="false" type="java.lang.String"%>
<%@ attribute name="items" required="true" type="java.util.Collection"%>
<%@ attribute name="itemValue" required="false" type="java.lang.String"%>
<%@ attribute name="itemLabel" required="false" type="java.lang.String"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld"%>

<template:errorSpanField path="${path}">
	<ycommerce:testId code="LoginPage_Item_${idKey}">
		<spring:theme code="${idKey}" var="themeIdKey" />
		<dt>
			<label id="label_${idKey}" class="${labelCSS}" for="${idKey}">
				<spring:theme code="${labelKey}" /> <c:if
					test="${mandatory != null && mandatory == true}">
					<span class="mandatory"> <spring:theme code="login.required"
							var="loginRequiredText" /> <img width="5" height="6"
						alt="${loginRequiredText}" title="${loginRequiredText}"
						src="${commonResourcePath}/images/mandatory.gif">
					</span>
				</c:if> <span class="skip"><form:errors path="${path}" /></span>
			</label>
		</dt>
		<%-- <form:checkboxes  path="${path}" items="${items}"
				itemValue="${not empty itemValue ? itemValue :'code'}"
				itemLabel="${not empty itemLabel ? itemLabel :'name'}" /> --%>
		<ul>
			<c:forEach items="${items}" var="item">

				<li><form:checkbox path="${path}" label="${item.name }" value="${item.id }"/></li>

			</c:forEach>
		</ul>

	</ycommerce:testId>
</template:errorSpanField>
