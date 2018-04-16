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
            <div class="input-group" style="float: right">
                <button class="form-control btn btn-default" onclick="queryList()"
                        style="margin-right:12px;width: 35px;height: 33px" type="button">
                    <i class="fa fa-refresh"></i>
                </button>
                <select class="form-control" onchange="queryList()" id="searchKind" style="width:150px;height: 33px">
                    <option value="-1">全部分类</option>
                    <c:forEach items="${foodKinds}" var="foodKind">
                        <option value="${foodKind.kindCode}">${foodKind.kindName}</option>
                    </c:forEach>
                </select>
                <div class="input-group">
                <input type="text" id="searchContent" class="form-control"
                       style="width:280px;height: 33px;margin-left: 30px"
                       placeholder="Search...">
                <%--<span class="input-group-btn">--%>
                <button class="btn btn-default input-group-addon" onclick="queryList()" style="width:35px;height: 33px" type="button">
                    <i class="fa fa-search"></i>
                </button>
                </div>
                <%--</span>--%>
            </div>
            <div id="pad-wrapper" class="gallery" style="clear: both;margin-top: 56px">
                <!-- gallery wrapper -->
                <div id="gallery-wrapper" class="gallery-wrapper" style="margin-top: 20px">
                </div>
                <!-- end gallery wrapper -->

            </div>

            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Add new image</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="input1" class="col-lg-2 control-label">Image:</label>
                                    <div class="col-lg-10">
                                        <input type="file" id="input1" class="pull-left"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="input1" class="col-lg-2 control-label">Description:</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="inputPassword1"
                                               placeholder="Description">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

        </div>
    </div>
    <script>
        var basePath = "<%=basePath%>";
    </script>
    <script src="<%=basePath%>static/js/jquery.js"></script>
    <script src="<%=basePath%>static/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/food/theme.js"></script>
    <script src="<%=basePath%>static/food/food_book.js"></script>
        <script src="<%=basePath%>static/js/init.js"></script>
</body>
</html>
