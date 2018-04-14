<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>我的预约</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href='<c:url value="static/css/bootstrap.min.css"/>' rel="stylesheet">

    <!-- Custom CSS -->
    <link href='<c:url value="static/css/sb-admin.css"/>' rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href='<c:url value="static/css/plugins/morris.css"/>' rel="stylesheet">

    <!-- Custom Fonts -->
    <link href='<c:url value="static/font-awesome/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<jsp:include page="nav.jsp"/>


<div class="form-group input-group">
    <input type="text" class="form-control">
    <span class="input-group-btn"><button class="btn btn-default" type="button"><i
            class="fa fa-search"></i></button></span>
</div>


</body>
</html>
