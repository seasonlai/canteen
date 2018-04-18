<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";

%>
<%--<script type="javascript">--%>
<%--var basePath = '${basePath}';--%>
<%--</script>--%>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<c:url value="/index.html"/>">饭堂系统</a>
    </div>

    <div class="nav navbar-right top-nav" style="padding-top: 5px">

        <c:if test="${empty USER_CONTEXT.userName}">
            <a class="navbar-text" href="<c:url value="/register.jsp"/>">注册</a>
            <a class="navbar-text" href="<c:url value="/login.jsp"/>">登录</a>
        </c:if>

        <c:if test="${!empty USER_CONTEXT.userName}">
            <span style="color: white">欢迎您，${USER_CONTEXT.userName}&nbsp;&nbsp;</span>
            <img src="<%=basePath%>static/img/head_def.jpg" width="40" height="40" class="img-circle">
        </c:if>
    </div>

    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav" style="width: 185px">
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#people_data">
                    <i class="fa fa-fw fa-bar-chart-o"></i> 饭堂数据<i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="people_data" class="collapse"> <%--aria-labelledby="dLabel"--%>
                    <li>
                        <a href="<c:url value="/data/data.html"/> ">人次统计/预估</a>
                    </li>
                    <li>
                        <a href="#">用餐预约统计</a>
                    </li>
                    <li>
                        <a href="<c:url value="/data/data-edit.html"/>">数据修改</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="<c:url value="/food/food_manager.html"/>"><i class="fa fa-fw fa-dashboard"></i> 餐品管理</a>
            </li>
            <li>
                <a href="<c:url value="/food/food_book.html"/>"><i class="fa fa-fw fa-book"></i> 用餐预约</a>
            </li>
            <li>
                <a href="<c:url value="/shopcar/myShopCar.html"/>"><i class="fa fa-fw fa-shopping-cart"></i> 我的购物车</a>
            </li>
            <li>
                <a href="<c:url value="/order/myOrder.html"/>"><i class="fa fa-fw fa-edit"></i> 我的订单</a>
            </li>
            <li>
                <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i>用户日志</a>
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>


<button style="position: absolute;right: 20px;bottom: 50px;" class="btn btn-lg btn-default">
    <li class="glyphicon glyphicon-shopping-cart"></li>
</button>


<div class="modal fade" id="processbar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 id="processbar_title" class="modal-title">正在处理</h4>
            </div>
            <div class="modal-body">
                <div style="display: none;position: absolute" class="progress progress-striped active">
                    <div class="progress-bar progress-bar-success" role="progressbar"
                         aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 40%;">
                        <span class="sr-only">正在处理</span>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
</div>