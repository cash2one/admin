//查询出所有话题组
var topicArray = new Array();

$(document).ready(function() {
	getTopicByBean();//加载话题组
	$("#search_user_topic_button").on("click",function(){
		getUserTopicList();
	});
	$(".selInput").keyup(function(){
		likeSearchTopic();
	});
});

//成员管理  统计数据加载
function initNumberCount(){
	var topicId = $("#select_value").val();
	$.ajax({
 		url : path + "/sociality/userTopicManager/numCount?topicId="+topicId,
		type: "get",
		dataType: "json",	//从服务器端返回的数据类型
		async: false,
		success: function(json) {
			var currentUserCount = json.data.currentUserCount;		//当前话题总人数
			var currentIncreaseCount = json.data.currentIncreaseCount;		//今日当前话题新增人数
			
			$('.current_user_count').html(currentUserCount);	//当前话题总人数
			$('.current_increase_count').html(currentIncreaseCount);//今日当前话题新增人数
			
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
						topicArray = json.data;
						for(var i=0;i<json.data.length;i++){
							select_html+= "<a javascript:void(0) onclick=\"selectTopicName("+json.data[i].topicId+")\">"+json.data[i].topicName+"</a>";
						}
						$("#select_topic_name").html(select_html);
					}
				}
			}
	});
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

//选择下拉框的话题标题在查询
function selectTopicName(topicId){
	$("#select_value").val(topicId);
	for(var i=0;i<topicJSONArray.length;i++){
		if(topicJSONArray[i].topicId == topicId){
			$(".select_txt").html(topicJSONArray[i].topicName);
		}
	}
	//查第一页数据
	getUserTopicList(1);
	initNumberCount();
	$("#display").show();
}

//查询用户话题
function getUserTopicList(curr){//curr 访问的页面的下标
	if($("#select_value").val() == ""){//没选择话题组的时候全局查询
		$.getJSON(path + "/sociality/userTopicManager/getUserAllTopicList",{
			"beginIndex":curr || 1,
		    "everyPage":15,
//		    "topicId":$("#select_value").val(),
		    "nickName":$("#search_nick_name_or_mobile").val(),
		    "mobile":$("#search_nick_name_or_mobile").val(),
		    "isBlacklist":$("#search_is_black_list").val(),
		    "timestamp":new Date().getTime()
		  }, function(res){
			  console.log(res);
			  if(res.code ==1){
			    var page = res.data.page;
			    var data = res.data.content;
				var html = "";
				$("#tbody").html("");
				for(var i=0; i<data.length; i++) {
					var nickName = data[i].nickName ==null?"":data[i].nickName;
					html += "<tr height='40px'>";
					html += "<td>"+data[i].topicName+"</td>";
					html += "<td>"+data[i].mobile+"</td>";
					html += "<td>"+nickName+"</td>";
					html += "<td><ul class=\"tableTime\"><li>"+data[i].createDateTime.substring(0,10)+"</li><li>"+data[i].createDateTime.substring(11,19)+"</li></ul></td>";
					if(data[i].debatepostDateTime !=""){
						html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostDateTime.substring(0,10)+"</li><li>"+data[i].debatepostDateTime.substring(11,19)+"</li></ul></td>";
					}else{
						html += "<td><ul class=\"tableTime\"><li></li><li></li></ul></td>";
					}
					if(data[i].isBlacklist == 0||data[i].isBlacklist==null){
						html += "<td>正常</td>";
					}else{
						html += "<td>禁言中</td>";
					}
					if(data[i].isBlacklist == 0||data[i].isBlacklist==null){
						html += "<td><button class=\"red_btn_small\" onclick=\"disableOrEnableUserTopic("+data[i].id+","+data[i].userId+",'"+data[i].nickName+"',"+data[i].mobile+",1,"+curr+")\">禁言</button></td>";
					}else{
						html += "<td><button class=\"blue_btn_small\" onclick=\"disableOrEnableUserTopic("+data[i].id+","+data[i].userId+",'"+data[i].nickName+"',"+data[i].mobile+",0,"+curr+","+data[i].topicId+")\">解除禁言</button></td>";
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
					        	getUserTopicList(obj.curr);
					        }
					      }
				  	});
				});
			  }else{
				  $("#tbody").html("");  
			  }
		  });
	}else{//选择话题组的时候在选中话题组内查询
		$.getJSON(path + "/sociality/userTopicManager/getUserTopicList",{
		    "beginIndex":curr || 1,
		    "everyPage":15,
		    "topicId":$("#select_value").val(),
		    "nickName":$("#search_nick_name_or_mobile").val(),
		    "mobile":$("#search_nick_name_or_mobile").val(),
		    "isBlacklist":$("#search_is_black_list").val(),
		    "timestamp":new Date().getTime()
		  }, function(res){
			  console.log(res);
			  if(res.code ==1){
			    var page = res.data.page;
			    var data = res.data.content;
				var html = "";
				$("#tbody").html("");
				for(var i=0; i<data.length; i++) {
					var nickName = data[i].nickName ==null?"":data[i].nickName;
					html += "<tr height='40px'>";
					html += "<td>"+data[i].topicName+"</td>";
					html += "<td>"+data[i].mobile+"</td>";
					html += "<td>"+nickName+"</td>";
					html += "<td><ul class=\"tableTime\"><li>"+data[i].createDateTime.substring(0,10)+"</li><li>"+data[i].createDateTime.substring(11,19)+"</li></ul></td>";
					if(data[i].debatepostDateTime !=""){
						html += "<td><ul class=\"tableTime\"><li>"+data[i].debatepostDateTime.substring(0,10)+"</li><li>"+data[i].debatepostDateTime.substring(11,19)+"</li></ul></td>";
					}else{
						html += "<td><ul class=\"tableTime\"><li></li><li></li></ul></td>";
					}
					if(data[i].isBlacklist == 0||data[i].isBlacklist==null){
						html += "<td>正常</td>";
					}else{
						html += "<td>禁言中</td>";
					}
					if(data[i].isBlacklist == 0||data[i].isBlacklist==null){
						html += "<td><button class=\"red_btn_small\" onclick=\"disableOrEnableUserTopic("+data[i].id+","+data[i].userId+",'"+data[i].nickName+"',"+data[i].mobile+",1,"+curr+")\">禁言</button></td>";
					}else{
						html += "<td><button class=\"blue_btn_small\" onclick=\"disableOrEnableUserTopic("+data[i].id+","+data[i].userId+",'"+data[i].nickName+"',"+data[i].mobile+",0,"+curr+","+data[i].topicId+")\">解除禁言</button></td>";
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
					        	getUserTopicList(obj.curr);
					        }
					      }
				  	});
				});
			  }else{
				  $("#tbody").html("");  
			  }
		  });
	}
}

