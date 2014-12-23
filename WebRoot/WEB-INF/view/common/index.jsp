<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<script src="${contextPath}/res/js/fancyBox/jquery-ui-1.8.22.custom.min.js"></script>
<script src="${contextPath}/res/js/fancyBox/jquery.fancybox-1.3.4.pack.js"></script>
<script src="${contextPath}/res/js/fancyBox/jquery.fancybox-1.3.4.js"></script>
<script src="${contextPath}/res/js/fancyBox/jquery.mousewheel-3.0.4.pack.js"></script>
<script src="${contextPath}/res/js/fancyBox/jquery.easing-1.3.pack.js"></script>

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
    <a href="javascript:void(0);" class="login">登陆</a>
    <a href="javascript:void(0);" class="register">注册</a>
    
    <a href="javascript:void(0);" class="test">
    	<img src="${contextPath}/res/joseph.jpeg" class="test" style="width:100px;">
    </a>
  </body>
</html>

<script type="text/javascript">
$(function(){
	$(".test").fancybox();

	
});
</script>
