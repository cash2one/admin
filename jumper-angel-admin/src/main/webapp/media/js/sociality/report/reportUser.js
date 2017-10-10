$(document).ready(function(){
	reportUserList(1);
	$("#searchReportUser").on("click",function(){
		reportUserList(1);
	});
});

function reportUserList(curr){
	$.ajax({
		url: path + "/sociality/reprot/user/getReportUserList",
		type: "get",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		data:{
		    "beginIndex":curr || 1,"everyPage":15,
		    "topicId":$("#select_value").val(),
		    "keyword":$("#keyword").val(),
		    "status":$("#status").val(),
		    "timestamp":new Date().getTime()
		    },
		success: function(json){
			if(json.code == 1){
				 	var page = json.data.page;
				    var data = json.data.content;
				    $("#tbody").html("");
					var html = "";
					console.log(data);
					for(var i=0; i<data.length; i++) {
						var reportUserVO = data[i];
		                html+="<tr>";
		                if (CheckImgExists()){
		                	html+="<td><img src='"+reportUserVO.beReportMessage+"'/></td>";
		                }else {
		                	html+="<td>"+reportUserVO.beReportMessage+"</td>";
		                }
		                html+="<td>"+reportUserVO.groupName+"</td>";
		                html+="<td>"+reportUserVO.userNickName+"</td>";
		                html+="<td><a href=\"#\" class=\"openBtn2\" onclick=\"showReportDetail('"+reportUserVO.userId+"','"+reportUserVO.groupId+"')\">"+reportUserVO.reportNum+"次</a></td>";
		                if(reportUserVO.reportNum <5){
		                	html+="<td>审核中</td>";
		                	html+="<td>";
		                	html+="<button class=\"yellow_btn_small\" onclick=\"updateReport('"+reportUserVO.userId+"','"+reportUserVO.groupId+"',2)\">通过</button>";
		                	html+="<button class=\"red_btn_small\" onclick=\"updateReport('"+reportUserVO.userId+"','"+reportUserVO.groupId+"',3)\">禁言</button>";
		                	html+="</td>";
		                }else{
		                	html+="<td>禁言中</td>";
		                	html+="<td>";
		                	html+="<button class=\"yellow_btn_small\" onclick=\"updateReport('"+reportUserVO.userId+"','"+reportUserVO.groupId+"',2)\">通过</button>";
		                	html+="<button class=\"blue_btn_small\" onclick=\"updateReport('"+reportUserVO.userId+"','"+reportUserVO.groupId+"',4)\">解除禁言</button>";
		                	html+="</td>";
		                }
		                html +="</tr>";
					}
					$("#tbody").html(html);
					layui.use(["laypage"], function(){
						var laypage = layui.laypage;
						laypage({
							  cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
						      pages:page.totalPage, //通过后台拿到的总页数
						      curr: curr || 1, //当前页
						      jump: function(obj,first){ //触发分页后的回调
						        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
						        	reportUserList(obj.curr);
						        }
						      }
					  	});
					});
			}else{
				$("#tbody").html("");
			}
		}
	});
}

//显示举报详情
function showReportDetail(busId,groupId){
	var param = {"busId":busId,"groupId":groupId,"dataType":3};
	$.ajax({
		url: path + "/sociality/reprot/getReportList",
		type: "post",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		data:JSON.stringify(param),
		success: function(json){
			console.log(json)
			if(json.code == 0){
				layer.alert(json.msgbox,{icon:2});
			}else{
				$("#reportTbody").html("");
				var list = json.data;
				var html ="";
				for(var i=0;i<list.length;i++){
					var reportVO = list[i];
					var nickName = reportVO.userNickName ==null ?"": reportVO.userNickName;
					html+= "<tr>";
					html+="<td><ul class=\"tableTime\"><li>"+reportVO.strDateTime.substring(0,10)+"</li><li>"+reportVO.strDateTime.substring(11,19)+"</li></ul></td>";
					html+="<td>"+reportVO.reportContent+"</td>";
					html+="<td>"+nickName+"</td>";
					html+= "</tr>";
				}
				layer.open({
			        title:'举报详情',
			        type:1,
			        area:["640px",""],
			        content:$(".showBox2")
			    });
				$("#reportTbody").html(html);
			}
		}
	});
}

//更新举报信息
function updateReport(busId,groupId,operatorType){
	var param = {"busId":busId,"groupId":groupId,"dataState":2,"dataType":3,"operatorType":operatorType};
	$.ajax({
		url: path + "/sociality/reprot/updateReport",
		type: "post",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		data:JSON.stringify(param),
		success: function(json){
			console.log(json)
			if(json.code == 0){
				layer.alert(json.msgbox,{icon:2});
			}else{
				layer.alert(json.msgbox,{icon:1},function(index){
					reportUserList(1);
					layer.close(index);
				});
			}
		}
	});
}


function CheckImgExists(imgurl) {
	var ImgObj = new Image(); //判断图片是否存在  
	ImgObj.src = imgurl;
	//没有图片，则返回-1  
	if (ImgObj.fileSize > 0 || (ImgObj.width > 0 && ImgObj.height > 0)) {
		return true;
	} else {
		return false;
	}
}