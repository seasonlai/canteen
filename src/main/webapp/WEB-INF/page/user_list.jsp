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
    <title>用户列表</title>

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
    <%--<link href='<%=basePath%>static/css/bootstrap-datetimepicker.min.css' rel="stylesheet">--%>
    <link href='<%=basePath%>static/css/bootstrap-datepicker.min.css' rel="stylesheet">

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
                        <h4>用户列表</h4>
                    </div>
                </div>


                <div class="row" style="margin-top: 16px">
                    <table id="tb_data" class="table table-hover">
                        <thead>
                        <tr class="headTitle">
                            <th class="col-md-1">
                                <span class="line"></span>
                                序号
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                用户名
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                用户密码
                            </th>
                            <th class="col-md-2">
                                用户类型
                            </th>
                        </tr>
                        </thead>
                        <tbody id="tBody">

                        </tbody>
                    </table>
                </div>
            </div>
            <!-- end orders table -->
            <div class="text-center">
                <ul id="splitBar" class="pagination">
                </ul>
            </div>

            <!-- /.panel-body -->
        </div>
    </div>
</div>
<script>
    var basePath = "<%=basePath%>";
</script>
<script src="<%=basePath%>static/js/jquery.js"></script>
<%--<script src="<%=basePath%>static/js/jquery.min.js"></script>--%>
<script src="<%=basePath%>static/js/bootstrap.min.js"></script>
<script src="<%=basePath%>static/js/moment.js"></script>
<!-- Morris Charts JavaScript -->
<script src="<%=basePath%>static/person/user_list.js"></script>

<script src="<%=basePath%>static/js/bootbox.min.js"></script>
<script src="<%=basePath%>static/js/init.js"></script>
</body>
</html>
