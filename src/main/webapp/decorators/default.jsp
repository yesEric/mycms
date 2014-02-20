<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp" %>
<html lang="en">
<head>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>"/>
    <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
    <link rel="stylesheet" type="text/css" media="all"
          href="<c:url value='/styles/lib/bootstrap-2.2.1.min.css'/>"/>
    <link rel="stylesheet" type="text/css" media="all"
          href="<c:url value='/styles/lib/bootstrap-responsive-2.2.1.min.css'/>"/>
    <link rel="stylesheet" type="text/css" media="all"
          href="<c:url value='/styles/style.css'/>"/>
    <!--[if lt IE 9]>
    <script src="/scripts/lib/html5shiv.js"></script> <![endif]-->

    <script type="text/javascript"
            src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
    <script type="text/javascript"
            src="<c:url value='/scripts/lib/bootstrap-2.2.1.min.js'/>"></script>
    <decorator:head/>
</head>
<body
        <decorator:getProperty property="body.id" writeEntireProperty="true"/>
        <decorator:getProperty property="body.class" writeEntireProperty="true"/>>

<div class="container">
    <h1 class="page-header">

        <small><fmt:message key="webapp.name"/></small>
    </h1>
    <div class="navbar">
        <div class="navbar-inner">
            <a href="#" class="btn btn-navbar" data-toggle="collapse"
               data-target=".nav-collapse"> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span>
            </a> <a href="#" class="brand"> FunCMS</a>


            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="/"><fmt:message key="app.homepage"/></a></li>
                    <c:if test="${menus!=null}">
                        <c:forEach var="menu" items="${menus}">


                            <c:if test="${!menu.subResources.isEmpty()}">
                                <li class="dropdown">
                                <a href="${menu.url}">${menu.name} </a>

                                <ul class="dropdown-menu">
                                    <c:forEach var="submenu" items="${menu.subResources}">
                                        <li><a href="${submenu.url}">${submenu.name}</a></li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${menu.subResources.isEmpty()}">
                                <li><a href="${menu.url}">${menu.name}</a></li>
                            </c:if>
                        </c:forEach>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <decorator:body/>
            <!-- 这里是将被继承的Body部分 -->
        </div>
    </div>
    <div id="footer">
        <span class="right"> </span>
    </div>
</div>
</body>
</html>