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
        <ul class="nav navbar-nav side-nav"   style="width: 185px">
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
                <a href="<c:url value="/order/myOrder.html"/>"><i class="fa fa-fw fa-edit"></i> 我的预约</a>
            </li>
            <li>
                <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i>用户日志</a>
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>
