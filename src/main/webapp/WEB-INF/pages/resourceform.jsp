<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="resourceDetail.title"/></title>

</head>


<div class="span12">
    <div class="tabbable" id="tabs-562454">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#panel-main" data-toggle="tab"><fmt:message key='resourceDetail.title'/></a>
            </li>
            <li>
                <a href="#panel-subLevel" data-toggle="tab"><fmt:message key='resourceDetail.subtitle'/></a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="panel-main">
                <p>

                <div class="col-sm-7">
                    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
                    <form:form commandName="resource" method="post" action="resourceform" id="resourceForm"
                               cssClass="well">
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

                        <select id="priority" name="priority" class="input-small">
                            <c:forEach var="priority" items="${typeList}">

                                <option value="${priority.value}" ${fn:endsWith(resource.priority,type.value) ? 'selected' : ''}>${priority.label}</option>
                            </c:forEach>

                        </select>
                        <form:errors path="priority" cssClass="help-block"/>
                        </div>
                        <spring:bind path="resource.type">
                            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label styleClass="control-label" key="resource.type"/>
                        <select id="type" name="type" class="input-small">
                            <c:forEach var="type" items="${typeList}">

                                <option value="${type.value}" ${fn:endsWith(resource.type,type.value) ? 'selected' : ''}>${type.label}</option>
                            </c:forEach>

                        </select>
                        <form:errors path="type" cssClass="help-block"/>
                        </div>
                        <spring:bind path="resource.parent.id">
                            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
                        </spring:bind>
                        <appfuse:label styleClass="control-label" key="resource.parentId"/>


                        <select id="parent.id" name="parent.id" class="input-small">
                            <c:forEach var="parent" items="${parentList}">

                                <option value="${parent.id}" ${fn:endsWith(resource.parent.id,parent.id) ? 'selected' : ''}>${parent.name}</option>
                            </c:forEach>

                        </select>

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
                </p>
            </div>
            <div class="tab-pane" id="panel-subLevel">
                <display:table name="resource.subResources" class="table table-condensed table-striped table-hover"
                               requestURI=""
                               id="resource.subResources" export="true" pagesize="25">
                    <display:column property="id" sortable="true" href="resourceform" media="html"
                                    paramId="id" paramProperty="id" titleKey="resource.id"/>
                    <display:column property="id" media="csv excel xml pdf" titleKey="resource.id"/>
                    <display:column property="name" sortable="true" titleKey="resource.name"/>
                    <display:column property="memo" sortable="true" titleKey="resource.memo"/>
                    <display:column property="url" sortable="true" titleKey="resource.url"/>
                    <display:column property="priority" sortable="true" titleKey="resource.priority"/>
                    <display:column property="type" sortable="true" titleKey="resource.type"/>
                    <display:column property="parent.id" sortable="true" titleKey="resource.parentId"/>

                    <display:setProperty name="paging.banner.item_name"><fmt:message
                            key="resourceList.resource"/></display:setProperty>
                    <display:setProperty name="paging.banner.items_name"><fmt:message
                            key="resourceList.resources"/></display:setProperty>
                    <display:setProperty name="export.excel.filename"><fmt:message
                            key="resourceList.title"/>.xls</display:setProperty>
                    <display:setProperty name="export.csv.filename"><fmt:message
                            key="resourceList.title"/>.csv</display:setProperty>
                    <display:setProperty name="export.pdf.filename"><fmt:message
                            key="resourceList.title"/>.pdf</display:setProperty>
                </display:table>
            </div>
        </div>
    </div>
</div>


<v:javascript formName="resource" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>