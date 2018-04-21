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

    <style type="text/css">

        #box {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
        }

        #userOpt {
            display: inline-flex;
            flex-direction: column;
            justify-content: flex-end;
            align-items: center;
            width: 320px;
        }

        #userInfo {
            display: inline-flex;
            flex-direction: column;
            width: 300px;

        }

        #userInfo div {
            display: inline-flex;
            justify-content: flex-end
        }

        #userInfo input {
            width: 200px;
        }
    </style>
</head>
<body>

<div style="display:flex;align-items:center;width:100%;height: 50px;
background-color: #3b9ff3;padding-left: 20px">
    <span class="h3" style="color: white;">用户中心</span>
</div>

<div id="box">
    <div id="userOpt">
        <button class="btn btn-sm btn-default">编辑</button>
        <span class="fa fa-line" style="width: 320px"></span>
    </div>
    <div id="userInfo">
        <div class="form-inline form-group">
            <label for="userName">用户名: </label>
            <input class="" id="userName"/>
        </div>
        <div class="form-inline form-group">
            <label for="password">密 码: </label>
            <input class="" id="password"/>
        </div>
        <div class="form-inline form-group">
            <label for="password2">再次输入密码: </label>
            <input class="" id="password2"/>
        </div>
        <div class="form-inline form-group">
            <label for="cardImg">学生证图片: </label>
            <input class="" type="file" id="cardImg"/>
        </div>
    </div>
    <img src="" width="290" height="290">

</div>

</body>
</html>
