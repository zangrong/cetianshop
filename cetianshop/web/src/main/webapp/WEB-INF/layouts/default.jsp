<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title>QuickStart示例:<sitemesh:write property='title' /></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/static/bootstrap/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/static/styles/default.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/static/styles/jquery-accordion-menu.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/styles/font-awesome.min.css" type="text/css" rel="stylesheet"/>
    <script src="${ctx}/static/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
    <script src="${ctx}/static/jquery/jquery-accordion-menu.js" type="text/javascript"></script>
    <sitemesh:write property='head' />
</head>

<body>
    <div class="container-fluid">
        <div class="row" id="header">
            <%@include file="header.jsp"%>
        </div>
        <div class="slidebar mobile">
            <%@include file="slidebar.jsp"%>
        </div>
        <div class="row main-content">
            <sitemesh:write property='body' />
        </div>
        <div class="row" id="footer">
            <%@include file="footer.jsp"%>
        </div>
    </div>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/default.js" type="application/javascript"></script>
</body>
</html>