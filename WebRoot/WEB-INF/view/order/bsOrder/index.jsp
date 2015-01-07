<%@ page language="java" import="java.util.*" pageEncoding="utf-8" session="false"%>
<%@ include file="/WEB-INF/common/header.jsp" %>

<!-- ligerGrid的js和样式表 -->
<script type="text/javascript" src="${contextPath}/res/js/ligerGrid/ligerGrid.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/res/js/ligerGrid/skins/Silvery/css/ligerui-all.css"/>

<body>
  Backstage order index page. <br>
</body>

<div>
	<div class="search-type">
		<%--<label><input type="text" value="" class="user-name-sea" placeHolder="输入登录名/昵称"/></label>
		<label><input type="text" value="" class="email-sea" placeHolder="输入email地址"/></label>
		<label>
			<input type="radio" name="roleId" value="" checked="checked">不限制
			<input type="radio" name="roleId" value="ID000">管理员
			<input type="radio" name="roleId" value="ID001">商品管理员
			<input type="radio" name="roleId" value="ID002">网购用户
			<input type="radio" name="roleId" value="ID003">订单管理员
		</label>
		--%><a class="search" href="javascript:void(0);">查询</a>
	</div>

	<div class="grid">
	</div>
	
	<div class="options">
		<%--<a class="add-btn" href="${basePath}bsUser/addOrEditUserPage.do">新增</a>
	--%></div>
</div>

<script type="text/javascript">
var grid = null;
$(function(){
	$(".search").click(function(){
		var name= $(".user-name-sea").val();
		var roleId= $("input[name='roleId']:checked").val();
		var email= $(".email-sea").val();
		grid.setOptions({
            parms: [
       			{
	                name: "name",
	                value: name
            	},{
	                name: "roleId",
	                value: roleId
            	},
            	{
	                name: "email",
	                value: email
            	}
            ]
        });
		grid.loadData();
	});
	
	
	
	
	/*
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
		var id = $(this).attr("id");
		removeUser(id, $(this), function(result){
			if(result.status == 1){
				grid.loadData();
			}else{
				alert(result.msg)
			}
		});
	});
	*/
	
	
	//数据显示表格
	grid = $(".grid").ligerGrid({
        className: "l-grid",//将l-grid层高强制自动
        columns: [{
            display: "商品名称",
            name: "goods_name",
            width: "10%"
        }, {
            display: "商品数量",
            name: "quantity",
            width: "10%"
        }, {	
            display: "总价",
            name: "total_price",
            width: "10%"
        }, {	
            display: "用户名称",
            name: "nick_name",
            width: "10%"
        }, {	
            display: "用户地址",
            name: "address",
            width: "20%"
        }, {	
            display: "付款状态",
            name: "pay_state",
            width: "5%",
            render: function(items){
            	if(items.pay_state == 0){
            		return "未付款";
            	}else{
            		return "已付款";
            	}
            	
            }
        }, {	
            display: "发货状态",
            name: "trans_state",
            width: "5%",
            render: function(items){
            	if(items.trans_state == 0){
            		return "未发货";
            	}else{
            		return "已发货";
            	}
            	
            }
        }, {	
            display: "签收状态",
            name: "sign_state",
            width: "5%",
            render: function(items){
            	if(items.sign_state == 0){
            		return "未签收";
            	}else{
            		return "已签收";
            	}
            	
            }
        }, {	
            display: "物流编号",
            name: "invoice_num",
            width: "15%",
            render:function(items){
            	if(items.invoice_num == null || items.invoice_num == ""){
            		return "";
            	}else{
            		return items.invoice_num;
            	}
            }
        }, {
            display: '操作',
            name: 'option',
            width: "10%",
            render: function(items){
            	var operation = "";
            	
            	if(items.pay_state == 1 && items.trans_state == 0){
            		operation += "<a href='${basePath}bsOrder/deliverGoodsPage.do?orderId=" + items.order_id + "' class='deliver-goods' id='" + items.order_id + "'>发货</a> ";
            	}
    	        return operation;	
            }
        }],
        root: 'pageObject',
        record: 'totalCount',
        url: '${basePath}bsOrder/orderGrid.do',
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
        	$(".deliver-goods").fancybox({
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