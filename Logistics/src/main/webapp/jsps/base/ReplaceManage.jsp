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
    <script type="text/javascript" src="js/logistics/base/replace.js"></script>
    
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
									<div class="input-group-addon">员工姓名</div>
									<input type="text" class="form-control condition" name="findReplaceByNameWithPage"
										id="replaceName" />
									<div class="input-group-addon search">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">开始日期</div>
									<div class="input-append date form_datetime" >
									    <input size="16" id="startTime" class="form-control" type="text" value="" readonly>
									    <span class="add-on"><i class="icon-remove"></i></span>
									    <span class="add-on"><i class="icon-calendar"></i></span>
									</div>
									<div class="input-group-addon removeTime">
										  <span class="glyphicon glyphicon-remove"></span>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">结束日期</div>
									<div class="input-append date form_datetime" >
									    <input size="16" id="endTime" class="form-control" type="text" value="" readonly>
									    <span class="add-on"><i class="icon-remove"></i></span>
									    <span class="add-on"><i class="icon-calendar"></i></span>
									</div>
									<div class="input-group-addon removeTime">
									   <span class="glyphicon glyphicon-remove"></span>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<button type="button" id="timeZoneQueryBtn"
									class="btn  btn-primary form-control">组合查询</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 按钮区 -->
		<div id="toolbar" class="btn-group">
			<button id="addReplaceBtn" type="button" class="btn btn-primary"
				data-toggle="modal" data-backdrop="static">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
			</button>
			<button id="updateReplaceBtn" type="button" class="btn btn-warning"
				data-toggle="modal" data-backdrop="static">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				修改
			</button>
			<button id="freezeReplaceBtn" type="button" class="btn btn-danger">
				<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
				冻结
			</button>
			<button id="activateReplaceBtn" type="button" class="btn btn-success">
				<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
				还原
			</button>
		</div>
		<input id="searchType" type="hidden" value="single"/>
		<table id="tb_departments"></table>
	</div>

	<!-- 操作职员模态框（Modal） -->
		<div class="modal fade" id="replaceModal" tabindex="-1" role="dialog"
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
					<form class="form-horizontal" id="replaceForm">
						<!-- 模态框体 -->
						<div class="modal-body">
							<input type="hidden" name="replaceId" id="replaceIdF">
							<div class="form-group">
								<label class="col-sm-3 control-label">替班人工号</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="rDispatcherNoF"
										name="rDispatcherNo" placeholder="请输入替班人工号">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">替班人姓名</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="replaceNameF"
										name="replaceName" placeholder="填入工号自动显示" readonly>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">被替班人工号</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="rdDispatcherNoF"
										name="rdDispatcherNo" placeholder="请输入被替班人姓名">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">被替班人姓名</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="replacedNameF"
										name="replacedName" placeholder="填入工号自动显示" readonly>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">开始时间</label>
								<div class="col-sm-8">
									<div class="input-append date form_datetime" >
									    <input size="16" name="startTime" id="startTimeF" class="form-control" type="text" value="" readonly>
									    <span class="add-on"><i class="icon-remove"></i></span>
									    <span class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">结束时间</label>
								<div class="col-sm-8">
									<div class="input-append date form_datetime" >
									    <input size="16" name="endTime" id="endTimeF" class="form-control" type="text" value="" readonly>
									    <span class="add-on"><i class="icon-remove"></i></span>
									    <span class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">组织结构名</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="organizationNameF"
										name="organizationName" placeholder="请输入组织结构名" readonly>
								</div>
							</div>
							<input type="hidden" id="organizationIdF" name="organizationId">
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
				<div id="wrapper" style="padding-left:20%;padding-right:20%">
					<div class="progress progress-striped active" >
				        <div id="prog" class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width:0%;min-width: 3em;">
				            <span id="proglabel">正在修改...</span>
				        </div>
				    </div>    
			    </div>
			</div>
		</div>
</body>
</html>