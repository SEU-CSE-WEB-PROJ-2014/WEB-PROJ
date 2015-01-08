<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="includes.jsp" %>
<%@ page import="com.easygo.user.bo.*" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}">
    
    <title>EasyGo</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="b2c,easygo">
	<meta http-equiv="description" content="easygo b2c platform">
	
	<!-- JS&CSS -------------------------------------------------------------------------------->
	<script src="${contextPath}/res/js/jquery.js"></script>
	<script src="${contextPath}/res/js/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/fancybox/fancybox.css"/>
  </head>
</html>


<!-- header数据 -------------------------------------------------------------------------->
<div class="header">
	<a href="${homePage}">首页</a>
	<div>
		<c:choose>
			<c:when test="${curUser != null}">
				${curUser.nickName}&nbsp;&nbsp;您好！
				<c:if test="${curUser.hasBackstageAuth}">
					<a href="${basePath}home/backstageIndex.do">后台管理</a>
				</c:if>
				<a href="${contextPath}/order/myOrderPage.do" class="my-order">我的订单</a>
				<a href="javascript:void(0);" class="logout">注销</a>
			</c:when>
			<c:otherwise>
				<a href="${basePath}user/loginPage.do" class="login">登录</a>
	   	 		<a href="${basePath}user/regUserPage.do" class="register">注册</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$(".register").fancybox({
	});
	$(".login").fancybox({
	});
	
	//注销
	$(".logout").click(function(){
		$.post(
			"${basePath}user/logout.do",
			{},
			function(result){
				if(result.status == 1){
					window.location.href = "${basePath}";
				}else{
					alert(result.msg);
				}
			},
			"json"
		);		
	});
});
</script>