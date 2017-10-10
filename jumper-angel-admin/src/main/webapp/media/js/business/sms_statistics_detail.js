//请求总页数
var totalPage = 0;
var curr = 1;

$(document).ready(function(){
	var hospId = $('.hospId').val();
	init_page(hospId,1);
	
	$('body').on('click','.initPage2',function(){
		var hospId = $(this).attr("hospId");
		var appid = $(this).attr("appid");
		var hospName = $(this).attr("hospName");
		 window.location.href = path + "/sms/init_page2?hospId="+hospId+"&appid="+appid+"&hospName="+hospName;
	});
});


function init_page(hospId,currIndex){
	$.ajax({
 		url : path + "/sms/searchSmsDetailByHospID",
		type: "get",
		data:{"pageIndex":currIndex, 
	    	"everyPage":10, 
	    	"hospId":hospId
	    },
		dataType: "json",	//从服务器端返回的数据类型
//		async: false,
		success: function(json) {
			if(json.code == 1){	
				var page = json.data.page;
			    var data = json.data.list;
					var html = "";
					for(var i=0; i<data.length; i++) {    
						html += "<tr height='40px'>";
						if(data[i].appid=="0001"){
							html += "<td>"+"全流程"+"</td>";
						}else if(data[i].appid=="0002"){
							html += "<td>"+"孕妇学校"+"</td>";
						}else if(data[i].appid=="0003"){
							html += "<td>"+"云随访"+"</td>";
						}
						html += "<td>"+data[i].totalSend+"</td>";
						html += "<td>"+data[i].totalSuccess+"</td>";
						html += "<td>"+data[i].totalFailure+"</td>";
						html += "<td><button class='blue_btn_small initPage2' hospId='"+data[i].hospId+"'appid="+data[i].appid+" hospName="+data[i].hospName+">详情</button></td>";
						html += "</tr>";
					}
					$("#tbody").html(html);	
					layui.use(["laypage"], function(){
						var laypage = layui.laypage;
						laypage({
							  cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
						      pages:page.totalPage, //通过后台拿到的总页数
						      curr: currIndex, //当前页
						      jump: function(obj,first){ //触发分页后的回调
						        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
						        	init_page(hospId,obj.curr);
						        }
						      }
					  	});
					});
			}else{
				$("#tbody").html("");
			}
	   	}
	  });
	
	
};


/*function init_page(){
	var hospId = $('.hospId').val();
	$.ajax({
 		url : path + "/sms/searchSmsDetailByHospID?hospId="+hospId+"&time="+new Date().getTime(),
		type: "post",
		dataType: "json",	//从服务器端返回的数据类型
		async: false,
		success: function(json) {
			var data = json.data;
				var html = "";
				for(var i=0; i<data.length; i++) {    
					
					html += "<tr height='40px'>";
					if(data[i].appid=="0001"){
						html += "<td>"+"全流程"+"</td>";
					}else if(data[i].appid=="0002"){
						html += "<td>"+"孕妇学校"+"</td>";
					}
					html += "<td>"+data[i].totalSend+"</td>";
					html += "<td>"+data[i].totalSuccess+"</td>";
					html += "<td>"+data[i].totalFailure+"</td>";
					html += "<td><button class='blue_btn_small initPage2' hospId='"+data[i].id+"'appid="+data[i].appid+" hospName="+data[i].hospName+">详情</button></td>";
					html += "</tr>";
				}
				$("#tbody").html(html);	
	   		}
	  });
	
}*/


