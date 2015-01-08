<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<!-- 分页插件 -->
<script type="text/javascript" src="${contextPath}/res/js/pager/jquery.simplePagination.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/pager/simplePagination.css">


<div>
	<div>
		<li class="order-type all-order" payState="" transState="" signState="">所有订单</li>
		<li class="order-type wait-pay-order" payState="0" transState="" signState="">待付款</li>
		<li class="order-type wait-deliver-order" payState="1" transState="0" signState="">待发货</li>
		<li class="order-type wait-sign-order" payState="1" transState="1" signState="0">待签收</li>
	</div>
	<div id="myOrderList">
	</div>
</div>

<script type="text/javascript">
function myOrder(pageNum){
	var payState = $(".order-type[class*=selected]").attr("payState");
	var transState = $(".order-type[class*=selected]").attr("payState");
	var signState = $(".order-type[class*=selected]").attr("payState");
	
	$("#myOrderList").load("${contextPath}/order/myOrder.do",{
		"payState" : payState,
		"transState" : transState,
		"signState" : signState,
		pageNum : pageNum
	});
}
$(function(){
	$(".order-type").click(function(){
		$(this).removeClass("selected").addClass("selected").siblings().removeClass("selected");
		myOrder(0);
	}).eq(0).click();
	
});
</script>
