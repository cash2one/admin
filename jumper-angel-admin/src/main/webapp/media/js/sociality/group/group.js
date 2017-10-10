
//圈子管理
var totalPage = 0;
var defaultCoverUrl="http://image.jumper-health.com/img/jlq/android/drawable-xxhdpi/default_icon.png";

$(document).ready(function() {
	
	//初始化上传组件
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	
	
	list(); //第一次今天加载
	initNumberCount(); // 初始化统计人数
	$('.search-icon').click(function (){ //点击搜索按钮
		list();
	});
});


//统计	交流圈    圈子管理（交流圈总数，交流圈总人数）
function initNumberCount(){
	$.ajax({
 		url : path + "/sociality/group/numCount",
		type: "get",
		dataType: "json",	//从服务器端返回的数据类型
		async: false,
		success: function(json) {
			var groupCount = json.data.groupCount;		//交流圈总数
			var groupUserCount = json.data.groupUserCount;		//交流圈总人数
			
			$('.group_count').html(groupCount);	//交流圈总数
			$('.group_user_count').html(groupUserCount);//交流圈总人数
			
		}
	});
}

function list(pageNo){//curr 访问的页面的下标
	var isFirst = true;
	if(null == pageNo || "" == pageNo || pageNo == 0){
		pageNo = 1;
		$.ajax({
			type : "POST",
			url : path + "/sociality/group/list",
			dataType : "json",
			data : {
				"pageNo" : pageNo,
				"rows" : everyPage,
				"first" : isFirst,
				"groupName":$('.group-name').val(),
				"dataStatus":$("#data_Status").val(),
				"allowAdd":$("#allow_Add").val(),
				"stamptime":new Date().getTime(),
			},
			async : false,
			success : function(json) {
				totalPage = json.data;
				console.log("几页:"+totalPage);
			}
		});
	} 
	//显示分页
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "pages",
		    pages: totalPage,
		   	skip: true,
		   	curr:pageNo,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   		var curr = obj.curr;
			   	//json参数
		   	 	var data = {
	   				"pageNo" : curr||1,//当前页
	   				"rows" : everyPage,
	   				"first" : false,
	   				"groupName":$('.group-name').val(),
	   				"dataStatus":$("#data_Status").val(),
	   				"allowAdd":$("#allow_Add").val(),
	   				"stamptime":new Date().getTime()
	   			};
		   	 	//请求数据
		   	 	$.ajax({
		   	 		url : path + "/sociality/group/list",
		   			type: "post",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				console.log("获取到json的参数："+json);
		   				var data = json.data;
		   				var html = "";
		   				$("#tbody").html("");
		   				for(var i=0; i<data.length; i++) {
		   					html += "<tr height='40px'>";
		   					html += "<td>"+data[i].groupName+"</td>";
		   					if(data[i].groupBriefIntr.length >= 20){
		   						html += "<td class='table-longtext' id='groupBriefIntr"+data.id+"'>"+data[i].groupBriefIntr.substring(0, 20)+"...<a href='javascript:' class='blue' value='"+data[i].groupBriefIntr+"'>[全文]</a></td>";
		   					}else{
		   						html += "<td>"+data[i].groupBriefIntr+"</td>";
		   					}
		   					html += "<td><img src="+data[i].groupImgUrl+"></td>";
		   					var userName = data[i].createUserId;
		   					if(null == userName){
		   						userName ="";
		   					}
			   				html += "<td>"+userName+"</td>";//登录用户为创建人
			   				html += "<td>"+new Date(parseInt(data[i].createUserTime)).format("yyyy/MM/dd HH:mm:s")+"</td>";
			   				
			   				html += "<td>"+data[i].peopleNumber+"</td>";
			   				if(data[i].dataStatus==1){
			   					html += "<td>正常</td>";
			   				}else{
			   					html += "<td>禁用</td>";
			   				}
			   				if(data[i].allowAdd==0){
			   					html += "<td>公开圈子</td>";
			   				}else{
			   					html += "<td>非公开圈子</td>";
			   				}
			   				if(data[i].dataStatus == 2){
			   					html += "<td><button class='blue_btn_small'  onclick=\"disableOrEnableGroup("+data[i].id+",1,"+curr+")\">启用</button></td>";
			   				}else{
			   					if(data[i].overhead==1){
			   						html += "<td><button class='red_btn_small'  onclick=\"disableOrEnableGroup("+data[i].id+",2,"+curr+")\">禁用</button>";
//				   					html += "<button class='blue_btn_small' onclick=\"editGroup("+data[i].id+","+curr+")\"> 编辑 </button>";
				   					html += "<button class='blue_btn_small' onclick=\"ShowDiv(2,"+data[i].id+","+curr+")\"> 编辑 </button>";
				   					
			   					}else{
			   						html += "<td><button class='red_btn_small'  onclick=\"disableOrEnableGroup("+data[i].id+",2,"+curr+")\">禁用</button>";
//				   					html += "<button class='blue_btn_small' onclick=\"editGroup("+data[i].id+","+curr+")\"> 编辑 </button>";
				   					html += "<button class='blue_btn_small' onclick=\"ShowDiv(2,"+data[i].id+","+curr+")\"> 编辑 </button>";
			   					}
			   				}
			   				html += "</tr>";
		   				}
		   				$("#tbody").html(html);
		   				pageNo = curr;
		   			}
		   		});
	   		}
	  	});
	});
	///显示全部内容
	$(document).on("click", ".blue", function() {
		var info = $(this).attr("value");
		layer.tips(info, this, {
			tips: [2, '#3595CC'],
			time: 4000
		});
	});
};

