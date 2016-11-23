<%--
  Created by IntelliJ IDEA.
  User: Alcott Hawk
  Date: 11/21/2016
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--广告大图--%>
<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="${ctx}/static/pics/img3.jpg" class="carousel-img" alt="First slide"/>
        </div>
        <div class="item">
            <img src="${ctx}/static/pics/img0.jpg" class="carousel-img" alt="Second slide"/>
        </div>
        <div class="item">
            <img src="${ctx}/static/pics/img3.jpg" class="carousel-img" alt="Third slide"/>
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev">&lsaquo;
    </a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next">&rsaquo;
    </a>
</div>
<%--内容--%>
<div class="content-box">
    <ul>
        <c:forEach begin="1" end="2">
            <c:forEach begin="1" end="5">
                <a href="${ctx}/goods/list">
                    <li class="goods">
                        <div class="img">
                            <img src="${ctx}/static/pics/img3.jpg" style="height: 200px;width: 200px" />
                            <h3 style="text-align: center">热销</h3>
                        </div>
                    </li>
                </a>
            </c:forEach>
            <br />
            <br />
        </c:forEach>
    </ul>
</div>
</body>
</html>
