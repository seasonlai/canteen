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
    <link href='<%=basePath%>static/css/bootstrap-datepicker.min.css' rel="stylesheet">
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
                    <button class="btn btn-default input-group-addon" onclick="queryList()"
                            style="width:35px;height: 33px" type="button">
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
                <div class="modal-dialog" style="width: 400px">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 id="modal_title" class="modal-title">Add new image</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group text-center" >
                                    <img id="foodImg" src="" height="150px" class="thumbnail"/>
                                </div>
                                <div class="form-group">
                                    <label for="foodDate" class="col-md-3 control-label">预约日期:</label>
                                    <div class="col-md-8">
                                        <input type="text" id="foodDate" style="height:30px;width:200px;"
                                               data-provide="datepicker" class="form-control datepicker"
                                               placeholder="开始时间">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="foodTime" class="col-md-3 control-label">用餐时间:</label>

                                    <div class="col-md-8">
                                        <select id="foodTime" class="form-control" style="height:30px;width:200px;">
                                            <c:forEach items="${foodTime}" var="item">
                                                <option value="${item.code}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="foodCount" class="col-md-3 control-label">餐品份数:</label>
                                    <div class="col-md-8  form-inline">
                                        <button class="btn btn-info btn-circle form-group-sm">
                                            <li class="fa fa-minus"></li>
                                        </button>
                                        <input type="text" id="foodCount" class="form-control" value="1"
                                               style="height:30px;width: 35px">
                                        <button class="btn btn-info btn-circle">
                                            <li class="fa fa-plus"></li>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="addToCard()">确定</button>
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
    <script src="<%=basePath%>static/js/bootstrap-datepicker.min.js"></script>
    <script src="<%=basePath%>static/js/bootstrap-datepicker.zh-CN.js"></script>
    <script src="<%=basePath%>static/food/theme.js"></script>
    <script src="<%=basePath%>static/food/food_book.js"></script>
    <script src="<%=basePath%>static/js/init.js"></script>
</body>
</html>
