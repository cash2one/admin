/**
 * 医院总表js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
/*$(document).ready(function(){
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	
});*/

//显示分页
function getPage(keywords,currIndex,start,end,currentIdentity){
	$.ajax({
		url:path+"/promotStatistics/findHospitalDetailStatistics",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords, "startTime":start, "endTime":end, "currentIdentity":currentIdentity},
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
		   	 		"keywords":keywords,
		   	 		"startTime":start,
		   	 		"endTime":end,
		   	 		"currentIdentity":currentIdentity
		   	 	};
		   	 	$.ajax({
		   			url: path + "/promotStatistics/findHospitalDetailStatistics",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				$("#total_count").text(json.data.totalCount);
		   				$("#add_count").text(json.data.totalAddCount);
		   				$("#heartmonitor_ount").text(json.data.heartMonitorCount);
		   				$("#lease_count").text(json.data.leaseCount);
		   				$("#outpatient_count").text(json.data.weightOutPatientCount);
		   				$("#firstbind_count").text(json.data.totalAddCount);
		   				$("#oldfirstbind_count").text(json.data.oldAddCount);
		   				$("#totalbind_count").text(json.data.totalBindCount);
		   				$("#search_time").text(json.data.searchTimePeriod);
		   				//清空输入框
		   				$("#keywords").val("");
		   				$("#start").val("");
		   				$("#end").val("");
		   				end_date.min = "";
		   				start_date.max = laydate.now();
		   				if(json.code==1){
//		   					$(".content2").css("display","block");
		   					var data = json.data.list;
			   				var html = "";
			   				var no = json.data.num;
			   				for(var i=0; i<data.length; i++) {
			   					no = no+1;
			   					html += "<tr height='40px'>";
			   					html += "<td>"+no+"</td>";
			   					html += "<td style='width:300px;'>"+data[i].userName+"</td>";
			   					html += "<td>"+data[i].mobile+"</td>";
			   					html += "<td>"+data[i].age+"</td>";
			   					if(currentIdentity==0){
			   						html += "<td>"+data[i].expectedDateOfConfinement+"</td>";
			   					}else if(currentIdentity==1){
			   						html += "<td>"+data[i].babyBirthDay+"</td>";
			   					}
			   					html += "<td>"+data[i].regTime+"</td>";
			   					html += "<td>"+data[i].bindingTime+"</td>";
			   					html += "<td><button class='red_btn_small findUserInfoDetail' user_id='"+data[i].userId+"'>查看详情</button></td>";
				   				html += "</tr>";
			   				}
			   				$("#tbody").html(html);
		   				}else{
		   					if(json.code==10){
		   						$("#tbody").html("");
		   					}
		   					layer.msg(json.msgbox, {
		  		      			icon: 1,
		  		      			shade: [0.5,'#000'],
		  		      			btn: ['确定']
		  		    		});
		   				}
		   				
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
		var start = $("#start").val();
		var end = $("#end").val();
		var current_identity = $("#current_identity").val();
		if(keywords == null || keywords == ""){
			layer.msg("请输入医院名称", {
	      			icon: 1,
	      			shade: [0.5,'#000'],
	      			btn: ['确定']
	    		});
		}else{
			$("#curr_hospital").text(keywords);
			$("#hospName").val(keywords);
			$("#startTime").val(start);
			$("#endTime").val(end);
			getPage(keywords,1,start,end,current_identity);
		}
	});

	//点击切换孕期用户和产后用户
	$(document).on("click",".userListBtn",function(){
		$(this).addClass("active");
		$(this).parent().siblings("li").find(".userListBtn").removeClass("active");
		var currentIdentity = $(this).attr("currentIdentity");
		var keywords = $("#hospName").val();
		var start = $("#startTime").val();
		var end = $("#endTime").val();
		$("#current_identity").val(currentIdentity);
		if(currentIdentity==0){
			$(".expectOrBaby").text("预产期");
		}else if(currentIdentity==1){
			$(".expectOrBaby").text("宝宝生日");
		}
		if(keywords != null && keywords != ""){
			$("#curr_hospital").text(keywords);
			getPage(keywords,1,start,end,currentIdentity);
		}
	});
	
	//查看详情
	$(document).on("click",".findUserInfoDetail",function(){
		var userId = $(this).attr("user_id");
		$.post(
			path+"/promotStatistics/findUserInfoDetail",
			{
				"userId":userId
			},function(json){
				var data = json.data;
				var html_left = "";
				html_left += "<tr><td class='tdChild1'>昵称：</td><td>"+data.nickName+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>真实姓名：</td><td>"+data.realName+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>年龄：</td><td>"+data.age+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>手机号码：</td><td>"+data.mobile+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>所在地区：</td><td>"+data.proName+data.cityName+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>怀孕状态：</td><td>"+data.pregnantStatus+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>所处孕周：</td><td>"+data.pregnantWeek+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>预产期： </td><td>"+data.expectedDateOfConfinement+"</td></tr>";
				html_left += "<tr><td class='tdChild1'>宝宝生日：</td><td>"+data.babyBirthDay+"</td></tr>";
				$("#table_left").html(html_left);
				
				var html_right = "";
				html_right += "<tr><td class='tdChild1'>注册时间：</td><td>"+data.regTime+"</td></tr>";
				html_right += "<tr><td class='tdChild1'>初次关联医院时间：</td><td>"+data.bindingTime+"</td></tr>";
				html_right += "<tr><td class='tdChild1'>初次关联医院名称：</td><td>"+data.firstBindHospitalName+"</td></tr>";
				html_right += "<tr><td class='tdChild1'>初次关联应用版本：</td><td>"+data.versionName+"</td></tr>";
				html_right += "<tr><td class='tdChild1'>初次关联操作设备：</td><td>"+data.firstBindMachine+"</td></tr>";
				html_right += "<tr><td class='tdChild1'></td><td></td></tr>";
				html_right += "<tr><td class='tdChild1'>当前关联医院：</td><td>"+data.currentBindHospitalName+"</td></tr>";
				html_right += "<tr><td class='tdChild1'>最近一次登录时间：</td><td>"+data.lastLoginTime+"</td></tr>";
				html_right += "<tr><td class='tdChild1'></td><td></td></tr>";
				$("#table_right").html(html_right);
			}
		);
		layer.open({
            title:'用户详情',
            type: 1,
            area: ['700px','560px'], //宽高
            content: $('.userDate'),
            btn: ['确定']
        });
	});
	
	//输入医院名称自动补全
	$("#keywords").autocomplete(path+"/promotStatistics/searchHospital",{
		dataType:"json",
		minChars:1,
		max:15,
		autoFill:false,
		matchContains:true,
		width:180,
		scrollHeight:330,
		parse:function(data){
				return $.map(data,function(row){
					return {
						data:row,
						value:row.name,
						result:row.name
					};
				});
		},
		formatItem:function(row,i,max){
			return row.name;
		}
	}).result(function(e,row){
		$("#keywords").val(row.name);
	});
	

});
