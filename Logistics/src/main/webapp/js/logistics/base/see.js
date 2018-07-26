/*------------user.js中主方法，UserManager.jsp加载完毕后执行----------------------*/
$(function(){
	//初始化消息提示框
	initToastr();
	//初始化日期框
	initDatetimepicker();
	//远程加载组织结构数据到本地会话缓存
	loadOrganizationInfo();
	//初始化用户数据表格
	initUserTable();
	
	//2.为组合查询按钮绑定事件
	$("#combineQueryBtn").click(combineQuery);
	
	//3.为四个管理按钮绑定事件
	$("#addUserBtn").click(initAddUserModal);
	$("#updateUserBtn").click(initUpdateUserModal);
	$("#forzenUserBtn").click(frozenUser);
	$("#recoverUserBtn").click(recoverUser);
	
	//4.为添加用户模态框中提交按钮绑定事件
	$("#addUser").click(addUser);
	$("#updateUser").click(updateUser);
	
});
/*----------------------user.js中主方法结束------------------------------------------*/
/*----------------------0.加载组织结构数据到会话缓存中--------------------------------*/
var loadOrganizationInfo = function(){
	$.ajax({
		type:"post",
		url : '../../sys/user/queryOrganizationIdAndName.action',
		dataType:'json',
		success:function(data){
			//将读取的数据存储在本地缓存中(长久有效)
			//window.localStorage.setItem("orgIdAndName",JSON.stringify(data));
			//将读取的数据存储在会话缓存中(会话有效)
			window.sessionStorage.setItem("orgIdAndNames",JSON.stringify(data));
		}
	});
};
/*---------------------1.初始化用户数据表格 开始------------------------------------------------*/
var initUserTable = function(){
	$('#tb_user').bootstrapTable({
		url : '../../sys/user/pageQueryUser.action', // 请求后台的URL（*）
		method : 'get', // 请求方式（*）
		toolbar : '#toolbar', // 工具按钮用哪个容器
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortOrder : "asc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [5,10,20,50,100], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : true,
		showColumns : true, // 是否显示所有的列
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		// height : 350, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "userId", // 每一行的唯一标识，一般为主键列
		showToggle : true, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
	    columns : [ {
			checkbox : true
		}, {
			field : 'userName',
			title : '用户名'
		}, {
			field : 'phoneNumber',
			title : '电话'
		}, {
			field : 'gender',
			title : '性别'
		}, {
			field : 'organizationName',
			title : '工作单位'
		}, {
			field : 'birthdate',
			title : '出生日期',
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'useable',
			title : '是否禁用',
			formatter : function(value, row, index) {
				if (row.useable=="Y") {
					return '是';
				} else {
					return "<font color='#FF0000'>否</font>";
				}
			}
		}]		
	});
};
/*---------------------1.初始化用户数据表格 结束------------------------------------------------*/

/*---------------------2.组合查询 开始----------------------------------------------------------*/
var combineQuery = function(){
	var obj = {
			 url:"../../sys/user/pageQueryUserWithCriteria.action",
			 silent:true,
			 query:{
				 userName : $("#userName").val(),
				 phoneNumber:$("#phoneNumber").val(),
				 gender:$("#gender").val(),
				 orgName:$("#orgName").val(),
				 startBirthdate:$("#startBirthdate").val(),
				 endBirthdate:$("#endBirthdate").val(),
	             useable:$("#useable").val()
			 }
	 };
	//刷新数据表格  
	$('#tb_user').bootstrapTable('refresh',obj);
};
/*---------------------2.组合查询结束----------------------------------------------------------*/

/*---------------------3.初始化添加用户模态框开始----------------------------------------------------------*/
var initAddUserModal = function(){
	//清空添加用户表单中的数据
	$("#addUserForm input,textarea").val("");
	$("#addUserForm").data('bootstrapValidator', null); 
	
	//$("#addUserForm").bootstrapValidator("resetForm");
	
	//从本地会话缓存中获取组织结构字符串
	var str = window.sessionStorage.getItem("orgIdAndNames");
	//解析为JSON字符串
	var jsonObj = JSON.parse(str);
	var html="";
	//组织结构下拉框中添加数据
	for(var i=0;i<jsonObj.length;i++) {
		html = html+"<option id='"+jsonObj[i].organizationId+"'>"+jsonObj[i].organizationName+"</option>";
	}
	//清空上次加载的数据
	$("#addUserForm #organizationName").empty();
	//添加后台数据
	$("#addUserForm #organizationName").append(html);
	//刷新下拉框
	$(".selectpicker").selectpicker("refresh");
		
	//初始化用户添加表单校验
	validateAddUserForm();
	
	//显示添加用户模态框 
	$("#addUserModal").modal({
		 show:true, //显示模态框
		 keyboard:false, //按ESC键无法关闭模态框
		 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
	});

};
/*---------------------3.1初始化添加用户模态框结束----------------------------------------------------------*/

