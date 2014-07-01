<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="formElement"
	tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld"%>

<template:page pageTitle="${pageTitle}">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<nav:accountNav selected="organizations" />
	<div class="span-20 last cust_acc-page">
		<div class="item_container_holder">
			<div class="title_holder">
				<div class="title">
					<div class="title-top">
						<span></span>
					</div>
				</div>
				<h2>
					<spring:theme code="form.required" text="Organizations" />
				</h2>
			</div>
			<div class="item_container">

				<form:form action="edit" method="post"
					commandName="organizationForm">
					<dl>

						<dt>
							<spring:theme code="account.organization.id" />
						</dt>
						<dd>
							<c:out value="${organizationForm.id }"></c:out>
							<input hidden="true" title="id" name="id" value="${organizationForm.id }" >
						</dd>
						<dt>
							<spring:theme code="account.organization.email" />
						</dt>
						<dd>
							<c:out value="${organizationForm.email }"></c:out>
						</dd>
						
						<formElement:formInputBox idKey="account.organization.name"
							labelKey="account.organization.name" path="name" mandatory="true" />
						<formElement:formInputBox idKey="account.organization.phoneNumber"
							labelKey="account.organization.phoneNumber" path="phoneNumber"
							mandatory="true" />
					</dl>
					<span style="display: block; clear: both;"> <ycommerce:testId
							code="profilePage_EditOrganizationButton">
							<button class="form">
								<spring:theme
									code="text.account.organization.editOrganization"
									text="Edit organization" />
							</button>
						</ycommerce:testId>
					</span>
				</form:form>

			</div>
		</div>
	</div>
</template:page>