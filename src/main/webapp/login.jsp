<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="<%=basePath%>static/css/reset.css">
        <link rel="stylesheet" href="<%=basePath%>static/css/supersized.css">
        <link rel="stylesheet" href="<%=basePath%>static/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="<c:url value='static/js/html5.js'/>"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>用户登录</h1>
            <input type="text" name="userName" class="username" placeholder="请输入您的用户名！">
            <input type="password" name="password" class="password" placeholder="请输入您的用户密码！">
            <form action="" method="post">
                <%--<input type="Captcha" class="Captcha" name="Captcha" placeholder="请输入验证码！">--%>
                <button type="submit" class="submit_button">登录</button>
                <div class="error"><span>+</span></div>
            </form>
            <%--<div class="connect">--%>
                <%--<p>快捷</p>--%>
                <%--<p>--%>
                    <%--<a class="facebook" href=""></a>--%>
                    <%--<a class="twitter" href=""></a>--%>
                <%--</p>--%>
            <%--</div>--%>
        </div>

        <script>
            var basePath="<%=basePath%>";
        </script>
        <!-- Javascript -->
        <script src="<%=basePath%>static/js/jquery.js"></script>
        <script src="<%=basePath%>static/js/supersized.3.2.7.min.js"></script>
        <script src="<%=basePath%>static/login/supersized-init.js" ></script>
        <script src="<%=basePath%>static/login/login.js" ></script>

    </body>
<div style="text-align:center;">
</div>
</html>