function showDiv(type,curr){
	var title ="新建圈子";
	var requestURL = path + "/sociality/group/create";
	if(type =="update"){
		title = "编辑圈子";
		requestURL = path + "/sociality/group/edit";
	}
	layer.open({
        title:title,
        type: 1,
        area: ['700px','590px'], //宽高
        content: $('.normal_form2'),
        btn: ['确定','取消'],
        yes:function(index){
        	var groupName = $("#group_Name").val();
        	if(groupName ==""){
        		$("#groupname_error").html("名称不能为空!");
        		$("#groupname_error").show();
        		return;
        	}else{
        		$("#groupname_error").hide();
        	}
        	/*if(groupName.length > 12){
        		$("#groupname_error").html("超出长度限制!");
        		$("#groupname_error").show();
        		return;
        	}else{
        		$("#groupname_error").hide();
        	}*/
        	var groupBriefIntr = $("#groupBriefIntr").val();
        	if(groupBriefIntr ==''){
        		$("#groupBriefIntr_error").show();
        		$("#groupBriefIntr_error").html("简介不能为空");
        		return;
        	}else{
        		$("#groupBriefIntr_error").hide();
        	}
        	if(groupBriefIntr.length > 150){
        		$("#groupBriefIntr_error").show();
        		$("#groupBriefIntr_error").html("简介长度限制!");
        		return;
        	}else{
        		$("#groupBriefIntr_error").hide();
        	}
        	var role = $("input[type='checkbox']:checked").val();
        	if(role==null){
        		role=0;
        	}
        	console.log(role);
 			var topicVO = {
  					"groupName":groupName,
  					"groupBriefIntr":groupBriefIntr,
  					"allowAdd":role,
  					"id": $("#_id").val()
  			};
 			console.log(topicVO);
 			var result = strLength(groupName);
 			if(!result){
 				$("#groupname_error").html("超出长度限制!");
        		$("#groupname_error").show();
        		return;
 			}else{
 				$("#groupname_error").hide();
 			}
         	$.ajax({
	   			url: requestURL,
	   			type: "post",
	   			contentType:"application/json",
	   			dataType: "json",//从服务器端返回的数据类型
	   			data: JSON.stringify(topicVO),
	   			success: function(json){
	   				if(json.code == 0){
	   					layer.alert(json.msgbox,{icon:2});
	   				}else{
	   					layer.alert(json.msgbox,{icon:1},function(){
	   						// 清空列表内容  
	   						$(".normal_form2 input[type='text']").val('');
	   						$(".normal_form2 textarea").val('');
	   						$(".normal_form2 input[type='checkbox']").removeAttr("checked");
	   						//$("input[type=reset]").trigger("click");
	   						layer.closeAll();
	   						list(curr);//刷新
	   					});
	   				}
	   			}
	   		});
        },cancel:function(index){
        	layer.close(index);
        }
    });
}

/*$("#group_Name").blur(function (){
	strLength($("#group_Name").val());
	("#groupname_error").attr("display","");
});*/
$("#group_Name").click(function (){
	$("#groupname_error").hide();
});

/**
 * 判断字符串字节长度
 * @param str
 */
function strLength(str){
	    var len = 0;
	    for (var i=0; i<str.length; i++) { 
	     var c = str.charCodeAt(i); 
	    //单字节加1 
	     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
	       len++; 
	     } 
	     else { 
	       len+=3; 
	     } 
	    }
	    if(len > 30){
	    	return false;
	    }else{
	    	return true;
	    }
	}
//添加话题组
function addGroup(){
	showDiv("add");
}
//编辑圈子 
function editGroup(id,curr){
	var param = {"id":id};
	console.log(param);
	$.ajax({
			url: path + "/sociality/group/getDataById?id="+id,
			type: "get",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
		///	data: JSON.stringify(param),
			success: function(json){
				console.log(json);
				if(json.code == 1){
					var VO = json.data;
					 $("#group_Name").val(VO.groupName);
					 $("#groupBriefIntr").val(VO.groupBriefIntr);
					 if(VO.allowAdd!=0){//将allow-add——input 这里勾选 和非勾选做一下判断
							 var checkbox1 = document.getElementById("allow_Add_input");
							 checkbox1.checked=true;
					}else{
						  var checkbox1 = document.getElementById("allow_Add_input");
						  checkbox1.checked=false;
					 }
					 showDiv("update",curr);
					 //list(curr);
					 $("#_id").val(VO.id);//
				}
			}
		});
}


