/**
 * 医院总表js
 */
//请求总页数
var totalPage = 0;
var curr = 1;
$(document).ready(function(){
	//初始化
	$(".orangeUploder").initOrangeUploader();
	//设置upload.js中div样式
	$("#progress").attr("class", "music_progress progress-striped active");
	$(".imgPicker").attr("style", "width: 100px; position: relative;");
	var keywords = "";
	var province = 0;
	var city = 0;
	var pointTime = "";
	getPage(keywords,1,province,city,pointTime);
});

//显示分页
function getPage(keywords,currIndex,province,city,pointTime){
	$.ajax({
		url:path+"/promotStatistics/findAllHospitalStatisticsList",
		type:"get",
		dataType:"json",
		data:{"pageIndex":1, "everyPage":everyPage, "first":true, "keywords":keywords, "province":province, "city":city, "pointTime":pointTime},
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
		   	 		"province":province,
		   	 		"city":city,
		   	 		"pointTime":pointTime
		   	 	};
		   	 	$.ajax({
		   			url: path + "/promotStatistics/findAllHospitalStatisticsList",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data.list;
		   				var html = "";
		   				var no = json.data.num;
		   				for(var i=0; i<data.length; i++) {
		   					no = no+1;
		   					html += "<tr height='40px'>";
		   					html += "<td>"+no+"</td>";
		   					html += "<td style='width:300px;'>"+data[i].hospitalName+"</td>";
		   					html += "<td>"+data[i].totalCount+"</td>";
		   					html += "<td>"+data[i].dailyAddCount+"</td>";
		   					html += "<td>"+data[i].hospConsultantCount+"</td>";
		   					html += "<td>"+data[i].leaseCount+"</td>";
		   					html += "<td>"+data[i].heartMonitorCount+"</td>";
		   					html += "<td>"+data[i].weightOutPatientCount+"</td>";
		   					html += "<td>"+data[i].sevenDaysAddCount+"</td>";
		   					html += "<td>"+data[i].currMonthAddCount+"</td>";
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
		var province = $("#province").val();
		var city = $("#city").val();
		var pointTime = $("#pointTime").val();
		getPage(keywords,1,province,city,pointTime);
	});

	//切换省份，省市级联
	$(document).on("change","#province",function(){
		var province_id = $("#province").val();
		$.ajax({
			type: 'get',
			dataType: 'json',
			data: 'id='+province_id,
			url: path + '/promotStatistics/getCityByProvince',
			success: function(ret){
				if(ret.code==1){
					if(ret.data != null){
						var content = '<option value="0">--- 请选择城市 ---</option>';
						var city_list = ret.data;
						for(var i = 0;i < city_list.length; i++){
							content+='<option value="'+city_list[i].id+'" >'+city_list[i].city_name+'</option>';
						}
						$("#city").html(content);
					}
				}
			}
		});
		
	});
	
	//导出数据
	$(document).on("click","#exportStatisticsData",function(){
		var keywords = $("#keywords").val();
		var province = $("#province").val();
		var city = $("#city").val();
		var pointTime = $("#pointTime").val();
		window.location.href = path+"/promotStatistics/exportHospitalStatisticsData?keywords="+keywords+"&province="+province+"&city="+city+"&pointTime="+pointTime;
	})
	


});
