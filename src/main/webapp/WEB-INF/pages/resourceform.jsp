<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="resourceDetail.title"/></title>

</head>

<div class="col-sm-3">
    <h2><fmt:message key='resourceDetail.heading'/></h2>
</div>

<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="resource" method="post" action="resourceform" id="resourceForm" cssClass="well">
        <form:hidden path="id"/>
        <spring:bind path="resource.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="resource.name"/>
        <form:input path="name" id="name" maxlength="50" autofocus="true" cssClass="form-control"/>
        <form:errors path="name" cssClass="help-inline"/>
        </div>
        <spring:bind path="resource.memo">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="resource.memo"/>
        <form:input path="memo" id="memo" maxlength="50" cssClass="form-control"/>
        <form:errors path="memo" cssClass="help-block"/>
        </div>
        <spring:bind path="resource.url">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="resource.url"/>
        <form:input path="url" id="url" maxlength="50" cssClass="form-control"/>
        <form:errors path="url" cssClass="help-block"/>
        </div>
        <spring:bind path="resource.priority">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="resource.priority"/>
        <form:input path="priority" id="priority" maxlength="50" cssClass="form-control"/>
        <form:errors path="priority" cssClass="help-block"/>
        </div>
        <spring:bind path="resource.type">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="resource.type"/>
        <form:input path="type" id="type" maxlength="50" cssClass="form-control"/>
        <form:errors path="type" cssClass="help-block"/>
        </div>
        <spring:bind path="resource.parent.id">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label styleClass="control-label" key="resource.parentId"/>
        <form:input path="parent.id" id="parent.id" maxlength="50" cssClass="form-control"/>
        <form:errors path="parent.id" cssClass="help-block"/>
        </div>


        <div class="form-group form-actions">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty resource.id}">
                <button type="submit" class="btn btn-default" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>
            <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<v:javascript formName="resource" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>