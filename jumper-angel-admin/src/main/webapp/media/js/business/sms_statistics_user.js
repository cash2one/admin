/**
 * 用户短信详情js
 */
//请求总页数
var totalPage = 0;
var curr = 1;

$(document).ready(function(){
	var hospId = $(".hospId").val();
	var appid = $(".appid").val();
	var mobile = "";
	var beginTime = "";
	var endTime = "";
	getPage(hospId,appid,mobile,beginTime,endTime,1);
	
});


//查看各个业务渠道发送短信用户情况
function getPage(hospId,appid,mobile,beginTime,endTime,currIndex){
	$.ajax({
		url: path + "/sms/findHospiDetailStatistics",
		type: "get",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
	    data:{"pageIndex":currIndex, 
		    	"everyPage":10, 
		    	"first":true, 
		    	"hospId":hospId,
		    	"appid":appid,
		    	"mobile":mobile,
		    	"beginTime":beginTime,
		    	"endTime":endTime
		    	},
		success: function(json){
			if(json.code == 1){
				 	var page = json.data.page;
				    var data = json.data.list;
				    $("#totalSend").text(json.data.totalSend);
	   				$("#totalSuccess").text(json.data.totalSuccess);
	   				$("#totalFailure").text(json.data.totalFailure);
				    $("#tbody").html("");
					console.log(data);
					var html = "";
	   				for(var i=0; i<data.length; i++) {
	   					if(data[i].content.length>10){
	   						moreStr = data[i].content.substring(0, 10)+"...<a href='javascript:' class='blue' value='"+data[i].content+"'>[全文]</a>";
	   					}else{
	   						moreStr = data[i].content;
	   					}
	   					html += "<tr height='40px'>";
	   					html += "<td style='width:300px;'>"+data[i].mobile+"</td>";
	   					html += "<td>"+data[i].createTime+"</td>";
	   					if(data[i].isSuccess==0){
	   						html += "<td>"+"发送失败"+"</td>";
	   					}else if(data[i].isSuccess==1){
	   						html += "<td>"+"发送成功"+"</td>";
	   					}
	   					
	   					html += "<td id='smsBriefIntr'>"+moreStr+"</td>";
		   				html += "</tr>";
	   				}
	   				$("#tbody").html(html);
					console.log(html);
					layui.use(["laypage"], function(){
						var laypage = layui.laypage;
						laypage({
							  cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
						      pages:page.totalPage, //通过后台拿到的总页数
						      curr: currIndex, //当前页
						      jump: function(obj,first){ //触发分页后的回调
						        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
						        	getPage(hospId,appid,mobile,beginTime,endTime,obj.curr);
						        }
						      }
					  	});
					});
			}else{
				if(json.code==10){
					    $("#totalSend").text(json.data.totalSend);
		   				$("#totalSuccess").text(json.data.totalSuccess);
		   				$("#totalFailure").text(json.data.totalFailure);
						$("#tbody").html("");
					}
					layer.msg(json.msgbox, {
		      			icon: 1,
		      			shade: [0.5,'#000'],
		      			btn: ['确定']
		    		});
			}
		}
	});
	///显示全部内容
	$(document).on("click", ".blue", function() {
		var info = $(this).attr("value");
		layer.tips(info, this, {
			tips: [2, '#3595CC'],
			time: 4000
		});
	});
};


//显示分页
/*function getPage(hospId,appid,mobile,beginTime,endTime,currIndex){
	$.ajax({
		url:path+"/sms/findHospitalDetailStatistics",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":10, "first":true, "hospId":hospId,"appid":appid,"mobile":mobile, "beginTime":beginTime, "endTime":endTime},
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
		   	 		"hospId":hospId,
		   	 		"appid":appid,
		   	 		"mobile":mobile,
		   	 		"beginTime":beginTime,
		   	 		"endTime":endTime,
		   	 		"pageIndex":curr,
		   	 		"everyPage":10,
		   	 		"first": false
		   	 	};
		   	 $.ajax({
		   			url: path + "/sms/findHospitalDetailStatistics",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				$("#totalSend").text(json.data.totalSend);
		   				$("#totalSuccess").text(json.data.totalSuccess);
		   				$("#totalFailure").text(json.data.totalFailure);
		   				//清空输入框
		   				$("#mobile").val("");
		   				$("#start").val("");
		   				$("#end").val("");
		   				end_date.min = "";
		   				start_date.max = laydate.now();
		   				if(json.code==1){
//		   					$(".content2").css("display","block");
		   					var data = json.data.list;
			   				var html = "";
			   				for(var i=0; i<data.length; i++) {
			   					if(data[i].content.length>10){
			   						moreStr = data[i].content.substring(0, 10)+"...<a href='javascript:void(0);' title='"+data[i].content+"' class='blue tips'>[全文]</a>";
			   					}else{
			   						moreStr = data[i].content;
			   					}
			   					html += "<tr height='40px'>";
			   					html += "<td style='width:300px;'>"+data[i].mobile+"</td>";
			   					html += "<td>"+data[i].sendTime+"</td>";
			   					html += "<td>"+data[i].isSuccess==0?"发送失败":"发送成功"+"</td>";
			   					html += "<td>"+moreStr+"</td>";
				   				html += "</tr>";
			   				}
			   				$("#tbody").html(html);
		   				}else{
		   					if(json.code==10){
		   						$("#tbody").html("");
		   					}
		   					layer.msg(json.msgbox, {
		  		      			icon: 1,
		  		      			shade: [0.5,'#000'],
		  		      			btn: ['确定']
		  		    		});
		   				}
		   				
		   			}
		   		});
	   		}
	  	});
	});
};
*/

$(function(){
	
	//搜索模块
	$(document).on("click","#searchBtn",function(){
		var mobile = $("#mobile").val();
		var start = $("#start").val();
		var end = $("#end").val();
		var hospId = $(".hospId").val();
		var appid = $(".appid").val();
		getPage(hospId,appid,mobile,start,end,1);
		
	});
	
});

