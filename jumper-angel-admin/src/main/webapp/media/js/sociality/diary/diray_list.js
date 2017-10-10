$(document).ready(function(){
	 init();
	 
	 $('.sel_diray').click(function (){
		 var $this = $(this),
		 	 $data = $this.parents('tr');
		 query($data.attr("id"));
	 });
	 
	 $('.sel_user_diray').click(function (){
		 var $this = $(this),
		 	 $data = $this.parents('tr'),
		 	 $id = $data.attr("id");
		 window.location.href = path + "/sociality/diary/init_page?id="+$id+"&name=";
	 });
	 
	 $('.search_data').click(function (){
		 var $this = $(this),
		 	 $data = $this.next().val();
		 console.log($data.length == 0);
		 if ($data.length == 0){
			 layer.alert('请输入用户名',{icon:2});
			 return;
		 }
		 window.location.href = path + "/sociality/diary/init_page?id=&name="+$data;
	 });

});

function init(){
	$.ajax({
 		url : path + "/sociality/diary/get",
		type: "post",
		dataType: "json",	//从服务器端返回的数据类型
		async: false,
		success: function(json) {
			var diary = json.data.diary;		//查询当前日记总数
			var latelyDiaryCount = json.data.latelyDiaryCount;		//查询近30新增日记总数
			var userCounts = json.data.userCounts;		//查询日记用户总数
			var maxUsers = json.data.maxUsers;		//查询日记总数前五的用户信息
			var maxDirays = json.data.maxDirays;		//查询最新发布的5条日记
			
			$('.diary_count').html(diary);
			$('.lately_count').html(latelyDiaryCount);
			$('.user_count').html(userCounts);
			
			var user_diary_list = "";
			for(var i=0; i<maxUsers.length; i++) {
				user_diary_list += "<tr id='"+maxUsers[i].userId+"'>";
				user_diary_list += "<td>"+maxUsers[i].userName+"</td>";
				user_diary_list += "<td>"+maxUsers[i].counts+"</td>";
				user_diary_list += "<td>"+maxUsers[i].createTime+"</td>";
				user_diary_list += "<td><button class='red_btn_small sel_user_diray'> 查看日记 </button></td>";
				user_diary_list += "</tr>";
			}
			$('.user_diary_list').html(user_diary_list);
			
			var diary_list = "";
			for(var i=0; i<maxDirays.length; i++) {
				diary_list += "<tr id='"+maxDirays[i].id+"'>";
				diary_list += "<td style='word-wrap:break-word;word-break:break-all;width: 10%;' >"+maxDirays[i].userName+"</td>";
				if(maxDirays[i].content.length >= 20){
					diary_list += "<td style='width: 71%;' class='table-longtext' id='content"+maxDirays[i].id+"'>"+maxDirays[i].content.substring(0,20)+"...<a href='javascript:' class='blue' value='"+maxDirays[i].content+"'>[全文]</a></td>";	
				}else{
					diary_list += "<td style='width: 71%;'>"+maxDirays[i].content+"</td>";
				}
				//diary_list += "<td>"+maxDirays[i].addTime+"</td>";
				diary_list += "<td>"+new Date(parseInt(maxDirays[i].addTime)).format("yyyy-MM-dd HH:mm:s")+"</td>";
				diary_list += "<td><button class='red_btn_small sel_diray'>查看详情 </button></td>";
				diary_list += "</tr>";
			}
			$('.diary_list').html(diary_list);
		}
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

function query(id){
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


