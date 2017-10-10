/**
 * 新闻文章js
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
	var isPublish = 1;
	var keywords = "";
	var chanels = 0;
	var periods = 0;
	getPage(isPublish, keywords, chanels, periods, 1);
});

//显示分页
function getPage(isPublish, keywords, chanels, periods, currIndex){
	$.ajax({
		url:path+"/news/findNewsInformationList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "isPublish":isPublish, "keywords":keywords, "chanels":chanels, "periods":periods},
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
		   	 		"isPublish":isPublish, 
		   	 		"keywords":keywords, 
		   	 		"chanels":chanels, 
		   	 		"periods":periods
		   	 	};
		   	 	$.ajax({
		   			url: path + "/news/findNewsInformationList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {
		   					var periods = "";
		   					if(data[i].period.indexOf("-1")>=0){
		   						periods += " 备孕期";
		   					}
		   					if(data[i].period.indexOf("1")>=0){
		   						periods += " 孕早期";
		   					}
		   					if(data[i].period.indexOf("2")>=0){
		   						periods += " 孕中期";
		   					}
		   					if(data[i].period.indexOf("3")>=0){
		   						periods += " 孕晚期";
		   					}
		   					if(data[i].period.indexOf("4")>=0){
		   						periods += " 0-6月";
		   					}
		   					if(data[i].period.indexOf("5")>=0){
		   						periods += " 6月-1岁";
		   					}
		   					if(data[i].period.indexOf("6")>=0){
		   						periods += " 1岁-3岁";
		   					}
		   					var moreStr = "";
		   					var newsUrl = data[i].newsUrl;
		   					var target = "_blank";
		   					if(data[i].title.length>18){
		   						moreStr = "...";
		   					}
		   					if(newsUrl == null || newsUrl == ""){
		   						newsUrl = "#";
		   						target = "_self";
		   					}
		   					html += "<tr height='40px'>";
		   					html += "<td title='"+data[i].title+"'><a href='"+newsUrl+"' target='"+target+"' style='color:#666666;'>"+data[i].title.substring(0,18)+moreStr+"</a></td>";
		   					html += "<td>"+data[i].channelName+"</td>";
			   				html += "<td>"+periods+"</td>";
			   				html += "<td><image src='"+data[i].imageUrl+"'/></td>";
			   				html += "<td>"+data[i].clicks+"</td>";
			   				html += "<td>"+data[i].yesterdayClicks+"</td>";
			   				html += "<td>"+data[i].lastWeekClicks+"</td>";
			   				html += "<td>"+data[i].lastMonthClicks+"</td>";
			   				html += "<td>"+data[i].shareNum+"</td>";
			   				html += "<td>"+data[i].commentNum+"</td>";
			   				html += "<td>"+data[i].addTime+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditNewsInformation' type='edit' newsId='"+data[i].id+"'>编辑</button>";
			   				if(isPublish==1){
			   					html +=	"<button class='red_btn_small offLineNewsInformation' newsId='"+data[i].id+"'>下线</button>"+
			   							"<button class='green_btn_small pushNewsMessage' newsId='"+data[i].id+"'>推送通知消息</button>";
			   				}else{
			   					html += "<button class='green_btn_small publishNewsInformation' newsId='"+data[i].id+"'>发布</button>"+
			   							"<button class='red_btn_small deleteNewsInformation' newsId='"+data[i].id+"'>删除</button>";
			   				}
							html += "<button class='yellow_btn_small showNewsComments' newsId='"+data[i].id+"'>查看评论</button>" +
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
	
	//点击已发布和未发布
	$(document).on("click",".isPublish",function(){
		var type = $(this).attr("type");
		var isPublish = 0;
		if(type=="yes"){
			isPublish = 1;
		}else{
			isPublish = 0;
		}
		$(this).find("li").addClass("tab_active");
		$(this).siblings("a").find("li").removeClass("tab_active");
		$("#isPublish").val(isPublish);
		getPage(isPublish,"",0,0, 1);
		
	});
	
	//条件搜索文章
	$(document).on("click","#searchBtn",function(){
		var keywords = $("#keywords").val();
		var isPublish = $("#isPublish").val();
		var chanels = $("#chanels").val();
		var periods = $("#periods").val();
		getPage(isPublish, keywords, chanels, periods, 1);
	});
	
	
	//文章下线
	$(document).on("click",".offLineNewsInformation",function(){
		var newsId = $(this).attr("newsId");
		$.post(
			path+"/news/offLineNewsInformation",
			{
				"newsId":newsId
			},function(ret){
//				layer.alert(ret.msgbox);
				layer.msg(ret.msgbox, {
	      			icon: 1,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
				if(ret.code==1){
					setTimeout(function(){
						var isPublish = 0;
						var keywords = "";
						var chanels = 0;
						var periods = 0;
						getPage(isPublish, keywords, chanels, periods, 1);//下线后跳转到未发布列表
						$(".isPublish").find("li").toggleClass("tab_active");
						//关闭弹出层
						
					},1000);
				}
			}
		);
	});
	
	
	//查看评论列表
	$(document).on("click",".showNewsComments",function(){
		var newsId = $(this).attr("newsId");
		//获取评论列表
		$.post(
			path+"/news/showNewsComments",
			{
				"newsId":newsId
			},function(json){
				var html = "";
				var data = json.data;
				if(json.code==10){
					html = "<tr height='40px'><td colspan='5'><span style='color:red;'>暂无评论！</span></td></tr>";
				}else if(json.code==1){
					for(var i=0;i<data.length;i++){
						html += "<tr height='40px'>";
	   					html += "<td>"+(i+1)+"</td>";
	   					html += "<td>"+data[i].userName+"</td>";
		   				html += "<td>"+data[i].content+"</td>";
		   				html += "<td>"+data[i].addTime+"</td>";
		   				html += "<td><button class='red_btn_small deleteNewsComments' commentId='"+data[i].id+"'>删除评论</button><td></tr>";
					}
				}
				$("#commentsBody").html(html);
			}
		);
		layer.open({
			title:'评论列表',
	  		type: 1,
	  		area: ['800px','750px'], //宽高
	  		content: $('.pop_talking')
		});
	});
	
	
	//删除评论
	$(document).on("click",".deleteNewsComments",function(){
		$this = $(this);
		var commentId = $(this).attr("commentId");
		layer.alert('要删除这条记录么？', {
            icon: 0,
            title: '提示',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
            	layer.close(index);
            	$.post(
            			path+"/news/deleteNewsComments",
            			{
            				"commentId":commentId
            			},function(ret){
//            				layer.alert(ret.msgbox);
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
		btnColor(0);
	});
	
	//删除文章（未发布的）
	$(document).on("click",".deleteNewsInformation",function(){
		$this = $(this);
		var newsId = $(this).attr("newsId");
		layer.alert('要删除这条记录么？', {
            icon: 0,
            title: '提示',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
            	layer.close(index);
            	$.post(
        				path+"/news/deleteNewsInformation",
        				{
        					"newsId":newsId
        				},function(ret){
//        					layer.alert(ret.msgbox);
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
		btnColor(0);
	});
	
	
	//发布文章
	$(document).on("click",".publishNewsInformation",function(){
		var newsId = $(this).attr("newsId");
		$.post(
			path+"/news/publishNewsInformation",
			{
				"newsId":newsId
			},function(ret){
//				layer.alert(ret.msgbox);
				layer.msg(ret.msgbox, {
	      			icon: 1,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
				if(ret.code==1){
					setTimeout(function(){
						var isPublish = 1;
						var keywords = "";
						var chanels = 0;
						var periods = 0;
						getPage(isPublish, keywords, chanels, periods, 1);//发布后跳转到已发布列表
						$(".isPublish").find("li").toggleClass("tab_active");
						//关闭弹出层
						
					},1000);
				}
			}
		);
	});
	
	//添加或修改文章
	$(document).on("click",".addOrEditNewsInformation",function(){
		var type  = $(this).attr("type");
		var newsId = $(this).attr("newsId");
		var isPublish = 0;
		var ua = UE.getEditor("content",{
			autoHeightEnabled: true,
	    	autoFloatEnabled: true
		});
		if(type == "add"){
			newsId = 0;
			$("#title").val("");
			$("#sourceFrom").val("");
			$("#fromLogoUrl").val("");
			$("#imgPicker_"+imgUploadId.split(",")[0]).attr("src", "");
			$("#introduct").val("");
			setTimeout(function(){
				ua.ready(function () {
				       // editor准备好之后才可以使用
				       ua.setContent("");
				     });
			},10);
			$("#content").val("");
			$("#imageUrl").val("");
			$("#imgPicker_"+imgUploadId.split(",")[1]).attr("src", "");
		}
		$.post(
				path+"/news/findNewsInformation",
				{
					"newsId":newsId
				},function(json){
					if(json.code==1){
						var news = json.data.news;
						var newsChanels = json.data.newsChanels;
						var pregnantStage = json.data.pregnantStage;
						//频道下拉列表
						var channelhtml = "";
						for(var i=0;i<newsChanels.length;i++){
							channelhtml += "<option value='"+newsChanels[i].id+"'>"+newsChanels[i].chanelName+"</option>";
						}
						$("#channels").html(channelhtml);
						
						//目标用户
						var pregnantStagehtml = "<label class='label labradio checkall'><input type='checkbox' id='checkall' value='0'/>全选</label>";
						for(var j=0;j<pregnantStage.length;j++){
							pregnantStagehtml += "<label class='label labradio'><input type='checkbox' name='checklist' value='"+pregnantStage[j].type+"'>"+pregnantStage[j].desc+"</label>";
						}
						$("#pregnantStagehtml").html(pregnantStagehtml);
						
						//文章信息
						if(type=="edit"){
							//下拉选中
							$("#channels").find("option[value='"+news.channelId+"']").attr("selected",true);
							//有的选中
							var period = news.period;
							$(".labradio").find("input").each(function(){
								var $this = $(this);
								if($(this).val()!=0){
									if(period.indexOf($(this).val())>=0){
										$this.attr("checked",true);
									}
								}
							});
							if($("input[name='checklist']:checked").length==$("input[name='checklist']").length){
								$("#checkall").prop("checked",true);
							}
							$("#title").val(news.title);
							$("#sourceFrom").val(news.sourceFrom);
							$("#logoUrl").val(news.fromLogoUrl);
							$("#imgPicker_"+imgUploadId.split(",")[0]).attr("src", news.imagePrefix+news.fromLogoUrl);
							$("#introduct").val(news.introduct);
//							ua.addListener("ready", function () {
							ua.ready(function () {
						       // editor准备好之后才可以使用
//								ua.execCommand( "clearlocaldata" );
								ua.setContent(news.content);
						     });
//							ua.destroy();
							$("#content").val(news.content);
							$("#imgUrl").val(news.imageUrl);
							$("#imgPicker_"+imgUploadId.split(",")[1]).attr("src", news.imagePrefix+news.imageUrl);
							isPublish = news.isPublish;
						}
						//孕期阶段默认全选
						if(type=="add"){
							$("input[type='checkbox']").prop("checked",true);
							
						}
					}
				}
			);
			$("#edui1,#edui1_iframeholder").css("width","638px");
			$("#content").css("padding",0);
			$("#content").css("display","inline-block");
			
		//上传作者头像
//   	 	layui.use("upload", function(){
//	   	  	layui.upload({
//	   	  		elem:"#logoUrl",
//	   	  	    url: path+"/file/upload", //上传接口
//	   	  	    success: function(res) { //上传成功后的回调
//	   	  	      var result = eval("("+res.data+")");
//	   	  	      //上传后图片地址
//	   	  	      var fileUrl = "/"+result.data.fileUrl;
//	   	  	      $("#fromLogoUrl").val(fileUrl);
//	   	  	    }
//	   	  	});
//   	 	});
//   	//上传图片成功后的地址
//			var uploadImgUrl = upload_img_url!=""?"/"+upload_img_url:"";
//			alert(uploadImgUrl);
		//上传主题图
//	   	 layui.use("upload", function(){
//		   	  	layui.upload({
//		   	  		elem:"#imgUrl",
//		   	  	    url: path+"/file/upload", //上传接口
//		   	  	    success: function(res) { //上传成功后的回调
//		   	  	      var result = eval("("+res.data+")");
//		   	  	      //上传后图片地址
//		   	  	      var fileUrl = "/"+result.data.fileUrl;
//		   	  	      $("#imageUrl").val(fileUrl);
//		   	  	    }
//		   	  	});
//		 	});
		layer.open({
			title:'文章编辑',
	  		type: 1,
	  		area: ['880px','750px'], //宽高
	  		content: $('.addOrEdit'),
	  		btn: ['保存','发布','取消'],
	  		yes: function(index){
//	  			addOrEditNews(index,ua,newsId,"save");
	  			var title = $("#title").val();
				var channelId = $("#channels").val();
				var sourceFrom = $("#sourceFrom").val();
				var fromLogoUrl = $("#logoUrl").val();
				var introduct = $("#introduct").val();
				var content = ua.getContent();//获取富文本中的内容
//				var content = ua.getPlainTxt();
				var imageUrl = $("#imgUrl").val();
				var stages = new Array();
				$("#pregnantStagehtml").find("input[name='checklist']").each(function(){
					if($(this).is(":checked")){
						stages.push($(this).val());
					}
				});
				layer.close(index);
				$.post(
					path+"/news/addOrEditNewsInformation",
					{
						"newsId":newsId,
						"title":title,
						"channelId":channelId,
						"sourceFrom":sourceFrom,
						"fromLogoUrl":fromLogoUrl,
						"introduct":introduct,
						"content":content,
						"imageUrl":imageUrl,
						"stages":stages.toString(),
						"isPublish":isPublish,
						"btnStatus":"save"
					},function(ret){
						layer.msg(ret.msgbox, {
			      			icon: 1,
			      			shade: [0.5,'#000'],
			      			btn: ['确定']
			    		});
	    			if(ret.code == 1){
//	    				setTimeout(function(){
	    					if(type=="add"){
	    						$(".isPublishYes").find("li").removeClass("tab_active");
	    						$(".isPublishNo").find("li").addClass("tab_active");
	    						$("#isPublish").val(0);
	    						curr = 1;
	    					}
	    					var keywords = $("#keywords").val();
	    					var isPublish = $("#isPublish").val();
	    					var chanels = $("#chanels").val();
	    					var periods = $("#periods").val();
	    					getPage(isPublish, keywords, chanels, periods, curr);
//	    				},1000);
	    			}
					}
				);
	    	}
			,btn2:function(index){
//				addOrEditNews(index,ua,newsId,"publish");
				$("#isPublish").val(isPublish);
				var title = $("#title").val();
				var channelId = $("#channels").val();
				var sourceFrom = $("#sourceFrom").val();
				var fromLogoUrl = $("#logoUrl").val();
				var introduct = $("#introduct").val();
				var content = ua.getContent();//获取富文本中的内容
//				var content = ua.getPlainTxt();
				var imageUrl = $("#imgUrl").val();
				var stages = new Array();
				$("#pregnantStagehtml").find("input[name='checklist']").each(function(){
					if($(this).is(":checked")){
						stages.push($(this).val());
					}
				});
				layer.close(index);
				$.post(
					path+"/news/addOrEditNewsInformation",
					{
						"newsId":newsId,
						"title":title,
						"channelId":channelId,
						"sourceFrom":sourceFrom,
						"fromLogoUrl":fromLogoUrl,
						"introduct":introduct,
						"content":content,
						"imageUrl":imageUrl,
						"stages":stages.toString(),
						"isPublish":1,
						"btnStatus":"publish"
					},function(ret){
						layer.msg(ret.msgbox, {
			      			icon: 1,
			      			shade: [0.5,'#000'],
			      			btn: ['确定']
			    		});
	    			if(ret.code == 1){
//	    				setTimeout(function(){
	    					if($("#isPublish").val()==0){
	    						$(".isPublishYes").find("li").addClass("tab_active");
	    						$(".isPublishNo").find("li").removeClass("tab_active");
	    						$("#isPublish").val(1);
	    						curr = 1;
	    					}
	    					var keywords = $("#keywords").val();
	    					var isPublish = $("#isPublish").val();
	    					var chanels = $("#chanels").val();
	    					var periods = $("#periods").val();
	    					getPage(isPublish, keywords, chanels, periods, curr);
//	    				},1000);
	    			}
					}
				);
			},
	    	end:function(){
	    		ua.destroy();
	    	}
		});
		btnColor(1);
	});
	
	function btnColor (value){
		if(value==1){
			$("body .layui-layer-btn1").css({"background":"#DC6666","color":"#fff","border":"1px solid #DC6666"});
		}else{
			$("body .layui-layer-btn1").css({"background":"#f1f1f1","color":"#333","border":"1px solid #dedede"});
		}
		
	}
	
	$(document).on("click","#checkall",function(){
		$("input[name='checklist']").prop("checked",$(this).is(":checked"));
	});
	
	$(document).on("click","input[name='checklist']",function(){
		if($("input[name='checklist']:checked").length<$("input[name='checklist']").length){
			$("#checkall").prop("checked",false);
		}else{
			$("#checkall").prop("checked",true);
		}
	});
	
	//推送通知消息
	$(document).on("click",".pushNewsMessage",function(){
		var newsId = $(this).attr("newsId");
		$("#titles").val("");
		$("#descriptions").val("");
		layer.open({
			title:'添加推送描述',
	  		type: 1,
	  		area: ['600px','300px'], //宽高
	  		content: $('.push_message'),
	  		btn: ['确定','取消'],
	  		yes: function(index){
	  			var title = $("#titles").val().trim();
	  			var description = $("#descriptions").val().trim();
	  			if(title == null || title == ""){
	  				$("#titles").next("span.errorMsg").text("标题不能为空！");
	  				return false;
	  			}else{
	  				$("#titles").next("span.errorMsg").text("");
	  			}
	  			if(description == null || description == ""){
	  				$("#descriptions").next("span.errorMsg").text("描述不能为空！");
	  				return false;
	  			}else{
	  				$("#descriptions").next("span.errorMsg").text("");
	  			}
	  			layer.close(index);
	  			$.post(
	  				path+"/news/pushNewsMessage",
	  				{
	  					"newsId":newsId,
	  					"title":title,
	  					"description":description,
	  				},function(ret){
	  					layer.msg(ret.msgbox, {
	  		      			icon: 1,
	  		      			shade: [0.5,'#000'],
	  		      			btn: ['确定']
	  		    		});
	  				}
	  			);
	  			
	    	}
		});
		btnColor(0);
	});
	
	
});

