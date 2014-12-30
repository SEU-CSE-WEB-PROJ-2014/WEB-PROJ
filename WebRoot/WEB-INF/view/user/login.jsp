<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<div>
    <div>
	 	<form>
	     	<ul>
	             <li><label><b>*</b>用户名：</label>
					<input id="loginName" name="loginName" type="text" placeholder="邮箱"/>
			     </li>
	             <li><label><b>*</b>密码：</label><input type="password" id="password"/></li>
	         </ul>
	     </form>
		 <div>
	     	<a id="loginBtn" href="javascript:void(0);"><b>登录</b></a>
	 	 </div>
	 </div>

</div>

<script type="text/javascript">
$(function(){
	$("#loginBtn").click(function(){
		var loginName = $("#loginName").val();
		var password = $("#password").val();

		$.post(
				"${basePath}user/login.do",
				{
					"loginName" : loginName,
					"password" : password
				},
				function(result){
					var status = result.status;
					if(status == 1){
						$.fancybox.close();
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
