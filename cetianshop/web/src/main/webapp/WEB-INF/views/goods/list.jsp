<%--
  Created by IntelliJ IDEA.
  User: Alcott Hawk
  Date: 11/21/2016
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>商品列表</title>
</head>
<body>
<div class="nav-bar">
    <a href="/index"><label>首页 / </label></a><label>商品分类</label>
</div>
<div class="content-box">
    <ul>
        <c:forEach begin="1" end="10">
            <a href="#">
                <li class="goods">
                    <div class="img">
                        <img src="${ctx}/static/pics/img3.jpg" style="height: 200px;width: 200px" />
                        <h3 style="text-align: center">热销</h3><br/>
                        <label style="text-decoration: line-through;color: #2b669a">原价￥：50：00</label><br/>
                        <label style="text-decoration: line-through;color: rgba(246, 39, 34, 0.75)">促销价￥：50：00</label>
                    </div>
                </li>
            </a>
        </c:forEach>
    </ul>
</div>
</body>
</html>
