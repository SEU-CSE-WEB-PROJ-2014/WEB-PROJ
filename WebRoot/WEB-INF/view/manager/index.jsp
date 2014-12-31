<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<body>
  EasyGo manager page. <br>
  <a href="javascript:void(0);" class="reload">重载</a>
</body>

<script type="text/javascript">
$(function(){
	$(".reload").click(function(){
		$.post(
			"manager/reload.do",
			{},
			function(result){
				if(result.status == 1){
					alert("重载成功");
				}else{
					alert("重载失败");
				}
			},
			"json"
		);
	});
	
});
</script>
