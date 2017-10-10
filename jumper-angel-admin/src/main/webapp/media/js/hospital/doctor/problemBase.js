/**
 * 问题库js
 */
//请求总页数
var totalPage = 0;
$(document).ready(function(){
	var keywords = "";
	getPage(keywords);
});

//显示分页
function getPage(keywords){
	$.ajax({
		url:path+"/hospital/doctor/problemBases",
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
		    cont: "page",
		    pages: totalPage,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    var curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false,
		   	 		"keywords":keywords
		   	 	};
		   	 	$.ajax({
		   			url: path + "/hospital/doctor/problemBases",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				var status = "";
		   				for(var i=0; i<data.length; i++) {
		   					if(data[i].content.length>15){
		   						moreStr = data[i].content.substring(0, 15)+"...<a href='javascript:void(0);' title='"+data[i].content+"' class='blue tips'>[全文]</a></td>";
		   					}else{
		   						moreStr = data[i].content;
		   					}
		   					status = data[i].isVisible==0?"公开":"隐藏";
		   					html += "<tr height='40px' id='id"+data[i].id+"'>";
		   					html += "<td>"+data[i].nick_name+"</td>";
		   					html += "<td>"+data[i].real_name+"</td>";
		   					html += "<td style='word-break: break-all; width: 350px;' class='table-longtext'>"+moreStr+"</td>";
		   					html += "<td>"+data[i].major_name+"</td>";
		   					html += "<td>"+data[i].add_time+"</td>";
			   				html += "<td><button type='button' class='red_btn_small get_problem' "+
			   					"' onClick='generalize1("+data[i].id+","+data[i].user_id+")'>领取问题</button></td>";
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
	$(document).on("click",".tips",function(){
		var info = $(this).attr("title");
		layer.tips(info, this, {
			  tips: [2, '#3595CC'],
			});
	});
	//关键字查询
	$(document).on("click","#searchItem",function(){
		var keywords = $("#keywords").val();
		getPage(keywords);
	});
	
});
//领取问题
function generalize1(id,userId){
	layer.alert($("#generalize").val(), {
		title:'提示',
		content: '确定领取该问题',
		btn:['确定','取消'],
		tbnAlign: 'c',
		yes: function(index, layero){
			var la=document.getElementsByClassName("layui-layer-btn0")
			la[0].style.pointerEvents="none"; 
			getProblem(id,userId,-1);
			layer.close(index);
		}
	});
}
function getProblem(id,userId,serviceType){
	$.ajax({
		type:"get",//请求方式
		url:"getProblem",//发送请求地址
		data:{//发送给数据库的数据
			chatUrl:"/accounts/add",
			updateUrl:"/im/update_doctor_question",
			consultantId: id,
			serviceType: serviceType,
			userId:userId
		},
		//请求成功后的回调函数有两个参数
		success:function(data,textStatus){
			generalize()
		}
	});
}
function generalize(){
	layer.alert($("#generalize").val(), {
		title:'提示',
		content: '已经领取该问题',
		btn:['确定'],
		btnAlign: 'c',
		yes: function(){
			layer.msg('操作成功', {time: 600});
			window.location.reload();
		}
	});
}

