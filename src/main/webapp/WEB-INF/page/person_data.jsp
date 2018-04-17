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
    <title>统计/预估</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
    <link href='<%=basePath%>static/css/bootstrap.min.css' rel="stylesheet">

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/compiled/elements.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/compiled/icons.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/compiled/elements.css">
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


            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-bar-chart-o fa-fw"></i> 用餐人数统计/预估
                    <div class="pull-right">
                        <div class="btn-group">
                            <button id="countKindBtn" type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                <%--<c:forEach end="exitId" var="countKind" items="${countKinds}">--%>
                                    <%--${countKind.name}--%>
                                    <%--<c:set var="exitId" value="0"></c:set>--%>
                                <%--</c:forEach>--%>

                            </button>
                            <ul class="dropdown-menu pull-right" id="countKindList" role="menu">
                                <c:forEach var="countKind" items="${countKinds}">
                                    <li><a onclick="refreshChart('${countKind.code}','${countKind.name}')">${countKind.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div id="morris-area-chart"></div>
            </div>
            <!-- /.panel-body -->
        </div>
    </div>
</div>
<script>
    var basePath = "<%=basePath%>";
</script>
<script src="<%=basePath%>static/js/jquery.js"></script>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<!-- Morris Charts JavaScript -->
<script src="<%=basePath%>static/js/vendor/raphael/raphael.min.js"></script>
<script src="<%=basePath%>static/js/vendor/morrisjs/morris.min.js"></script>
<script src="<%=basePath%>static/person/morris-data.js"></script>
<script src="<%=basePath%>static/person/data_count.js"></script>

<script src="<%=basePath%>static/js/init.js"></script>
</body>
</html>
