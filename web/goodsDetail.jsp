<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品详情页</title>

</head>
<body>
<%@ include file="header.jsp" %>

<div style="margin: 0 auto;width: 90%;">
    <ol class="breadcrumb">
        <li><a href="#">小米商城</a></li>
        <li><a href="#">手机</a></li>
        <li class="active"><a href="getGoodsListByTypeId?typeid=${product.t_id}">手机</a></li>
    </ol>
</div>

<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-6">
            <a href="#" class="thumbnail">
                <img src="${pageContext.request.contextPath}/${product.p_image}" width="560" height="560"
                     alt="${product.p_name}"/>
            </a>
        </div>
        <div class="col-xs-6 col-md-6">
            <div class="panel panel-default" style="height: 560px">
                <div class="panel-heading">商品详情</div>
                <div class="panel-body">
                    <div style="margin-left: 10px;">
                        <p>产品名称:&nbsp;&nbsp;&nbsp;<span class="text-danger"
                                                        style="font-size: 15px;">${product.p_name}</span></p>

                        <p>市场价格:&nbsp;&nbsp;&nbsp;<span class="text-danger"
                                                        style="font-size: 15px;">${product.p_price}</span>&nbsp;&nbsp;&nbsp;<span
                                class="glyphicon glyphicon-yen"></span></p>
                        <p>上市时间:&nbsp;&nbsp;&nbsp;${product.p_time}</p>
                        <p>热销指数:&nbsp;&nbsp;&nbsp;
                            <c:forEach begin="1" end="${product.p_state}">
                                <img src="image/star_red.gif" alt="star"/>
                            </c:forEach>
                        </p>
                        <p>详细介绍:</p>
                        <p>&nbsp;&nbsp;${product.p_info}</p>
                        <a href="${pageContext.request.contextPath}/addCart?goodsId=${product.p_id}&price=${product.p_price}"
                           class="btn btn-warning">加入购物车&nbsp;&nbsp;&nbsp;<span
                                class="glyphicon glyphicon-shopping-cart"></span></a>&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-danger">直接购买</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<%@ include file="footer.jsp" %>


</body>
</html>