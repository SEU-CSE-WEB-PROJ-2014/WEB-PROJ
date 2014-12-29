<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="includes.jsp" %>
<%@ page import="com.easygo.user.bo.*" %>
<%@ page import="com.easygo.common.utils.userManager.*" %>

<%
	LoginUser curUser = UserManager.getCurrentUser();
	request.setAttribute("curUser", curUser);
	
%>

<!-- JS -------------------------------------------------------------------------------->
<script src="${contextPath}/res/js/jquery.js"></script>
<script src="${contextPath}/res/js/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/fancybox/fancybox.css"/>

<!-- header数据 -------------------------------------------------------------------------->
<div class="header">
	<c:choose>
		<c:when test="${curUser != null}">
			${curUser.nickName}&nbsp;&nbsp;您好！
			<a href="javascript:void(0);" class="logout">注销</a>
		</c:when>
		<c:otherwise>
			<a href="${basePath}user/loginPage.do" class="login">登陆</a>
   	 		<a href="${basePath}user/regUserPage.do" class="register">注册</a>
		</c:otherwise>
	</c:choose>
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