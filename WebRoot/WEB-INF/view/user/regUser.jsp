<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<link rel="stylesheet" type="text/css" href="${contextPath}/res/CSS/regAndLogin.css">

<div class="reg-div">
    <div>
	 	<form>
	     	<ul>
	     		 <li><h4>请注册</h4></li>
	             <li><label><b>*</b>邮箱：</label>
					<input id="email" name="email" type="text" placeholder="请输入有效邮箱"/>
			     </li>
	             <li><label class='form-label'><b>*</b>密码：</label><input type="password" placeholder="6-15位密码" maxlength=15 id="pwd" name="password"/></li>
	             <li><label class='form-label'><b>*</b>确认密码：</label><input type="password" placeholder="6-15位密码" maxlength=15 id="pwd1"/>
	         	 <li><label class='form-label'><b>*</b>昵称：</label><input placeholder="请输入您的昵称" id="nickName" name="nickName" maxlength="25" type="text"/></li>
	         	 <li class="sex">
	             	<label class='form-label'>性别：</label>
	             </li>
	         </ul>
	     </form>
		 <div class="reg-button">
	     	<a id="regBtn" href="javascript:void(0);"><b>注册</b></a>
	 	 	<a href="javascript:void(0);">返回登录</a>
	 	 </div>
	 </div>

</div>

<script type="text/javascript">
$(function(){
	$("#regBtn").click(function(){
		var email = $("#email").val();
		var password = $("#pwd").val();
		var nickName = $("#nickName").val();
		var loginName = nickName;
		$.post(
				"${basePath}user/regUser.do",
				{
					"email" : email,
					"password" : password,
					"nickName" : nickName,
					"loginName" : loginName
				},
				function(result){
					var status = result.status;
					if(status == 1){
						$.fancybox.close();
					}else{
						alert(result.msg);
					} 
				},
				"json"
		);		
	});
});
</script>
