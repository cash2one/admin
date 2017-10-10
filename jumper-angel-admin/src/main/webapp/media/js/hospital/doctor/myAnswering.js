/**
 * 我的解答（回复中）js
 */
//请求总页数
var totalPage = 0;
$(document).ready(function(){
	var keywords = "";
	getPage(keywords);
});

//显示分页
function getPage(keywords){
	$.ajax({
		url:path+"/hospital/doctor/myAnswerings",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords,chatUrl:"/accounts/add",url:"/im/sel_latest_question"},
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
		   	 		"first": false,
		   	 		"keywords":keywords,
		   	 		"chatUrl":"/accounts/add",
		   	 		"url":"/im/sel_latest_question"
		   	 	};
		   	 	$.ajax({
		   			url: path + "/hospital/doctor/myAnswerings",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var status = "";
		   				for(var i=0; i<data.length; i++) {
		   					if(data[i].content.length>15){
		   						moreStr = data[i].content.substring(0, 15)+"...<a href='javascript:void(0);' title='"+data[i].content+"' class='blue tips'>[全文]</a></td>";
		   					}else{
		   						moreStr = data[i].content;
		   					}
		   					status = data[i].isVisible==0?"公开":"隐藏";
		   					html += "<tr height='40px'>";
		   					html += "<td>"+data[i].nick_name+"</td>";
		   					html += "<td>"+data[i].real_name+"</td>";
		   					html += "<td style='word-break: break-all; width: 350px;' class='table-longtext'>"+moreStr+"</td>";
		   					html += "<td>"+data[i].major_name+"</td>";
		   					html += "<td>"+data[i].add_time+"</td>";
		   					html += "<td>"+data[i].statusDisplay+"</td>";
		   					html += "<td><button type='button' class='green_btn_small reply_topic' id='"+data[i].id+
		   							"' fromUserId='"+data[i].doctor_id+"' toUserId='"+data[i].user_id+"' toNickName='"+data[i].nick_name+
		   							"' com_path='"+data[i].commonPath+"'>回复话题</button> ";
		   					html += " <button type='button' class='red_btn_small close_topic' id='"+data[i].id+"'>关闭咨询</button></td>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}
$(function(){
	//查看问题全文
	$(document).on("click",".tips",function(){
		var info = $(this).attr("title");
		layer.tips(info, this, {
			  tips: [2, '#3595CC'],
			});
	});
	
	//关键字查询
	$(document).on("click","#searchItem",function(){
		var keywords = $("#keywords").val();
		getPage(keywords);
	});
	
	//回复话题
	$(document).on("click",".reply_topic",function(){
		var com_path = $(this).attr("com_path");
		var fromUserId = $(this).attr("fromUserId");
		var toUserId = $(this).attr("toUserId");
		var toNickName = $(this).attr("toNickName");
		var comsultantId = $(this).attr("id");
		var src = com_path + "/chat/init?fromUserId="+
			fromUserId+"&fromNickName=天使医生&fromHeadUrl&fromUserType=4&toUserId="+
			toUserId+"&toNickName="+
			toNickName+"&toHeadUrl&toUserType=2&userType=1&busCode=10154&color=2&consultantId="+
			comsultantId;
			//alert(src);
		console.log(src);
		$("#user").attr("src",src);
		ShowDiv();
	});
	
	//关闭咨询
	$(document).on("click",".close_topic",function(){
		var ths = $(this);
		layer.alert($("#generalize").val(), {
		      title:'提示',
		      btn:['确定', '返回'],
		      content: '确定要关闭该咨询吗？',
		      btnAlign: 'c',
		      yes: function(){
		    	  deletConsult(ths);
		          layer.msg('操作成功', {time: 600});
		      }
		  });
	});
});
function ShowDiv(){
	layer.open({
		type: 3,
		title:'聊天页面',
  		type: 1, //宽高
  		area: ['620px','700px'], //宽高
		shadeClose:false,
  		content: $('.normal_form')
	});
} 
function deletConsult(ths){
	$.ajax({
		type:"get",//请求方式
		url:"deletConsult",//发送请求地址
	    data:{//发送给数据库的数据
	    	consultId: ths.attr("id")
	  	},
    	//请求成功后的回调函数有两个参数
    	success:function(data,textStatus){
    		window.location.reload();
    	}
    });
}
