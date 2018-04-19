<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE HTML>
<html>
<head>
    <title>我的订单</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href='<%=basePath%>static/css/bootstrap.min.css' rel="stylesheet">
    <link href="<%=basePath%>static/css/bootstrap-overrides.css" type="text/css" rel="stylesheet">

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/compiled/elements.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/compiled/icons.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/compiled/elements.css">
    <!-- this page specific styles -->
    <link rel="stylesheet" href="<%=basePath%>static/css/compiled/gallery.css" type="text/css" media="screen"/>

    <!-- libraries -->
    <link href="<%=basePath%>static/css/lib/font-awesome.css" type="text/css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href='<%=basePath%>static/css/sb-admin.css' rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href='<%=basePath%>static/css/plugins/morris.css' rel="stylesheet">

    <!-- Custom Fonts -->
    <link href='<%=basePath%>static/font-awesome/css/font-awesome.min.css' rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div id="wrapper" style="padding-left: 185px">
    <jsp:include page="nav.jsp"/>

    <div id="page-wrapper" style="height: 100%">
        <div class="container-fluid">
            <!-- orders table -->
            <div class="table-wrapper orders-table section">
                <div class="row head">
                    <div class="col-md-12">
                        <h4>我的订单</h4>
                    </div>
                </div>

                <div class="row filter-block">
                    <div class="pull-right">
                        <div id="statusBtnGroup" class="btn-group pull-right">
                            <button status="-1" onclick="queryOrderList(-1)" class="glow left large active">全&nbsp;部</button>
                            <c:forEach var="item" items="${orderStatusObj}" varStatus="stat">
                                <c:if test="${!stat.last}">
                                    <button status="${item.code}" onclick="queryOrderList(${item.code})" class="glow middle large">${item.name}</button>
                                </c:if>
                                <c:if test="${stat.last}">
                                    <button status="${item.code}" onclick="queryOrderList(${item.code})" class="glow right large">${item.name}</button>
                                </c:if>
                            </c:forEach>
                            <%--<button onclick="queryOrderList(0)" class="glow middle large">未完成</button>--%>
                            <%--<button onclick="queryOrderList(1)" class="glow right large">已完成</button>--%>
                            <%--<button onclick="queryOrderList(2)" class="glow right large">已取消</button>--%>
                        </div>
                        <%--<input type="text" class="search order-search" placeholder="Search for an order.."/>--%>
                    </div>
                </div>

                <div class="row" style="margin-top: 16px">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-md-2">
                                订单号
                            </th>
                            <th class="col-md-4">
                                <span class="line"></span>
                                餐品名称
                            </th>
                            <th class="col-md-1">
                                <span class="line"></span>
                                餐品数量
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                预约时间
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                下单时间
                            </th>
                            <th class="col-md-1">
                                <span class="line"></span>
                                状态
                            </th>
                            <th class="col-lg-2">
                                <span class="line"></span>
                                总费用
                            </th>
                            <th class="col-md-3">
                                <span class="line"></span>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody id="orderBody">
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- end orders table -->
            <div class="text-center">
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script>
    var basePath = "<%=basePath%>";
    var foodTime = JSON.parse('${foodTime}');
    var orderStatus = JSON.parse('${orderStatus}');
</script>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/js/init.js"></script>
<script src="<%=basePath%>static/order/order.js"></script>
</body>
</html>
