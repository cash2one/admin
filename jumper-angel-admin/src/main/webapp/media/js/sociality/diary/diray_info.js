$(document).ready(function(){
	init_page();
});

function init_page(){
	var keywords = $('.data_id').val();
	var name = $('.data_name').val();
	$.ajax({
		url: path+"/sociality/diary/list?time="+new Date().getTime(),
		type: "POST",
		dataType: "json",
		data: {"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords, "name":name},
		async:false,
		success:function(json){
			totalPage = json.data;
		}
	});
	//显示分页
	layui.use(["laypage"], function() {
		var laypage = layui.laypage;
		layui.laypage({
		    cont: "user_diary_pages",
		    pages: totalPage,
		   	skip: true,
		   	jump: function(obj) {
		   	    var curr = obj.curr;
		   	 	var data = {
			   	 		"pageIndex":curr,
			   	 		"everyPage":everyPage,
			   	 		"first": false,
			   	 		"keywords":keywords,
			   	 		"name":name
			   	 	};
		   	 	$.ajax({
		   	 		url: path+"/sociality/diary/list",
		   	 		type: "POST",
		   	 		dataType: "json",//从服务器端返回的数据类型
	   	 			data: data,
	   	 			async: false,
	   	 			success: function(json) {
	   	 				var data = json.data;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {    
		   					html += "<tr id='"+data[i].id+"'>";
		   					html += "<td style='word-wrap:break-word;word-break:break-all;width: 10%;'>"+data[i].userName+"</td>";
		   					if(data[i].content.length >= 20){
		   					html += "<td style='width: 71%;' class='table-longtext' id='content"+data[i].id+"'>"+data[i].content.substring(0,20)+"...<a href='javascript:' class='blue' value='"+data[i].content+"'>[全文]</a></td>";//大于或者等于20点击出[全文]
		   					}else{
		   						html += "<td style='width: 71%;'>"+data[i].content+"</td>";
		   					}
		   					html += "<td>"+data[i].createTime+"</td>";
		   					html += "<td><button class='red_btn_small' onclick=show("+data[i].id+")> 查看详情</button></td>";
		   					html += "</tr>";
		   				}
		   				$("#user_diary_data").html(html);
			   		 }
			   	 });
	   		}
	  	});
	});
	///显示全部内容
	$(document).on("click", ".blue", function() {
		var info = $(this).attr("value");
		layer.tips(info, this, {
			tips: [2, '#3595CC'],
			time: 4000
		});
	});
	
}

function show(id){
	$.ajax({
		url : path + "/sociality/diary/get_data?id="+id,
		type: "POST",
		contentType:"application/json",
		dataType: "json",	//从服务器端返回的数据类型
		success: function(json){
			var obj = json.data;
			$('.content').val(obj.content);
			$('.add_time').val(obj.createTime);
			$('.user_name').val(obj.userName);
			var imgs = $(obj.imgs);
			var img = "";
			if (imgs.length > 0){
				for(var i=0; i<imgs.length; i++) {
					img += "<img class='margin-r' style='margin-top: 4%; margin-left: -4px;' src='"+imgs[i]+"'/>";
				}
			}else {
				var filePath = path+"/assets/images/imgdefault.png"
				img += "<img class='margin-r' style='margin-top: 4%; margin-left: -4px;' src='"+filePath+"'/>";
			}
			$('.imgs').html(img);
			
			$('.is_del').html('正常');
			if (obj.isDelete == 1){
				$('.is_del').html('删除');
			}
			
		}
	});
	
	layer.open({
		title:'日记内容 - 查看详情',
  		type: 1,
  		area: ['50%'], //宽高
  		content: $('.normal_form')
	});
}
