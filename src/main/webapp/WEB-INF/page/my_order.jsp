<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";

%>
<html>
<head>
    <title>用餐预约</title>

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
                        <h4>我的用餐预约</h4>
                    </div>
                </div>

                <div class="row filter-block">
                    <div class="pull-right">
                        <div class="btn-group pull-right">
                            <button class="glow left large active">全部</button>
                            <button class="glow middle large">未完成</button>
                            <button class="glow right large">已完成</button>
                        </div>
                        <%--<input type="text" class="search order-search" placeholder="Search for an order.."/>--%>
                    </div>
                </div>

                <div class="row" style="margin-top: 16px">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-md-2">
                                预约号
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                餐品名称
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                预约时间
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                下单时间
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                状态
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                价格
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                        <tr class="first">
                            <td>
                                <a href="#">#459</a>
                            </td>
                            <td>
                                Jan 03, 2013
                            </td>
                            <td>
                                <a href="#">John Smith</a>
                            </td>
                            <td>
                                <span class="label label-success">Completed</span>
                            </td>
                            <td>
                                3
                            </td>
                            <td>
                                $ 3,500.00
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">#510</a>
                            </td>
                            <td>
                                Feb 22, 2013
                            </td>
                            <td>
                                <a href="#">Anna Richards</a>
                            </td>
                            <td>
                                <span class="label label-info">Pending</span>
                            </td>
                            <td>
                                5
                            </td>
                            <td>
                                $ 800.00
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">#590</a>
                            </td>
                            <td>
                                Mar 03, 2013
                            </td>
                            <td>
                                <a href="#">Steven McFly</a>
                            </td>
                            <td>
                                <span class="label label-success">Completed</span>
                            </td>
                            <td>
                                2
                            </td>
                            <td>
                                $ 1,350.00
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#">#618</a>
                            </td>
                            <td>
                                Jan 03, 2013
                            </td>
                            <td>
                                <a href="#">George Williams</a>
                            </td>
                            <td>
                                <span class="label">Canceled</span>
                            </td>
                            <td>
                                8
                            </td>
                            <td>
                                $ 3,499.99
                            </td>
                        </tr>
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

<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<%--<script src="<%=basePath%>static/food/theme.js"></script>--%>
</body>
</html>
