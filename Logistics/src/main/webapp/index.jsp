<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 这里用了shiro框架<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%> --%>
<%
	String userName = request.getParameter("userName");
	request.getSession().setAttribute("userName", userName);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<!-- Jquery组件引用 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/jquery-3.1.1.min.js"></script>

<!-- bootstrap组件引用 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<!-- bootstrap table组件以及中文包的引用 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap-table/bootstrap-table.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/bootstrap-table/bootstrap-table.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/sweetalert/sweetalert.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/toastr/toastr.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/toastr/toastr.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/sweetalert/sweetalert.min.js"></script>
<%--     <script type="text/javascript" src="${pageContext.request.contextPath}/js/other/toastr/toastr.js.map"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/toastr/toastr.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/sys/user.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/bootstrap-validator/bootstrapValidator.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap-validator/bootstrapValidator.min.js"></script>
<!--  -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/other/bootstrap-select/css/bootstrap-select.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/other/bootstrap-select/js/bootstrap-select.min.js"></script>


<!-- 导入user.js   -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ssm/sys/user.js"></script>

<style type="text/css">
#userName_prompt {
	text-align: center;
	color: red;
}

#password_prompt {
	text-align: center;
	color: red;
}

#salary_prompt {
	text-align: center;
	color: red;
}

#birthday_prompt {
	text-align: center;
	color: red;
}

#telephone_prompt {
	text-align: center;
	color: red;
}

#oldPassword_prompt {
	text-align: center;
	color: red;
}

#newPassword_prompt {
	text-align: center;
	color: red;
}

#userName {
	text-align: center;
	color: red;
	display: none;
}

.bottom-margin {
	margin-bottom: 10px;
	margin-top: 10px;
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
									<div class="input-group-addon">用户名</div>
									<input type="text" class="form-control" name="userName"
										id="userName" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">电话</div>
									<input type="text" class="form-control" name="phoneNumber"
										id="phoneNumber" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>

							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">性别</div>
									<select id="gender" name="gender"
										class="selectpicker show-tick form-control">
										<option></option>
										<option value="男">男</option>
										<option value="女">女</option>
									</select>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">工作单位</div>
									<!-- 这里用缩写，避免和下边添加用户的组织下拉框框同名 -->
									<input type="text" class="form-control" name="orgName"
										id="orgName" />
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
						</div>

						<div class="row bottom-margin">
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">出生日期</div>
									<input type="text" class="form-control form_datetime"
										id="startBirthdate" name="startBirthdate">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">出生日期</div>
									<input type="text" class="form-control form_datetime"
										id="endBirthdate" name="endBirthdate">
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<div class="input-group-addon">是否可用</div>
									<select id="useable" name="useable"
										class="selectpicker show-tick form-control">
										<option></option>
										<option value="Y">是</option>
										<option value="N">否</option>
									</select>
									<div class="input-group-addon">
										<span class="glyphicon glyphicon glyphicon-search"></span>
									</div>
								</div>
							</div>
							<div class="col-md-2">
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
			<!-- <shiro:hasPermission name="新增用户"> shiro标签，判断是否有权限，下同-->
			<button id="addUserBtn" type="button" class="btn btn-primary"
				data-toggle="modal" data-backdrop="static">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
			</button>
			<!-- </shiro:hasPermission> -->
			<!-- <shiro:hasPermission name="修改用户"> -->
			<button id="updateUserBtn" type="button" class="btn btn-warning"
				data-toggle="modal" data-backdrop="static">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				修改
			</button>
			<!-- </shiro:hasPermission> -->
			<!-- <shiro:hasPermission name="作废用户"> -->
			<button id="forzenUserBtn" type="button" class="btn btn-danger">
				<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
				冻结
			</button>
			<!-- </shiro:hasPermission> -->
			<!-- <shiro:hasPermission name="还原用户"> -->
			<button id="recoverUserBtn" type="button" class="btn btn-success">
				<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
				还原
			</button>
			<!-- </shiro:hasPermission> -->
		</div>

		<!-- 数据显示区 -->
		<table id="tb_user"></table>
	</div>

	<!-- 添加模态框（Modal） -->
	<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 模态框头 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加用户</h4>
				</div>
				<form class="form-horizontal" id="addUserForm">
					<!-- 模态框体 -->
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="userName"
									name="userName" placeholder="请输入用户名">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password"
									placeholder="请输入密码" name="password">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">出生日期</label>
							<div class="col-sm-10">
								<input type="text" class="form-control form_datetime"
									placeholder="请选择出生日期" name="birthdate" id="birthdate">
							</div>
						</div>

						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label" for="gender">性别</label>
							<div class="col-sm-10">
								<select id="gender" name="gender"
									class="selectpicker show-tick form-control">
									<option>男</option>
									<option>女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">组织名</label>
							<div class="col-sm-10">
								<select id="organizationName" name="organizationName"
									class="selectpicker show-tick form-control">
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="phoneNumber"
									placeholder="请输入电话号码" name="phoneNumber">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="description"
									name="description" rows="3"></textarea>
							</div>
						</div>

					</div>
					<!-- 模态框尾 -->
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="addUser">添加用户</button>
						<button type="button" class="btn btn-default4"
							data-dismiss="modal">关闭</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 修改用户模态框（Modal） -->
	<div class="modal fade" id="updateUserModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 修改用户模态框头部 -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改用户</h4>
				</div>
				
				<form class="form-horizontal" id="updateUserForm">
					<!-- 模态框体 -->
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="userName"
									name="userName" disabled="disabled">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password"
									placeholder="请输入新密码" name="password">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">出生日期</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="birthdate"
									id="birthdate" disabled="disabled">
							</div>
						</div>

						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label" for="gender">性别</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="gender"
									disabled="disabled" name="gender">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">组织名</label>
							<div class="col-sm-10">
								<select id="organizationName" name="organizationName"
									class="selectpicker show-tick form-control">
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="phoneNumber"
									placeholder="请输入电话号码" name="phoneNumber">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="description"
									name="description" rows="3"></textarea>
							</div>
						</div>

					</div>
					<!-- 模态框尾 -->
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="updateUser">修改用户</button>
						<button type="button" class="btn btn-default4"
							data-dismiss="modal">关闭</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>