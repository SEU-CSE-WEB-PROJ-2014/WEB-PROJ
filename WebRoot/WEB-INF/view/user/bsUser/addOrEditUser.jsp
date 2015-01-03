<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<div>
	<input type="hidden" class="user-id" value="${user.userId}">
	<li><label>登录名：</label><input type="text" value="${user.loginName}" placeHolder="输入登录名称" class="login-name"></li>
	<li><label>昵称：</label><input type="text" value="${user.nickName}" placeHolder="输入昵称" class="nick-name"></li>
	<li><label>性别：</label>
		<select class="sex">
			<option value="0" <c:if test="${user.sex == 0}">selected</c:if>>男</option>
			<option value="1" <c:if test="${user.sex == 1}">selected</c:if>>女</option>
		</select>		
	</li>
	<li><label>email：</label><input type="text" value="${user.email}" placeHolder="输入email" class="email"></li>
	<li><label>地址：</label><input type="text" value="${user.address}" placeHolder="输入地址" class="address"></li>
	<li><label>角色：</label>
		<select class="role-id">
			<option value="ID000" <c:if test="${user.roleId == 'ID000'}">selected</c:if>>管理员</option>
			<option value="ID001" <c:if test="${user.roleId == 'ID001'}">selected</c:if>>商品管理员</option>
			<option value="ID002" <c:if test="${user.roleId == 'ID002'}">selected</c:if>>网购用户</option>
			<option value="ID003" <c:if test="${user.roleId == 'ID003'}">selected</c:if>>订单管理员</option>
		</select>
	</li>
	
	<input type="button" class="ok-btn" value="确定">
	<input type="button" class="cancel-btn" value="取消">
</div>


<script type="text/javascript">
$(function(){
	$(".ok-btn").click(function(){
		var userId = $(".user-id").val();
		var loginName = $.trim($(".login-name").val());
		var nickName = $(".nick-name").val();
		var sex = $(".sex").val();
		var email = $(".email").val();
		var address = $(".address").val();
		var roleId = $(".role-id").val();
		
		$.post(
			"${basePath}bsUser/AddOrEditUser.do",
			{
				"userId" : userId,
				"loginName" : loginName,
				"nickName" : nickName,
				"sex" : sex,
				"email" : email,
				"address" : address,
				"roleId" : roleId
			},
			function(result){
				if(result.status == 1){
					$.fancybox.close();
				}else{
					alert(result.msg);
				}
				
			},
			"json"
		);
	});
	
	$(".cancel-btn").click(function(){
		$.fancybox.close();
	});
});

</script>
