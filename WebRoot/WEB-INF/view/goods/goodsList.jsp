<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/includes.jsp" %>


<ul>
	<c:forEach items="${pageObject.resultList}" var="goods" varStatus="goodsStatus">
		<li>
			<a href="javascript:void(0);">${goods.goods_name}</a>
			<label>${goods.price}</label>
			<label>${goods.quantity}</label>
		</li>
	</c:forEach>
</ul>

<div id="goodsListPager"></div>

<script type="text/javascript">
$(function(){
	$("#goodsListPager").pagination({
		"items" : "${pageObject.totalPage}",
		"itemsOnPage" : "${pageObject.pageNum + 1}",
		"onPageClick" : function(pageNumber, event){
			searchGoods(pageNumber-1);			
		}
	});
});
</script>
