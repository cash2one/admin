 $(document).ready(function(){
	 getTopicByBean();//加载话题组
	 $("#search_debatepost_button").on("click",function(){
		 getDebatepostList(1);
	 });
	 $(".selInput").keyup(function(){
		likeSearchTopic();
	 });
 });
 
//帖子管理  统计数据加载
 function initNumberCount(){
 	var topicId = $("#select_value").val();
 	$.ajax({
  		url : path + "/sociality/debatepostManager/numCount?topicId="+topicId,
 		type: "get",
 		dataType: "json",	//从服务器端返回的数据类型
 		async: false,
 		success: function(json) {
 			var currentTopDebatePostCount = json.data.currentTopDebatePostCount;				//当前话题帖子总数
 			var currentTopIncreaseDebatePostCount = json.data.currentTopIncreaseDebatePostCount;	//今日当前话题新增帖子数
 			var currentTopDebatePostComCount = json.data.currentTopDebatePostComCount;			//今日当前话题帖子评论数
 			
 			$('.currentTopDebatePostCount').html(currentTopDebatePostCount);	//当前话题帖子总数
 			$('.currentTopIncreaseDebatePostCount').html(currentTopIncreaseDebatePostCount);//今日当前话题新增帖子数
 			$('.currentTopDebatePostComCount').html(currentTopDebatePostComCount);//今日当前话题帖子评论数
 			
 		}
 	});
 }
 
 
 
//话题组集合
var topicJSONArray = [];
 //查询所有话题组信息
function getTopicByBean(){
 	var topicVO = {"topicName":$(".selInput").val()};
 	$.ajax({
		url: path + "/sociality/topicManager/getTopicByBean",
		type: "get",
		contentType:"application/json",
		dataType: "json",//从服务器端返回的数据类型
		data:topicVO,
		success: function(json){
			console.log(json)
			if(json.code == 1){
				topicJSONArray = json.data;
				if(json.data.length >0){
					var select_html = "";
					for(var i=0;i<json.data.length;i++){
						select_html+= "<a javascript:void(0) onclick=\"selectTopicName("+json.data[i].topicId+")\">"+json.data[i].topicName+"</a>";
					}
					$("#select_topic_name").html(select_html);
				}
			}
		}
 	});
} 
//选择一个话题 
function selectTopicName(topicId){
	$("#select_value").val(topicId);
	for(var i=0;i<topicJSONArray.length;i++){
		if(topicJSONArray[i].topicId == topicId){
			$(".select_txt").html(topicJSONArray[i].topicName);
		}
	}
	getDebatepostList(1);
	initNumberCount();//
	$("#display").show();
}

//对下拉框的话题list进行搜索
function likeSearchTopic(){
	var setInputValue = $(".selInput").val();
	var select_html = "";
	if(setInputValue == ''){
		for(var i=0;i<topicJSONArray.length;i++){
			select_html+= "<a javascript:void(0) onclick=\"selectTopicName("+topicJSONArray[i].topicId+")\">"+topicJSONArray[i].topicName+"</a>";
		}
	}else{
		for(var i=0;i<topicJSONArray.length;i++){
			if(topicJSONArray[i].topicName.indexOf(setInputValue) ==0){
				select_html+= "<a javascript:void(0) onclick=\"selectTopicName("+topicJSONArray[i].topicId+")\">"+topicJSONArray[i].topicName+"</a>";
			}
		}
	}
	$("#select_topic_name").html(select_html);
}


