<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<div>
	<input type="hidden" class="type-id" value="${type.goodsTypeId}">
	<label>种类名称：</label><input type="text" value="${type.typeName}" placeHolder="输入类型名称" class="type-name">
	<label>种类简介：</label><textarea placeholder="输入类型简介" class="type-intro">${type.typeIntro}</textarea>
	<input type="button" class="ok-btn" value="确定">
	<input type="button" class="cancel-btn" value="取消">
</div>


<script type="text/javascript">
$(function(){
	$(".ok-btn").click(function(){
		var typeName = $(".type-name").val();
		var typeIntro = $(".type-intro").val();
		var typeId = $(".type-id").val();
		
		$.post(
			"${basePath}bsGoodsType/AddOrEditgoodsType.do",
			{
				"typeId" : typeId,
				"typeName" : typeName,
				"typeIntro" : typeIntro				
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
