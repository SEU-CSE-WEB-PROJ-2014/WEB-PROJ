<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>


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
				"user/login.do",
				{
					"loginName" : loginName,
					"password" : password
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
