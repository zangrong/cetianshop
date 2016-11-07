<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
    <title>JBlog 后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/dist/css/AdminLTE.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/dist/css/skins/_all-skins.min.css"/>
    <sitemesh:write property='head' />
</head>

<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
    <%@ include file="/WEB-INF/layouts/top.jsp"%>
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
        <sitemesh:write property='body' />
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
		<div class="content-wrapper" style="min-height: 500px">
	                <p>a test</p>
	    </div>
	<script type="application/javascript" rel="script" src="${ctx}/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script type="application/javascript" rel="script" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="application/javascript" rel="script" src="${ctx}/static/dist/js/app.min.js"></script>
	</div>
</body>
</html>