//禁言或者取消禁言
function disableOrEnableGroup(id,dataStatus,curr){
	var param = {"id":id,"dataStatus":dataStatus};
	console.log(param);
	$.ajax({
			url: path + "/sociality/group/disableOrEnableGroup",
			type: "post",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			data: JSON.stringify(param),
			success: function(json) {
				console.log(json);
				if(json.code == 0){
					layer.alert(json.msgbox,{icon:2});
				}else{
					layer.alert(json.msgbox,{icon:1},function(index){
						list(this);
						layer.close(index);
						list(curr);
					});
				}
			}
	});
}


//弹出层
function ShowDiv(status,id,curr) {
	var title = "";
	var requestURL = null;
	if(status==1){
		title = "新建圈子";
		requestURL = path + "/sociality/group/create";
	}if(status ==2){
		title = "编辑圈子";
		requestURL = path + "/sociality/group/edit";
	}  
	var data = null;
	//新增
	if(status == 1) {
		$("#group_Name").val("");
	//	$(".img-thumbnail").attr("src", "");
		$(".img-thumbnail").attr("src", defaultCoverUrl);
		$("#groupBriefIntr").val("");
		$("#allow_Add_input").attr("checked", false);
	} else if(status == 2) {	//修改
		//获取圈子详情
		$.ajax({
			url: path + "/sociality/group/getDataById?id="+id,
			type: "get",
			contentType:"application/json",
			dataType: "json",//从服务器端返回的数据类型
			success: function(json){
				data = json.data;
				$("#group_Name").val(data.groupName);
				$("#groupBriefIntr").val(data.groupBriefIntr);
				var checkbox1 = null;
				if(data.allowAdd!=0){//将allow-add——input 这里勾选 和非勾选做一下判断
					checkbox1 = document.getElementById("allow_Add_input");
					checkbox1.checked=true;
				}else{
					var checkbox1 = document.getElementById("allow_Add_input");
					checkbox1.checked=false;
				}
				if(data.coverUrl!=null&&data.coverUrl!=""){
					$("#coverUrl").val(data.coverUrl);
					$(".img-thumbnail").attr("src", data.imagePrefix+data.coverUrl);
				}else{
					$("#coverUrl").val(defaultCoverUrl);
					$(".img-thumbnail").attr("src", defaultCoverUrl);
				}
		//		$("#coverUrl").val(data.coverUrl);
		//		$("#coverUrl").val(defaultCoverUrl);
		//		$(".img-thumbnail").attr("src", data.imagePrefix+data.coverUrl);
				
				
				$("#_id").val(data.id);
			}
		});
	}
 	//保存
	layer.open({
		title:title,
  		type: 1, 
  		area: ['660px'], //宽高
  		content: $('.normal_form2'),
  		btn: ['保存'],
  		yes: function(index){
  			//上传图片成功后的地址
  			var uploadImgUrl = upload_img_url!=""?"/"+upload_img_url:"";
  			//标题
  			var groupName = $("#group_Name").val();
  			if(groupName ==""){
        		$("#groupname_error").html("名称不能为空!");
        		$("#groupname_error").show();
        		return;
        	}else{
        		$("#groupname_error").hide();
        	}
  			var groupBriefIntr = $("#groupBriefIntr").val();
        	if(groupBriefIntr ==''){
        		$("#groupBriefIntr_error").show();
        		$("#groupBriefIntr_error").html("简介不能为空");
        		return;
        	}else{
        		$("#groupBriefIntr_error").hide();
        	}
        	if(groupBriefIntr.length > 150){
        		$("#groupBriefIntr_error").show();
        		$("#groupBriefIntr_error").html("简介长度限制!");
        		return;
        	}else{
        		$("#groupBriefIntr_error").hide();
        	}
        	var role = $("input[type='checkbox']:checked").val();
        	if(role==null){
        		role=0;
        	}
        	console.log(role);
 			var topicVO = {
  					"groupName":groupName,
  					"groupBriefIntr":groupBriefIntr,
  					"allowAdd":role,
  					"id": $("#_id").val(),
  					"coverUrl":uploadImgUrl==""?null:uploadImgUrl
  			};
 			console.log(topicVO);
 			var result = strLength(groupName);
 			if(!result){
 				$("#groupname_error").html("超出长度限制!");
        		$("#groupname_error").show();
        		return;
 			}else{
 				$("#groupname_error").hide();
 			}
 			var param = JSON.stringify(topicVO);
 			$.ajax({
	   			url: requestURL,
	   			type: "post",
	   			contentType:"application/json",
	   			dataType: "json",//从服务器端返回的数据类型
	   			data: param,
	   			success: function(json){
	   				if(json.code == 0){
	   					layer.alert(json.msgbox,{icon:2});
	   				}else{
	   					layer.alert(json.msgbox,{icon:1},function(){
	   						// 清空列表内容  
	   						$(".normal_form2 input[type='text']").val('');
	   						$(".normal_form2 textarea").val('');
	   						$(".normal_form2 input[type='checkbox']").removeAttr("checked");
	   				//		$(".normal_form2 input[type='checkbox']").removeAttr("checked");
	   						//$("input[type=reset]").trigger("click");
	   						layer.closeAll();
	   						list(curr);//刷新
	   						upload_img_url="";
	   					});
	   				}
	   			}
	   		});
    	}
	});
}




