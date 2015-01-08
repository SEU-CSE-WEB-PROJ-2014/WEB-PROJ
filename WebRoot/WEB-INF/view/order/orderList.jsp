<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>

<ul>
	<c:forEach items="${pageObject.resultList}" var="order" varStatus="orderStatus">
		<li orderId="${order.order_id}">
			<a href="${contextPath}/goods/browseGoods.do?goodsId=${order.goods_id}" target="_blank">${order.goods_name}</a>
			<label>￥${order.total_price}&nbsp;&nbsp;<span>${order.price} x ${order.quantity}</span></label>
			<label>
				<c:choose>
					<c:when test="${order.pay_state == 0}">
						<a href="#pwdVerifyDiv" class="pay-btn verify-password">付款</a>
					</c:when>				
					<c:when test="${order.pay_state == 1 && order.trans_state == 0}">
						<a class="">待发货</a>
					</c:when>				
					<c:when test="${order.pay_state == 1 && order.trans_state == 1 && order.sign_state == 0}">
						<a href="#pwdVerifyDiv" class="sign-btn verify-password">签收</a>
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
			searchGoods(pageNumber-1);
			return false;
		}
	});
	
	$(".verify-password").fancybox({
		onStart: function(){
			$(this);
			debugger
			$("#pwdVerifyDiv").show();
		},
		onClosed: function(){
			$(this);
			debugger
			$("#pwdVerifyDiv").hide();	
		}
	});
});
</script>
