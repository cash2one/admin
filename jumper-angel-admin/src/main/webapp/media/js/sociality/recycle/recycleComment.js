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
	 $.getJSON(path + "/sociality/recycleManager/getDeletedComment",{
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
				html += "<td>"+data[i].fromNickName+"</td>";
				html += "<td>"+data[i].content+"</td>";
				html += "<td>"+data[i].toNickName+"</td>";
				html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostTitle+"</li>" +
				"<li class=\"blue\">" +
				"<a href=\#\"  class=\"openBtn1\" onclick=\"showDebatepostDetail("+data[i].debatepostId+")\">[查看话题详情]</a>" +
				"</li></ul></td>";
				html += "<td>"+data[i].strDateTime+"</td>";
				html += "<td>"+data[i].deletedTime+"</td>";
				html += "<td>"+data[i].deleteAdmin+"</td>";
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

$(function(){
	$('#searchComment').click(function (){
		 var keyword = $("#keyword").val();
		 if (keyword == ""||keyword==null||keyword==undefined){
			 return;
		 }
		 getPage(1);
	 });
})

