<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ page import="com.easygo.common.utils.Constant" %>
<%@ page import="com.easygo.common.utils.userManager.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String contextPath = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
	
	request.setAttribute("contextPath", contextPath);
	request.setAttribute("basePath", basePath);
	request.setAttribute("homePage", Constant.HOME_PAGE);
	LoginUser curUser = UserManager.getCurrentUser();
	request.setAttribute("curUser", curUser);
%>
