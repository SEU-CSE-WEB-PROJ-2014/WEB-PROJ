<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/CSS/goodsList.css">

<ul>
	<c:forEach items="${pageObject.resultList}" var="goods" varStatus="goodsStatus">
		<li class="listAll">
			<a class="goodsList" href="${contextPath}/goods/browseGoods.do?goodsId=${goods.goods_id}">${goods.goods_name}</a>
			<label class="price">${goods.price}</label>
			<label class="quantity">${goods.quantity}</label>
		</li>
	</c:forEach>
</ul>

<div id="goodsListPager"></div>

<script type="text/javascript">
$(function(){
	$("#goodsListPager").pagination({
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
});
</script>