/*---------------------3.2添加用户开始----------------------------------------------------------*/
var addUser = function(e){
	//阻止表单默认提交
	e.preventDefault();
	//获取添加用户表单中填写的数据字符串
	var addUserFormData = $("#addUserForm").serialize();
	//数据拼接上组织结构Id
	addUserFormData = addUserFormData +"&organizationId=" + $("#organizationName option:selected").attr("id");
	
	$.ajax({
		type: "post", 
		url:'../../sys/user/addUser.action',
		data :addUserFormData,					   
		success:function(data) {
			if (data ==true) {		
				toastr.success('添加用户成功!');
			} else {
				toastr.error('添加用户失败!');
			}
			$("#addUserModal").modal("hide");
			$('#tb_user').bootstrapTable('refresh',{url:'../../sys/user/pageQueryUser.action'}); 
		}   
	});
	
};
/*---------------------3.2添加用户结束----------------------------------------------------------*/

/*---------------------4.1初始化 修改用户模态框开始----------------------------------------------------------*/
var initUpdateUserModal = function(){
	//获取用户选择的用户记录数组
	var selectUserData = $("#tb_user").bootstrapTable("getSelections");
	var useable = []; 
	var userId =[];
	for (var i = 0; i < selectUserData.length; i++) {
		userId[i] = selectUserData[i]['userId'];
		useable[i] = selectUserData[i]['useable'];
	}
	if (selectUserData.length === 0) {
		toastr.warning('请选择一行进行修改!');
	}else if(selectUserData.length>1){
		toastr.warning('一次只能修改一行!');
	}else if(useable[0] === "N"){
		toastr.warning('该用户已被禁用');
	}else{							  
		//显示修改用户模态框 
		$("#updateUserModal").modal({
			 show:true, //显示模态框
			 keyboard:false, //按ESC键无法关闭模态框
			 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
		});
		//从本地会话存储中获取组织结构信息字符串
		var str = window.sessionStorage.getItem("orgIdAndNames");
		//解析为json对象
		var jsonObj = JSON.parse(str);
		//准备组织结构下拉框的html文本
		var html="";
		for(var i=0;i<jsonObj.length;i++) {
			html = html+"<option id='"+jsonObj[i].organizationId+"'>"+jsonObj[i].organizationName+"</option>";
		}
		//清空上次加载的数据
		$("#updateUserForm #organizationName").empty();
		//添加下拉框的html文本
		$("#updateUserForm #organizationName").append(html);
		//刷新下拉框
		$(".selectpicker").selectpicker("refresh");
		//往修改用户表单的各组件中绑定用户选择的数据
		$("#updateUserForm #userName").val(selectUserData[0].userName);
		$("#updateUserForm #birthdate").val(selectUserData[0].birthdate);
		$("#updateUserForm #gender").val(selectUserData[0].gender);
		$("#updateUserForm #phoneNumber").val(selectUserData[0].phoneNumber);
		$("#updateUserForm #description").val(selectUserData[0].description);
	}
};
/*---------------------4.1初始化 修改用户模态框结束----------------------------------------------------------*/

/*---------------------4.2 修改用户开始----------------------------------------------------------*/
var updateUser = function(e){
	//阻止表单默认提交
	e.preventDefault();
	alert("请参考添加用户完成.......");
	
	
};
/*---------------------4.2 修改用户结束----------------------------------------------------------*/

