<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<script src="${contextPath}/res/js/jquery.js"></script>
<script src="${contextPath}/res/js/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/fancybox/fancybox.css"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}">
    
    <title>EasyGo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    EasyGo home page. <br>
    <a href="${basePath}user/loginPage.do" class="login">登陆</a>
    <a href="${basePath}user/regUserPage.do" class="register">注册</a>
  </body>
</html>

<script type="text/javascript">
$(function(){
	$(".register").fancybox({
	});
	$(".login").fancybox({
	});

	
});
</script>
