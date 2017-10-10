/**
 * 话题组
 */
function getTopicList(curr){ //curr 访问的页面的下标
  $.getJSON(path + "/sociality/topicManager/getTopicList",{
    "beginIndex":curr || 1,
    "everyPage":15,
    "isDelete":$("#is_delete").val(),
    "topicName":$("#like_topic_name").val(),
    "timestamp":new Date().getTime()
  }, function(res){
	    var page = res.data.page;
	    var data = res.data.content;
	    $("#tbody").html("");
		var html = "";
		for(var i=0; i<data.length; i++) {
			//var userNickName = data[i].userNickName ==null?"":userNickName;
			html += "<tr height='40px'>";
			html += "<td>"+data[i].positionId+"</td>";
			html += "<td>"+data[i].topicName+"</td>";
			if(data[i].topicProfile.length > 20){
				html += "<td>"+data[i].topicProfile.substring(0,20)+"...<a href='javascript:' class='blue' value='"+data[i].topicProfile+"'>[全文]</a></td>";
			}else{
				html += "<td>"+data[i].topicProfile+"</td>";
			}
			var userName = data[i].userName;
			if(null == userName){
				userName ="";
			}
			html += "<td>"+userName+"</td>";
			html += "<td><ul class=\"tableTime\"><li>"+data[i].createDateTime.substring(0,10)+"</li><li>"+data[i].createDateTime.substring(11,19)+"</li></ul></td>";
			html += "<td>"+data[i].topicMembership+"人</td>";
			html += "<td>"+data[i].totalposts+"</td>";
			html += "<td>"+data[i].status+"</td>";
			if(data[i].isDelete ==1){
				html += "<td><button class=\"blue_btn_small\" onclick=\"disableOrEnableTopic("+data[i].topicId+",0,"+curr+")\">启用</button></td>";
			}else{
//				if(data[i].dataState == 0){
					html += "<td><button class=\"red_btn_small\" onclick=\"disableOrEnableTopic("+data[i].topicId+",1,"+curr+")\">禁用</button>";
					html += "<button class=\"green_btn_small\" onclick=\"editTopic("+data[i].topicId+","+curr+")\">编辑</button>";
				//	html += "<button class=\"yellow_btn_small\" onclick=\"setOrCancelRecommendTopic("+data[i].topicId+",1,"+curr+")\">设为推广</button></td>";
					html += "<button class=\"yellow_btn_small\" onclick=\"climbUp("+data[i].topicId+","+data[i].positionId+","+curr+")\">置上</button></td>";
//				}else{
//					html += "<td><button class=\"red_btn_small\" onclick=\"disableOrEnableTopic("+data[i].topicId+",1)\">禁用</button>";
//					html +=	"<button class=\"green_btn_small\" onclick=\"editTopic("+data[i].topicId+","+curr+")\">编辑</button>";
//					html +=	"<button class=\"yellow_btn_small\" onclick=\"setOrCancelRecommendTopic("+data[i].topicId+",0,"+curr+")\">取消推广</button></td>";
//				}
			}
			html += "</tr>";
		}
		$("#tbody").html(html);
		//显示分页
		layui.use(["laypage"], function(){
			var laypage = layui.laypage;
			layui.laypage({
				  cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
			      pages:page.totalPage, //通过后台拿到的总页数
			      curr: curr || 1, //当前页
			      jump: function(obj,first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			        	getTopicList(obj.curr);
			        }
			      }
		  	});
		});
   });
};

//置上操作
/**
 * topicId 话题ID
 * positionId 位置ID
 * curr 访问的页面的下标
 */
function climbUp(topicId, positionId,curr){
	if(positionId<=1){
		return false;
	}else{
		var topicVO = {
				"topicId" : topicId,
				"positionId" : positionId
			};
			console.log(topicVO);
			$.ajax({
				url : path + "/sociality/topicManager/climbUp",
				type : "post",
				contentType : "application/json",
				dataType : "json",// 从服务器端返回的数据类型
				data : JSON.stringify(topicVO),
				success : function(data) {
					/*if (data.code == 0) {
						layer.alert(data.msgbox, {
							icon : 2
						});
					} else {
						layer.alert(data.msgbox, {
							icon : 1
						}, function(index) {
							layer.close(index);
							getTopicList(curr);
						});
					}*/
					if (data.code == 0) {
						layer.alert(data.msgbox, {
							icon : 2
						});
					} else {
//						layer.alert(data.msgbox, {
//							icon : 1
//						}, function(index) {
//							layer.close(index);
							getTopicList(curr);
//						});
					}
				}
			});
	}
}

// 统 话题组小组管理（话题组总数，话题组总人数，今日新增帖子总数）
function initNumberCount(){
	$.ajax({
 		url : path + "/sociality/topicManager/numCount",
		type: "get",
		dataType: "json",	//从服务器端返回的数据类型
		async: false,
		success: function(json) {
			var topCount = json.data.topCount;		//话题组总数
			var userCount = json.data.userCount;		//话题组总人数
			var increaseCount = json.data.increaseCount;		//今日新增帖子总数
			var debatepostCount = json.data.debatepostCount;    //帖子总数
			
			$('.top_count').html(topCount);	//话题组总数
			$('.user_count').html(userCount);//话题组总人数
			$('.increase_count').html(increaseCount);//今日新增帖子总数
			$('.debatepost_count').html(debatepostCount);//今日新增帖子总数
			
		}
	});
}

$(document).on("click", ".blue", function() {
	var info = $(this).attr("value");
	layer.tips(info, this, {
		tips: [2, '#3595CC'],
		time: 4000
	});
});

