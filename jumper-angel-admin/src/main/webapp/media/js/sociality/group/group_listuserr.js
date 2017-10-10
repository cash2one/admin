$(document).ready(function() {
	getGroupByBean();//加载
	$("#search_user_group_button").on("click",function(){
		getUserGroupList();
	});
	$(".selInput").keyup(function(){
		likeSearchGroup();
	});
});

//圈子管理   成员管理  统计数据加载
function initNumberCount(){
	var groupId = $("#select_value").val();//圈子id
	$.ajax({
 		url : path + "/group/user/numCount?groupId="+groupId,
		type: "get",
		dataType: "json",	//从服务器端返回的数据类型
		async: false,
		success: function(json) {
			var currentGroupCount = json.data.currentGroupCount;				//当前交流圈人数
			var currentGroupIncreaseCount = json.data.currentGroupIncreaseCount;	//今日当前交流圈新增人数
			var currentGroupSpeakCount = json.data.currentGroupSpeakCount;			//今日当前交流圈发言人数
			
			$('.currentGroupCount').html(currentGroupCount);	//当前交流圈人数
			$('.currentGroupIncreaseCount').html(currentGroupIncreaseCount);//今日当前交流圈新增人数
			$('.currentGroupSpeakCount').html(currentGroupSpeakCount);//今日当前交流圈发言人数
			
		}
	});
}






//圈子查询集合
var GroupJSONArray = [];
//查询所有圈子信息
function getGroupByBean(){
	var SocialityGroupVo = {"groupName":$(".selInput").val()};
	$.ajax({
			url: path + "/sociality/group/getGroupByBean",
			type: "get",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			data:SocialityGroupVo,
			success: function(json){
				if(json.code == 1){
					GroupJSONArray = json.data;
					if(json.data.length >0){
						var select_html = "";
						for(var i=0;i<json.data.length;i++){
							select_html+= "<a javascript:void(0) onclick=\"selectGroupName("+"'"+json.data[i].groupId+"'"+")\">"+json.data[i].groupName+"</a>";
						}
						$("#select_group_name").html(select_html);
					}
				}
			}
	});
}

//对下拉框的话题list进行搜索
function likeSearchGroup(){
	var setInputValue = $(".selInput").val();
	var select_html = "";
	if(setInputValue == ''){
		for(var i=0;i<GroupJSONArray.length;i++){
			select_html+= "<a javascript:void(0) onclick=\"selectGroupName("+"'"+GroupJSONArray[i].groupId+"'"+")\">"+GroupJSONArray[i].groupName+"</a>";
		}
	}else{
		for(var i=0;i<GroupJSONArray.length;i++){
			
			if(GroupJSONArray[i].groupName.indexOf(setInputValue) ==0){
				select_html+= "<a javascript:void(0) onclick=\"selectGroupName("+"'"+GroupJSONArray[i].groupId+"'"+")\">"+GroupJSONArray[i].groupName+"</a>";
			}
		}
	}
	$("#select_group_name").html(select_html);
}

//选择下拉框的圈子标题在查询
function selectGroupName(groupId){
	$("#select_value").val(groupId);
	for(var i=0;i<GroupJSONArray.length;i++){
		if(GroupJSONArray[i].groupId == groupId){
			$(".select_txt").html(GroupJSONArray[i].groupName);
		}
	}
	// 查询第一页
	getUserGroupList(1);
	// 初始化页面统计数据
	initNumberCount();
	$("#display").show();
}

