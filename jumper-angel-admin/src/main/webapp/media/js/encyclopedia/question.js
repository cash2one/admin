/**
 * 孕期百科js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	var keywords = "";
	var classId = 0;
	var typeId = 0;
	getPage(keywords,classId,typeId,1);
});

//显示分页
function getPage(keywords,classId,typeId,currIndex){
	$.ajax({
		url:path+"/encyclopedia/findQuestionList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords, "classId":classId, "typeId":typeId},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "pages",
		    pages: totalPage,
		    curr: currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false,
		   	 		"keywords":keywords,
		   	 		"classId":classId,
		   	 		"typeId":typeId
		   	 	};
		   	 	$.ajax({
		   			url: path + "/encyclopedia/findQuestionList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var status = "";
		   				var operator = "";
		   				var detailsUrl = "";
		   				for(var i=0; i<data.length; i++) {
		   					if(data[i].detailsUrl.indexOf("http")>=0){
		   						detailsUrl = data[i].detailsUrl;
		   					}else{
		   						detailsUrl = data[i].filePrefix+data[i].detailsUrl;
		   					}
		   					status = data[i].status==0?"公开":"隐藏";
		   					operator = data[i].status==0?"隐藏":"公开";
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td>"+data[i].questionTypeName+"</td>";
		   					html += "<td>"+data[i].title+"</td>";
		   					html += "<td>"+detailsUrl+"</td>";
		   					html += "<td class='status'>"+status+"</td>";
		   					html += "<td>"+data[i].questionAddTime+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditQuestion' type='edit' question_id='"+data[i].id+"' title='"+data[i].title+"' status='"+data[i].status+"' introduction='"+data[i].questionIntorduction+"' answer='"+data[i].answer+"' question_type='"+data[i].questionTypeId+"' class_id='"+data[i].classId+"' img_url='"+data[i].imgUrl+"' prefix='"+data[i].filePrefix+"'>编辑</button>"+
						   				"<button class='green_btn_small operateQuestion' question_id='"+data[i].id+"' status='"+data[i].status+"'>"+operator+"</button>"+
						   				"<button class='red_btn_small deleteQuestion' question_id='"+data[i].id+"'>删除</button>"+
			   						"</td>";
			   				html += "</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}


$(function(){
	//搜索模块
	$(document).on("click","#searchBtn",function(){
		var keywords = $("#keywords").val();
		var classId = $("#questionClass").val();
		var typeId = $("#questionType").val();
		if(typeId==null || typeId==""){
			typeId = 0;
		}
		getPage(keywords,classId,typeId,1);
	});


	//切换所属模块 获取模块下的标签
	$(document).on("change","#questionClass",function(){
		var classId = $(this).val();
		$.post(
			path+"/encyclopedia/findQuestionTypeByClass",
			{
				"classId":classId
			},function(json){
				var typeHtml = "<option value='0'>显示所有标签</option>";
				if(json.code==1){
					var data = json.data;
					for(var i=0;i<data.length;i++){
						typeHtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
				}
				
				$("#questionType").html(typeHtml);
			}
		);
	});

	$(document).on("change","#questionClass1",function(){
		var classId = $(this).val();
		$.post(
			path+"/encyclopedia/findQuestionTypeByClass",
			{
				"classId":classId
			},function(json){
				var typeHtml = "";
				if(json.code==1){
					var data = json.data;
					for(var i=0;i<data.length;i++){
						typeHtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
				}
				
				$("#questionType1").html(typeHtml);
			}
		);
	});

	//删除常见问题
	$(document).on("click",".deleteQuestion",function(){
		var questionId = $(this).attr("question_id");
		var $this = $(this);
		 layer.alert('要删除这条记录么？', {
	            icon: 0,
	            title: '提示',
	            btn:['删除','取消'],
	            btnAlign: 'c',
	            yes: function(index, layero){
	                layer.close(index);
	                $.post(
	                	path+"/encyclopedia/deleteQuestion",
	                	{
	                		"questionId":questionId
	                	},function(ret){
	                		layer.msg(ret.msgbox, {
			  		      			icon: 1,
			  		      			shade: [0.5,'#000'],
			  		      			btn: ['确定']
			  		    		});
	                		if(ret.code==1){
	                			$this.parent().parent().remove();
	                		}
	                	}
	                );
	            }
	        });
	});


	//添加或修改常见问题
	$(document).on("click",".addOrEditQuestion",function(){
		var type = $(this).attr("type");
		var questionId = $(this).attr("question_id");
		var title = $(this).attr("title");
		var status = $(this).attr("status");
		var introduction = $(this).attr("introduction");
		var content = $(this).attr("answer");
		var questionType = $(this).attr("question_type");
		var classId = $(this).attr("class_id");
		var imgUrl = $(this).attr("img_url");
		var prefix = $(this).attr("prefix");
		var ua = UE.getEditor("content",{
			autoHeightEnabled: true,
	    	autoFloatEnabled: true
		});
		$.ajax({ url: path+"/encyclopedia/findAllQuestionClassList",async:false,
			success: function(json){
				var data = json.data;
				var typehtml = "";
				for(var i=0;i<data.length;i++){
					typehtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("#questionClass1").html(typehtml);
			}
		});
		if(type=="add"){
			classId = $("#questionClass1").val();
		}
		$.ajax({ url: path+"/encyclopedia/findQuestionTypeByClass",async:false,data:"classId="+classId,
			success: function(json){
				if(json.code==1){
				var data = json.data;
				var typehtml = "";
					for(var i=0;i<data.length;i++){
						typehtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
				}
				$("#questionType1").html(typehtml);
			}
		});
		
		if(type=="edit"){
			
			$("#questionClass1").find("option[value='"+classId+"']").attr("selected",true);
			$("#questionType1").find("option[value='"+questionType+"']").attr("selected",true);
			$("#status").find("option[value='"+status+"']").attr("selected",true);
			$("#title").val(title);
			$("#introduction").val(introduction);
			setTimeout(function(){
				ua.ready(function () {
			       // editor准备好之后才可以使用
			       ua.setContent(content);
			     });
			},10);
			$("#imgUrl").val(imgUrl);
			$(".img-thumbnail").attr("src", prefix+imgUrl);
		}
		if(type=="add"){
			questionId = 0;
			$("#title").val("");
			$("#introduction").val("");
			$("#imgUrl").val("");
			setTimeout(function(){
				ua.ready(function () {
				       // editor准备好之后才可以使用
				       ua.setContent("");
				     });
			},100);
			$(".img-thumbnail").attr("src", "");
		}
		
		$("#edui1,#edui1_iframeholder").css("width","638px");
		$("#content").css("padding",0);
		$("#content").css("display","inline-block");
//		$(".edui-editor,.edui-editor-iframeholder").css("width","638px");
//		 layui.use("upload", function(){
//		   	  	layui.upload({
//		   	  		elem:"#imageUrl",
//		   	  	    url: path+"/file/upload", //上传接口
//		   	  	    success: function(res) { //上传成功后的回调
//		   	  	      var result = eval("("+res.data+")");
//		   	  	      //上传后图片地址
//		   	  	      var fileUrl = "/"+result.data.fileUrl;
//		   	  	      $("#imgUrl").val(fileUrl);
//		   	  	    }
//		   	  	});
//		 	});
		layer.open({
			title:'百科内容编辑',
	  		type: 1,
	  		area: ['880px','750px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			typeId = $("#questionType1").val();
	  			classId = $("#questionClass1").val();
	  			status = $("#status").val();
	  			title = $("#title").val().trim();
	  			introduction = $("#introduction").val().trim();
	  			answer = ua.getContent();
	  			imgUrl = $("#imgUrl").val();
	  			//上传图片成功后的地址
	  			var uploadImgUrl = upload_img_url!=""?"/"+upload_img_url:imgUrl;
	  			if(title == "" || title == null){
	  				layer.alert("请输入内容标题！");
	  				return;
	  			}
	  			if(answer == "" || answer == null){
	  				layer.alert("请输入百科内容！");
	  				return;
	  			}
	  			if(introduction == "" || introduction == null){
	  				layer.alert("请输入内容概要！");
	  				return;
	  			}
	  			if(uploadImgUrl == "" || uploadImgUrl == null){
	  				layer.alert("请上传主题图片！");
	  				return;
	  			}
	  			layer.close(index);
	  			$.post(
						path+"/encyclopedia/addOrEditQuestion",
						{
							"questionId":questionId,
							"typeId":typeId,
		  					"classId":classId,
		  					"status":status,
		  					"title":title,
		  					"introduction":introduction,
		  					"answer":answer,
		  					"imgUrl":uploadImgUrl!=""?uploadImgUrl:$("#imgUrl").val()
						},function(data){
							layer.msg(data.msgbox, {
		  		      			icon: 1,
		  		      			shade: [0.5,'#000'],
		  		      			btn: ['确定']
		  		    		});
	            			if(data.code == 1){
	            			setTimeout(function(){
//	            				window.location.reload();
	            				var keywords = $("#keywords").val();
	            				var classId = $("#questionClass").val();
	            				var typeId = $("#questionType").val();
	            				if(typeId==null || typeId==""){
	            					typeId = 0;
	            				}
	            				getPage(keywords,classId,typeId,curr);
	            			},1000);
	            			}
						}
					)
	    	},
	    	end:function(){
	    		ua.destroy();
	    	}
		});
		
	});
	
	
	//公开或隐藏百科内容
	$(document).on("click",".operateQuestion",function(){
		var questionId = $(this).attr("question_id");
		var status = $(this).attr("status");
		var $this = $(this);
		$.post(
            	path+"/encyclopedia/operateQuestion",
            	{
            		"questionId":questionId,
            		"status":status
            	},function(ret){
            		layer.msg(ret.msgbox, {
	  		      			icon: 1,
	  		      			shade: [0.5,'#000'],
	  		      			btn: ['确定']
	  		    		});
            		if(ret.code==1){
            			$this.html(status==0?"公开":"隐藏");
            			$this.attr("status",status==0?1:0);
            			$this.parent().siblings(".status").html(status==0?"隐藏":"公开");
            		}
            	}
            );
	});
	


});
