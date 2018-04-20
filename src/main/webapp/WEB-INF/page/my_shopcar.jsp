<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html>
<html>
<head>
    <title>我的餐车</title>

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

    <!-- libraries -->
    <link href="<%=basePath%>static/css/lib/font-awesome.css" type="text/css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href='<%=basePath%>static/css/sb-admin.css' rel="stylesheet">

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
                        <h4>我的餐车</h4>
                    </div>
                </div>

                <div class="row" style="margin-top: 16px">
                    <table class="table table-hover">
                        <thead>
                        <tr class="headTitle">
                            <th class="col-sm-1 text-center">
                                <input type="checkbox" id="checkAllBtn" onclick="checkAll()">
                            </th>
                            <th class="col-lg-4">
                                <span class="line"></span>
                                餐品名称
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                餐品数量
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                餐品单价
                            </th>
                            <th class="col-md-1">
                                <span class="line"></span>
                                用餐时间
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                餐品总价
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody id="shopCarBody">
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="pull-right"
                 style="height:60px;position:fixed;bottom: 0px;right:12px;border-top: 1px solid silver;">
                <strong>总价：</strong>
                <small>￥</small>
                <span id="totalPrice" style="margin-right: 25px">0</span>
                <button class="btn btn-warning" onclick="submitOrder()" ><span class="h4" style="color: white">下单</span></button>
            </div>


        </div>
    </div>
</div>
<script>
    var basePath = "<%=basePath%>";
    var foodTime = JSON.parse('${foodTime}');
</script>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/js/init.js"></script>
<script src="<%=basePath%>static/js/bootbox.min.js"></script>
<script src="<%=basePath%>static/shopcar/shopcar.js"></script>
</body>
</html>
