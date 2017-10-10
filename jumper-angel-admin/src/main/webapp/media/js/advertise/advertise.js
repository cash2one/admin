/**
 * 广告js
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
	getPage(keywords,1);
});

//显示分页
function getPage(keywords,currIndex){
	$.ajax({
		url:path+"/advertise/findAdvertiseList",
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
		   	 		"keywords":keywords
		   	 	};
		   	 	$.ajax({
		   			url: path + "/advertise/findAdvertiseList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var imageUrl = "";
		   				var webUrl = "";
		   				var operator = "";
		   				for(var i=0; i<data.length; i++) {
		   					//图片地址
		   					if(data[i].imageUrl.indexOf("http")>=0){
		   						imageUrl = data[i].imageUrl;
		   					}else{
		   						imageUrl = data[i].filePrefix+data[i].imageUrl;
		   					}
		   					//网页链接
		   					if(data[i].webUrl.indexOf("http")>=0){
		   						webUrl = data[i].webUrl;
		   					}else{
		   						webUrl = data[i].filePrefix+data[i].webUrl;
		   					}
		   					operator = data[i].isBanner==0?"设置为banner":"取消banner";
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td style='width:300px;'>"+data[i].description+"</td>";
		   					html += "<td><image src='"+imageUrl+"'/></td>";
		   					html += "<td><a target='_blank' style='color:#666;' href='"+webUrl+"' title='"+webUrl+"'>"+webUrl.substring(0,50)+"</a></td>";
		   					html += "<td>"+data[i].addTimeStr+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditAdvertiseInfo' type='edit' ad_id='"+data[i].id+"' description='"+data[i].description+"' " +
			   						"image_url='"+data[i].imageUrl+"' web_url='"+data[i].webUrl+"' is_activity='"+data[i].isActivity+"' " +
			   								"prefix='"+data[i].filePrefix+"'>编辑</button>"+
						   				"<button class='red_btn_small deleteAdvertise' ad_id='"+data[i].id+"'>删除</button>"+
						   				"<button class='green_btn_small operateAdvertise' ad_id='"+data[i].id+"' is_banner='"+data[i].isBanner+"'>"+operator+"</button>"
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
		getPage(keywords,1);
	});

	//删除广告
	$(document).on("click",".deleteAdvertise",function(){
		var ad_id = $(this).attr("ad_id");
		var $this = $(this);
		 layer.alert('要删除这条记录么？', {
	            icon: 0,
	            title: '提示',
	            btn:['删除','取消'],
	            btnAlign: 'c',
	            yes: function(index, layero){
	                layer.close(index);
	                $.post(
	                	path+"/advertise/deleteAdvertise",
	                	{
	                		"advertiseId":ad_id
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
	$(document).on("click",".addOrEditAdvertiseInfo",function(){
		var type = $(this).attr("type");
		var ad_id = $(this).attr("ad_id");
		var description = $(this).attr("description");
		var image_url = $(this).attr("image_url");
		var web_url = $(this).attr("web_url");
		var is_activity = $(this).attr("is_activity");
		var prefix = $(this).attr("prefix");
		if(type=="edit"){
			$("#description").val(description);
			$("#imgUrl").val(image_url);
			$("#webUrl").val(web_url);
			$(".img-thumbnail").attr("src", prefix+image_url);
			$("#isActivity").find("option[value='"+is_activity+"']").attr("selected",true);
		}
		if(type=="add"){
			ad_id = 0;
			$("#description").val("");
			$("#imgUrl").val("");
			$("#webUrl").val("");
			$(".img-thumbnail").attr("src", "");
		}
		layer.open({
			title:'广告内容编辑',
	  		type: 1,
	  		area: ['700px','500px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			description = $("#description").val().trim();
	  			web_url = $("#webUrl").val().trim();
	  			is_activity = $("#isActivity").val();
	  			imgUrl = $("#imgUrl").val();
	  			//上传图片成功后的地址
	  			var uploadImgUrl = upload_img_url!=""?"/"+upload_img_url:imgUrl;
	  			if(description == "" || description == null){
	  				layer.alert("请输入广告描述！");
	  				return;
	  			}
	  			if(description.length>200){
	  				layer.alert("广告描述不能超过200字符！");
	  				return;
	  			}
	  			if(uploadImgUrl == "" || uploadImgUrl == null){
	  				layer.alert("请上传广告图片！");
	  				return;
	  			}
	  			if(web_url == "" || web_url == null){
	  				layer.alert("请输入广告链接！");
	  				return;
	  			}
	  			layer.close(index);
	  			$.post(
						path+"/advertise/addOrEditAdvertise",
						{
							"ad_id":ad_id,
							"description":description,
		  					"web_url":web_url,
		  					"is_activity":is_activity,
		  					"image_url":uploadImgUrl!=""?uploadImgUrl:$("#imgUrl").val()
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
	            				getPage(keywords,curr);
	            			},1000);
	            			}
						}
					)
	    	}
		});
		
	});
	
	
	//设置或取消banner
	$(document).on("click",".operateAdvertise",function(){
		var ad_id = $(this).attr("ad_id");
		var is_banner = $(this).attr("is_banner");
		var $this = $(this);
		$.post(
            	path+"/advertise/operateAdvertise",
            	{
            		"ad_id":ad_id,
            		"is_banner":is_banner
            	},function(ret){
            		layer.msg(ret.msgbox, {
	  		      			icon: 1,
	  		      			shade: [0.5,'#000'],
	  		      			btn: ['确定']
	  		    		});
            		if(ret.code==1){
            			$this.html(is_banner==0?"取消banner":"设置为banner");
            			$this.attr("is_banner",is_banner==0?1:0);
            		}
            	}
            );
	});
	


});
