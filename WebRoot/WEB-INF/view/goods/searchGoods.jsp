<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<!-- 分页插件 -->
<script type="text/javascript" src="${contextPath}/res/js/pager/jquery.simplePagination.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/pager/simplePagination.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/res/CSS/searchGoods2.css">
<!-- 搜索条件 -->
<div>
	<a class="searchOverall sea-btn" href="javascript:void(0);">查找</a>
	<input class="searchOverall sea-goods-name" type="text" placeHolder="查找商品">
	<div class="goodsType">
	 
		<c:forEach items="${types}" var="type" varStatus="typeStatus">
		   <li class="typeList" <c:if test="${type.goods_type_id == typeId}">selected</c:if>>
			<a href="javascript:void(0);" title="${type.type_intro}" 
				class="goods-type" 
				typeId="${type.goods_type_id}">${type.type_name}</a>
		   </li>
		</c:forEach>
	 
	</div>
	<div >
		<input type="text" placeHolder="最低价格" class="sea-min-price" value="">
		<input type="text" placeHolder="最高价格" class="sea-max-price" value="">
	</div >
</div>

<!-- 商品列表 -->
<div id="goodsList">
	<jsp:include page="goodsList.jsp"></jsp:include>
</div>

<script type="text/javascript">
function searchGoods(pageNum){
	var goodsName = $(".sea-goods-name").val();
	var typeId = "";
	if($(".typeList[class*=selected]").length > 0){
		typeId = $(".typeList[class*=selected]").children("a").attr("typeId");
	}
	var minPrice = $(".sea-min-price").val();
	var maxPrice = $(".sea-max-price").val();
	window.location.href = "${basePath}goods/searchGoods.do?"
		+ "pageNum=" + pageNum
		+ "&goodsName=" + goodsName
		+ "&typeId=" + typeId
		+ "&minPrice=" + minPrice
		+ "&maxPrice=" + maxPrice;
}

$(function(){
	$(".sea-btn").click(function(){
		searchGoods(0);
	});
	$(".typeList").click(function(){
		$(this).removeClass("selected").addClass("selected").siblings().removeClass("selected");
		$(".sea-btn").click();
	});
});
</script>
