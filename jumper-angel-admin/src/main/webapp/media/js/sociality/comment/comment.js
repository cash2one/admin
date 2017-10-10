//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	$('#searchComment').click(function (){
		 var keyword = $("#keyword").val();
		 if (keyword == ""||keyword==null||keyword==undefined){
			 return;
		 }
		 getPage();
	 });
	
});

//显示分页
function getPage(currIndex){
	 $.getJSON(path + "/sociality/commentManager/getCommentByContentOrNickName",{
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
				html += "<td>"+data[i].strDateTime+"</td>";  
				html += "<td>"+data[i].fromNickName+"</td>";
				html += "<td>"+data[i].content+"</td>";
				html += "<td>"+data[i].toNickName+"</td>";
//				html += "<td>"+data[i].debatepostTitle+"</td>";
				html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostTitle+"</li>" +
				"<li class=\"blue\">" +
				"<a href=\#\"  class=\"openBtn1\" onclick=\"showDebatepostDetail("+data[i].debatepostId+")\">[查看话题详情]</a>" +
				"</li></ul></td>";
				html += "<td><button class=\"red_btn_small\" onclick=\"deleteComment("+data[i].id+","+data[i].debatepostId+","+data[i].topicId+",'"+data[i].messageType+"')\">删除</button></td>";
//				html += "<td><a class=\"red_btn_small\" href='javascript:deleteComment("+data[i].id+","+data[i].messageType+");'>删除</a></td>";
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
/**
 * 删除评论
 */
function deleteComment(id,debatepostId,topicId,messageType) {
	var str="to";
	layer.confirm("确定删除该评论？", {
	  btn: ['确定', '取消']
	}, function(index, layero){
		if(messageType=="comment"){//删除评论
			//执行修改操作
			$.ajax({
				url: path + "/sociality/commentManager/deleteCommentById",
				type: "POST",
				dataType: "json",//从服务器端返回的数据类型
				data: {"cmtId":id,"debatepostId":debatepostId,"topicId":topicId},
				success: function(json) {
					//关闭当前对话框
					layer.close(index);
					layer.alert(json.msgbox);
					//获取当前页的数据
					getPage(curr);
				}
			});
		}else if(messageType=="docomment"){//删除回复
			//执行修改操作
			$.ajax({
				url: path + "/sociality/commentManager/deleteDocommentById",
				type: "POST",
				dataType: "json",//从服务器端返回的数据类型
				data: {"dctId":id,"debatepostId":debatepostId,"topicId":topicId},
				success: function(json) {
					//关闭当前对话框
					layer.close(index);
					layer.alert(json.msgbox);
					//获取当前页的数据
					getPage(curr);
				}
			});
		}
	}, function(index){
		//取消
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