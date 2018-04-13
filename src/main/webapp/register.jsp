<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
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
<body>

    <h3 class="text-center">新用户注册</h3>

    <div class="row" style="width:300px;margin: 32px auto">
        <div class="col-lg-6">

            <form role="form"
                  method="post" onsubmit="return myCheck()"
                  action="<c:url value="/login/doRegister.html"/>">

                <div class="form-group">
                    <label>请输入用户名</label>
                    <input name="userName" style="width:300px;" class="form-control" id="userName">
                    <%--<p class="help-block">Example block-level help text here.</p>--%>
                </div>

                <div class="form-group">
                    <label>请输入密码</label>
                    <input class="form-control" style="width:300px;" name="password" id="pwd" type="password" placeholder="6位数字以上">
                </div>

                <div class="form-group">
                    <label>请次输入密码</label>
                    <input class="form-control" style="width:300px;" id="pwd2" type="password" placeholder="6位数字以上">
                </div>

                <div class="form-group">
                    <label>学生证图片</label>
                    <input type="file">
                    <p class="help-block">请选择小于1M的图片</p>
                </div>
                <button type="submit" style="width: 260px;margin: 12px auto;" class="btn btn-default">注册</button>
            </form>
        </div>
    </div>
</body>

<!-- jQuery -->
<script src='<%=basePath%>static/js/jquery.js'></script>

<!-- Bootstrap Core JavaScript -->
<script src='<%=basePath%>static/js/bootstrap.min.js'></script>

<script src=='<%=basePath%>static/register/register.js'></script>

<script>

    var errorMsg="${errorMsg}";
    if(errorMsg)
        alert(errorMsg);

    function myCheck() {
        if(!$('#userName').val()){
            alert('用户名不能为空')
            return false;
        }
        if ($('#pwd').val()!=$('#pwd2').val()) {
            alert("两次输入的密码不一致");
            return false;
        } else {
            return true;
        }
    }
</script>
</html>
