<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

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
					<spring:theme code="text.account.organizations"
						text="Organizations" />
				</h2>
			</div>
			<div class="item_container">
				<c:if test="${not empty organizations}">
					<table id="Organizations">
						<thead>
							<tr>
								<th id="header1"><spring:theme code="text.address"
										text="Address" /></th>
								<th id="header2"><spring:theme code="text.updates"
										text="Updates" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${organizations}" var="organization">
								<tr>
									<td headers="header1"><ycommerce:testId
											code="addressBook_address_label">
											<ul>
												<li>${fn:escapeXml(organization.name)}</li>
												<li>${fn:escapeXml(organization.email)}</li>
												<li>${fn:escapeXml(organization.phoneNumber)}</li>
											</ul>
										</ycommerce:testId></td>
									<td headers="header2"><ycommerce:testId
											code="addressBook_addressOptions_label">
											<ul class="updates">
												<li><ycommerce:testId
														code="addressBook_removeAddress_button">
														<a href="add-organization/${organization.id}"
															class="actionlink"><spring:theme code="text.add"
																text="Add" /></a>
													</ycommerce:testId></li>
											</ul>
										</ycommerce:testId></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${empty organizations}">
					<p>
						<spring:theme code="text.account.organizations.noOrganizations"
							text="You all organizations" />
					</p>
				</c:if>
				<ycommerce:testId code="addressBook_addNewAddress_button">
					<a href="organizations">
						<button class="positive left" type="submit">
							<spring:theme code="text.account.organizations.myOrganization"
								text="My organizations" />
						</button>
					</a>
					<a href="create-organization">
						<button class="positive left" type="submit">
							<spring:theme code="text.account.organization.createOrganization"
								text="Create organization" />
						</button>
					</a>
				</ycommerce:testId>
			</div>
		</div>
	</div>
</template:page>