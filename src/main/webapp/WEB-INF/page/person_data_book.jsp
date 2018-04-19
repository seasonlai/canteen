<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";

%>
<html>
<head>
    <title>用餐预约统计</title>

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
                        <h4>预约用餐统计</h4>
                    </div>
                </div>
                <div class="row filter-block">
                    <div class="pull-right form-inline" role="form" aria-orientation="horizontal">
                        <button class="form-control btn btn-default" onclick="queryPageList()"
                                style="margin-right:12px;width: 35px;height: 33px" type="button">
                            <i class="fa fa-refresh"></i>
                        </button>
                        <select class="form-control" onchange="queryList()" id="searchKind"
                                style="width:150px;height: 33px">
                            <option value="-1">全部分类</option>
                            <c:forEach items="${foodKinds}" var="foodKind">
                                <option value="${foodKind.kindCode}">${foodKind.kindName}</option>
                            </c:forEach>
                        </select>
                        <select class="form-control" onchange="queryList()" id="foodTimeKind"
                                style="width:150px;height: 33px">
                            <option value="-1">全部时间段</option>
                            <c:forEach items="${foodTime}" var="time">
                                <option value="${time.code}">${time.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group">
                            <input type="text" id="countTime" style="height:30px;width:200px;"
                                   data-provide="datepicker"
                                   class="form-control datepicker" placeholder="预约用餐日期">
                        </div>
                        <div class="input-group">
                            <input type="text" id="searchContent" style="height:30px;width:200px;"
                                   class="form-control" placeholder="搜索餐品名">
                        </div>
                        <button class="btn btn-default input-group-adn"
                                style="width: 35px;height:30px;margin-left: 12px;"
                                onclick="queryPageList()"><i class="fa fa-search"></i></button>
                        <%--<input type="text" class="search order-search" placeholder="Search for an order.."/>--%>
                    </div>
                </div>
                <%--<input type="text" id="datepicker" class="form-control">--%>
                <%--<div class="input-group date" data-provide="datepicker">--%>
                <%----%>
                <%--<div class="input-group-addon">--%>
                <%--<span class="glyphicon glyphicon-th"></span>--%>
                <%--</div>--%>
                <%--</div>--%>
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
                                餐品名称
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                餐品类别
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                用餐类型
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>
                                预约日期
                            </th>
                        </tr>
                        </thead>
                        <tbody id="tBody">

                        </tbody>
                    </table>
                </div>
            </div>
            <!-- end orders table -->
            <div id="splitBarDiv" class="text-center">
                共&nbsp;
                <span id="dataCount"></span>
                &nbsp;条数据&nbsp;&nbsp;
                <span id="pageCount"></span>
                &nbsp;页
                <ul id="splitBar" class="pagination">
                </ul>
            </div>


            <!-- 数据编辑 -->
            <div class="modal fade" data-backdrop='false' id="dataModify" tabindex="-1" role="dialog"
                 aria-labelledby="dataModifyLabel"
                 aria-hidden="true">
                <div class="modal-dialog" style="width: 450px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="food_modify_title">数据操作</h4>
                        </div>
                        <div class="modal-body">
                            <form id="dataForm" enctype="multipart/form-data" class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label for="dataDate" class="col-lg-4 control-label">数据日期:</label>
                                    <div class="col-lg-8">
                                        <input type="text" id="dataDate" style="height:30px;width:200px;"
                                               data-provide="datepicker" class="form-control datepicker"
                                               placeholder="开始时间">
                                        <input type="hidden" name="dataId" id="dataId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dataPerson" class="col-lg-4 control-label">用餐人数:</label>
                                    <div class="col-lg-8">
                                        <input type="text" style="width: 200px" name="dataPerson" class="form-control"
                                               id="dataPerson"
                                               placeholder="请输入用餐人数">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="modifyData()">保存</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->


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
<script src="<%=basePath%>static/js/bootstrap-datepicker.min.js"></script>
<script src="<%=basePath%>static/js/bootstrap-datepicker.zh-CN.js"></script>
<!-- Morris Charts JavaScript -->
<script src="<%=basePath%>static/js/vendor/raphael/raphael.min.js"></script>
<script src="<%=basePath%>static/js/vendor/morrisjs/morris.min.js"></script>
<script src="<%=basePath%>static/person/data_book.js"></script>

<script src="<%=basePath%>static/js/init.js"></script>
</body>
</html>