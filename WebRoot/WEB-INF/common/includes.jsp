<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
	
	request.setAttribute("contextPath", contextPath);
	request.setAttribute("basePath", basePath);
%>
