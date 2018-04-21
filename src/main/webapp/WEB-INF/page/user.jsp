<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html>
<html>
<head>
    <title>用户中心</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='<%=basePath%>static/css/bootstrap.min.css' rel="stylesheet">
</head>
<body>

<div style="display:flex;align-items:center;width:100%;height: 50px;
background-color: #3b9ff3;padding-left: 20px">
    <span class="h3" style="color: white;">用户中心</span>
</div>

<div id="box" class="text-center" style="margin: 30px auto">
    <form class="form-horizontal" role="form">
        <div class="form-group form-inline">
            <label for="userName" class="col-md-4 control-label">用户名:</label>
            <input readonly id="userName" value="${USER_CONTEXT.userName}" class="form-control col-md-2">
        </div>
        <div class="form-group form-inline">
            <label for="password" class="col-md-4 control-label">密&nbsp;&nbsp;码:</label>
            <input readonly id="password" type="password" value="xxxxxxx" class="form-control col-md-2">
        </div>
        <div class="form-group">
            <label for="cardImg" class="col-md-4 control-label">学生证图片:</label>
            <img id="cardImg" src="${USER_CONTEXT.idCardImg}" style="width:280px;height: 280px;" class="form-control col-md-2">
        </div>
    </form>

</div>

</body>
</html>
