<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<link rel="stylesheet" type="text/css" href="${contextPath}/res/CSS/goodsDetail.css"/>

<div class="goods-detail-content">
	<input type="hidden" value="${goods.goods_id}" class="goods-id">
	<div class="goods-name-and-type">
		${goods.type_name}<br/>
		<div class="goods-name">
			${goods.goods_name}
		</div>
	</div>
	<div>单价：<label class="nums">${goods.price}</label></div>
	<div>
		库存：<label class="nums">${goods.quantity}</label><br/>
		购买数量：<input type="text" value="1" class="buy-quantity nums">	
		<input type="button" value="购买" class="buy-btn">
	</div>
	
	<div class="title-row">商品介绍</div>
	<div>${goods.description}</div>
</div>

<script type="text/javascript">
$(function(){
	$(".buy-btn").click(function(){
		var goodsId = $(".goods-id").val();
		var quantity = $(".buy-quantity").val();
		
		$.post(
			"${contextPath}/order/order.do",
			{
				goodsId : goodsId,
				quantity : quantity
			},
			function(result){
				if(result.status == 1){
					alert("下单成功");
				}else{
					alert("下单失败：\n" + result.msg);
				}
			},
			"json"
		);
	});
});
</script>