//查询帖子并且分页
function getDebatepostList(curr){//curr 访问的页面的下标
	if($("#select_value").val() ==""){//没选择话题组的时候在所有话题组内查询
		$.ajax({
			url: path + "/sociality/debatepostManager/getAllDebatepostList",
			type: "get",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			data:{
			    "beginIndex":curr || 1,"everyPage":15,
//			    "topicId":$("#select_value").val(),
			    "keyword":$("#keyword").val(),
			    "status":$("#status").val(),
			    "timestamp":new Date().getTime()
			    },
			success: function(json){
				if(json.code == 1){
					 	var page = json.data.page;
					    var data = json.data.content;
						var html = "";
						console.log(data);
						$("#tbody").html("");
						for(var i=0; i<data.length; i++) {
							html += "<tr height='40px'>";
							html += "<td>"+data[i].topicName+"</td>"; //话题组
							html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostTitle+"</li>" +
									"<li class=\"blue\">" +
									"<a href=\#\"  class=\"openBtn1\" onclick=\"showDebatepostDetail("+data[i].debatepostId+")\">[查看话题详情]</a>" +
									"</li></ul></td>";
							if(data[i].commentNumber>0){
								html += "<td><a href=\"#\" onclick=\"showCommentDetail("+data[i].debatepostId+","+data[i].topicId+","+curr+")\" class=\"openBtn2\">"+data[i].commentNumber+"条</a></td>";
							}else{
								html += "<td><a href=\"#\" class=\"openBtn2\">"+data[i].commentNumber+"条</a></td>";
							}
							html += "<td>"+data[i].pointPraise+"个</td>";
							html += "<td>"+data[i].readNumber+"个</td>";
							var nickName = data[i].nickName ==null ?"":data[i].nickName;
							html += "<td>"+nickName+"</td>";
							if(data[i].debatepostDateTime !="" && data[i].debatepostDateTime !=null){
								html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostDateTime.substring(0,10)+"</li><li>"+data[i].debatepostDateTime.substring(11,19)+"</li></ul></td>";
							}else{
								html += "<td><ul class=\"tableTime\"><li></li><li></li></ul></td>";
							}
							if(data[i].isDelete == 0){
								if(data[i].whetherPopular ==0){
									html += "<td>正常</td>";
								}else{
									html += "<td>推广</td>";
								}
							}else if(data[i].isDelete == 1){
								html += "<td>隐藏</td>";
							}
							if(data[i].isDelete == 0){
								if(data[i].whetherPopular ==0){
									html += "<td>" +
											"<button class=\"green_btn_small\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",1,"+curr+")\">隐藏</button>" +
											"<button class=\"red_btn_small delBtn\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",2,"+curr+")\">删除</button>" +
											"<button class=\"yellow_btn_small\" onclick=\"setOrancelwhetherPopularDebatepost("+data[i].debatepostId+",1,"+curr+")\">推广</button></td>";
								}else{
									html += "<td><button class=\"yellow_btn_small\" onclick=\"setOrancelwhetherPopularDebatepost("+data[i].debatepostId+",0,"+curr+")\">取消推广</button></td>";
								}
							}else if(data[i].isDelete == 1){
								html += "<td><button class=\"green_btn_small\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",0,"+curr+")\">恢复显示</button>" +
										"<button class=\"red_btn_small delBtn\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",2,"+curr+")\">删除</button></td>";
							}
							html += "</tr>";
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
							        	getDebatepostList(obj.curr);
							        }
							      }
						  	});
						});
				}else{
					$("#tbody").html("");
				}
			}
		});
	}else{//选择话题组的时候在选中话题组内查询
		$.ajax({
			url: path + "/sociality/debatepostManager/getDebatepostList",
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
						var html = "";
						console.log(data);
						$("#tbody").html("");
						for(var i=0; i<data.length; i++) {
							html += "<tr height='40px'>";
							html += "<td>"+data[i].topicName+"</td>"; //话题组
							html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostTitle+"</li>" +
									"<li class=\"blue\">" +
									"<a href=\#\"  class=\"openBtn1\" onclick=\"showDebatepostDetail("+data[i].debatepostId+")\">[查看话题详情]</a>" +
									"</li></ul></td>";
							if(data[i].commentNumber>0){
								html += "<td><a href=\"#\" onclick=\"showCommentDetail("+data[i].debatepostId+","+data[i].topicId+","+curr+")\" class=\"openBtn2\">"+data[i].commentNumber+"条</a></td>";
							}else{
								html += "<td><a href=\"#\" class=\"openBtn2\">"+data[i].commentNumber+"条</a></td>";
							}
							html += "<td>"+data[i].pointPraise+"个</td>";
							html += "<td>"+data[i].readNumber+"个</td>";
							var nickName = data[i].nickName ==null ?"":data[i].nickName;
							html += "<td>"+nickName+"</td>";
							if(data[i].debatepostDateTime !="" && data[i].debatepostDateTime !=null){
								html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostDateTime.substring(0,10)+"</li><li>"+data[i].debatepostDateTime.substring(11,19)+"</li></ul></td>";
							}else{
								html += "<td><ul class=\"tableTime\"><li></li><li></li></ul></td>";
							}
							if(data[i].isDelete == 0){
								if(data[i].whetherPopular ==0){
									html += "<td>正常</td>";
								}else{
									html += "<td>推广</td>";
								}
							}else if(data[i].isDelete == 1){
								html += "<td>隐藏</td>";
							}
							if(data[i].isDelete == 0){
								if(data[i].whetherPopular ==0){
									html += "<td>" +
											"<button class=\"green_btn_small\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",1,"+curr+")\">隐藏</button>" +
											"<button class=\"red_btn_small delBtn\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",2,"+curr+")\">删除</button>" +
											"<button class=\"yellow_btn_small\" onclick=\"setOrancelwhetherPopularDebatepost("+data[i].debatepostId+",1,"+curr+")\">推广</button></td>";
								}else{
									html += "<td><button class=\"yellow_btn_small\" onclick=\"setOrancelwhetherPopularDebatepost("+data[i].debatepostId+",0,"+curr+")\">取消推广</button></td>";
								}
							}else if(data[i].isDelete == 1){
								html += "<td><button class=\"green_btn_small\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",0,"+curr+")\">恢复显示</button>" +
										"<button class=\"red_btn_small delBtn\" onclick=\"showOrHideOrDelDebatepost("+data[i].debatepostId+","+data[i].topicId+",2,"+curr+")\">删除</button></td>";
							}
							html += "</tr>";
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
							        	getDebatepostList(obj.curr);
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
}

