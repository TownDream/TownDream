$(function(){

	//输入框后的搜索框被点击
	$(".search").click(function(){
		$("#searchType").attr("value","single");
		var search = $(this).parent().find("input").eq(0);
		var condition = search.val();
		if(condition==null){
			condition = $(this).parent().find("select").eq(0).val();
			var action = $(this).parent().find("select").eq(0).attr("name");
			conditionName = $(this).parent().find("select").eq(0).attr("id");
		}else{
			var conditionName = search.attr("id");
		}
		if(condition == "" || condition==null){
			toastr.warning('条件不能为空！');
		}else{
			$('#tb_departments').bootstrapTable('refresh',
					{url:'/Logistics/base/dispatcher/findDispatcherByCriteriaWithPage.action?'+conditionName+'='+condition});
		}
	});

	//组合查询
	$("#combineQueryBtn").click(function(){
		$("#searchType").attr("value","combined");
		var obj = {url:"/Logistics/base/dispatcher/findDispatcherByCriteriaWithPage.action"};
		//刷新数据表格  
		$('#tb_departments').bootstrapTable('refresh',obj);
	});

	//---------------------------------按钮的点击---------------------------------------
	//添加按钮
	$("#addDispatcherBtn").click(function(){
		// 清空之前的校验
		$("#dispatcherForm").data('bootstrapValidator').destroy();
	    $('#dispatcherForm').data('bootstrapValidator', null);
	    // 设置添加职员校验方法
	    validateForAddForm();
		addDispatcher();
	});

	//编辑按钮
	$("#updateDispatcherBtn").click(function(){
		// 清空之前的校验
		$("#dispatcherForm").data('bootstrapValidator').destroy();
	    $('#dispatcherForm').data('bootstrapValidator', null);
	    // 设置修改职员校验方法
	    validateForUpdateForm();
		updateDispatcher();
	});

	//冻结按钮
	$("#freezeDispatcherBtn").click(function(){
		action='freezeDispatcher';
		freezeOrActivateDispatcher(action);
	});

	//激活按钮
	$("#activateDispatcherBtn").click(function(){
		action='activateDispatcher';
		freezeOrActivateDispatcher(action);
	});

	var addDispatcher = function(){
		//更改表标题
		$(".modalTitel").text("添加职员");
		$(".modalTitel").attr("value","addDispatcher");
		//清空表中数据
		$("#dispatcherForm")[0].reset();
		$("#dispatcherNoF").attr("value","");
		//显示添加基础档案模态框 
		$("#dispatcherModal").modal({
			 show:true, //显示模态框
			 keyboard:false, //按ESC键无法关闭模态框
			 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
		});
	};

	//编辑按钮
	var updateDispatcher = function(){
		var value = $('#tb_departments').bootstrapTable("getSelections");
		if(value.length==1){
			//更改表标题
			$(".modalTitel").text("修改职员");
			$(".modalTitel").attr("value","updateDispatcher");
			//使无需修改的工号失效
			$("#dispatcherNoF").attr("readonly","readonly");
			//清空表中数据
			$("#dispatcherForm")[0].reset();
			$("#dispatcherIdF").val(value[0].dispatcherId);
			$("#dispatcherNoF").val(value[0].dispatcherNo);
			$("#dispatcherNameF").val(value[0].dispatcherName);
			$("#phoneNumberF").val(value[0].phoneNumber);
			$("#dispatcherTypeF").val(value[0].dispatcherType);
			$("#checkPwdF").val(value[0].checkPwd);
			$("#busTypeF").val(value[0].busType);
			$("#pickStandardNameF").val(value[0].pickStandardName);
			$("#busNumberF").val(value[0].busNumber);
			$("#organizationNameF").val(value[0].organizationName);
			$("#usePDAF").val(value[0].usePDA);
			$("#useableF").val(value[0].useable);
			//显示添加基础档案模态框 
			$("#dispatcherModal").modal({
				 show:true, //显示模态框
				 keyboard:false, //按ESC键无法关闭模态框
				 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
			});
		}else{
			toastr.warning('请选择一行进行修改!');
		}
	};

	// 添加校验
	var  validateForAddForm = function(){
		$('#dispatcherForm').bootstrapValidator({
			message: 'This value is not valid',
			submitButtons: '#submit',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
			feedbackIcons:{
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
	        fields: {
	            dispatcherName:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 2,
	                        max:30,
	                        message: '请输入真实姓名'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[\u4E00-\u9FA5A-Za-z]+$/,
	                        message: '只能输入中文英文'
	                    },
	            	}
	            },
	            phoneNumber:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
	                        message: '请输入正确的手机号'
	                    },
	                    remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
	                        url: '/Logistics/base/dispatcher/existDispatcherByPhoneNumber.action',
	                        message: '手机号已存在',
	                        type:'post',
	                        delay :  2000
	                    },
	            	}
	            },
	            dispatcherType:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	}
	            },
	            checkPwd:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 6,
	                        max:16,
	                        message: '请输入6-16位'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[a-zA-Z\d_]{6,}$/,
	                        message: '包含特殊字符'
	                    },
	            	}
	            },
	            busType:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	}
	            },
	            pickStandardName:{
	            	validators: {
	            		stringLength: {//检测长度
	                        min: 6,
	                        message: '请选择取派标准'
	                    },
	            	}
	            },
	            busNumber:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 6,
	                        max: 7,
	                        message: '长度必须在6-7之间'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,
	                        message: '请输入真实的车牌号'
	                    },
	                    remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
	                        url: '/Logistics/base/dispatcher/existDispatcherByBusNumber.action',
	                        message: '该车号已存在',
	                        type:'post',
	                        delay :  2000
	                    },
	            	}
	            },
	            organizationName:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	}
	            },
	            usePDA:{
	            	validators: {
	            		stringLength: {//检测长度
	                        max: 2,
	                        message: '请选择是否使用PDA'
	                    },
	            	}
	            },
	            useable:{
	            	validators: {
	            		stringLength: {//检测长度
	                        max: 2,
	                        message: '请选择是否可用'
	                    },
	            	}
	            },
	        }
	     
	    });
	};

	//修改校验
	var validateForUpdateForm = function(){
		$('#dispatcherForm').bootstrapValidator({
			message: 'This value is not valid',
			submitButtons: '#submit',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
			feedbackIcons:{
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
	        fields: {
	            dispatcherName:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 2,
	                        max:30,
	                        message: '请输入真实姓名'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[\u4E00-\u9FA5A-Za-z]+$/,
	                        message: '只能输入中文英文'
	                    },
	            	}
	            },
	            phoneNumber:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
	                        message: '请输入正确的手机号'
	                    },
	            	}
	            },
	            dispatcherType:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	}
	            },
	            checkPwd:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 6,
	                        max:16,
	                        message: '请输入6-16位'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[a-zA-Z\d_]{6,}$/,
	                        message: '包含特殊字符'
	                    },
	            	}
	            },
	            busType:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	}
	            },
	            pickStandardName:{
	            	validators: {
	            		stringLength: {//检测长度
	                        min: 6,
	                        message: '请选择取派标准'
	                    },
	            	}
	            },
	            busNumber:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 6,
	                        max: 7,
	                        message: '长度必须在6-7之间'
	                    },
	                    regexp: {//正则验证
	                        regexp: /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,
	                        message: '请输入真实的车牌号'
	                    },
	            	}
	            },
	            organizationName:{
	            	validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	}
	            },
	            usePDA:{
	            	validators: {
	            		stringLength: {//检测长度
	                        max: 2,
	                        message: '请选择是否使用PDA'
	                    },
	            	}
	            },
	            useable:{
	            	validators: {
	            		stringLength: {//检测长度
	                        max: 2,
	                        message: '请选择是否可用'
	                    },
	            	}
	            },
	        }
	    });
	};

	//
	validateForAddForm();

	//冻结激活方法
	var freezeOrActivateDispatcher = function(action){
		var value = $('#tb_departments').bootstrapTable("getSelections");
		var dispatcherIds = new Array();
		if(action.indexOf('freeze')>=0){ // 如果是冻结操作
			
			for(var i=0;i<value.length;i++){ // 遍历被选中的职员的职员ID
				if(value[i].useable=='Y'){	// 判断是否为可用，可用就拼接，不可用就不拼接
					dispatcherIds.push(value[i].dispatcherId);
				}
			}
		}else{ // 如果是激活操作
			for(var i=0;i<value.length;i++){ // 遍历被选中的职员的职员ID
				if(value[i].useable=='N'){ // 判断是否为可用，不可用就拼接，可用就不拼接
					dispatcherIds.push(value[i].dispatcherId);
				}
			}
		}
		if(value.length==0){ // 判断是否有被选中的对应职员 
			toastr.warning('请选择至少一行进行修改!');
		}else if(dispatcherIds.length==0){
			toastr.warning('请选择正确行做正确操作!');
		}else{
			$.ajax({
				type: "post", 
				url:'/Logistics/base/dispatcher/'+action+'.action',
				data:{"dispatcherIds":dispatcherIds},		
				dataType : "json",
				success:function(data) {
					if (data > 0) {		
						toastr.success('成功修改'+data+'名职员!');
					} else {
						toastr.error('修改失败!');
					}
					$('#tb_departments').bootstrapTable('refresh'); 
				}   
			});
		}
	};
	

	// 自动生辰工号
	var generateDispatcherNo = function(){
		var dispatcherData = $("#dispatcherForm").serialize();
		// 将组织结构ID加入表单中
		dispatcherData = dispatcherData +"&organizationId=" + $("#organizationNameF option:selected").attr("id")
		+"&pickStandardId=" + $("#pickStandardNameF option:selected").attr("id")
		+"&dispatcherTypeId=" + $("#dispatcherTypeF option:selected").attr("id");
		
		$.ajax({
			type: "post", 
			url:'/Logistics/base/dispatcher/generateDispatcherNo.action',
			data:dispatcherData,					   
			success:function(data) {
				$("#dispatcherNoF").val(data);
			}   
		});
	};
	
	// 提交方法
	var submit = function(){
		var action = $(".modalTitel").attr("value");
		//将工号的数据填入表单中
		var dispatcherData = $("#dispatcherForm").serialize();
		dispatcherData = dispatcherData +"&organizationId=" + $("#organizationNameF option:selected").attr("id")
		+"&pickStandardId=" + $("#pickStandardNameF option:selected").attr("id")
		+"&dispatcherTypeId=" + $("#dispatcherTypeF option:selected").attr("id");
		
		$.ajax({
			type: "post", 
			url:'/Logistics/base/dispatcher/'+action+'.action',
			data:dispatcherData,					   
			success:function(data) {
				if(data=="Y"){
					toastr.success('修改成功!');
					$('#tb_departments').bootstrapTable('refresh'); //刷新表单
					$("#dispatcherModal").modal("hide"); //关闭modal
				}else{
					toastr.error('修改失败!');
					$('#tb_departments').bootstrapTable('refresh'); //刷新表单
					$("#dispatcherModal").modal("hide"); //关闭modal
				}
			}   
		});
	};
	
