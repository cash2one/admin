//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
/*	$('#searchComment').click(function (){
		 var keyword = $("#keyword").val();
		 if (keyword == ""||keyword==null||keyword==undefined){
			 return;
		 }
		 getPage(1);
	 });*/
	getPage(1);
});

//显示分页
function getPage(currIndex){
	 $.getJSON(path + "/sociality/recycleManager/getDeletedDebatepost",{
		"beginIndex":currIndex || 1,
	    "everyPage":15,
	    "keyword":$("#keyword").val(),
	    "timestamp":new Date().getTime()
	 }, function(res){
		 var page = res.data.page;
		    var data = res.data.content;
		    $("#tbody").html("");
			var html = "";
			for(var i=0; i<data.length; i++) {
				html += "<tr height='40px'>";
				html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostTitle+"</li>" +
				"<li class=\"blue\">" +
				"<a href=\#\"  class=\"openBtn1\" onclick=\"showDebatepostDetail("+data[i].debatepostId+")\">[查看话题详情]</a>" +
				"</li></ul></td>";
//				html += "<td>"+data[i].commentNumber+"条</td>";
				if(data[i].commentNumber>0){
					html += "<td><a href=\"#\" onclick=\"showCommentDetail("+data[i].debatepostId+","+data[i].topicId+","+currIndex+")\" class=\"openBtn2\">"+data[i].commentNumber+"条</a></td>";
				}else{
					html += "<td><a href=\"#\" class=\"openBtn2\">"+data[i].commentNumber+"条</a></td>";
				}
				html += "<td>"+data[i].pointPraise+"个</td>";
				html += "<td>"+data[i].readNumber+"个</td>";
				html += "<td>"+data[i].nickName+"</td>";
				html += "<td>"+data[i].debatepostDateTime+"</td>";
				html += "<td>"+data[i].deletedTime+"</td>";
				html += "<td>"+data[i].deletedAdmin+"</td>";
				html += "</tr>";
			}
		$("#tbody").html(html);
		//显示分页
		layui.use(["laypage"], function(){
			var laypage = layui.laypage;
			layui.laypage({
				  cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
			      pages:page.totalPage, //通过后台拿到的总页数
			      curr: currIndex || 1, //当前页
			      jump: function(obj,first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			        	getPage(obj.curr);
			        }
			      }
		  	});
		});
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
				if(img_str != ""){
					var imgs = img_str.split(";");
					for(var i=0; i<imgs.length; i++){
						if (imgs[i].length != 0){
							img_html += "<img src=\""+imgs[i]+"\">";
						}
					}
				}
				$(".SBimgBox").html(img_html);
			}
		}
	});
}

//显示评论详细
function showCommentDetail(debatepostId,topicId,currIndex){
	layer.open({
		title:"评论详情",
		type:1,
		area:["1000px",""],
		content:$(".showBox2"),
		cancel: function(index){ 
			getPage(currIndex);//关闭评论详情页面刷新父页面的列表
		}  
	});
	getCommentDetail(debatepostId,topicId);
}

//获取已删除的评论详情数据
function getCommentDetail(debatepostId,topicId){
	$.ajax({
		url: path + "/sociality/commentManager/getDelCommentAndDocommentList?debatepostId="+debatepostId+"&topicId="+topicId,
		type: "get",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		success: function(json){
			console.log(json)
			if(json.code == 0){
				layer.alert(json.msgbox,{icon:2});
			}else{
				$("#comment_tbody").html("");
			  	if(json.data.length >0){
			  		var html = "";
					console.log(json.data);
					for(var i=0; i<json.data.length; i++) {
						var messageType = json.data[i].messageType;
						var fromNickName = json.data[i].fromNickName ==null?"":json.data[i].fromNickName;
						var toNickName = json.data[i].toNickName ==null?"":json.data[i].toNickName;
						//评论
						html += "<tr>";
						html += "<td><ul class=\"tableTime\"><li>"+json.data[i].strDateTime.substring(0,10)+"</li><li>"+json.data[i].strDateTime.substring(11,19)+"</li></ul></td>";
						html += "<td>"+fromNickName+"</td>";
						html += "<td>"+json.data[i].content+"</td>";
						html += "<td>"+toNickName+"</td>";
/*						if(messageType == 'comment'){
							html += "<td><button class=\"red_btn_small delBtn\" onclick=\"deleteCommentById("+json.data[i].id+","+json.data[i].debatepostId+")\">删除</button></td>";
						}else{
							html += "<td><button class=\"red_btn_small delBtn\" onclick=\"deleteDocommentById("+json.data[i].id+","+json.data[i].debatepostId+")\">删除</button></td>";
						}*/
						html += "</tr>";
					}
					$("#comment_tbody").html(html);
			  	}
			}
		}
	});
}

$(function(){
	$('#searchComment').click(function (){
		 var keyword = $("#keyword").val();
		 if (keyword == ""||keyword==null||keyword==undefined){
			 return;
		 }
		 getPage(1);
	 });
})