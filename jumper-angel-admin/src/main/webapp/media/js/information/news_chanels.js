/**
 * 新闻频道js
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
	getPage(1);
});

//显示分页
function getPage(currIndex){
	$.ajax({
		url:path+"/news/findNewsChanelsList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true},
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
		   	 	};
		   	 	$.ajax({
		   			url: path + "/news/findNewsChanelsList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var status = "";
		   				for(var i=0; i<data.length; i++) {
		   					status = data[i].isDefaultSub==true?"是":"否";
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td>"+data[i].chanelName+"</td>";
			   				html += "<td>"+data[i].channelDesc+"</td>";
			   				html += "<td>"+data[i].chanelLabels+"</td>";
			   				html += "<td>"+status+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditNewsChanels' type='edit' chanelId='"+data[i].id+"' chanelName='"+data[i].chanelName+"' chanelDesc='"+data[i].channelDesc+"' isDefaultSub='"+data[i].isDefaultSub+"' chanelLabels='"+data[i].chanelLabels+"' imgUrl='"+data[i].imgUrl+"' prefix='"+data[i].prefix+"'>编辑</button>"+
								"<button class='green_btn_small topNewsChanels' chanelId='"+data[i].id+"'>置上</button>"+
								"<button class='red_btn_small deleteNewsChanels' chanelId='"+data[i].id+"' chanelName='"+data[i].chanelName+"'>删除</button>" +
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
	//删除新闻频道
	$(document).on("click",".deleteNewsChanels",function(){
		var $this = $(this);
		var chanelId = $(this).attr("chanelId");
		var chanelName = $(this).attr("chanelName");
		if(chanelName == "孕期知识"){
			layer.alert("孕期知识频道不能被删除！");
			return false;
		}
        layer.alert('要删除这条记录么？', {
            icon: 0,
            title: '提示',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
            	layer.close(index);
            	$.post(
            			path+"/news/deleteNewsChanels",
            			{
            				"chanelId":chanelId
            			},function(ret){
            				layer.alert(ret.msgbox);
                			if(ret.code == 1){
                				$this.parent().parent().remove();
                			}
            			}
            		);
            }
        });
	});
	
	//频道置上
	$(document).on("click",".topNewsChanels",function(){
		var chanelId = $(this).attr("chanelId");
		$.post(
			path+"/news/topNewsChanels",
			{
				"chanelId":chanelId
			},function(ret){
				if(ret.code==1){
					getPage(curr);
				}else{
					layer.alert("置上操作失败，请稍后重试！");
				}
			}
		);
	});
	
	var count = 0;
	$(".tab_list").find("span.labels").each(function(){
		count++;
		});
	//新增频道标签
	$(document).on("click",".addtab",function(){
		if(count >= 5){
			layer.alert("频道标签数不能超过5个！");
			return;
		}
		var tab_label = $("input[name='new_tab']").val().trim();
		
		if(tab_label==""||tab_label==null){
			layer.alert("请输入标签名称！");
			return;
		}else{
			if(tab_label.length>20){
				layer.alert("频道标签不能超过20个字符！");
				return;
			}
			var flag = false;
			$(".tab_list").find("span.labels").each(function(){
				if($(this).text()==tab_label){
					layer.alert("该频道标签已存在，请重试！");
					flag = true;
					return;
				}
  			});
			if(!flag){
				var tipcon = '<span class="blueback pop_tab2"><span class="labels">'+$("input[name='new_tab']").val() +'</span><i class="pop_tab_delete2">×</i>' +'</span>';
				$('.tab_list').prepend(tipcon);
				count++;
			}
		}
	});
	$(document).on("click",".pop_tab_delete2",function(){
		$(this).parent().remove();
	});
	
	//编辑频道
	$(document).on("click",".addOrEditNewsChanels",function(){
		var chanelId = $(this).attr("chanelId");
		var chanelName = $(this).attr("chanelName");
		var chanelDesc = $(this).attr("chanelDesc");
		var isDefaultSub = $(this).attr("isDefaultSub");
		var chanelLabels = $(this).attr("chanelLabels");
		var imgUrl = $(this).attr("imgUrl");
		var prefix = $(this).attr("prefix");
		var type = $(this).attr("type");
		$("#channel_tab").val("");
		if(type == "edit"){
			$("#chanelName").val(chanelName);
			if(chanelDesc!="无"){
				$("#chanelDesc").val(chanelDesc);
			}
			var isDefault = (isDefaultSub=="true")?1:0;
			$("#isDefaultSub").find("option[value='"+isDefault+"']").attr("selected",true);
			$("#imgUrl").val(imgUrl);
			$(".img-thumbnail").attr("src", prefix+imgUrl);
			$('.tab_list').html("");
			if(chanelLabels!="无"){
				var labels = new Array();
				labels = chanelLabels.split("、");
				for(var i=0; i<labels.length; i++){
					var tipcon = '<span class="blueback pop_tab2"><span class="labels">'+labels[i] +'</span><i class="pop_tab_delete2">×</i>' +'</span>';
					$('.tab_list').prepend(tipcon);
				}
			}
		}
		if(type == "add"){
			$("#chanelName").val("");
			$("#chanelDesc").val("");
			$("#imgUrl").val("");
			$(".tab_list").html("");
			$(".img-thumbnail").attr("src", "");
			
		}
		//上传频道图片
   	 	/*layui.use("upload", function(){
	   	  	layui.upload({
	   	  		elem:"#images",
	   	  	    url: path+"/file/upload", //上传接口
	   	  	    success: function(res) { //上传成功后的回调
	   	  	      var result = eval("("+res.data+")");
	   	  	      //上传后图片地址
	   	  	      var fileUrl = "/"+result.data.fileUrl;
	   	  	      $("#imgUrl").val(fileUrl);
	   	  	    }
	   	  	});
   	 	});*/
		layer.open({
			title:'频道',
	  		type: 1,
	  		area: ['680px','560px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			if(type=="add"){
	  				chanelId = 0;
	  			}
	  			chanelName = $("#chanelName").val();
	  			chanelDesc = $("#chanelDesc").val();
	  			isDefaultSub = $("#isDefaultSub").val();
	  			imgUrl = $("#imgUrl").val();
	  			//上传图片成功后的地址
	  			var uploadImgUrl = upload_img_url!=""?"/"+upload_img_url:imgUrl;
	  			var chanelLabelArray = new Array();
	  			$(".tab_list").find("span.labels").each(function(){
	  				chanelLabelArray.push($(this).text());
	  			});
	  			if(chanelName.trim()==null||chanelName.trim()==""){
	  				layer.alert("请输入频道名称！");
	  				return;
	  			}
	  			layer.close(index);
	  			$.post(
	  				path+"/news/checkChanelsName",
	  				{
	  					"chanelId":chanelId,
	  					"chanelName":chanelName
	  				},function(ret){
	  					if(ret.code==1){
	  						layer.alert("频道名重复，请检查！");
	  						return;
	  					}else{
	  						$.post(
  								path+"/news/addOrEditNewsChanels",
  								{
  									"chanelId":chanelId,
  				  					"chanelName":chanelName,
  				  					"chanelDesc":chanelDesc,
  				  					"isDefaultSub":isDefaultSub,
  				  					"imgUrl":uploadImgUrl,
  				  					"chanelLabelArray":chanelLabelArray.toString()
  								},function(data){
  									layer.alert(data.msgbox);
  			            			if(data.code == 1){
  			            				setTimeout(function(){
//  			            					window.location.reload();
  			            					getPage(curr);
  			            				},1000);
  			            			}
  								}
  							);
	  					}
	  				}
	  			)
//	  			layer.close(index);
	    		
	    	}
		});
	});
	
	
	
});