//-----------------------------------表单提交方法------------------------------------------
	// 修改时，等待的进度条
	var updateProgress = function(speed){
		$("#dispatcherModal").modal("hide"); //关闭modal
		//显示进度条模态框 
		$("#progressModal").modal({
			 show:true, //显示模态框
			 keyboard:false, //按ESC键无法关闭模态框
			 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
		});
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
			}else{
				$("#progressModal").modal("hide"); //关闭modal
			}
		}, speed);
	};

	// 点击提交按钮
	$("#submit").click(function(){
		$("#dispatcherForm").bootstrapValidator('validate');//提交验证
		validResult = $("#dispatcherForm").data('bootstrapValidator').isValid();
	    if (validResult) {//获取验证结果，如果成功，执行下面代码
			// 调用生成工号的方法
			generateDispatcherNo();
			// 调用出进度条
			updateProgress(30);
			// 三秒后提交表单
			setTimeout(function(){// 定时，在3秒后获取校验结果
				submit();
			},3050);
	    }
	});

	//--------------------------下拉列表初始化数据方法------------------------------------------
	// 存储组织结构数据
	var loadOrganizationInfo = function(){
		var url_at = "/Logistics/sys/organization/findAllOrganizationNameWithUseable.action"; 
		 $.ajax({ 
	          url:url_at , 
	          type:"post" , 
	          dataType:"json",
	          success:function(data){
	        	  window.sessionStorage.setItem("orgNameAndOrgId",JSON.stringify(data));
	          } 
	     });
	};

	// 存储取派标准数据
	var loadPickStandardInfo = function(){
		var url_at = "/Logistics/base/pickStandard/findAllPSNameAndPSIdWithUseable.action"; 
		$.ajax({ 
	          url:url_at , 
	          type:"post" , 
	          dataType:"json",
	          success:function(data){
	        	  window.sessionStorage.setItem("pSNameAndPSId",JSON.stringify(data));
	          }
	    });
	};
	
	// 存储驾驶车型数据
	var loadBusTypeInfo = function(){
		var url_at = "/Logistics/base/assistantDocument/findAllBusTypeAndIdWithUseable.action"; 
		 $.ajax({ 
	          url:url_at , 
	          type:"post" , 
	          dataType:"json",
	          success:function(data){
	        	  window.sessionStorage.setItem("busTypeAndId",JSON.stringify(data));
	          } 
	     });
	};
	
	// 取派员类型数据
	var loadDispatcherTypeInfo = function(){
		var url_at = "/Logistics/base/assistantDocument/findAllDispatcherTypeAndIdWithUseable.action"; 
		 $.ajax({ 
	          url:url_at , 
	          type:"post" , 
	          dataType:"json",
	          success:function(data){
	        	  window.sessionStorage.setItem("dispTypeAndId",JSON.stringify(data));
	          } 
	     });
	};
	
	// 初始化数据方法
	var infoPage = function(){
		//从本地会话缓存中获取驾驶车型
		var bst = window.sessionStorage.getItem("busTypeAndId");
		//解析为JSON字符串
		var bstJson = JSON.parse(bst);
		//驾驶车型下拉框中添加数据
		$("#busTypeF").append("<option></option>");
		for(var i=0;i<bstJson.length;i++) {
			 $("#busTypeF").append("<option id='"+bstJson[i].assistantDocumentId+"'>"+bstJson[i].assistantDocumentName+"</option>"); 
		}
		
		//从本地会话缓存中获取取派员类型数据
		var dst = window.sessionStorage.getItem("dispTypeAndId");
		//解析为JSON字符串
		var dstJson = JSON.parse(dst);
		//驾驶车型下拉框中添加数据
		$("#dispatcherType").append("<option></option>");
		$("#dispatcherTypeF").append("<option></option>");
	
		for(var i=0;i<dstJson.length;i++) {
			$("#dispatcherType").append("<option id='"+dstJson[i].assistantDocumentId+"'>"+dstJson[i].assistantDocumentName+"</option>");
			 $("#dispatcherTypeF").append("<option id='"+dstJson[i].assistantDocumentId+"'>"+dstJson[i].assistantDocumentName+"</option>"); 
		}
		
		//从本地会话缓存中获取组织结构字符串
		var org = window.sessionStorage.getItem("orgNameAndOrgId");
		//解析为JSON字符串
		var orgJson = JSON.parse(org);
		//组织结构下拉框中添加数据
		for(var i=0;i<orgJson.length;i++) {
			 $("#organizationNameF").append("<option id='"+orgJson[i].organizationId+"'>"+orgJson[i].organizationName+"</option>"); 
		}
		
		//从本地会话缓存中获取取派标准字符串
		var pic = window.sessionStorage.getItem("pSNameAndPSId");
		//解析为JSON字符串
		var picJson = JSON.parse(pic);
		//取派标准下拉框中添加数据
		$("#pickStandardName").append("<option></option>");
		$("#pickStandardNameF").append("<option></option>");
		for(var i=0;i<picJson.length;i++) {
			 $("#pickStandardName").append("<option id='"+picJson[i].pickStandardId+"'>"+picJson[i].pickStandardName+"</option>"); 
			 $("#pickStandardNameF").append("<option id='"+picJson[i].pickStandardId+"'>"+picJson[i].pickStandardName+"</option>"); 
		}
	}
	
	// 初始化取派员类型
	loadDispatcherTypeInfo();
	
	// 初始化取派表标准
	loadPickStandardInfo();
	
	// 初始化驾驶车型
	loadBusTypeInfo();
	
	// 初始化组织结构数据
	loadOrganizationInfo();

	// 初始化页面数据
	infoPage();
});