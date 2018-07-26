$(function(){
	// --------------------------------表格js--------------------------------
	//初始化Table 
	var oTableInit = function() {
		$('#tb_departments').bootstrapTable({
			url :"/Logistics/base/replace/findAllReplaceWithPage.action", //请求后台的URL（*） 
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
			uniqueId : "replaceId", //每一行的唯一标识，一般为主键列 
			showToggle : true, //是否显示详细视图和列表视图的切换按钮 
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			contentType : "application/x-www-form-urlencoded",//指定传输的数据格式
		    columns : [ 
		    	{field:'·',title:'选择',checkbox:true},    
		        {field:'replaceId',title:'替班ID',width:150,fixed:true},    
		        {field:'replaceManId',title:'替班人ID',width:150,align:"center"},
		        {field:'replaceName',title:'替班人姓名',width:200,align:"center"},
		        {field:'replacedId',title:'被替班人ID',width:250,align:"center"},
		        {field:'replacedName',title:'被替班人姓名',width:150,align:"center"},
		        {field:'startTime',title:'替班开始时间',width:150,align:"center",
					formatter:function(value){
						return dateFormatter(value,"see");
					}		        	
		        },
		        {field:'endTime',title:'替班结束时间',width:100,align:"center",
		        	formatter:function(value){
						return dateFormatter(value,"see");
					}		
		        },
		        {field:'organizationId',title:'组织结构ID',width:50,align:"center"},
		        {field:'organizationName',title:'组织结构名',width:150,align:"center"},
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
		  	                  startTime:$("#startTime").val()!=""?dateFormatter($("#startTime").val(),"uploading"):dateFormatter("1900/00/00 00:00","uploading"),
							  endTime:$("#endTime").val()!=""?dateFormatter($("#endTime").val(),"uploading"):dateFormatter("3000/00/00 00:00","uploading"),
		  	              };
	          	}
	            return temp;
	        },
		});
	}; //得到查询的参数
	
	//初始化表单
	oTableInit();
	//隐藏列
	$('#tb_departments').bootstrapTable('hideColumn', 'replaceId');
	$('#tb_departments').bootstrapTable('hideColumn', 'replaceManId');
	$('#tb_departments').bootstrapTable('hideColumn', 'replacedId');
	$('#tb_departments').bootstrapTable('hideColumn', 'organizationId');
	
	 $(".form_datetime").datetimepicker({
	        format: "yyyy-mm-dd hh:ii:ss",
	        autoclose: true,
	        todayBtn: true,
	        minuteStep: 10,
	        language:'zh-CN',
	}); 
	 
	//清除的代码
    $(".removeTime").click(function(){
         $(this).parent().parent().find("input").val("");
    })
	 
	//---------------------------输入框后的搜索框被点击----------------------------------------
	$(".search").click(function(){
		$("#searchType").attr("value","single");
		var condition = $(this).parent().find("input").eq(0).val();
		if(condition==null){
			condition = $(this).parent().find("select").eq(0).val();
			var action = $(this).parent().find("select").eq(0).attr("name");
		}else{
			var action = $(this).parent().find("input").eq(0).attr("name");
		}
		if(condition == "" || condition==null){
			toastr.warning('条件不能为空！');
		}else{
			$('#tb_departments').bootstrapTable('refresh',
					{url:'/Logistics/base/replace/'+action+'.action?condition='+condition});
		}
	});
	 
    //时区查询
	$("#timeZoneQueryBtn").click(function(){
		$("#searchType").attr("value","combined");
		var obj = {
				 url:"/Logistics/base/replace/findReplaceByCriteriaWithPage.action"
		 };
		//刷新数据表格  
		$('#tb_departments').bootstrapTable('refresh',obj);
	});
	 
	//---------------------------------按钮的点击---------------------------------------
	//添加按钮
	$("#addReplaceBtn").click(function(){
		// 清空之前的校验
        $('#replaceForm').data('bootstrapValidator', null);
        // 设置添加替班员校验方法
        validateReplaceForm();
        addReplace();
	});
	
	//修改按钮
	$("#updateReplaceBtn").click(function(){
		// 清空之前的校验
        $('#replaceForm').data('bootstrapValidator', null);
        // 设置添加替班员校验方法
        validateReplaceForm();
        updateReplace();
	});
	
	//冻结按钮
	$("#freezeReplaceBtn").click(function(){
		freezeOrActivateReplace("freezeReplace");
	});
	
	//激活按钮
	$("#activateReplaceBtn").click(function(){
		freezeOrActivateReplace("activateReplace");
	});
	
	var addReplace = function(){
		//更改表标题
		$(".modalTitel").text("添加替班");
		$(".modalTitel").attr("value","addReplace");
		//清空表中数据
		$("#replaceForm")[0].reset();
		//显示添加基础档案模态框 
		$("#replaceModal").modal({
			 show:true, //显示模态框
			 keyboard:false, //按ESC键无法关闭模态框
			 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
		});
	};
	//-------------------------------按钮具体方法--------------------------------------
	//修改方法
	var updateReplace = function(){
		var value = $('#tb_departments').bootstrapTable("getSelections");
		if(value.length==1){
			//更改表标题
			$(".modalTitel").text("修改职员");
			$(".modalTitel").attr("value","updateReplace");
			
			//异步请求根据员工ID获取员工号
			$.ajax({
				type: "post", 
				url:'/Logistics/base/dispatcher/findDispatcherNoByDispathcerId.action',
				data:{dispatcherId:value[0].replaceManId},				   
				success:function(data) {
					$("#rDispatcherNoF").val(data[0]);
				}   
			});
			//异步请求根据员工ID获取员工号,员工名
			$.ajax({
				type: "post", 
				url:'/Logistics/base/dispatcher/findDispatcherNoByDispathcerId.action',
				data:{dispatcherId:value[0].replacedId},				   
				success:function(data) {
					$("#rdDispatcherNoF").val(data[0]);
				}   
			});
			
			//清空表中数据
			$("#replaceForm")[0].reset();
			$("#replaceIdF").val(value[0].replaceId);
			$("#replaceNameF").val(value[0].replaceName);
			$("#replacedNameF").val(value[0].replacedName);
			$("#startTimeF").val(dateFormatter(value[0].startTime,"uploading"));
			$("#endTimeF").val(dateFormatter(value[0].endTime,"uploading"));
			$("#organizationNameF").val(value[0].organizationName);
			$("#useableF").val(value[0].useable);
			//显示添加基础档案模态框 
			$("#replaceModal").modal({
				 show:true, //显示模态框
				 keyboard:false, //按ESC键无法关闭模态框
				 backdrop:"static" //背景灰色，且点击空白区无法关闭模态框
			});
		}else{
			toastr.warning('请选择一行进行修改!');
		}
	};
	
	// 校验
	var  validateReplaceForm = function(){
		$('#replaceForm').bootstrapValidator({
			message: 'This value is not valid',
			submitButtons: '#submit',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
			feedbackIcons:{
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
            fields: {
            	rDispatcherNo: {
            		validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 6,
	                        max: 6,
	                        message: '长度必须是6位数'
	                    },
	                   remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                            url: '/Logistics/base/dispatcher/existDispatcherByDispatcherNoInReplace.action',
                            message: '该员工号为空',
                            delay :  3000,
                            type:'post'
                        },
                        different: {//不能和用户名相同
                            field: 'rdDispatcherNo',//需要进行比较的input name值
                            message: '不能和被替班人工号相同'
                        }
	            	}
	            },
	            rdDispatcherNo: {
            		validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        min: 6,
	                        max: 6,
	                        message: '长度必须是6位数'
	                    },
	                   remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
                            url: '/Logistics/base/dispatcher/existDispatcherByDispatcherNoInReplaced.action',
                            message: '该员工号为空',
                            delay : 3000,
                            type:'post'
                        },
                        different: {//不能和用户名相同
                            field: 'rDispatcherNo',//需要进行比较的input name值
                            message: '不能和替班人工号相同'
                        },
	            	},
	            	
	            },
	            startTime: {
            		validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	},
	            },
	            endTime: {
            		validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	            	},
	            	/*callback:function(value, validator, $field){
	            		
	            	}*/
	            },
	            useable: {
            		validators: {
	            		notEmpty: {//检测非空,radio也可用
	                        message: '文本框必须输入'
	                    },
	                    stringLength: {//检测长度
	                        max: 2,
	                        message: '请选择是否可用'
	                    },
	            	},
	            },
            }
	    });
	};
	
	//格式化日期方法
	function dateFormatter (value,use) {
		if(value!=""){
			var date = new Date(value);
		    var year = date.getFullYear().toString();
		    var month = (date.getMonth() + 1);
		    var day = date.getDate().toString();
		    var hour = date.getHours().toString();
		    var minutes = date.getMinutes().toString();
		    var seconds = date.getSeconds().toString();
		    if (month < 10) {
		        month = "0" + month;
		    }
		    if (day < 10) {
		        day = "0" + day;
		    }
		    if (hour < 10) {
		        hour = "0" + hour;
		    }
		    if (minutes < 10) {
		        minutes = "0" + minutes;
		    }
		    if (seconds < 10) {
		        seconds = "0" + seconds;
		    }
		    if(use=="uploading"){
		    	return year + "/" + month + "/" + day + " " + hour + ":" + minutes;
		    }else if(use=="see"){
		    	return year + "-" + month + "-" + day + " " + hour + ":" + minutes;
		    }
		}else{
			return null;
		}
	}
	
	//员工号输入框失焦时间，将对应员工名自动填入的方法
	$("#rDispatcherNoF").blur(function(){
		var dispatcherNo = $(this).val();
		var organizationName = "";
		//异步请求根据员工号获取员工名，组织结构ID，组织结构名填入对应的框框中
		$.ajax({
			type: "post", 
			url:'/Logistics/base/dispatcher/findNameByDispatcherNo.action',
			data:{dispatcherNo:dispatcherNo},				   
			success:function(data) {
				$("#replaceNameF").val(data[0]);
				$("#organizationIdF").val(data[1]);
				$("#organizationNameF").val(data[2]);
			}   
		});
	});
	
	$("#rdDispatcherNoF").blur(function(){
		var dispatcherNo = $(this).val();
		$.ajax({
			type: "post", 
			url:'/Logistics/base/dispatcher/findNameByDispatcherNo.action',
			data:{dispatcherNo:dispatcherNo},			
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			success:function(data) {
				$("#replacedNameF").val(data[0]);
			}   
		});
	});
	
	// 点击提交按钮
	$("#submit").click(function(){
		$("#replaceForm").bootstrapValidator('validate');//提交验证
		$("#replaceModal").modal("hide"); //关闭modal
		updateProgress(30);// 调用进度条显示方法,参数为进度条速度
		setTimeout(function(){// 定时，在3秒后获取校验结果
			validator();
		},3050);
	});
	
	// 提交的校验方法
	var validator = function(){
		if ( $("#replaceForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
        	var action = $(".modalTitel").attr("value");
        	var startTime = dateFormatter($("#startTimeF").val(),"uploading");
        	$("#startTimeF").val(startTime);
    		var endTime = dateFormatter($("#endTimeF").val(),"uploading");
    		$("#endTimeF").val(endTime);
    		var replaceData = $("#replaceForm").serialize();
    		$.ajax({
    			type: "post", 
    			url:'/Logistics/base/replace/'+action+'.action',
    			data:replaceData,					   
    			success:function(data) {
    				if(data=="Y"){
    					toastr.success('修改成功!');
    					$('#tb_departments').bootstrapTable('refresh'); //刷新表单
    				}else{
    					toastr.error('修改失败!');
    					$('#tb_departments').bootstrapTable('refresh'); //刷新表单
    				}
    			}   
    		});
        }else{
        	toastr.warning('请检查填写信息!');
        }
	};
	
	// 修改时，等待的进度条
	var updateProgress = function(speed){
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

	//初始化表单校验
	validateReplaceForm();

	//冻结激活方法
	var freezeOrActivateReplace = function(action){
		var value = $('#tb_departments').bootstrapTable("getSelections");
		var replaceIds = new Array();
		if(action.indexOf('freeze')>=0){ // 如果是冻结操作
			for(var i=0;i<value.length;i++){ // 遍历被选中的职员的职员ID
				if(value[i].useable=='Y'){	// 判断是否为可用，可用就拼接，不可用就不拼接
					replaceIds.push(value[i].replaceId);
				}
			}
		}else{ // 如果是激活操作
			for(var i=0;i<value.length;i++){ // 遍历被选中的职员的职员ID
				if(value[i].useable=='N'){ // 判断是否为可用，不可用就拼接，可用就不拼接
					replaceIds.push(value[i].replaceId);
				}
			}
		}
		if(value.length==0){ // 判断是否有被选中的对应职员 
			toastr.warning('请选择至少一行进行修改!');
		}else if(replaceIds.length==0){
			toastr.warning('请选择正确行做正确操作!');
		}else{
			$.ajax({
				type: "post", 
				url:'/Logistics/base/replace/'+action+'.action',
				data:{"replaceIds":replaceIds},		
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
	
	
	//暂停方法
    function sleep(n) { //n表示的毫秒数
        var start = new Date().getTime();
        while (true) if (new Date().getTime() - start > n) break;
    }  
	
});
