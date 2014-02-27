<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="resourceList.title"/></title>

</head>
<div class="col-sm-10">
    <h2><fmt:message key='resourceList.heading'/></h2>

    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/resourceform'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="resourceList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="resourceList" export="true" pagesize="25">
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