/*---------------------5.1 冻结(逻辑删除)用户开始 --------------------------------------------------*/
var frozenUser = function(){
		var selectUserData = $('#tb_user').bootstrapTable('getSelections');
		//用户没有选择了数据，给出提示，退出！
		if(selectUserData.length==0){
			toastr.warning('请至少选择一行进行冻结!');
			return;
		}
		//用户选择了一行以上的数据
		if(selectUserData.length >1 ){
			//遍历，如果选择的用户中有已被禁用的，给出提示，退出!
			for(var i=0; i<selectUserData.length;i++){
				if(selectUserData[i].useable == "N"){
					toastr.warning('您选择的目标用户中有已被禁用的');
					return;
				} 
			}
			/*用户选择的数据中没有被禁用的，继续给出操作提示，询问是否冻结*/
			swal({  
					title: "操作提示",   //弹出框的title  
					text: "确定冻结吗？",  //弹出框里面的提示文本  
					type: "warning",    //弹出框类型  
					showCancelButton: true, //是否显示取消按钮  
					confirmButtonColor: "#DD6B55",//确定按钮颜色  
					cancelButtonText: "取消",//取消按钮文本  
					confirmButtonText: "是的，确定冻结！",//确定按钮上面的文档  
					closeOnConfirm: true
				  }, 
				  function () { //确定冻结后的功能代码
					  alert("后续功能请自己完成");
				      //发送异步请求冻结用户
					  /* $.ajax({
							type: "GET", 
							url:'../../deleteUserByUserId.action',
							data : {userIds:arrs},
							success:function(result) {
								if (result == "success") {
									toastr.success('冻结成功!');
								} else {
									toastr.error('冻结失败!');
								}
								$('#tb_user').bootstrapTable('refresh',{url:'../../sys/user/pageQueryUser.action'}); 
							}
					  	});*/
			});//swal()方法结束
		}
};
/*---------------------5.1 冻结用户结束--------------------------------------------------*/
/*---------------------6.1 解冻用户开始--------------------------------------------------*/
var recoverUser = function(){
	alert("参考冻结用户完成....");
}

/*---------------------6.1 解冻用户结束--------------------------------------------------*/



/*---------------------5.消息提示框初始化开始--------------------------------------------------*/
var initToastr = function(){
	toastr.options= {  
			closeButton: false,  //是否显示关闭按钮
			debug: false,   //是否为调试
			progressBar: true,  //是否显示进度条
			positionClass: "toast-top-center",  //在页面中显示的位置
			onclick: null,  //点击事件
			showDuration: "3000",  //显示动作时间
			hideDuration: "3000",  //隐藏动作时间
			timeOut: "3000",  //自动关闭超时时间
			extendedTimeOut: "1000",   //加长展现时间
			showEasing: "swing",  
			hideEasing: "linear",  
			preventDuplicates: true ,
			preventOpenDuplicates: true, //重复内容的提示框在开启时只出现一个
			showMethod: "fadeIn",  //显示的方式
			hideMethod: "fadeOut" //隐藏的方式
		};  
};
/*---------------------5.消息提示框初始化结束--------------------------------------------------*/


/*---------------------6.日期框初始化开始----------------------------------------------------*/
var initDatetimepicker = function(){
	$(".form_datetime").datetimepicker({
	    format: "yyyy-mm-dd",
	    autoclose: true,
	    todayBtn: true,
	    language:'zh-CN',
	    pickerPosition:"bottom-right",
	    minView:2,
	    todayHighlight:true
	  });
};
/*---------------------6.日期框初始化结束----------------------------------------*/




/*---------------------7.初始化添加用户表单校验器--------------------------------------------------------*/
var  validateAddUserForm = function(){
	$('#addUserForm').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons:{
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		submitButtons: 'button[type="submit"]',
	    fields: {
	    	userName: {
	            validators: {
	               notEmpty: {
	                 message: '用户名不能为空!',
	               },
	               regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                       regexp: /^[a-zA-Z0-9_\.]+$/,
                       message: '只能是数字和字母_.'
                   },
	               stringLength: {
                       min: 6,
                       max: 18,
                       message: '用户名长度必须在6到18位之间!'
                   },
                   threshold :  6 , //6个字符以上发送ajax请求，检测用户名是否存在
                   /*remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
		                       url: 'exist2.do',//验证地址
		                       message: '用户已存在',//提示消息
		                       delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
		                       type: 'POST'//请求方式
                   }*/
	            }
	        },
	        password: {
                 validators: {
                     notEmpty: {
                         message: '密码不能为空!'
                     },
                     stringLength: {
                         min: 6,
                         max: 18,
                         message: '密码长度必须在6到18位之间!'
                     },
                     different: {//不能和用户名相同
                         field: 'userName',//需要进行比较的input name值
                         message: '不能和用户名相同'
                     }
                 }
             },
	        birthdate: {
	            validators: {
	               notEmpty: {
	                  message: '出生日期不能为空!'
	               }
	             }
	        },
	        phoneNumber: {
                 validators: {
                     notEmpty: {
                         message: '电话不能为空!'
                     },
                     regexp: {
                         regexp: /^1(3|4|5|7|8)\d{9}$/,
                         message: '电话号码格式不正确!'
                     }
                 }
             }
	    }
    });
};
/*----------------清除表单数据-----------------------------------*/




