/**
 * 我的解答（已结束）js
 */
//请求总页数
var totalPage = 0;
var keywords = "";
$(document).ready(function(){
	
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	getPage(keywords,1);
});

//显示分页
function getPage(keywords,currIndex){
	$.ajax({
		url:path+"/hospital/majorList/majorLists",
		type:"get",
		dataType:"json",
		data:{
			"pageIndex":currIndex,
			"everyPage":0,
			"keywords":keywords,
			"first":true
		},
		async:false,
		success:function(json){
			totalPage = json.data;
			//console.log(totalPage+"--------------"+totalPage);
		},error:function(){
			////console.log("失败");
		}
	});
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "page",
		    pages: totalPage,
		    curr:currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    var curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":15,
		   	 		"keywords":keywords,
		   	 		"first":false
		   	 	};
		   	 	$.ajax({
		   			url: path + "/hospital/majorList/majorLists",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				//var status = "";
		   				//console.log(json);
		   				for(var i=0; i<data.length; i++) {
		   					var url='';
		   					var imageUrl=data[i].imageUrl;
		   					var filePath=data[i].filePath
		   					url='../../assets/images/base/i.png';
		   					if(imageUrl!=''){
		   						//if(imageUrl.indexOf("http")>=0){
			   					url=data[i].imageUrl;
			   					//}else
			   					if(imageUrl=='../../assets/images/base/i.png'){
			   						url='../../assets/images/base/i.png';
			   					}else if(imageUrl.indexOf("http")==-1 && imageUrl!='../../assets/images/base/i.png'){
			   						url=filePath+'/'+imageUrl;//拼接url
			   					}
		   					}
		   					var date = new Date(data[i].addTime); //日期转换yy-mm-dd
		   					var mon = date.getMonth() + 1;
		   					var day = date.getDate();
		   					var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day);
		   					
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td>"+data[i].major+"</td>";
		   					html += "<td><img src='"+url+"'></td>";
		   					html += "<td>"+nowDay+"</td>";
		   					html += "<td><button class='green_btn_small addHospital' data-type='1' data-id='"+data[i].id+"' data-url='"+url+"'  data-major='"+data[i].major+"'>编辑</button><br>";
		   					html += "<button class='red_btn_small remove' data-majorId='"+data[i].id+"'>删除</button></td></tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}
//点击编辑
$(document).on("click",'.addHospital',function(){
	$("#name").val("");
	$("#uploadfile").val("");
	$(".pthis").html("");
	$(".img-thumbnail").attr("src","/admin/assets/images/empty.png");
	var _this=$(this);
	var type=_this.attr("data-type");
	var id=_this.attr("data-id");
	if(type==0){
		generalize(0,major);
	}else if(type==1){
		var major=_this.attr("data-major");
		var url=_this.attr("data-url");
		$("#name").val(major);
		$("#uploadfile").val(url);
		$(".img-thumbnail").attr("src",url);
		generalize(id,major);
	}
})


//编辑
function generalize(id,major){
	var active_c='';
	if($(".layui-laypage-curr em").eq(1).html()!=undefined){
		active_c=$(".layui-laypage-curr em").eq(1).html();
	}else{
		active_c=1;
	}
	layer.open({
		title:'科室信息管理',
  		type: 1,
  		area: ['460px','320px'], //宽高
  		content: $('.normal_form2'),
  		btn: ['保存','取消'],
  		yes: function(indexw){
  			var major=$("#name").val();
  			var imageUrl=$("#uploadfile").val();
  			var valid=validmajor(major);
  			if(major==''){
  				$(".pthis").html("*请输入科室名称");
  				return;
  			}
  			///group1/M00/2F/D4/eE08olltrnqAA44EAABUknYkYfE224.png
  			/*if(imageUrl=="/admin/assets/images/base/i.png"){
  				imageUrl='/group1/M00/05/71/wKgCQ1ll3ReAWxUMAABUkjiXJyI959.png';
  			}*/
  			if(valid){
  				$.ajax({
  	  				url:path+'/hospital/majorList/addOrUpdateMajor',
  	  				type:'post',
  	  				data:{
  	  					"majorId":id,
  	  					"major":major,
  	  					"imageUrl":imageUrl
  	  				},
  	  				success:function(json){
  	  					//console.log(json);
  	  					if(json.code==1){
  	  						//console.log("成功");
  	  						layer.close(indexw);
  	  						getPage('',active_c);
  	  					}else if(json.code==0){
  	  						$(".pthis").html("*科室名称已经存在");
  	  					}
  	  				}
  	  			})
  			}
  		},
    	end:function(){
    		//ua.destroy();
    	}
	});
}
//删除
$(document).on("click",'.remove',function(){
	var active_c='';
	if($(".layui-laypage-curr em").eq(1).html()!=undefined){
		active_c=$(".layui-laypage-curr em").eq(1).html();
	}else{
		active_c=1;
	}
	var majorId=$(this).attr("data-majorId")
	layer.alert('', {
	      title:'提示',
	      btn:['确认', '取消'],
	      content: '确认删除科室？',
	      btnAlign: 'c',
	      yes: function(s){
	    	  $.ajax({
	    		  url:path+'/hospital/majorList/deleteMajor',
	    		  type:'get',
	    		  data:{
	    			  "majorId":majorId
	    		  },
	    		  success:function(json){
	    			  //console.log(json);
	    			  if(json.code==1){
	    				  getPage(keywords,active_c);
	    				  layer.close(s);
	    				  layer.msg('操作成功', {time: 600});
	    			  }else{
	    			  }
	    		  }
	    	  })
	    	  
	      }
	  });
});
//科室名称判断
$("#name").blur(function(){
	var major=$("#name").val();
	validmajor(major);
})
function validmajor(name){
	var valid=true;
	var val=/^[\u4E00-\u9FA5\uF900-\uFA2D\w-]+$/;
	if(name!=''){
		if(name.length>15){
			valid = false;
			$(".pthis").html("*科室的名称不能超过15个汉字");
		}else if(!val.test(name)){
			valid = false;
			$(".pthis").html("*请输入数字，英文大小写，汉字");
		}else{
			valid = true;
			$(".pthis").html("");
		}
	}
	
	return valid;
}

//关键字查询
$(document).on("click","#searchItem",function(){
	keywords = $("#keywords").val();
	getPage(keywords,1);
});
