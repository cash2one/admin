//总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function() {
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	getPage(1);
	
});
function getPage(currIndex){
	//请求总页数
	$.ajax({
		url: path + "/music/findMusic",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: {"pageIndex":1, "everyPage":everyPage, "first":true},
		async: false,//同步
		success: function(json) {
			totalPage = json.data;
		}
	});
	//加载标签数据
	var musicLabel = loadLabel();
	//显示分页
	layui.use(["laypage"], function() {
		var laypage = layui.laypage;
		layui.laypage({
		    cont: "page",
		    pages: totalPage,
		    curr:currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false
		   	 	};
		   	 	//请求宝宝发育变化
		   	 	$.ajax({
		   			url: path + "/music/findMusic",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data.list;
		   				//序号
		   				var no = json.data.num;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {
		   					no = no+1;
		   					var labelName = "";
		   					if(data[i].labelId != "" && data[i].labelId != null) {
		   						//标签
			   					var labelId = data[i].labelId.split(",");
			   					for(var j=0; j<labelId.length; j++) {
			   						for(var k=0; k<musicLabel.length; k++) {
			   							if(parseInt(labelId[j]) == musicLabel[k].id) {
			   								labelName+=musicLabel[k].labelName+",";
			   							}
			   						}
			   					}
		   					}
		   					labelName = labelName.substring(0, labelName.lastIndexOf(","));
		   					//上线状态
		   					var statuStr = data[i].status==1?"已上线":"未上线";
		   					//按钮显示
		   					var butStatus = data[i].status==1?"下线":"上线";
		   					//孕期信息
		   					var categoryInfo = data[i].category==3?"孕期电台":"";
		   					html += "<tr id='target'>";
		   					html += "<td>"+no+"</td>";
	   						html += "<td><img src='"+data[i].imagePrefix+"/"+data[i].coverUrl+"'></td>";
	   						html += "<td>"+data[i].name+"</td>";
   							html += "<td>"+categoryInfo+"</td>";
							html += "<td>"+labelName+"</td>";
							html += "<td>"+data[i].length+"</td>";
							html += "<td>"+data[i].formatTime+"</td>";
							html += "<td id='sta"+data[i].id+"'>"+statuStr+"</td>";
							html += "<td width='25%'>";
							html += "<button class='yellow_btn_small' id='onLineStatus"+data[i].id+"' onclick='onLineStatus("+data[i].id+")' line='"+data[i].status+"'>"+butStatus+"</button>";
							html += "<button class='green_btn_small' onclick='ShowDiv(2, "+data[i].id+")'>修改</button>";
							html += "<button class='grey_btn_small' onClick='play(\""+data[i].imagePrefix+data[i].voiceUrl+"\")'>试听</button>";
							html += "<button class='red_btn_small' onClick='deletediv("+data[i].id+")'>删除</button>";
		   					html += "<button class='blue_btn_small' onClick='firstMusic("+data[i].id+")'>置顶</button>";
		   					html += "</td>";
	   						html += "</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
	
}

//电台上下线
function onLineStatus(id) {
	//获取上下线状态
	var status = $("#onLineStatus"+id).attr("line");
	if(status == 1) {	//上线
		status = 0;
	} else {	//下线
		status = 1;
	}
	//参数
	var param = {
		"id":id,
		"status":status
	};
	param = JSON.stringify(param);
	//修改
	$.ajax({
		url: path + "/music/saveAndUpdatePregnantMusicInfo",
		type: "POST",
		contentType: "application/json;charset=utf-8",
		dataType: "json",//从服务器端返回的数据类型
		data: param,
		success: function(json) {
			if(json.code == 1) {
				$("#sta"+id).html(status==1?"已上线":"未上线");
				if(status == 0) {	//上线
					$("#onLineStatus"+id).html("上线");
				} else {	//下线
					$("#onLineStatus"+id).html("下线");
				}
				$("#onLineStatus"+id).attr("line", status);
			} else {
				layer.msg(json.msgbox, {
	      			icon: 1,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
			}
		}
	});
}
//删除
function deletediv(id) {
	layer.confirm("要删除这条记录么？", {
		but:["确定", "取消"]
	}, function(index) {
		//参数
		var param = {
			"id":id,
			"isDeleted":1
		};
		param = JSON.stringify(param);
		//修改
		$.ajax({
			url: path + "/music/saveAndUpdatePregnantMusicInfo",
			type: "POST",
			contentType: "application/json;charset=utf-8",
			dataType: "json",//从服务器端返回的数据类型
			data: param,
			success: function(json) {
				if(json.code == 1) {
					layer.alert("删除成功！");
					//刷新页面
//					window.location.reload(true);
					getPage(curr);
				} else {
					layer.msg("删除失败！", {
		      			icon: 1,
		      			shade: [0.5,'#000'],
		      			btn: ['确定']
		    		});
				}
			}
		});
	}, function(index) {
		
	});
}
//播放
function play(url) {
	//清空div
	$("#mysong").html("");
	$("#mysong").jmp3({
		filepath: url,
		autoplay:"true",
		repeat:"false",
		backcolor: "000000",
		forecolor: "00ff00",
		width: 200,
		showdownload: "false"
	});
}
//置顶
function firstMusic(id) {
	$.ajax({
		url: path + "/music/firstMusic",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: {"id":id},
		async: false,
		success: function(json) {
			if(json.code == 1) {
//				window.location.reload(true);
				getPage(curr);
			} else {
				layer.msg(json.msgbox, {
	      			icon: 2,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
			}
		}
	});
}
//弹出层
function ShowDiv(status, id) {
	//mp3文件播放时长
	var duration = "";
	//上传成功后的地址
	var fileUrl = "";
	//孕期电台详情
	var data = null;
	//新增
	if(status == 1) {
		$("#name").val("");
		$("#voiceUrl").val("");
		$(".img-thumbnail").attr("src", "");
		$("#status").attr("checked", false);
		//加载标签数据
		loadLabel();
	} else if(status == 2) {	//修改
		//获取电台详情
		$.ajax({
			url: path + "/music/findPregnantMusicInfoDetail",
			type: "get",
			dataType: "json",//从服务器端返回的数据类型
			data: {"id":id},
			async: false,
			success: function(json) {
				data = json.data;
			}
		});
		$("#name").val(data.name);
		$("#voiceUrl").val(data.voiceUrl);
		$("#coverUrl").val(data.coverUrl);
		$(".img-thumbnail").attr("src", data.imagePrefix+data.coverUrl);
		var onLine = false;
		//上线
		if(data.status == 1) {
			onLine = true;
		} 
		$("#status").attr("checked", onLine);
		if(data.labelId != "" && data.labelId != null) {
			//已选标签
			var selectLabel = data.labelId.split(",");
			//加载标签数据
			var label = loadLabel();
			var html = "";
			for(var j=0; j<selectLabel.length; j++) {
				for(var i=0; i<label.length; i++) {
					if(parseInt(selectLabel[j]) == label[i].id) {
						html += "<span class='blueback pop_tab2'><span class='labels' id='"+label[i].id+"'>"+label[i].labelName+"</span><i class='pop_tab_delete2'>×</i></span>";
					}
				}
			}
			$("#selectedLabel").html(html);
		}
	}
	//上传
 	layui.use("upload", function(){
	  	layui.upload({
	  		title: "选择音频文件",
	  	    url: path+"/file/upload", //上传接口
	  	    ext: "mp3",
	  	    success: function(res) { //上传成功后的回调
	  	      console.log(res);
	  	      if(res.code == 1) {
	  	    	  var datas = res.data;
	  	    	  //获取时长
	  	    	  duration = datas.substring(datas.indexOf("#")+1, datas.lastIndexOf("#"));
	  	    	  //替换掉时长，变成标准json格式数据
	  	    	  datas = datas.replace("#"+duration+"#", "");
	  	    	  //json字符串转json对象
	  	    	  datas = eval("("+datas+")");
	  	    	  //上传成功后的地址
		  	      fileUrl = "/"+datas.data.fileUrl;
	  	    	  $("#voiceUrl").val(fileUrl);
	  	      } else {
	  	    	layer.msg(json.msgbox, {
	      			icon: 1,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
	  	      }
	  	    }
	  	});
 	});
 	//保存
	layer.open({
		title:'上传节目',
  		type: 1,
  		area: ['660px'], //宽高
  		content: $('.normal_form'),
  		btn: ['保存'],
  		yes: function(index){
  			//标题
  			var name = $("#name").val();
  			//上传图片成功后的地址
  			var uploadImgUrl = upload_img_url!=""?"/"+upload_img_url:"";
  			//是否上线
  			var onLine = $("#status").is(":checked");
  			var labelId = "";
  			//循环取得标签ID
			$("#selectedLabel span span").each(function() {
				labelId += $(this).attr("id")+",";
			});
			if(labelId != "") {
				labelId = labelId.substring(0, labelId.lastIndexOf(","));
			}
			if(name == "") {
				$("#titleError").html("标题不能为空！");
				return;
			} else {
				$("#titleError").html("");
			}
  			//参数
  			var param = {
  				"id":data!=null?data.id:null,
				"name":name,
				"voiceUrl":$("#voiceUrl").val(),
				"length":duration!=""?duration:data.length,
				"category":3,
				"listenTimes":0,
				"coverUrl":uploadImgUrl==""?$("#coverUrl").val():uploadImgUrl,
				"status":onLine==true?1:0,
				"isDeleted":0,
				"labelId":labelId
 			};
  			//转json字符串
  			param = JSON.stringify(param);
  			$.ajax({
  				url: path + "/music/saveAndUpdatePregnantMusicInfo",
  				type: "POST",
  				contentType: "application/json;charset=utf-8",
  				dataType: "json",//从服务器端返回的数据类型
  				data: param,
  				success: function(json) {
  					if(json.code == 1) {
  						layer.close(index);
  						if(status == 1) {
  							layer.msg('添加成功！', {
  				      			icon: 1,
  				      			shade: [0.5,'#000'],
  				      			btn: ['确定']
  				    		}, function() {
//  				    			window.location.reload(true);
  				    			getPage(curr);
  				    		});
  						} else if(status == 2) {
  							layer.msg('修改成功！', {
  				      			icon: 1,
  				      			shade: [0.5,'#000'],
  				      			btn: ['确定']
  				    		}, function() {
//  				    			window.location.reload(true);
  				    			getPage(curr);
  				    		});
  						}
  					} else {
  						layer.close(index);
  						layer.msg('操作失败！', {
			      			icon: 2,
			      			shade: [0.5,'#000'],
			      			btn: ['确定']
			    		});
  					} 
  				}
  			});
    	}
	});
}
//标签个数
var count = 0;
//新增标签
function addLabel() {
	count ++;
	if(count>5) {
		layer.msg("最多只能添加5个标签！", {
  			icon: 2,
  			shade: [0.5,'#000'],
  			btn: ['确定']
		});
		return;
	} 
	//标签名
	var labelName = $("#labelName").val();
	if(labelName != "") {
		//新增标签
		var param = {
			"labelName":labelName
		};
		//转json字符串
		param = JSON.stringify(param);
		$.ajax({
			url: path + "/music/saveLabel",
			type: "POST",
			contentType: "application/json;charset=utf-8",
			dataType: "json",//从服务器端返回的数据类型
			data: param,
			success: function(json) {
				if(json.code == 1) {
					var html = "<span class='blueback pop_tab2'><span class='labels' id='"+json.data+"'>"+labelName+"</span><i class='pop_tab_delete2'>×</i></span>";
					$("#selectedLabel").append(html);
					//加载电台标签数据
					loadLabel();
				} else {
					layer.msg(json.msgbox, {
		      			icon: 2,
		      			shade: [0.5,'#000'],
		      			btn: ['确定']
		    		});
				}
			}
		});
	} else {
		//获取选中的标签
		var name = $("#labelId").find("option:selected").text();
		//获取选中的标签ID
		var id = $("#labelId").find("option:selected").val();
		//是否出现重复
		var isRepetition = false;
		if(typeof($("#selectedLabel span span").html()) != "undefined") {
			//循环取得标签名字
			$("#selectedLabel span span").each(function() {
				var span = $(this).html();
				if(span == name) {
					isRepetition = true;
					return false;
				}
			});
			if(isRepetition) {
				layer.msg("不能添加重复的标签！", {
	      			icon: 2,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
				return;
			}
		}
		var html = "<span class='blueback pop_tab2'><span class='labels' id='"+id+"'>"+name+"</span><i class='pop_tab_delete2'>×</i></span>";
		$("#selectedLabel").append(html);
		isRepetition = false;
	}
}
//加载电台标签数据
function loadLabel() {
	var data = null;
	$.ajax({
		url: path + "/music/findLabelAll",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: {},
		async: false,
		success: function(json) {
			data = json.data;
			if(json.code == 1) {
				var html = "";
				for(var i=0; i<data.length; i++) {
					html += "<option value='"+data[i].id+"'>"+data[i].labelName+"</option>";
				}
				$("#labelId").html(html);
			}
		}
	});
	return data;
}
//删除标签
$(document).on("click",".pop_tab_delete2",function(){
	$(this).parent().remove();
});