
<%@ page import="grailsapplication1.Domain1" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'domain1.label', default: 'Domain1')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-domain1" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-domain1" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'domain1.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="age" title="${message(code: 'domain1.age.label', default: 'Age')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${domain1InstanceList}" status="i" var="domain1Instance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${domain1Instance.id}">${fieldValue(bean: domain1Instance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: domain1Instance, field: "age")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${domain1InstanceTotal}" />
			</div>
		</div>
	</body>
</html>
