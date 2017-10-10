/**
 * 新闻频道js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	var keywords = "";
	getPage(keywords, 1);
});

//显示分页
function getPage(keywords, currIndex){
	$.ajax({
		url:path+"/encyclopedia/findQuestionClassList",
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
		   			url: path + "/encyclopedia/findQuestionClassList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var status = "";
		   				for(var i=0; i<data.length; i++) {
		   					status = data[i].isVisible==0?"公开":"隐藏";
		   					html += "<tr height='40px'>";
		   					html += "<td>"+(i+1)+"</td>";
		   					html += "<td>"+data[i].name+"</td>";
		   					html += "<td>"+status+"</td>";
		   					html += "<td>"+data[i].classAddTime+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditQuestionClass' type='edit' classId='"+data[i].id+"' className='"+data[i].name+"' isVisible='"+data[i].isVisible+"'>编辑</button>"+
			   							"<button class='red_btn_small deleteQuestionClass' classId='"+data[i].id+"'>删除</button>"+
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
		getPage(keywords, 1);
	});
	
	//添加或编辑模块
	$(document).on("click",".addOrEditQuestionClass",function(){
		var type = $(this).attr("type");
		var classId = $(this).attr("classId");
		var className = $(this).attr("className");
		var isVisible = $(this).attr("isVisible");
		if(type=="add"){
			classId = 0;
			$("#className").val("");
		}
		if(type=="edit"){
			$("#className").val(className);
			$("#isVisible").find("option[value='"+isVisible+"']").attr("selected",true);
		}
		layer.open({
			title:'内容模块',
	  		type: 1,
	  		area: ['500px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			layer.close(index);
	  			className = $("#className").val().trim();
	  			isVisible = $("#isVisible").val();
	  			if(className=="" || className==null){
	  				layer.alert("请输入模块名！");
	  				return;
	  			}
	  			$.post(
  		  				path+"/encyclopedia/checkQuestionClassName",
  		  				{
  		  					"classId":classId,
  		  					"className":className
  		  				},function(ret){
  		  					if(ret.code==1){
  		  						layer.alert(ret.msgbox);
  		  						return;
  		  					}else{
  		  						$.post(
  		  							path+"/encyclopedia/addOrEditQuestionClass",
  		  							{
  		  								"classId":classId,
  		  			  					"className":className,
  		  			  					"isVisible":isVisible
  		  							},function(data){
  		  								layer.msg(data.msgbox, {
  		  			  		      			icon: 1,
  		  			  		      			shade: [0.5,'#000'],
  		  			  		      			btn: ['确定']
  		  			  		    		});
  		  		            			if(data.code == 1){
	  		  		            			setTimeout(function(){
//	  		  		            				window.location.reload();
	  		  		            				var keywords = $("#keywords").val();
	  		  		            				getPage(keywords, 1);
	  		  		            			},1000);
  		  		            			}
  		  							}
  		  						);
  		  					
  		  					}
  		  				}
  		  			);
	    	}
		});
	});
	
	//删除模块
	$(document).on("click",".deleteQuestionClass",function(){
		var classId = $(this).attr("classId");
		var $this = $(this);
		 layer.alert('要删除这条记录么？', {
	            icon: 0,
	            title: '提示',
	            btn:['删除','取消'],
	            btnAlign: 'c',
	            yes: function(index, layero){
	                layer.close(index);
	                $.post(
	                	path+"/encyclopedia/deleteQuestionClass",
	                	{
	                		"classId":classId
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
});
