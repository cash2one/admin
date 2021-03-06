/**
 * 我的解答（已结束）js
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
		url:path+"/hospital/doctor/myAnswerends",
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
		   	 		"first": false,
		   	 		"keywords":keywords
		   	 	};
		   	 	$.ajax({
		   			url: path + "/hospital/doctor/myAnswerends",
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
		   					html += "<td>"+data[i].evaluate+"</td>";
		   					html += "<td><button type='button' class='red_btn_small' onClick='showComment("+
		   						data[i].id+",1,"+	data[i].user_id+","+data[i].doctor_id+",-1)'>咨询详情</button></td>";
//		   					html += "<td><button type='button' class='red_btn_small show_comments' id='"+data[i].id+
//		   					"' user_id='"+data[i].user_id+"' doctor_id='"+data[i].doctor_id+"'>咨询详情</button></td>";
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
	
//	//咨询详情
//	$(document).on("click",".show_comments",function(){
//		var ths = $(this);
//		$.ajax({
//			type:"get",//请求方式
//			url:"showComments",//发送请求地址
//		    data:{//发送给数据库的数据
//		    	 
//		    	//业务代码|服务类型
//		    	chatUrl:"/accounts/add",
//		    	updateUrl:"/im/sel_message",
//		    	serviceType:-1,
//		    	userId: ths.attr("user_id"),
//		    	doctor_id: ths.attr("doctor_id"),
//		    	isAdmin: 1,
//		    	consultantId:ths.attr("id")
//		    	
//		  	},
//	    	//请求成功后的回调函数有两个参数
//	    	success:function(result,textStatus){
//	    		var data = result.data;
//	    		var html = '';
//	    		var a1=/\.tempAu/;
//	    		var a2=/\.audioI/;
//	    		var b=/http/;
//	    		
//	    		for(var index = data.length-1;index>=0;index--){
//	    			if ("TIMImageElem"==data[index].msgType) {
//	   					html += "<tr><td >"+data[index].sendChatName+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].sendTime+"\n\t</td></tr>";
//	   					html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;<img src='"+data[index].msgContent+"'  class='img' height='"+data[index].height+"' width='"+data[index].width+"'></img></td></tr>";
//	    			}else if (data[index].msgType=="TIMSoundElem") {
//	    				html += "<tr><td >"+data[index].sendChatName+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].sendTime+"\n\t</td></tr>";
//	    				var a1T = a1.test(data[index].msgContent);
//	    				var a2T = a2.test(data[index].msgContent);
//	    				if(a1T || a2T){
//	    					if(b.test(data[index].msgContent)){
//	    						html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;"+
//		    						"<div><i id='xx_"+data[index].id+"' class='audioI' style='width:32px; height:32px; float:left;overflow:hidden;'>"+
//		    						"<img  src='"+path+"/assets/images/audio.png' style='width: 100%;  height: 100%;'></i><audio style='display:none;' class='aAudio' id='aaa_"+data[index].id+"' src='"+
//		    						data[index].msgContent+"'controls='controls'></audio><span style='float:left;margin:4px 0 0 5px;'>"+
//		    						data[index].size+"</span><i class='dataYl' style='display:none;'>"+data[index].size+"</i></div></td></tr>";
//	    					}else{
//		   						html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;"+
//		   							"<div><i id='xx_"+data[index].id+"' class='audioI' style='width:32px; height:32px; float:left;overflow:hidden;'>"+
//		   							"<img  src='"+path+"/assets/images/audio.png' style='width: 100%;  height: 100%;'></i><audio style='display:none;' class='aAudio' id='aaa_"+data[index].id+"' src='"+
//		   							data[index].filePath+data[index].msgContent+"'controls='controls'></audio><span style='float:left;margin:4px 0 0 5px;'>"+
//		   							data[index].size+"</span><i class='dataYl' style='display:none;'>"+data[index].size+"</i></div></td></tr>";
//	    					}
//	    				}else{
//	    					html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;发送了一条语音</img></td></tr>";
//	    				}
//					}else{
//	   					html += "<tr><td >"+data[index].sendChatName+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].sendTime+"\n\t</td></tr>";
//	   					html += "<tr><td >&nbsp;&nbsp;&nbsp;&nbsp;"+data[index].msgContent+"</td></tr>";
//					}
//	    		}
//	    		$("#msg_content").html(html);
//				ShowDiv(); 
//	    	}
//	    });
//	});
});
//------------------------------------
//放大图片
$(document).on("click",".img",function(){
	var img_width = $(this).attr("width");
	var img_height = $(this).attr("height");
	var win_width = $(document).width();
	var win_height = $(document).height();
	var height = "100%";
	var width = "100%";
	if(img_width > win_width - 50){
		width = win_width - 50;
		height = img_height * (width/img_width);
	}
	if(height > win_height - 50){
		height = win_height - 50;
		width = img_width * (height/img_height);
	}
//	alert("img_width="+img_width+",img_height="+img_height)
//	alert("win_width="+win_width+",win_height="+win_height)
//	alert("width="+width+",height="+height)
	layer.open({
		type: 1,
		title: false,
		closeBtn: 1,
		area: '[100%,100%]',
		skin: 'layui-layer-nobg', //没有背景色
		shadeClose: true,
		content: "<img src='"+$(this).attr("src")+"' height='"+height+"' width='"+width+"'/>"
	});
})


function leftAudio(opt){
	var message = '<div class="leftM" id="'+opt.msgId+'"><p class="timeM">'+opt.time+'</p><div class="centerM"><span class="nameM" title="'+opt.niceName+'">'+opt.niceName+'</span><p class="pointM"></p><i class="fl"><img src="'+recveImg+'"></i><p class="messageB fl"><i id="audio_'+opt.audioId+'" class="fl audioI"><img src="'+audioIcon+'"/></i><audio class="aAudio" style="display:none;" id="'+opt.audioId+'" src="'+opt.src+'" controls="controls"></audio><span class="fl" style="margin:2px 0 0 8px;">'+opt.size+'</span><i class="dataYl" style="display:none;">'+opt.size+'</i></p></div></div>';
	return message;
}
//播放语音
var timerNew = null;
var newNumber = 0;
$(document).on("click",".audioI",function(){
	var index = $(this).attr("id");
	for(var i = 0;i<$(".aAudio").length;i++){
		var xI = $(".aAudio").eq(i).prevAll().attr("id");
		if(xI != index){
			var id = $(".aAudio").eq(i).attr("id");
			var playAudio = document.getElementById(id);
			playAudio.pause();
		}
	}
	var _this = $(this);
	var id = $(this).next().attr("id");
	var playAudio = document.getElementById(id);
	var number = parseInt($(this).nextAll("span").text());
	var oldNumber = parseInt($(this).nextAll(".dataYl").text());
	if(playAudio.paused){
		clearInterval(timerNew);
		playAudio.play();
		timerNew = setInterval(function(){
			number = number - 1;
			_this.nextAll("span").text(number);
			if(number == -1){
				clearInterval(timerNew);
				_this.nextAll("span").text(oldNumber);
			}
		},1000)
	}else{
		playAudio.pause();
		clearInterval(timerNew);
	}
});
//------------------------------------
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
