<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<!-- ligerGrid的js和样式表 -->
<script type="text/javascript" src="${contextPath}/res/js/ligerGrid/ligerGrid.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/ligerGrid/skins/Silvery/css/ligerui-all.css"/>

<body>
  Backstage GoodsType index page. <br>
</body>
<div>
	<div class="search-type">
		<label><input type="text" value="" class="type-name-sea" placeHolder="输入种类名称"/></label>
		<a class="search" href="javascript:void(0);">查询</a>
	</div>
	
	<div class="goods-type-grid">
	</div>
	
	<div class="options">
		<a class="add-btn" href="${basePath}bsGoodsType/AddOrEditgoodsTypePage.do">新增</a>
	</div>
</div>

<script type="text/javascript">
function removeType(typeId, obj, callback){
	$.post(
		"${basePath}bsGoodsType/delGoodsType.do",
		{
			"typeId" : typeId
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
	$(".search").click(function(){
		var typeName= $(".type-name-sea").val();
		grid.setOptions({
            parms: [{
                name: "typeName",
                value: typeName
            }]
        });
		grid.loadData();
	});
	
	
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
		var typeId = $(this).attr("id");
		removeType(typeId, $(this), function(result){
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
            display: "种类Id",
            name: "goods_type_id",
            width: "20%"
        }, {
            display: "种类名称",
            name: "type_name",
            width: "20%"
        }, {	
            display: "种类简介",
            name: "type_intro",
            width: "30%"
        }, {
            display: '操作',
            name: 'option',
            width: "30%",
            render: function(items){
            	var operation = "";
   	        	operation += "<a class='edit' id='" + items.goods_type_id + "' href='${basePath}bsGoodsType/AddOrEditgoodsTypePage.do?typeId="
   	        			+ items.goods_type_id + "'>编辑</a> ";
   				operation += "<a href='javascript:void(0);' class='remove' id='" + items.goods_type_id + "'>删除</a> ";
    	        return operation;	
            }
        }],
        root: 'pageObject',
        record: 'totalCount',
        url: '${basePath}bsGoodsType/goodsTypeGrid.do',
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
