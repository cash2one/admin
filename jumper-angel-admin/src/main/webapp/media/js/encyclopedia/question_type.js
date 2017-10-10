/**
 * 新闻频道js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	var keywords = "";
	var classId = 0;
	getPage(keywords,classId, 1);
});

//显示分页
function getPage(keywords,classId, currIndex){
	$.ajax({
		url:path+"/encyclopedia/findQuestionTypeList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords, "classId":classId},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	layui.use(["laypage"], function() {
		layui.laypage({
		    cont: "pages",
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
		   	 		"first": false,
		   	 		"keywords":keywords,
		   	 		"classId":classId
		   	 	};
		   	 	$.ajax({
		   			url: path + "/encyclopedia/findQuestionTypeList",
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
		   					html += "<td>"+data[i].className+"</td>";
		   					html += "<td>"+status+"</td>";
		   					html += "<td>"+data[i].typeAddTime+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditQuestionType' type='edit' type_id='"+data[i].id+"' type_name='"+data[i].name+"' parent_id='"+data[i].parentId+"' is_visible='"+data[i].isVisible+"'>编辑</button>"+
			   							"<button class='red_btn_small deleteQuestionType' type_id='"+data[i].id+"'>删除</button>"+
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
		getPage(keywords,classId,1);
	});

	//切换所属模块查询
	$(document).on("change","#questionClass",function(){
		var classId = $(this).val();
		var keywords = $("#keywords").val();
		getPage(keywords,classId,1);
	});

	//删除标签
	$(document).on("click",".deleteQuestionType",function(){
		var typeId = $(this).attr("type_id");
		var $this = $(this);
		 layer.alert('要删除这条记录么？', {
	            icon: 0,
	            title: '提示',
	            btn:['删除','取消'],
	            btnAlign: 'c',
	            yes: function(index, layero){
	                layer.close(index);
	                $.post(
	                	path+"/encyclopedia/deleteQuestionType",
	                	{
	                		"typeId":typeId
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


	//添加或修改标签
	$(document).on("click",".addOrEditQuestionType",function(){
		var type = $(this).attr("type");
		var typeId = $(this).attr("type_id");
		var typeName = $(this).attr("type_name");
		var parentId = $(this).attr("parent_id");
		var isVisible = $(this).attr("is_visible");
		if(type=="add"){
			typeId = 0;
			$("#typeName").val("");
		}
		$.ajax({ url: path+"/encyclopedia/findAllQuestionClassList",async:false,
			success: function(json){
				var data = json.data;
				var classhtml = "";
				for(var i=0;i<data.length;i++){
					classhtml += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("#questionClass1").html(classhtml);
			}
		});
		
		
		if(type=="edit"){
			$("#questionClass1").find("option[value='"+parentId+"']").attr("selected",true);
			$("#typeName").val(typeName);
			$("#isVisible").find("option[value='"+isVisible+"']").attr("selected",true);
		}
		layer.open({
			title:'内容标签',
	  		type: 1,
	  		area: ['500px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			layer.close(index);
//	    		layer.msg('提交成功！', {
//	      			icon: 1,
//	      			shade: [0.5,'#000'],
//	      			btn: ['确定']
//	    		});
	  			typeName = $("#typeName").val().trim();
	  			classId = $("#questionClass1").val();
	  			isVisible = $("#isVisible").val();
	  			if(typeName == "" || typeName == null){
	  				layer.alert("请输入标签名称！");
	  				return;
	  			}
	  			$.post(
	  				path+"/encyclopedia/checkQuestionType",
	  				{
	  					"typeId":typeId,
	  					"typeName":typeName,
	  					"classId":classId
	  				},function(ret){
	  					if(ret.code==1){
	  						layer.alert(ret.msgbox);
	  						return;
	  					}else{
	  						$.post(
	  							path+"/encyclopedia/addOrEditQuestionType",
	  							{
	  								"typeId":typeId,
	  			  					"typeName":typeName,
	  			  					"isVisible":isVisible,
	  			  					"classId":classId
	  							},function(data){
	  								layer.msg(data.msgbox, {
		  			  		      			icon: 1,
		  			  		      			shade: [0.5,'#000'],
		  			  		      			btn: ['确定']
		  			  		    		});
	  		            			if(data.code == 1){
	  		            			setTimeout(function(){
//	  		            				window.location.reload();
	  		            				var classId = $("#questionClass").val();
	  		            				var keywords = $("#keywords").val();
	  		            				getPage(keywords,classId,curr);
	  		            			},1000);
	  		            			}
	  							}
	  						)
	  					}
	  				}
	  			);
	    	}
		});
		
	});


});
