<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:if test='${!empty successMsg}'>${successMsg}</c:if> <c:if
		test='${empty successMsg}'> 操作成功</c:if></title>
<script language=javascript>
	setTimeout("window.location.href='index.html'", 180)
</script>
</head>
<body>
	<c:if test='${!empty successMsg}'>${successMsg}</c:if>
	<c:if test='${empty successMsg}'>操作成功</c:if>
</body>
</html>