$(document).ready(function() {
	// 初始化上传控件
	// 删除图片方法
	$(".imghover").click(function() {
		$("#view").attr('src', '');
		$("#thematic_img").val('');
		$(this).fadeOut('slow');
	});
	var clipArea = new bjj.PhotoClip("#clipArea", {
		size : [ 640 , 300 ],
		outputSize : [ 640, 300 ],
		file : "#file",
		view : "#view",
		ok : "#clipBtn",
		loadStart : function() {
			// console.log("照片读取中");
		},
		loadComplete : function() {
			// console.log("照片读取完成");
		},
		clipFinish : function(dataURL) {
			// console.log(dataURL);
		}
	});
	$("#searchTopic").on("click", function() {
		getTopicList(1)
	});
	
	//初始化table列表
	getTopicList(1);
	
	// 初始化统计数据
	initNumberCount();
});

//弹出话题层
function showDiv(type,curr) {//curr 访问的页面的下标
	var title = "新建话题组";
	var requestURL = path + "/sociality/topicManager/addTopic";
	if (type == "update") {
		title = "修改";
		requestURL = path + "/sociality/topicManager/editTopic";
	}
	layer.open({
		title : title,
		type : 1,
		area : [ '700px', '590px' ], // 宽高
		content : $('.normal_form2'),
		btn : [ '确定', '取消' ],
		yes : function(index) {
			var topicName = $("#topic_name").val();
			if (topicName == "") {
				$("#topic_name_error").html("名称不能为空!");
				$("#topic_name_error").show();
				return;
			} else {
				$("#topic_name_error").hide();
			}
			if (topicName.length > 12) {
				$("#topic_name_error").html("超出长度限制!");
				$("#topic_name_error").show();
				return;
			} else {
				$("#topic_name_error").hide();
			}
			var thematicImg = $("#thematic_img").val();
			if (thematicImg == '') {
				$("#thematic_img_error").html("请上传一张图片!");
				return;
			} else {
				$("#thematic_img_error").html("最多1张（图片比例：640*300）");
			}
			var topicProfile = $("#topic_profile").val();
			if (topicProfile == '') {
				$("#topic_profile_error").show();
				$("#topic_profile_error").html("简介不能为空");
				return;
			} else {
				$("#topic_profile_error").hide();
			}
			if (topicProfile.length > 200) {
				$("#topic_profile_error").show();
				$("#topic_profile_error").html("简介长度限制!");
				return;
			} else {
				$("#topic_profile_error").hide();
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
			});
		},
		cancel : function(index) {
			layer.close(index);
		}
	});
	imgnull();
}

// 添加话题组
function addTopic() {
	showDiv("add");
}
// 编辑话题
function editTopic(topicId,curr) {//curr 访问的页面的下标
	$.ajax({
		url : path + "/sociality/topicManager/getTopic?topicId=" + topicId,
		type : "get",
		contentType : "application/json",
		dataType : "json",// 从服务器端返回的数据类型
		success : function(json) {
			if (json.code == 1) {
				var topicVO = json.data;
				$("#topic_name").val(topicVO.topicName);
				$("#view").attr("src",
						topicVO.imagePrefix + topicVO.thematicImg);
				$("#thematic_img").val(topicVO.thematicImg);
				$("#topic_profile").val(topicVO.topicProfile);
				$("#topic_id").val(topicVO.topicId);
				showDiv("update",curr);
			}
		}
	});
}
//启用或禁用话题组
function disableOrEnableTopic(topicId, isDelete,curr) {//curr 访问的页面的下标
	var topicVO = {
		"topicId" : topicId,
		"isDelete" : isDelete
	};
	console.log(topicVO);
	$.ajax({
		url : path + "/sociality/topicManager/disableOrEnableTopic",
		type : "post",
		contentType : "application/json",
		dataType : "json",// 从服务器端返回的数据类型
		data : JSON.stringify(topicVO),
		success : function(json) {
			console.log(json);
			if (json.code == 0) {
				layer.alert(json.msgbox, {
					icon : 2
				});
			} else {
				layer.alert(json.msgbox, {
					icon : 1
				}, function(index) {
					layer.close(index);
					getTopicList(curr);
				});
			}
		}
	});
}
//设为推荐或取消推荐话题组
function setOrCancelRecommendTopic(topicId, dataState,curr) {//curr 访问的页面的下标
	var topicVO = {
		"topicId" : topicId,
		"dataState" : dataState
	};
	console.log(topicVO);
	$.ajax({
		url : path + "/sociality/topicManager/setOrCancelRecommendTopic",
		type : "post",
		contentType : "application/json",
		dataType : "json",// 从服务器端返回的数据类型
		data : JSON.stringify(topicVO),
		success : function(json) {
			if (json.code == 0) {
				layer.alert(json.msgbox, {
					icon : 2
				});
			} else {
				layer.alert(json.msgbox, {
					icon : 1
				}, function(index) {
					layer.close(index);
					getTopicList(curr);
				});
			}
		}
	});
}

//判断删除图片显示方法
function imgnull() {
	var img = $("#view").attr('src');
	if (img != '') {
		$('.imghover').show();
	} else {
		$('.imghover').hide();
	}
}
//上传剪裁完的图片
function uploadBase64(base64Data) {
	var data = {
		"baseFile" : base64Data.split(",")[1],
		"fileSuffix" : "jpeg"
	};
	$.ajax({
		url : path + "/sociality/topicManager/uploadImg",
		type : "post",
		data : data,
		dataType : "json",// 从服务器端返回的数据类型
		success : function(json) {
			console.log(json);
			if (json.code == 1) {
				var data = json.data.data;
				console.log(data.fileUrl);
				$("#thematic_img").val("/" + data.fileUrl);
			}
		}
	});
}
	      