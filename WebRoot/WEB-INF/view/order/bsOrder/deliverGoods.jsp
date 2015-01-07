<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<div>
	<input type="hidden" class="order-id" value="${orderId}">
	<li><label>物流编号：</label><input type="text" value="" placeHolder="输入物流编号" class="invoice-num"></li>
				
	</li>
	
	<input type="button" class="ok-btn" value="确定">
	<input type="button" class="cancel-btn" value="取消">
</div>


<script type="text/javascript">
$(function(){
	$(".ok-btn").click(function(){
		var orderId = $.trim($(".order-id").val());
		var invoiceNum = $(".invoice-num").val();
		
		$.post(
			"${basePath}bsOrder/deliverGoods.do",
			{
				"orderId" : orderId,
				"invoiceNum" : invoiceNum
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
