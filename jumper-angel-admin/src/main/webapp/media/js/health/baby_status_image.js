//总页数
var totalPage = 0;
$(document).ready(function() {
	//请求总页数
	$.ajax({
		url: path + "/baby/findBaby",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: {"pageIndex":1, "everyPage":everyPage, "first":true},
		async: false,//同步
		success: function(json) {
			totalPage = json.data;
		}
	});
	//显示分页
	layui.use(["laypage"], function() {
		var laypage = layui.laypage;
		layui.laypage({
		    cont: "page",
		    pages: totalPage,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    var curr = obj.curr;
		   	    console.log(curr);	
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false
		   	 	};
		   	 	//请求宝宝发育变化
		   	 	$.ajax({
		   			url: path + "/baby/findBaby",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {
		   					var img = data[i].imageUrl!=null?data[i].imagePrefix+data[i].imageUrl:path+"/assets/images/imgdefault.png";
		   					html+="<tr>";
		   					if(data[i].week == -1) {
		   						html+="<td>备孕期</td>";
		   					} else {
		   						html+="<td>孕"+data[i].week+"周</td>";
		   					}
   							html+="<td><img src='"+img+"' id='img"+data[i].week+"'></td>";
   							html+="<td title='"+img+"' id='url"+data[i].week+"'>"+img.substring(0, 30)+"...</td>";
   							html+="<td><input type='file' name='file' class='layui-upload-file' onclick='getWeek("+data[i].week+", \""+data[i].imagePrefix+"\")'></td>";
							html+="</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
			   	//上传
		   	 	layui.use("upload", function(){
			   	  	layui.upload({
			   	  	    url: path+"/file/upload", //上传接口
			   	  	    success: function(res) { //上传成功后的回调
			   	  	      console.log(res);
			   	  	      var result = eval("("+res.data+")");
			   	  	      //上传后图片地址
			   	  	      var fileUrl = "/"+result.data.fileUrl;
			   	  	      //参数
			   	  	      var param = {
			   	      		  "week":week,
			   	      		  "imageUrl":fileUrl,
			   	      		  "updateImage":1,
			   	      		  "identifying":2
			   	  	      };
			   	  	      param = JSON.stringify(param);
			   	  	      //修改图片
		   	 	 	      $.ajax({
		   	 	 	    	  url: path + "/baby/savePrepnancy",
		   	 	 	    	  type: "POST",
		   	 	 	    	  contentType: "application/json;charset=utf-8",
		   	 	 	    	  dataType: "json",//从服务器端返回的数据类型
		   	 	 	    	  data: param,
		   	 	 	    	  success: function(json) {
		   	 	 	    		  if(json.code == 1) {
		   	 	 	    			  var imgPath = imagePrefix+fileUrl;
		   	 	 	    			  $("#img"+week).attr("src", imgPath);
		   	 	 	    			  $("#url"+week).html(imgPath.substring(0, 30)+"...");
		   	 	 	    		  }
		   	 	 	    	  }
		   	 	 	      });
			   	  	    }
			   	  	});
		   	 	});
	   		}
	  	});
	});
});
//孕周
var week = 0;
//图片前缀
var imagePrefix = "";
//获取孕周
function getWeek(id, prefix) {
	week = id;
	imagePrefix = prefix;
}