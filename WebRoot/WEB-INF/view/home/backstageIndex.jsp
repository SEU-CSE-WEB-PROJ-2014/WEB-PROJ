<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>


<p>EasyGo backstage page.</p>

<div>
	<c:forEach items="${backstageUrls}" var="urlInfo">
		<a href="${basePath}${urlInfo.url}">${urlInfo.url_name}</a>
	</c:forEach>
</div>


<script type="text/javascript">
$(function(){

});
</script>