//启用或禁用用户所在的话题组
/*function disableOrEnableUserTopic(id,isBlacklist,curr){//curr 访问的页面的下标
	var topicVO = {"id":id,"isBlacklist":isBlacklist};
	console.log(topicVO);
	$.ajax({
			url: path + "/sociality/userTopicManager/disableOrEnableUserTopic",
			type: "post",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			data: JSON.stringify(topicVO),
			success: function(json) {
				console.log(json);
				if(json.code == 0){
					layer.alert(json.msgbox,{icon:2});
				}else{
					layer.alert(json.msgbox,{icon:1},function(index){
						layer.close(index);
						getUserTopicList(curr);
					});
				}
			}
	});
}*/

function disableOrEnableUserTopic(id,userId,nickName,mobile,isBlacklist,curr,topicId){//curr 访问的页面的下标
	var topicVO = {"topicName":""};
	var title = "";
	if(isBlacklist==1){
		title = "用户禁言";
		$.ajax({
			url: path + "/sociality/topicManager/getTopicByBean",
			type: "get",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			data:topicVO,
			success: function(json){
				console.log(json)
				if(json.code == 1){
					if(json.data.length >0){
						var checkbox_html ="<form id=topicForm><div height='70px'>";
						for(var i=1;i<=json.data.length;i++){
							if(isBlacklist==1){
								checkbox_html+= "<label><input type=\"checkbox\" name="+json.data[i-1].topicName+" checked=\"checked\"  value="+json.data[i-1].topicId+"><span>"+json.data[i-1].topicName+"</span></label>";
							}else{
								checkbox_html+= "<label><input type=\"checkbox\" name="+json.data[i-1].topicName+" value="+json.data[i-1].topicId+"><span>"+json.data[i-1].topicName+"</span></label>";
							}
							if(i%3==0){
								checkbox_html+="</div></br>";
								checkbox_html+="<div height='70px'>";
							}
						}
						checkbox_html+="</form>";
						$("#checkboxTopic").html(checkbox_html);
						layer.open({
							title : title,
							type : 1,
							area : [ '700px', '590px' ], // 宽高
							content : $('.normal_form2'),
							btn : [ '确定', '取消' ],
							yes : function(index) {
								var v = [];//存放话题ID
								var fields = $('#topicForm').serializeArray();
								jQuery.each(fields, function(i, field){
									v.push(field.value == '' ? '' : field.value.trim());
								});
								var ids = "";
								for(var i = 0;i<v.length;i++){
									if(i<v.length-1){
										ids+=v[i]+",";
									}else if(i==v.length-1){
										ids+=v[i];
									}
								}
							//	var topicPO={"id":id,"isBlacklist":isBlacklist,"topicIds":JSON.stringify(ids)};
								var topicPO={"id":id,"userId":userId,"nickName":nickName,"isBlacklist":isBlacklist,"topicIds":ids};
								$.ajax({
									url : path + "/sociality/userTopicManager/forbiddenSpeaking",
									type : "post",
									contentType : "application/json",
									dataType : "json",// 从服务器端返回的数据类型
									data : JSON.stringify(topicPO),
//									data : topicPO,
									success : function(json) {
										layer.close(index);
										if (json.code == 0) {
											layer.close(index);
					  	  					layer.msg("禁言失败", {
					  	  		      			icon: 2
					  	  		    		});
										} else {
											layer.close(index);
					  	  					layer.msg("禁言成功", {
					  	  		      			icon: 1
					  	  		    		});
					  	  					getUserTopicList(curr);
										}
									}
								});
							},
							cancel : function(index) {
								layer.close(index);
							}
						});
					}
				}
			}
	});
	}else if(isBlacklist==0){
		title = "解除禁言";
		layer.alert('确定解除禁言？', {
            icon: 0,
            title: '提示',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
            	layer.close(index);
            	var topicPO={"id":id,"userId":userId,"nickName":nickName,"isBlacklist":isBlacklist,"topicIds":topicId};
            	$.ajax({
					url : path + "/sociality/userTopicManager/forbiddenSpeaking",
					type : "post",
					contentType : "application/json",
					dataType : "json",// 从服务器端返回的数据类型
					data : JSON.stringify(topicPO),
//					data : topicPO,
					success : function(json) {
						layer.msg("已解除禁言", {
        	      			icon: 1
        	 //     			shade: [0.5,'#000'],
        	 //     			btn: ['确定']
        	    		});
        				if(json.code==1){
        				//	$this.parent().parent().remove();
        					getUserTopicList(curr);
        				}
					}
            	});
            }
		});
	}
}

