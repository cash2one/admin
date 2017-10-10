/**
 * 商户信息js
 */
//总页数
var totalPage = 0;
$(document).ready(function() {
	//显示数据
	initData();
});
//显示数据
function initData() {
	//请求总页数
	$.ajax({
		url: path + "/business/findAllBusiness",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: {"pageIndex":1, "everyPage":everyPage, "first":true, "businessName":$("#businessName").val()},
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
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    var curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false,
		   	 		"businessName":$("#businessName").val()
		   	 	};
		   	 	//请求商户信息
		   	 	$.ajax({
		   			url: path + "/business/findAllBusiness",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {
		   					html+="<tr>";
	   						html+="<td>"+data[i].businessName+"</td>";
	   						html+="<td>"+data[i].email+"</td>";
	   						html+="<td>"+dateFormat(data[i].createDate)+"</td>";
	   						html+="<td>"+data[i].xdy+"</td>";
	   						html+="<td>"+data[i].txy+"</td>";
	   						html+="<td>"+data[i].xty+"</td>";
	   						html+="<td>"+data[i].xyy+"</td>";
	   						html+="<td>"+data[i].xo2y+"</td>";
	   						html+="<td>"+data[i].twj+"</td>";
	   						html+="<td>"+data[i].tzc+"</td>";
	   						html+="<td>"+data[i].statusName+"</td>";
	   						html+="<td><a href='"+path+"/business/deviceFromJsp?id="+data[i].id+"'>详情</a></td>";
   							html+="</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}