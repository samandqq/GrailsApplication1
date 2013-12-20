<%@ page import="grailsapplication1.Domain1" %>



<div class="fieldcontain ${hasErrors(bean: domain1Instance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="domain1.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="50" value="${domain1Instance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: domain1Instance, field: 'age', 'error')} required">
	<label for="age">
		<g:message code="domain1.age.label" default="Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="age" type="number" value="${domain1Instance.age}" required=""/>
</div>

