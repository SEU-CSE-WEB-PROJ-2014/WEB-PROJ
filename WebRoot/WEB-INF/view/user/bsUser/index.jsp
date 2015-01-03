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
		<label><input type="text" value="" class="user-name-sea" placeHolder="输入登录名/昵称"/></label>
		<label><input type="text" value="" class="email-sea" placeHolder="输入email地址"/></label>
		<label>
			<input type="radio" name="roleId" value="" checked="checked">不限制
			<input type="radio" name="roleId" value="ID000">管理员
			<input type="radio" name="roleId" value="ID001">商品管理员
			<input type="radio" name="roleId" value="ID002">网购用户
			<input type="radio" name="roleId" value="ID003">订单管理员
		</label>
		<a class="search" href="javascript:void(0);">查询</a>
	</div>

	<div class="goods-type-grid">
	</div>
	
	<div class="options">
		<a class="add-btn" href="${basePath}bsUser/addOrEditUserPage.do">新增</a>
	</div>
</div>

<script type="text/javascript">
function removeUser(id, obj, callback){
	$.post(
		"${basePath}bsUser/delUser.do",
		{
			"userId" : id
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
	
	
	//数据显示表格
	grid = $(".goods-type-grid").ligerGrid({
        className: "goods-type-grid",//将l-grid层高强制自动
        columns: [{
            display: "登录名",
            name: "login_name",
            width: "15%"
        }, {
            display: "昵称",
            name: "nick_name",
            width: "15%"
        }, {	
            display: "性别",
            name: "quantity",
            width: "10%",
            render: function(items){
            	if(items.sex == 0){
            		return "男";
            	}else if(items.sex == 1){
            		return "女";
            	}else{
            		return "";
            	}
            }
        }, {	
            display: "角色",
            name: "type_name",
            width: "10%",
            render: function(items){
            	if(items.role_id == "ID000"){
            		return "管理员";
            	}else if(items.role_id == "ID001"){
            		return "商品管理员";
            	}else if(items.role_id == "ID002"){
            		return "网购用户";
            	}else if(items.role_id == "ID003"){
            		return "订单管理员";
            	}
            	return "错误的身份";
            }
        }, {	
            display: "email",
            name: "email",
            width: "15%"
        }, {	
            display: "地址",
            name: "address",
            width: "15%"
        }, {
            display: '操作',
            name: 'option',
            width: "20%",
            render: function(items){
            	var operation = "";
   	        	operation += "<a class='edit' id='" + items.user_id + "' href='${basePath}bsUser/addOrEditUserPage.do?userId="
   	        			+ items.user_id + "'>编辑</a> ";
   				operation += "<a href='javascript:void(0);' class='remove' id='" + items.user_id + "'>删除</a> ";
    	        return operation;	
            }
        }],
        root: 'pageObject',
        record: 'totalCount',
        url: '${basePath}bsUser/userGrid.do',
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
