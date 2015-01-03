<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<div>
	<input type="hidden" class="goods-id" value="${goods.goodsId}">
	<li><label>商品名称：</label><input type="text" value="${goods.goodsName}" placeHolder="输入商品名称" class="goods-name"></li>
	<li><label>价格：</label><input type="text" value="${goods.price}" placeHolder="输入商品价格" class="price"></li>
	<li><label>数量：</label><input type="text" value="${goods.quantity}" placeHolder="输入商品数量" class="quantity"></li>
	<li><label>商品介绍：</label><textarea class="description">${goods.description}</textarea></li>
	<li><label>商品种类：</label>
				
	</li>
	
	<input type="button" class="ok-btn" value="确定">
	<input type="button" class="cancel-btn" value="取消">
</div>


<script type="text/javascript">
$(function(){
	$(".ok-btn").click(function(){
		var goodsId = $.trim($(".goods-id").val());
		var goodsName = $(".goods-name").val();
		var price = $(".price").val();
		var quantity = $(".quantity").val();
		var description = $(".description").val();
		
		$.post(
			"${basePath}bsGoods/addOrEditGoods.do",
			{
				"goodsId" : goodsId,
				"goodsName" : goodsName,
				"price" : price,
				"quantity" : quantity,
				"description" : description,
				"typeId" : ""
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
