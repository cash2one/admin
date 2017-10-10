/**
 * 医院总表js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	var hospName = "";
	getPage(hospName,1);
	
	//查看详情
	$('body').on('click','.searchSmsDetailByHospID',function(){
		var hospId = $(this).attr("hospId");
		var hospName = $(this).attr("hospName");
		 window.location.href = path + "/sms/init_page?hospId="+hospId+"&hospName="+hospName;
	});
});

//加载页面时显示分页
function getPage(hospName,currIndex){
	$.ajax({
		url:path+"/sms/findAllHospitalSmsStatisticsList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":10, "first":true, "hospName":hospName},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "pages",
		    pages: totalPage,
		    curr: currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":10,
		   	 		"first": false,
		   	 		"hospName":hospName
		   	 	};
		   	 	$.ajax({
		   			url: path + "/sms/findAllHospitalSmsStatisticsList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data.list;
		   				var html = "";
		   				var no = json.data.num;
		   				if(json.code==1){
		   					for(var i=0; i<data.length; i++) {
			   					no = no+1;
			   					html += "<tr height='40px'>";
			   					html += "<td style='width:300px;'>"+data[i].hospName+"</td>";
			   					html += "<td>"+data[i].totalSend+"</td>";
			   					html += "<td>"+data[i].totalSuccess+"</td>";
			   					html += "<td>"+data[i].totalFailure+"</td>";
			   					html += "<td><button class='blue_btn_small searchSmsDetailByHospID' hospId='"+data[i].hospId+"' hospName="+data[i].hospName+">查看详情</button></td>";
			   					html += "</tr>";
			   				}
			   				$("#tbody").html(html);
		   				}else {
		   					if(json.code==10){
		   						$("#tbody").html("");
		   						layer.msg(json.msgbox, {
			  		      			icon: 1,
			  		      			shade: [0.5,'#000'],
			  		      			btn: ['确定']
			  		    		});
		   					}
		   				}
		   			}
		   		});
	   		}
	  	});
	});
}


$(function(){
	//搜索模块
	$(document).on("click","#searchBtn",function(){
		var hospName = $("#hospName").val();
	//	getPageByHospName(hospName,1);
		getPage(hospName,1);
	});

	//输入医院名称自动补全
	$("#hospName").autocomplete(path+"/sms/searchHospital",{
		dataType:"json",
		minChars:1,
		max:15,
		autoFill:false,
		matchContains:true,
		width:180,
		scrollHeight:330,
		parse:function(data){
				return $.map(data,function(row){
					return {
						data:row,
						value:row.name,
						result:row.name
					};
				});
		},
		formatItem:function(row,i,max){
			return row.name;
		}
	}).result(function(e,row){
		$("#hospName").val(row.name);
	});
});
