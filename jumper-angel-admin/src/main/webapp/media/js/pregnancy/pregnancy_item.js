/**
 * 产检项目管理js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	var keywords = "";
	var infoId = 0;
	getPage(keywords,infoId, curr);
});


function getPage(keywords,infoId,currIndex){
	$.ajax({
		url:path+"/pregnancy/findPregAnteExamItemInfoList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords, "infoId":infoId},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	//显示分页
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
		   	 		"infoId":infoId
		   	 	};
		   	 	//请求宝宝发育变化
		   	 	$.ajax({
		   			url: path + "/pregnancy/findPregAnteExamItemInfoList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var moreStr = "";
		   				for(var i=0; i<data.length; i++) {
		   					if(data[i].content.length>18){
		   						moreStr = data[i].content.substring(0, 18)+"...<a href='javascript:void(0);' title='"+data[i].content+"' class='blue tips'>[全文]</a></td>";
		   					}else{
		   						moreStr = data[i].content;
		   					}
		   					html += "<tr height='40px'>";
		   					html += "<td>第"+data[i].examinationNumbers+"次产检</td>";
		   					html += "<td>"+data[i].name+"</td>";
			   				html += "<td>"+moreStr;
			   				html += "<td>"+data[i].addTime+"</td>";
			   				html += "<td><button class='blue_btn_small addOrEditExamItem' itemId='"+data[i].id+"' examId='"+data[i].examinationInfoId+"' examNumbers='"+data[i].examinationNumbers+"' name='"+data[i].name+"' content='"+data[i].content+"' type='edit'> 编辑 </button>" +
			   						"<button itemId='"+data[i].id+"' class='red_btn_small deleteExamItem'> 删除 </button>" +
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
	//tips提示框
	$(document).on("click",".tips",function(){
		var info = $(this).attr("title");
		layer.tips(info, this, {
			  tips: [2, '#3595CC'],
//			  time: 5000
			});
	});
	
	//关键字查询
	$(document).on("click","#searchItem",function(){
		var keywords = $("#keywords").val();
//		var infoId = 0;
		var infoId = $("#changeItem").val();
		getPage(keywords,infoId,1);
	});
	
	//下拉列表切换
	$(document).on("change","#changeItem",function(){
		var infoId = $("#changeItem").val();
//		var keywords = "";
		var keywords = $("#keywords").val();
		getPage(keywords,infoId,1);
	});
	
	/**
	 * 删除产检信息
	 */
	$(document).on("click",".deleteExamItem",function(){
		var $this = $(this);
		var id = $(this).attr("itemId");
        layer.alert('要删除这条记录么？', {
            icon: 0,
            title: '提示',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
            	layer.close(index);
            	$.post(
            		path+"/pregnancy/deleteExaminationItemInfo",
            		{
            			"examinationItemInfoId":id
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
	
	/**
	 * 添加或修改产检项目信息
	 */
	$(document).on("click",".addOrEditExamItem",function(){
		var type = $(this).attr("type");
		var itemId = $(this).attr("itemId");
		var examId = $(this).attr("examId");
		var name = $(this).attr("name");
		var content = $(this).attr("content");
		$.ajax({
   			url: path+"/pregnancy/findExaminationInfos",
   			type: "get",
   			dataType: "json",//从服务器端返回的数据类型
   			async: false,
   			success: function(json) {
   				var data = json.data;
    			var html = "";
    			if(json.code == 1){
    				for(var i=0; i<data.length; i++){
    					html += "<option value='"+data[i].id+"'>第"+data[i].examinationNumbers+"次产检</option>";
    				}
    				$("#examInfoId").append(html);
    			}
   			}
   		});
		if(type=="edit"){
			$("#itemId").val(itemId);
			$("#itemName").val(name);
			$("#itemContent").val(content);
			$("#examInfoId").find("option[value='"+examId+"']").attr("selected",true);
		}
		layer.open({
			title:'设置产检项目信息',
	  		type: 1,
	  		area: ['600px','450px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			var itemId = $("#itemId").val();
	  			var examId = $("#examInfoId").val();
	  			if($("#itemName").val().trim()==null || $("#itemName").val().trim()==""){
	  				$("#itemName").next("span.errorMsg").text("*请输入项目名！");
	  				return false;
	  			}else{
	  				$("#itemName").next("span.errorMsg").text("");
	  			}
	  			if(type=="add"){
					itemId = 0;
				}
	  			//检查项目名称是否重复
	  			$.post(
	  				path+"/pregnancy/checkExamItemInfo",
	  				{
	  					"itemId":itemId,
	  					"examId":examId,
	  					"itemName":$("#itemName").val()
	  				},function(ret){
	  					if(ret.code==1){
  							$("#itemName").siblings("span.errorMsg").text("*产检项目名重复，请检查！");
  							return false;
  						}else{
  							if($("#itemContent").val().trim()==null || $("#itemContent").val().trim()==""){
  				  				$("#itemContent").next("span.errorMsg").text("*请输入项目详情描述！");
  				  				return false;
  				  			}else{
  				  				$("#itemContent").next("span.errorMsg").text("");
  				  			}
  							layer.close(index);
  				  			$.post(
  				            		path+"/pregnancy/addOrEditExamItemInfo",
  				            		{
  				            			"itemId":itemId,
  				            			"examId":$("#examInfoId").val(),
  				            			"itemName":$("#itemName").val(),
  				            			"itemContent":$("#itemContent").val(),
  				            		},function(ret){
  				            			layer.alert(ret.msgbox);
  				            			if(ret.code == 1){
  				            				var keywords = $("#keywords").val();
  				            				var infoId = $("#changeItem").val();
  				            				getPage(keywords,infoId, curr);
  				            				/*setTimeout(function(){
  				            					window.location.reload();
  				            				},1000);*/
  				            			}
  				            		}
  				            	);
  						}
	  				}
	  			)
	  			
	    	}
		});
	});
});