//查询用户话s题
function getUserGroupList(curr){
	if($("#select_value").val() == ""){
		layer.alert("请选择一个圈子在查询");
	}else{								   
		$.getJSON(path + "/group/user/getUserGroupList",{
		    "beginIndex":curr || 1,
		    "everyPage":15,
		    "groupId":$("#select_value").val(),//圈子id
		    "nickName":$("#search_nick_name_or_mobile").val(),//用户昵称
		    "mobile":$("#search_nick_name_or_mobile").val(),//用户的手机号码
		    "isBlacklist":$("#search_isblacklist").val(),//状态 是否禁言  筛选   禁言     以及账号类型
		    "isKeeper":$("#search_isKeeper_list").val(),//设置y
		    "timestamp":new Date().getTime()
		  }, function(res){
			  if(res.code ==1){
			    var page = res.data.page;
			    var data = res.data.content;
				var html = "";
				$("#tbody").html("");
				for(var i=0; i<data.length; i++) {
					var nickName = data[i].nickName ==null?"":data[i].nickName;
					html += "<tr height='40px'>";
					html += "<td>"+data[i].mobile+"</td>";
					html += "<td>"+data[i].nickName+"</td>";
					if(data[i].isKeeper ==4){
						html += "<td>宝妈 </td>";
					}else if(data[i].isKeeper ==2){
						html += "<td>管理员</td>";
					}else if(data[i].isKeeper ==3){
						html += "<td>医生</td>";
					}else if(data[i].isKeeper ==5){
						html += "<td>备孕</td>";
					}else if(data[i].isKeeper ==1){
						html += "<td>孕妈</td>";
					}else{
						html += "<td></td>";
					}
					html += "<td><ul class=\"tableTime\"><li>"+data[i].createDateTime.substring(0,10)+"</li><li>"+data[i].createDateTime.substring(11,19)+"</li></ul></td>";
					
					
					html += "<td>"+data[i].speakingDateTime.substring(0,10)+"</td>";
					
					
					if(data[i].isBlacklist == 0){
						html += "<td>正常</td>";
					}else{
						html += "<td>禁言</td>";
					}
					if(data[i].isBlacklist == 0){
						html += "<td><button class=\"red_btn_small\" onclick=\"isBlacklistGroup("+data[i].id+","+data[i].userId+",1,"+data[i].groupId+","+curr+")\">禁言</button>";
						html += "<button class=\"blue_btn_small\"  onClick=\'showDiv3("+data[i].id+","+data[i].userId+","+data[i].groupId+","+curr+")\'>设置身份</button></td>";
					}else{
						html += "<td><button class=\"blue_btn_small\" onclick=\"isBlacklistGroup("+data[i].id+","+data[i].userId+",0,"+data[i].groupId+","+curr+")\">解除禁言</button></td>";
					}
					html += "</tr>";
				}
				$("#tbody").html(html);
				layui.use(["laypage"], function(){
					var laypage = layui.laypage;
					laypage({
						  cont: 'page', //容器。值支持id名、原生dom对象
					      pages:page.totalPage, //通过后台拿到的总页数
					      curr: curr || 1, //当前页
					      jump: function(obj,first){ //触发分页后的回调
					        if(!first){ //点击跳页触发函数自身
					        	getUserGroupList(obj.curr);
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
/**
 * 启用或者禁用
 * @param id
 * @param isBlacklist
 */
function isBlacklistGroup(id,userId,isBlacklist,groupId,curr){
	var param = {"id":id,"userId":userId,"isBlacklist":isBlacklist,"groupId":groupId};
	$.ajax({
			url: path + "/group/user/isBlacklistGroup",
			type: "post",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			data: JSON.stringify(param),
			success: function(json) {
				if(json.code == 0){
					layer.alert(json.msgbox,{icon:2});
				}else{
					layer.alert(json.msgbox,{icon:1},function(index){
						getUserGroupList(curr);
						layer.close(index);
					});
				}
			}
	});
}
/**
 * 设置身份
 * @param id
 * @param isBlacklist
 */
function showDiv3(id,userId,groupId,curr){
	var title="设置身份";
	layer.open({
		 title:title,
	        type: 1,
	        area: ['400px','300px'],
	        content: $('.showBox3'),
	        btn: ['确定','取消'],
	        yes: function(index){	
	        	var role = $("input[type='radio']:checked").val();
	        	var param= {"id":id,"groupId":groupId,"userId":userId,"isKeeper":role,"mark":role};
	        	$.ajax({
	    			url: path + "/group/user/showDiv3",
	    			type: "post",
	    			contentType:"application/json",
	    			dataType: "json",//从服务器端返回的数据类型
	    			data: JSON.stringify(param),
	    			success: function(json) {
	    				if(json.code == 0){
	    					layer.alert(json.msgbox,{icon:2});
	    				}else{
	    					layer.alert(json.msgbox,{icon:1},function(index){
	    						console.log("close current alert");
	    						layer.closeAll();
	    						getUserGroupList(curr);
	    					});
	    				}
	    			}
	        	});
	       }
	});
}


