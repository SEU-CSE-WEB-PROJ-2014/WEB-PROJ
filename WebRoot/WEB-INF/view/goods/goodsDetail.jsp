<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>


<input type="hidden" value="${goods.goods_id}" class="goods-id">
<div>${goods.goods_name}</div>
<div><label>分类：</label>${goods.type_name}</div>
<div><label>单价：</label>${goods.price}</div>
<div>
	<label>库存：</label>${goods.quantity}
	<label>购买数量：</label><input type="text" value="1" class="buy-quantity">	
	<input type="button" value="购买" class="buy-btn">
</div>

<div>商品介绍</div>
<div>${goods.description}</div>

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
