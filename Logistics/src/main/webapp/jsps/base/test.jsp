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
	$().ready(function() {
		// 模拟进度条：百分数增加，0-30时为红色，30-60为黄色，60-90为蓝色，>90为绿色
		var value = 0;
		setInterval(function(e){
			if (value != 100) {
				value = parseInt(value) + 1;
				$("#prog").css("width", value + "%").text(value + "%");
				if (value>=0 && value<=30) {
					$("#prog").addClass("progress-bar-danger");
			    } else if (value>=30 && value <=60) {
			    	$("#prog").removeClass("progress-bar-danger");
			        $("#prog").addClass("progress-bar-warning");
			    } else if (value>=60 && value <=90) {
			        $("#prog").removeClass("progress-bar-warning");
			        $("#prog").addClass("progress-bar-info");
			    } else if(value >= 90 && value<100) {
			        $("#prog").removeClass("progress-bar-info");
			        $("#prog").addClass("progress-bar-success");    
			    }
			}
		}, 50);
	});
});
</script>

<body>
	<div id="wrapper" style="padding-left: 20%;padding-right:20%">
		<div class="progress progress-striped active" >
	        <div id="prog" class="progress-bar" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100" style="width:0%;min-width: 3em;">
	            <span id="proglabel">正在修改...</span>
	        </div>
	    </div>    
    </div> 
</body>
</html>