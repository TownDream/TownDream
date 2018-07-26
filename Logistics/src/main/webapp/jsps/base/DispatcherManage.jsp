<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <!-- Jquery组件引用 -->
    <script type="text/javascript" src="js/other/jquery-3.1.1.min.js"></script>
	
	<!-- bootstrap组件引用 -->
	<script type="text/javascript" src="js/other/bootstrap/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="js/other/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="js/other/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<!-- bootstrap table组件以及中文包的引用 -->
	<script type="text/javascript" src="js/other/bootstrap-table/bootstrap-table.js"></script>
    <link rel="stylesheet" type="text/css" href="js/other/bootstrap-table/bootstrap-table.css" />
    <link rel="stylesheet" type="text/css" href="js/other/sweetalert/sweetalert.css" />
    <link rel="stylesheet" type="text/css" href="js/other/toastr/toastr.css" />
    <link rel="stylesheet" type="text/css" href="js/other/toastr/toastr.min.css" />
    <script type="text/javascript" src="js/other/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" src="js/other/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="js/other/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="js/other/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" src="js/other/toastr/toastr.min.js"></script>
    <link rel="stylesheet" type="text/css" href="js/other/bootstrap-validator/bootstrapValidator.min.css"/>
    <script type="text/javascript" src="js/other/bootstrap-validator/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="js/logistics/base/dispatcher.js"></script>
    
    
<style type="text/css">
 
.row{
	margin:20px 0;
} 

.condition{
   text-align: center;
}

#combineQueryDiv{
	margin-left:86%;
}

#wrapper{
	margin-top:50%;
}

