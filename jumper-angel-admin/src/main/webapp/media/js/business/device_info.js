/**
 * 商户信息js
 */
//总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function() {
	//显示数据
	initData(1);
	
	//file选择事件
	$("#file").on("change", function() {
		var file = $("#file").val();
		file = file.substring(file.lastIndexOf("\\")+1, file.length);
		$(".fileclass").val(file);
	});
});
//显示数据
function initData(currIndex) {
	var param = {
		"businessId":$("#businessId").val(),
		"mac":$("#mac").val(),
		"deviceType":$("#deviceType option:selected").val(),
		"pageIndex":1,
		"everyPage":everyPage,
		"first":true
	};
	//请求总页数
	$.ajax({
		url: path + "/business/findBusinessDevice",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: param,
		async: false,//同步
		success: function(json) {
			totalPage = json.data;
		}
	});
	//显示分页
	layui.use(["laypage"], function() {
		var laypage = layui.laypage;
		layui.laypage({
		    cont: "page",
		    pages: totalPage,
		    curr:currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   		curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"businessId":$("#businessId").val(),
		   			"mac":$("#mac").val(),
		   			"deviceType":$("#deviceType option:selected").val(),
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false
		   	 	};
		   	 	//请求商户信息
		   	 	$.ajax({
		   			url: path + "/business/findBusinessDevice",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {
		   					//设备类型名称
		   					var deviceTypeName = "";
		   					if(data[i].deviceType==1) {
		   						deviceTypeName = "心电仪";
		   					} else if(data[i].deviceType==2) {
		   						deviceTypeName = "胎心仪";
		   					} else if(data[i].deviceType==3) {
		   						deviceTypeName = "血糖仪";
		   					} else if(data[i].deviceType==4) {
		   						deviceTypeName = "血压仪";
		   					} else if(data[i].deviceType==5) {
		   						deviceTypeName = "体温计";
		   					} else if(data[i].deviceType==6) {
		   						deviceTypeName = "血氧仪";
		   					} else if(data[i].deviceType==7) {
		   						deviceTypeName = "体重秤";
		   					}
		   					//连接类型名称
		   					var connectName = "";
		   					if(data[i].connectType==1) {
		   						connectName = "GPRS";
		   					} else if(data[i].connectType==2) {
		   						connectName = "WIFI";
		   					} else if(data[i].connectType==3) {
		   						connectName = "蓝牙";
		   					} else if(data[i].connectType==4) {
		   						connectName = "蓝牙广播";
		   					} else if(data[i].connectType==5) {
		   						connectName = "蓝牙直连";
		   					}
		   					//状态名称
		   					var statusName = "";
		   					if(data[i].stat==0) {
		   						statusName = "启用";
		   					} else if(data[i].stat==1) {
		   						statusName = "禁用";
		   					}
		   					html+="<tr>";
	   						html+="<td>"+data[i].deviceName+"</td>";
	   						html+="<td>"+data[i].seriesNumber+"</td>";
	   						html+="<td>"+data[i].mac+"</td>";
	   						html+="<td>"+data[i].manufacturer+"</td>";
	   						html+="<td>"+deviceTypeName+"</td>";
	   						html+="<td>"+connectName+"</td>";
	   						html+="<td>"+statusName+"</td>";
	   						html+="<td>"+dateFormat(data[i].outdate)+"</td>";
	   						html+="<td><a href='javascript:deleteDevice("+data[i].id+");'>删除</a></td>";
   							html+="</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}

/**
 * 删除设备
 */
function deleteDevice(id) {
	layer.confirm("你是否删除该商户设备，删除后商户无法使用该设备！", {
	  btn: ['确定', '取消']
	}, function(index, layero){
		//执行修改操作
		$.ajax({
			url: path + "/business/deleteDevice",
			type: "get",
			dataType: "json",//从服务器端返回的数据类型
			data: {"id":id},
			success: function(json) {
				//关闭当前对话框
				layer.close(index);
				layer.alert(json.msgbox);
				//获取当前页的数据
				initData(curr);
			}
		});
	}, function(index){
		//取消
	});
}

/**
 * 导入excel文件
 */
function importExcel() {
	var index;
	//导入excel文件
	$.ajax({
		url: path + "/business/importExcel?businessId="+$("#businessId").val(),
		type: "POST",
		cache: false,//上传文件不需要缓存
		dataType: "json",//从服务器端返回的数据类型
		data:new FormData($('#uploadForm')[0]),
		processData: false,//因为data值是FormData对象，不需要对数据做处理，<form>标签添加enctype="multipart/form-data"属性
		contentType: false,//因为是由<form>表单构造的FormData对象，且已经声明了属性enctype="multipart/form-data"，所以这里设置为false。
		beforeSend: function() { //请求中
			index = layer.load(3, {
			  shade: [0.4,'#000'] //0.1透明度的白色背景
			});
		},
		success: function(json) {
			if(json.code == 1) {
				var data = json.data;
				//成功条数
				var sucessNum = data.sucessNum;
				//重复数据
				var repetitionData = data.existResult;
				//重复条数
				var repetitionNum = repetitionData.length;
				if(repetitionNum == 0) {
					//获取当前页的数据
					initData(1);
					layer.alert(json.msgbox);
					return;
				}
				$("#importInfo").html("导入成功！成功条数："+sucessNum+"；失败条数："+repetitionNum+"，失败原因：数据已存在；失败数据如下所示：");
				var html = "";
   				for(var i=0; i<repetitionData.length; i++) {
   					//设备类型名称
   					var deviceTypeName = "";
   					if(repetitionData[i].deviceType==1) {
   						deviceTypeName = "心电仪";
   					} else if(repetitionData[i].deviceType==2) {
   						deviceTypeName = "胎心仪";
   					} else if(repetitionData[i].deviceType==3) {
   						deviceTypeName = "血糖仪";
   					} else if(repetitionData[i].deviceType==4) {
   						deviceTypeName = "血压仪";
   					} else if(repetitionData[i].deviceType==5) {
   						deviceTypeName = "体温计";
   					} else if(repetitionData[i].deviceType==6) {
   						deviceTypeName = "血氧仪";
   					} else if(repetitionData[i].deviceType==7) {
   						deviceTypeName = "体重秤";
   					}
   					//连接类型名称
   					var connectName = "";
   					if(repetitionData[i].connectType==1) {
   						connectName = "GPRS";
   					} else if(repetitionData[i].connectType==2) {
   						connectName = "WIFI";
   					} else if(repetitionData[i].connectType==3) {
   						connectName = "蓝牙";
   					} else if(repetitionData[i].connectType==4) {
   						connectName = "蓝牙广播";
   					} else if(repetitionData[i].connectType==5) {
   						connectName = "蓝牙直连";
   					}
   					html+="<tr>";
						html+="<td>"+repetitionData[i].deviceName+"</td>";
						html+="<td>"+repetitionData[i].seriesNumber+"</td>";
						html+="<td>"+repetitionData[i].mac+"</td>";
						html+="<td>"+repetitionData[i].manufacturer+"</td>";
						html+="<td>"+deviceTypeName+"</td>";
						html+="<td>"+connectName+"</td>";
						html+="</tr>";
   				}
   				$("#deviceData").html(html);
				layer.open({
					title:'导入反馈信息',
			  		type: 1,
			  		area: ['700px','400px'], //宽高
			  		content: $('.normal_form'),
			  		btn: ['确定'],
			  		yes: function(index){
			  			layer.close(index);
			    	}
				});
			} else {
				layer.alert(json.msgbox);
			}
			//获取当前页的数据
			initData(1);
		},
		complete: function() { //请求完成
			layer.close(index);
		}
	});
}