$(document).ready(function(){
	reportCommentList(1);
	$("#searchReportComment").on("click",function(){
		reportCommentList(1);
	});
});

//查询被举报的评论
function reportCommentList(curr){
	$.ajax({
		url: path + "/sociality/reprot/comment/getReportCommentList",
		type: "get",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		data:{
		    "beginIndex":curr || 1,"everyPage":15,
		    "topicId":$("#select_value").val(),
		    "keyword":$("#keyword").val(),
		    "dataStatus":$("#dataStatus").val(),
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
						var reportCommentVO = data[i];
		                html+="<tr>";
		                html+="<td>"+reportCommentVO.content+"</td>";
		                html+="<td><ul class=\"tableTime\"><li>"+reportCommentVO.debatepostTitle+"</li><li class=\"blue\"><a href=\"#\" class=\"openBtn1\" onclick=\"showDebatepostDetail("+reportCommentVO.debatepostId+")\">[查看话题详情]</a></li></ul></td>";
		                html+="<td>"+reportCommentVO.fromNickName+"</td>";
		                html+="<td><a href=\"#\" class=\"openBtn2\" onclick=\"showReportDetail('"+reportCommentVO.id+"')\">"+reportCommentVO.reportNum+"次</a></td>";
		                if(reportCommentVO.reportNum <5){
		                	html+="<td>审核中</td>";
		                	html+="<td>";
		                	html+="<button class=\"yellow_btn_small\" onclick=\"updateReport("+data[i].id+",5,"+curr+")\">通过</button>";
		                	html+="<button class=\"red_btn_small delBtn\" onclick=\"updateReport("+data[i].id+",2,"+curr+")\">删除</button>";
		                	html+="</td>";
		                }else{
		                	html+="<td>审核中</td>";
		                	html+="<td>";
		                	html+="<button class=\"yellow_btn_small\" onclick=\"updateReport("+data[i].id+",5,"+curr+")\">通过</button>";
		                	html+="<button class=\"red_btn_small delBtn\" onclick=\"updateReport("+data[i].id+",2,"+curr+")\">删除</button>";
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
						        	reportCommentList(obj.curr);
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

//显示帖子详情
function showDebatepostDetail(debatepostId){
	$.ajax({
		url: path + "/sociality/debatepostManager/showDetebapostDetail?debatepostId="+debatepostId,
		type: "get",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		success: function(json){
			console.log(json)
			if(json.code == 0){
				layer.alert(json.msgbox,{icon:2});
			}else{
				layer.open({
			        title:json.data.debatepostTitle,
			        type:1,
			        area:["1000px",""],
			        content:$(".showBox")
			    });
				$("#debatepost_content").html("");
				$(".SBimgBox").html("");
				$("#debatepost_content").html(json.data.debatepostContent);
				var img_html ="";
				var img_str = json.data.img;
				console.log(img_str);
				if(img_str != ""){
					var imgs = img_str.split(";");
					for(var i=0;i<imgs.length;i++){
						img_html+="<img src=\""+file_server_url+imgs[i]+"\" alt=\"1\">";
					}
					$(".SBimgBox").html(img_html);
				}
			}
		}
	});
}

//显示举报详情
function showReportDetail(busId){
	var param = {"busId":busId,"dataType":2};
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
function updateReport(busId,operatorType,curr){
	var param = {"busId":busId,"dataState":2,"dataType":2,"operatorType":operatorType};
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
					reportCommentList(curr);
					layer.close(index);
				});
			}
		}
	});
}