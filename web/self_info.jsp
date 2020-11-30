<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="css/login2.css">
    <link rel="stylesheet" href="js/bootstrap.min.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>个人中心-收货地址页面</title>
    <script type="text/javascript">
        function deleteAddr(aid) {
            if (confirm("确定删除地址吗？")) {
                location.href = "address?method=delete&aid=" + aid;
            }
        }

        function defaultAddr(aid) {
            if (confirm("是否设为默认地址?")) {
                location.href = "address?method=setDefault&aid=" + aid;
            }
        }
    </script>
</head>
<body>
<%@ include file="header.jsp" %>
<!--网站中间内容开始-->
<div id="dingdanxiangqing_body">
    <div id="dingdanxiangqing_body_big">
        <div id="big_left">
            <p style="font-size:18px;margin-top: 15px">订单中心</p>
            <a id="big_left_a" href="dingdanxiangqing.html">我的订单</a><br/>
            <a id="big_left_a" href="">意外保</a><br/>
            <a id="big_left_a" href="">团购订单</a><br/>
            <a id="big_left_a" href="">评价晒单</a><br/>
            <p style="font-size:18px">个人中心</p>
            <a id="big_left_a" href="self_info.html">我的个人中心</a><br/>
            <a id="big_left_a" href="">消息通知</a><br/>
            <a id="big_left_a" href="">优惠券</a><br/>
            <a id="big_left_a" href="">收货地址</a><br/>
        </div>
        <div id="big_right" style="height: 500px;overflow: scroll;">

            <div style="margin:0 20px;">
                <h3>收货地址</h3>
                <hr>
                <table class="table table-striped table-hover table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>收件人</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach var="address" items="${addresses}" varStatus="i">
                        <tr>
                            <Td>${i.count}</Td>
                            <td>${address.a_name}</td>
                            <td>${address.a_phone}</td>
                            <td>${address.a_detail}</td>
                            <td>
                                <button onclick="deleteAddr(${address.a_id})" class="btn btn-danger btn-sm">删除</button>&nbsp;&nbsp;
                                <button class="btn btn-default btn-sm" data-toggle="modal"
                                        data-target="#myModal${address.a_id}">修改
                                </button>&nbsp;&nbsp;
                                <!-- 弹出模态框 -->

                                <div class="modal fade" tabindex="-1" role="dialog" id="myModal${address.a_id}">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span>&times;</span>
                                                </button>
                                                <h4 class="modal-title">修改地址</h4>
                                            </div>
                                            <form action="address?method=update" method="post"
                                                  class="form-horizontal">
                                                <div class="motal-body">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">收件人</label>
                                                        <div class="col-sm-10">
                                                            <input type="hidden" name="a_id" value="${address.a_id}">
                                                            <input type="hidden" name="a_state"
                                                            value="${address.a_state}">
                                                            <input type="text" name="a_name" class="form-control"
                                                                   value="${address.a_name}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">电话</label>
                                                        <div class="col-sm-10">
                                                            <input type="text" name="a_phone" class="form-control"
                                                                   value="${address.a_phone}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">收件人</label>
                                                        <div class="col-sm-10">
                                                            <textarea rows="4" class="form-control"
                                                                      name="a_detail"></textarea>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="motal-footer" style="text-align: center">
                                                    <button type="submit" class="btn btn-primary">修改</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <button onclick="defaultAddr(${address.a_id})" class="btn btn-primary btn-sm">设为默认
                                </button>
                                <c:if test="${address.a_state==1}">
                                    <span class="badge" style="background-color:red;">默认</span>
                                </c:if>
                                <c:if test="${address.a_state==0}">
                                    <span class="badge">普通</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <br>
            <div class="container" style="width:960px;">

                <form action="address?method=add" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 form-label">收件人</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="a_name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 form-label">手机号</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="a_phone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-label">详细地址</label>
                        <textarea rows="3" class="form-control" name="a_detail"></textarea>
                    </div>
                    <div class="form-group col-md-12">
                        <input type="submit" class="btn btn-primary" value="添加地址">
                    </div>
                    <input type="hidden" value="${loginUser.u_id}" name="u_id">
                </form>
            </div>
        </div>
        u_
    </div>
</div>

<!-- 底部 -->
<%@ include file="footer.jsp" %>

</body>
</html>