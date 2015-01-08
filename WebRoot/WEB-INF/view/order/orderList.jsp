<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<ul>
	<c:forEach items="${pageObject.resultList}" var="order" varStatus="orderStatus">
		<li orderId="${order.order_id}">
			<a href="${contextPath}/goods/browseGoods.do?goodsId=${order.goods_id}" target="_blank">${order.goods_name}</a>
			<label>￥${order.total_price}&nbsp;&nbsp;<span>${order.price} x ${order.quantity}</span></label>
			<label>
				${order.pay_state}__${order.trans_state}__${order.sign_state}
				<c:choose>
					<c:when test="${order.pay_state == 0}">
						<a href="javascript:void(0);" class="pay-btn verify-password">付款</a>
					</c:when>				
					<c:when test="${order.pay_state == 1 && order.trans_state == 0}">
						<a class="">待发货</a>
					</c:when>				
					<c:when test="${order.pay_state == 1 && order.trans_state == 1 && order.sign_state == 0}">
						<a href="javascript:void(0);" class="sign-btn verify-password">签收</a>
					</c:when>		
					<c:when test="${order.pay_state == 1 && order.trans_state == 1 && order.sign_state == 1}">
						<a class="">交易成功</a>
					</c:when>				
				</c:choose>
			</label>
		</li>
	</c:forEach>
</ul>

<div id="pager"></div>

<!-- 密码验证框 ---------------------------------------->
<div id="pwdVerifyDiv" style="display:none;">
	<div>
		${curUser.nickName}&nbsp;您好<br/>
		为确保账户安全，请验证密码：<br>
		<input type="password" class="pwd-verify-input" value="" placeHolder="请输入密码"/>
		<input type="button" class="verify-ok-btn" value="确定">
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#pager").pagination({
		prevText: '上一页',
		nextText: '下一页',
		cssStyle: 'light-theme',
		"selectOnClick": false,
		"pages" : "${pageObject.totalPage}",
		"currentPage" : "${pageObject.pageNum + 1}",
		"onPageClick" : function(pageNumber, event){
			myOrder(pageNumber-1);
			return false;
		}
	});
	
	$(".pay-btn").click(function(){
		var orderId = $(this).parents("li").attr("orderId");
		$.post(
			"${contextPath}/order/payOrder.do",
			{
				orderId : orderId
			},
			function(result){
				if(result.status == 1){
					alert("付款成功");
					myOrder("${pageObject.pageNum}");
				}else{
					alert("付款失败：\n" + result.msg);
					return false;
				}
			},
			"json"
		);
		
	});
	
	$(".sign-btn").click(function(){
		var orderId = $(this).parents("li").attr("orderId");
		$.post(
			"${contextPath}/order/signOrder.do",
			{
				orderId : orderId
			},
			function(result){
				if(result.status == 1){
					alert("签收成功");
					myOrder("${pageObject.pageNum}");
				}else{
					alert("签收失败：\n" + result.msg);
				}
			},
			"json"
		);
		
	});
	
	/*
		$(".verify-password").fancybox({
		onStart: function(){
			$("#pwdVerifyDiv").show();
		},
		onClosed: function(){
			$("#pwdVerifyDiv").hide();	
		}
	});
	*/
});
</script>