//显示或隐藏或删除帖子
function showOrHideOrDelDebatepost(debatepostId,topicId,isDelete,curr){
	var param = {"debatepostId":debatepostId,"topicId":topicId,"isDelete":isDelete};
	$.ajax({
			url: path + "/sociality/debatepostManager/disableOrEnableOrDeleteDetebapost",
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
						getDebatepostList(curr);
						layer.close(index);
   					});
				}
			}
	});
}
//设置或取消设置推广
function setOrancelwhetherPopularDebatepost(debatepostId,whetherPopular,curr){
	var param = {"debatepostId":debatepostId,"whetherPopular":whetherPopular, "isDelete":0};
	$.ajax({
			url: path + "/sociality/debatepostManager/setOrCancelwhetherPopularDebatepost",
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
						getDebatepostList(curr);
						layer.close(index);
   					});
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
function showCommentDetail(debatepostId,topicId,curr){
	layer.open({
		title:"评论详情",
		type:1,
		area:["1000px",""],
		content:$(".showBox2"),
		cancel: function(index){ 
			getDebatepostList(curr);//关闭评论详情页面刷新父页面的列表
		}  
	});
	getCommentDetail(debatepostId,topicId);
}

//获取评论详情数据
function getCommentDetail(debatepostId,topicId){
	$.ajax({
		url: path + "/sociality/commentManager/getCommentAndDocommentList?debatepostId="+debatepostId+"&topicId="+topicId,
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
						if(messageType == 'comment'){
							html += "<td><button class=\"red_btn_small delBtn\" onclick=\"deleteCommentById("+json.data[i].id+","+json.data[i].debatepostId+","+json.data[i].topicId+")\">删除</button></td>";
						}else{
							html += "<td><button class=\"red_btn_small delBtn\" onclick=\"deleteDocommentById("+json.data[i].id+","+json.data[i].debatepostId+","+json.data[i].topicId+")\">删除</button></td>";
						}
						html += "</tr>";
					}
					$("#comment_tbody").html(html);
			  	}
			}
		}
	});
}

//删除评论
function deleteCommentById(cmtId,debatepostId,topicId){
	$.ajax({
			url: path + "/sociality/commentManager/deleteCommentById",
			type: "post",
			dataType: "json",//从服务器端返回的数据类型
			data:{"cmtId":cmtId,"debatepostId":debatepostId,"topicId":topicId},
			success: function(json){
				console.log(json)
				if(json.code == 0){
					layer.alert(json.msgbox,{icon:2});
				}else{
					layer.alert(json.msgbox,{icon:1},function(index){
						layer.close(index);
						getCommentDetail(debatepostId,topicId);
   					});
				}
			}
	});
}

//删除回复
function deleteDocommentById(dctId,debatepostId,topicId){
	$.ajax({
			url: path + "/sociality/commentManager/deleteDocommentById",
			type: "post",
			dataType: "json",//从服务器端返回的数据类型
			data:{"dctId":dctId,"debatepostId":debatepostId,"topicId":topicId},
			success: function(json){
				console.log(json)
				if(json.code == 0){
					layer.alert(json.msgbox,{icon:2});
				}else{
					layer.alert(json.msgbox,{icon:1},function(index){
						layer.close(index);
						getCommentDetail(debatepostId,topicId);
   					});
				}
			}
	});
}