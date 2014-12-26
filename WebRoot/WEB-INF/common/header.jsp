<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="includes.jsp" %>
<%@ page import="com.easygo.user.bo.*" %>

<%
	String msg = null;
	HttpSession s= request.getSession(false);
	if(s == null){
		msg = "session 为空";
	}else{
		CoreUser user = (CoreUser)s.getAttribute("CoreUser");
		if(user != null){
			msg = "当前登录：" + user.getNickName();
		}else{
			msg = "session 不为空，无登录用户";
		}
	}
	request.setAttribute("msg", msg);
%>

<!-- header数据 -------------------------------------------------------------------------->
<div class="header">
	${msg}
</div>