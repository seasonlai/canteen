<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<%--
  Created by IntelliJ IDEA.
  User: Wellhope
  Date: 2018/4/13
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link href='<%=basePath%>static/css/bootstrap.min.css' rel="stylesheet">

</head>
<body style="background-color: #2e6da4">
<div style="width:400px;margin: 50px auto;background-color: white">
    <h3 class="text-center" style="padding-top: 20px">新用户注册</h3>

    <div class="row" style="width:300px;margin: 32px auto">
        <div class="col-lg-6">

            <form role="form"
                  style="width: 300px"
                  method="post" onsubmit="return myCheck()"
                  enctype="multipart/form-data"
                  action="<c:url value="/login/doRegister"/>">

                <div class="form-group">
                    <input name="userName" style="width:300px;" class="form-control" id="userName" placeholder="请输入用户名">
                    <%--<p class="help-block">Example block-level help text here.</p>--%>
                </div>

                <div class="form-group">
                    <input class="form-control" style="width:300px;" name="password" id="pwd" type="password"
                           placeholder="请输入密码">
                </div>

                <div class="form-group">
                    <input class="form-control" style="width:300px;" id="pwd2" type="password" placeholder="请再次输入密码">
                </div>

                <div class="form-group" style="width:300px;">
                    <div class="file-container"
                         style="display:inline-block;position:relative;overflow: hidden;vertical-align:middle">
                        <button class="btn btn-default fileinput-button" type="button">学生证图片</button>
                        <input type="file" name="idCardImg" id="idCardImg" onchange="loadFile(this.files[0])"
                               style="position:absolute;top:0;left:0;font-size:34px; opacity:0">
                    </div>
                    <span id="filename" style="width: 250px;margin-top:20px;vertical-align: middle">未选择文件</span>
                    <p class="help-block">请选择<1M的图片</p>
                </div>

                <a href="<c:url value="/login.jsp"/> " style="float: right">已有帐号,去登陆</a>
                <button type="submit" style="width: 260px;margin-left: 18px;margin-top: 20px;margin-bottom: 20px"
                        class="btn btn-success">注册
                </button>
            </form>
        </div>
    </div>
</div>
</body>

<!-- jQuery -->
<script src='<%=basePath%>static/js/jquery.js'></script>

<!-- Bootstrap Core JavaScript -->
<script src='<%=basePath%>static/js/bootstrap.min.js'></script>
<script src="<%=basePath%>static/js/bootbox.min.js"></script>

<script>

    var errorMsg = "${errorMsg}";
    if (errorMsg)
        alertWindow(errorMsg);

    function myCheck() {

        if (!$('#userName').val()) {
            alert('用户名不能为空');
            return false;
        }
        if (!$('#pwd').val()) {
            alert('密码不能为空');
            return false;
        }
        if ($('#pwd').val() != $('#pwd2').val()) {
            alert("两次输入的密码不一致");
            return false;
        }

        return true;
    }

    function loadFile(file) {
        $("#filename").html(file.name);
    }

</script>
</html>