//function showDiv(mobile,isBlacklist,curr){//curr 访问的页面的下标
//	var topicVO = {"mobile":mobile,"isBlacklist":isBlacklist};
//	console.log(topicVO);
//	var title = "";
//	if(isBlacklist==1){
//		title = "用户禁言";
//	}else{
//		title = "解除禁言";
//	}
//	var html = "";
//	html += "<tr height='40px'>";
//	for(var i=0;i<topicArray.length();i++){
//		html += "<td><input type=\"checkbox\" id="+topicArray[i].topicId+">"+topicArray[i].topicName+"</input>";
//	}
//	layer.open({
//		title : title,
//		type : 1,
//		area : [ '700px', '590px' ], // 宽高
//		content : $('.normal_form2'),
//		content : html,
//		btn : [ '确定', '取消' ],
//		yes : function(index) {
			/*var topicName = $("#topic_name").val();
			if (topicName == "") {
				$("#topic_name_error").html("名称不能为空!");
				$("#topic_name_error").show();
				return;
			} 
			var topicVO = {
				"topicName" : topicName,
				"topicProfile" : topicProfile,
				"thematicImg" : thematicImg,
				"topicId" : $("#topic_id").val()
			};
			$.ajax({
				url : requestURL,
				type : "post",
				contentType : "application/json",
				dataType : "json",// 从服务器端返回的数据类型
				data : JSON.stringify(topicVO),
				success : function(json) {
					layer.close(index);
					if (json.code == 0) {
						layer.close(index);
  	  					layer.msg(json.msgbox, {
  	  		      			icon: 2
  	  		    		});
					} else {
						layer.close(index);
  	  					layer.msg(json.msgbox, {
  	  		      			icon: 1
  	  		    		});
  	  					getTopicList(curr);
					}
				}
			});*/
//		},
//		cancel : function(index) {
//			layer.close(index);
//		}
//	});
//}