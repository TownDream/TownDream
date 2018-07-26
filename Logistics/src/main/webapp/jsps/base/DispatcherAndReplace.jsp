<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<script type="text/javascript">
$(function(){
	var height=$(window).height()-$("#myTab").height();
	var iframes = $("iframe");
	for(var i=0;i<iframes.length;i++){
		iframes.eq(i).css("height",height);
	}
});
</script>
<body>
	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#home" data-toggle="tab">职员表</a>
		</li>
		<li><a href="#ios" data-toggle="tab">替班表</a></li>
	</ul>
	
	
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="home">
			<iframe   frameborder=0  border=0   width=100%  src="jsps/base/DispatcherManage.jsp"></iframe>  
		</div>
		<div class="tab-pane fade" id="ios">
			<iframe   frameborder=0  border=0   width=100%  src="jsps/base/ReplaceManage.jsp"></iframe>  
		</div>
	</div>

</body>
</html>