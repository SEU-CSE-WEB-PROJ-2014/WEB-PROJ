<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<script src="${contextPath}/res/js/jquery.js"></script>

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
    EasyGo manager page. <br>
    <a href="javascript:void(0);" class="reload">重载</a>
  </body>
</html>

<script type="text/javascript">
$(function(){
	$(".reload").click(function(){
		$.post(
			"manager/reload.do",
			{},
			function(result){
				if(result.status == 1){
					alert("重载成功");
				}else{
					alert("重载失败");
				}
			},
			"json"
		);
	});
	
});
</script>
