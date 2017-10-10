/**
 * 咨询列表js
 */
//请求总页数
var totalPage = 0;
$(document).ready(function(){
	var keywords = "";
//	getHospitalList(keywords);
	getPage(keywords);
});
//显示医院列表
function getHospitalList(keywords){
	$.ajax({
		url:path+"/hospital/homepage/getHospitalList",
		type:"get",
		dataType:"json",
		data:{"keywords":keywords},
		async:false,
		success:function(json){
			totalPage = json.data;
			console.log(totalPage);
		}
	});
}

//显示分页
function getPage(keywords){
	$.ajax({
		url:path+"/hospital/homepage/platformFunction",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	layui.use(["laypage"], function() {
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
		   	 		"hospitalId":42,
		   	 		"first": false,
		   	 		"keywords":keywords
		   	 	};
		   	 	$.ajax({
		   			url: path + "/hospital/homepage/platformFunctions",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				console.log(data);
		   				var html = "";
		   				var status = "";
		   				for(var i=0;i < data.length;i++){
		   					html += "<tr height='40px'>";
		   					html += "<td><img></img>";
		   					html += "<br>"+data[i].title+"</td>";
		   					html += "<td>服务状态：";
		   					if(data[i].closed == 0){
		   						html += "已开通";
		   					}else{
		   						html += "未开通";
		   					}
		   					html += "<br>位置排序：该功能不可排序";
		   					html += "<br>入口状态：该功能不可隐藏";
		   					html += "<br>链接：";
		   					html += "</td></tr>";
		   				}
//		   				for(var i=0; i<data.length; i++) {
//		   					if(data[i].content.length>15){
//		   						moreStr = data[i].content.substring(0, 15)+"...<a href='javascript:void(0);' title='"+data[i].content+"' class='blue tips'>[全文]</a></td>";
//		   					}else{
//		   						moreStr = data[i].content;
//		   					}
//		   					status = data[i].isVisible==0?"公开":"隐藏";
//		   					html += "<tr height='40px'>";
//		   					html += "<td>"+data[i].nick_name+"</td>";
//		   					html += "<td>"+data[i].real_name+"</td>";
//		   					html += "<td style='word-break: break-all; width: 350px;' class='table-longtext'>"+moreStr+"</td>";
//		   					html += "<td>"+data[i].major_name+"</td>";
//		   					html += "<td>"+data[i].add_time+"</td>";
//		   					html += "<td>"+data[i].doc_name+"</td>";
//		   					html += "<td>"+data[i].statusDisplay+"</td>";
//		   					if(""==data[i].doc_name){
//		   						html += "<td><button type='button' class='grey_btn_small' disabled='disabled'>咨询详情</button>";
//		   					}else{
//		   						html += "<td><button type='button' class='red_btn_small' onClick='showComment("+
//		   						data[i].id+","+data[i].is_admin+","+	data[i].user_id+","+data[i].doctor_id+","+data[i].service_type+")'>咨询详情</button>";
//		   					}
//							if (data[i].evaluate==1){
//								if(data[i].comments_content!=null){
//									if(data[i].comments_status==0){
//										html += "<button type='button' class='red_btn_small' onClick='generalize("+data[i].id+")' id='"+data[i].id+"' value='"+data[i].comments_content+"'>查看评论</button>";
//									}else{
//										html += "<button type='button' class='grey_btn_small' disabled='disabled'>评论已删除</button>";
//									}
//								}else{
//									html += "<button type='button' class='grey_btn_small' disabled='disabled'>暂无评论</button>";
//								}
//							}else{
//								html += "<button type='button' class='grey_btn_small' disabled='disabled'>暂无评论</button>";
//							}
//			   				html += "</td></tr>";
//		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}

