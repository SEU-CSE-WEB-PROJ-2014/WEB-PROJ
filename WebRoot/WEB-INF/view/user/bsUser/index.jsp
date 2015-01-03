<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<!-- ligerGrid的js和样式表 -->
<script type="text/javascript" src="${contextPath}/res/js/ligerGrid/ligerGrid.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/ligerGrid/skins/Silvery/css/ligerui-all.css"/>


<body>
  Backstage User index page. <br>
</body>

<div>
	<div class="search-type">
		<label><input type="text" value="" class="type-name-sea" placeHolder="输入种类名称"/></label>
		<a class="search" href="javascript:void(0);">查询</a>
	</div>
	
	<div class="goods-type-grid">
	</div>
	
	<div class="options">
		<a class="add-btn" href="${basePath}bsGoods/addOrEditGoodsPage.do">新增</a>
	</div>
</div>

<script type="text/javascript">
function removeGoods(id, obj, callback){
	$.post(
		"${basePath}bsGoods/delGoods.do",
		{
			"goodsId" : id
		},
		function(result){
			if(callback){
				callback.call(obj, result);
			}
		},
		"json"
	);
}


var grid = null;
$(function(){
	//新增
	$(".add-btn").fancybox({
		width:600,
		height:340,
		onClosed: function(){
			grid.loadData();
		}
	});
	
	//删除
	$(".remove").die("click").live("click", function(){
		var goodsId = $(this).attr("id");
		removeGoods(goodsId, $(this), function(result){
			if(result.status == 1){
				grid.loadData();
			}else{
				alert(result.msg)
			}
		});
	});
	
	
	//数据显示表格
	grid = $(".goods-type-grid").ligerGrid({
        className: "goods-type-grid",//将l-grid层高强制自动
        columns: [{
            display: "商品名称",
            name: "goods_name",
            width: "20%"
        }, {
            display: "商品价格",
            name: "price",
            width: "10%"
        }, {	
            display: "商品数量",
            name: "quantity",
            width: "10%"
        }, {	
            display: "商品类型",
            name: "type_name",
            width: "10%"
        }, {	
            display: "商品描述",
            name: "description",
            width: "25%"
        }, {
            display: '操作',
            name: 'option',
            width: "25%",
            render: function(items){
            	var operation = "";
   	        	operation += "<a class='edit' id='" + items.goods_id + "' href='${basePath}bsGoods/addOrEditGoodsPage.do?goodsId="
   	        			+ items.goods_id + "'>编辑</a> ";
   				operation += "<a href='javascript:void(0);' class='remove' id='" + items.goods_id + "'>删除</a> ";
    	        return operation;	
            }
        }],
        root: 'pageObject',
        record: 'totalCount',
        url: '${basePath}bsGoods/goodsGrid.do',
        isScroll: false,
        width: "100%",
        checkbox: true,
        selectRowButtonOnly: true,//只允许点击复选框才能选择行
        onRClickToSelect: false,//禁止右击选择行
        pageParmName: 'pageNum',
        pagesizeParmName: 'pageSize', //页记录数参数名，(提交给服务器)
        pageSizeOptions: [5, 10, 15, 20],
        onAfterShowData: function(){
            $(".l-grid-loading").hide();
        	if($(".l-grid-row", $(".l-grid-body-table")).length <= 0){
				$("#notice").html("没有符合条件的数据");
	        }else{
	        	$("#notice").html("");
		    }
        	
        	//编辑
        	$(".edit").fancybox({
        		width:600,
        		height:340,
        		onClosed: function(){
        			grid.loadData();
        		}
        	});
   		}
    });

});
</script>