</style>
<script type="text/javascript">
$(function(){
	
	//初始化Table 
	var oTableInit = function() {
		$('#tb_departments').bootstrapTable({
			url :"/Logistics/base/dispatcher/pageQueryDispatcher.action", //请求后台的URL（*） 
			method : 'post', //请求方式（*） 
			toolbar : '#toolbar', //工具按钮用哪个容器 
			striped : true, //是否显示行间隔色 
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*） 
			pagination : true, //是否显示分页（*） 
			sortable : false, //是否启用排序 
			sortOrder : "asc", //排序方式 
			queryParams : oTableInit.queryParams,//传递参数（*）
			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*） 
			pageNumber : 1, //初始化加载第一页，默认第一页 
			pageSize : 5, //每页的记录行数（*） 
			pageList : [ 5, 10, 15, 20 ], //可供选择的每页的行数（*） 
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大 
			strictSearch : true,
			showColumns : true, //是否显示所有的列 
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行 
			//height : 350, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度 
			uniqueId : "dispatcherId", //每一行的唯一标识，一般为主键列 
			showToggle : true, //是否显示详细视图和列表视图的切换按钮 
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			contentType : "application/x-www-form-urlencoded",//指定传输的数据格式
		    columns : [ 
		    	{field:'·',title:'选择',checkbox:true},    
		        {field:'dispatcherId',title:'取派员ID',width:150,fixed:true},    
		        {field:'dispatcherNo',title:'员工工号',width:150,align:"center"},
		        {field:'dispatcherName',title:'员工姓名',width:200,align:"center"},
		        {field:'phoneNumber',title:'手机号码',width:250,align:"center"},
		        {field:'dispatcherTypeId',title:'取派员类型ID',width:150,align:"center"},
		        {field:'dispatcherType',title:'职员类型',width:150,align:"center"},
		        {field:'checkPwd',title:'查台密码',width:100,align:"center"},
		        {field:'pickStandardId',title:'取派标准ID',width:50,align:"center"},
		        {field:'busId',title:'驾驶班车ID',width:50,align:"center"},
		        {field:'busType',title:'驾驶车型',width:100,align:"center"},
		        {field:'pickStandardName',title:'取派标准',width:150,align:"center"},
		        {field:'busNumber',title:'车牌号码',width:100,align:"center"},
		        {field:'organizationId',title:'组织结构ID',width:50,align:"center"},
		        {field:'organizationName',title:'组织结构名',width:150,align:"center"},
		        {field:'usePDA',title:'使用PDA',width:50,align:"center",
		        	formatter:function(value){
		        		return value == "Y" ? "是" : "否";
		        	}
		        },
		        {field:'useable',title:'是否可用',width:50,align:"center",
		        	formatter:function(value){
		        		return value == "Y" ? "是" : "否";
		        	}
		        },
		        {field:'description',title:'备注',width:50,align:"center"}
		    ],
	        queryParams : function (params) {
	              //这里的键的名字和控制器的变量名必须一至，这边改动，控制器也需要改成一样的
	          	var pageSize=params.limit;
	          	var pageIndex=(params.offset / params.limit) + 1;
	          	var searchType = $("#searchType").attr("value");
	          	if(searchType=="single"){
	          		 var temp = {   
	   	                  pageSize: params.limit,                         //页面大小
	   	                  pageIndex: (params.offset / params.limit) + 1,  
	   	                  sort: params.sort,      //排序列名  
	   	                  sortOrder: params.order, //排位命令（desc，asc） 
	   	              };
	          	}else if(searchType=="combined"){
	          		 var temp = {   
	   	                  pageSize: params.limit,                         //页面大小
	   	                  pageIndex: (params.offset / params.limit) + 1,  
	   	                  sort: params.sort,      //排序列名  
	   	                  sortOrder: params.order, //排位命令（desc，asc） 
	   	                  dispatcherNo : $("#dispatcherNo").val(),
	   					  dispatcherName:$("#dispatcherName").val(),
	   					  phoneNumber:$("#phoneNumber").val(),
	   					  busNumber:$("#busNumber").val(),
	   					  dispatcherType:$("#dispatcherType").val(),
	   					  pickStandardName:$("#pickStandardName").val(),
	   					  busType:$("#busType").val(),
	   			          useable:$("#useable").val(),
	   			          usePDA:$("#usePDA").val(),
	   	              };
	          	}
	              return temp;
	        },
		});
	}; //得到查询的参数
	
	
	//-------------------------初始化表单-----------------------------
	oTableInit();
	//隐藏列
	$('#tb_departments').bootstrapTable('hideColumn', 'dispatcherId');
	$('#tb_departments').bootstrapTable('hideColumn', 'dispatcherTypeId');
	$('#tb_departments').bootstrapTable('hideColumn', 'pickStandardId');
	$('#tb_departments').bootstrapTable('hideColumn', 'busId');
	$('#tb_departments').bootstrapTable('hideColumn', 'organizationId');
});
</script>
</head>
<body>
	<div class="panel-body" style="padding-bottom: 0px;">
		<!-- 条件组合查询区 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseOne"> <span
					class="glyphicon glyphicon-triangle-bottom"></span> 快速检索
				</a>
			</div>
			<div class="panel-collapse collapse in" id="collapseOne">
				<form id="formSearch" class="form-horizontal">
					<div class="container-fludid">
						<div class="row bottom-margin">
						
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">员工编号</div>
									<input type="text" class="form-control condition" name="dispatcherNo"
										id="dispatcherNo" />
									<div class="input-group-addon search" >
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">员工姓名</div>
									<input type="text" class="form-control condition" name="dispatcherName"
										id="dispatcherName" />
									<div class="input-group-addon search" >
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">手机号码</div>
									<!-- 这里用缩写，避免和下边添加用户的组织下拉框框同名 -->
									<input type="text" class="form-control condition" name="phoneNumber"
										id="phoneNumber" />
									<div class="input-group-addon search">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">车牌号码</div>
									<!-- 这里用缩写，避免和下边添加用户的组织下拉框框同名 -->
									<input type="text" class="form-control condition" name="busNumber"
										id="busNumber" />
									<div class="input-group-addon search" >
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
						</div>
						
						<div class="row bottom-margin">
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">职员类型</div>
									<select id="dispatcherType" name="dispatcherType" class="selectpicker show-tick form-control condition">
									</select>
									<div class="input-group-addon search">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">取派标准</div>
									<select id="pickStandardName" name="findDispatcherByPickStandardNameWithPage"
										class="selectpicker show-tick form-control condition">
									</select>
									<div class="input-group-addon search">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">是否可用</div>
									<select id="useable" name="useable"
										class="selectpicker show-tick form-control condition">
										<option></option>
										<option value="Y">是</option>
										<option value="N">否</option>
									</select>
									<div class="input-group-addon search">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">使用PDA</div>
									<select id="usePDA" name="usePDA"
										class="selectpicker show-tick form-control condition">
										<option></option>
										<option value="Y">是</option>
										<option value="N">否</option>
									</select>
									<div class="input-group-addon search">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
						</div>

						<div class="row"  id="combineQueryDiv">
							<div class="col-md-9">
								<button type="button" id="combineQueryBtn"
									class="btn  btn-primary form-control">组合查询</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 按钮区 -->
		<div id="toolbar" class="btn-group">
			<button id="addDispatcherBtn" type="button" class="btn btn-primary"
				data-toggle="modal" data-backdrop="static">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
			</button>
			<button id="updateDispatcherBtn" type="button" class="btn btn-warning"
				data-toggle="modal" data-backdrop="static">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				修改
			</button>
			<button id="freezeDispatcherBtn" type="button" class="btn btn-danger">
				<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
				冻结
			</button>
			<button id="activateDispatcherBtn" type="button" class="btn btn-success">
				<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
				还原
			</button>
		</div>
		<input id="searchType" type="hidden" value="single"/>
		<table id="tb_departments"></table>
	</div>

	<!-- 操作职员模态框（Modal） -->
		<div class="modal fade" id="dispatcherModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- 模态框头 -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title modalTitel"></h4>
					</div>
					<form class="form-horizontal" id="dispatcherForm">
						<!-- 模态框体 -->
						<input type="hidden" class="form-control" id="dispatcherIdF" name="dispatcherId">
						<div class="modal-body">
							<div class="form-group">
								<label class="col-sm-3 control-label">员工工号</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="dispatcherNoF" name="dispatcherNo" placeholder="完成其他信息填写后点击提交自动生成" readonly="readonly">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">员工姓名</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="dispatcherNameF"
										name="dispatcherName" placeholder="请输入员工姓名">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">手机号码</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="phoneNumberF"
										name="phoneNumber" placeholder="请输入基础档案名">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">取派员类型</label>
								<div class="col-sm-8">
									<select id="dispatcherTypeF" name="dispatcherType"
										class="selectpicker show-tick form-control">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">查台密码</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="checkPwdF"
										name="checkPwd" placeholder="请输入查台密码">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">驾驶车型</label>
								<div class="col-sm-8">
									<select id="busTypeF" name="busType"
										class="selectpicker show-tick form-control">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label"
									for="scale">取派标准</label>
								<div class="col-sm-8">
									<select id="pickStandardNameF" name="pickStandardName"
										class="selectpicker show-tick form-control">
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">车牌号码</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="busNumberF"
										name="busNumber" placeholder="请输入车牌号码">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">组织结构名</label>
								<div class="col-sm-8">
									<select id="organizationNameF" name="organizationName"
										class="selectpicker show-tick form-control">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"
									for="pickStandardName">使用PDA</label>
								<div class="col-sm-8">
									<select id="usePDAF" name="usePDA"
										class="selectpicker show-tick form-control">
										<option>请选择</option>
										<option value="Y">是</option>
										<option value="N">否</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label" for="useable">是否可用</label>
								<div class="col-sm-8">
									<select id="useableF" name="useable"
										class="selectpicker show-tick form-control">
										<option>请选择</option>
										<option value="Y">是</option>
										<option value="N">否</option>
									</select>
								</div>
							</div>
						</div>
						<!-- 模态框尾 -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="submit">提交</button>
							<button type="button" class="btn btn-default4"
								data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="progressModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 进度条 -->
				<div id="wrapper" style="padding-left: 20%;padding-right:20%">
					<div class="progress progress-striped active" >
				        <div id="prog" class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width:0%;min-width: 3em;">
				            <span id="proglabel">正在提交...</span>
				        </div>
				    </div>    
			    </div>
			</div>
		</div>
</body>
</html>