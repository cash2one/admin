//总页数
var totalPage = 0;
var curr = 1;
//状态  1:新增、2:修改
var oprations = 1;
$(document).ready(function() {
	//加载数据
	getPage(1);
	//tips显示全部内容
	$(document).on("click", ".blue", function() {
		var info = $(this).attr("value");
		layer.tips(info, this, {
			tips: [2, '#3595CC'],
			time: 4000
		});
	});
	//radio选择
	$(":radio").change(function() {
		var value = $(this).val();
		//怀孕中
		if(value == 0) {
			if(oprations == 1) {
				//启用
				$("#week").attr("disabled", false);
			} else if(oprations == 2) {
				//禁用
				$("#week").attr("disabled", true);
			}
		} else if(value == 2) { //备孕期
			//禁用
			$("#week").attr("disabled", true);
			$("#week").val("");
		}
	});
});
function getPage(currIndex) {
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
		    curr:currIndex,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   		curr = obj.curr;
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
		   				var moreStr = "";
		   				for(var i=0; i<data.length; i++) {
		   					if(data[i].weekDescription.length>36){
		   						moreStr = data[i].weekDescription.substring(0, 36)+"...<a href='javascript:' class='blue' value='"+data[i].weekDescription+"'>[全文]</a>";
		   					}else{
		   						moreStr = data[i].weekDescription;
		   					}
		   					html += "<tr height='40px' id='id"+data[i].week+"'>";
		   					if(data[i].week == -1) {
		   						html += "<td>备孕期</td>";
		   					} else {
		   						html += "<td id='week"+data[i].week+"'>孕"+data[i].week+"周</td>";
		   					}
		   					var fetalHeight = data[i].fetalHeight == "" ? 0:data[i].fetalHeight;
		   					var bothArm = data[i].bothArm == "" ? 0:data[i].bothArm;
		   					var fetalWeight = data[i].fetalWeight == "" ? 0:data[i].fetalWeight;
		   					var bothNeck = data[i].bothNeck == "" ? 0:data[i].bothNeck;
		   					html += "<td id='fetalHeight"+data[i].week+"'>"+fetalHeight+"</td>";
			   				html += "<td id='bothArm"+data[i].week+"'>"+bothArm+"</td>";
			   				html += "<td id='fetalWeight"+data[i].week+"'>"+fetalWeight+"</td>";
			   				html += "<td id='bothNeck"+data[i].week+"'>"+bothNeck+"</td>";
			   				html += "<td class='table-longtext' id='weekDescription"+data[i].week+"'>"+moreStr+"</td>";
			   				html += "<td><button class='blue_btn_small' onClick='ShowDiv(2, "+data[i].week+")'> 编辑 </button><button class='red_btn_small' onclick='del("+data[i].week+", this)'>删除</button></td>";
			   				html += "</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}
//显示弹出层
function ShowDiv(opration, week){
	//隐藏
	$("#error").html("");
	$("#error").hide();
	//新增
	if(opration == 1) {
		oprations = 1;
		//选中
		$(":radio[name='Pregnancystate'][value='2']").prop("checked", "checked");
		$("#pregnancy0").attr("disabled", false);
		$("#pregnancy2").attr("disabled", false);
		//禁用
		$("#week").attr("disabled", true);
		//孕周
		$("#week").val("");
		//身长
		$("#fetalHeight").val("");
		//顶臀长
		$("#bothArm").val("");
		//体重
		$("#fetalWeight").val("");
		//双顶径
		$("#bothNeck").val("");
		//宝宝发育状况
		$("#weekDescription").val("");
	} else if(opration == 2) { //查询
		oprations = 2;
		//查询孕期详情
		$.ajax({
			url: path + "/baby/findPrepnancyDetail",
			type: "get",
			dataType: "json",//从服务器端返回的数据类型
			data: {"week":week},
			success: function(json) {
				var data = json.data;
				/****** 赋值 ******/
				//选中
				$(":radio[name='Pregnancystate'][value='" + data.pregnancyStatus + "']").prop("checked", "checked");
				if(data.pregnancyStatus == 0) { //怀孕期
					$("#pregnancy1").attr("disabled", "disabled");
					$("#pregnancy2").attr("disabled", "disabled");
				} else if(data.pregnancyStatus == 1) {
					$("#pregnancy0").attr("disabled", "disabled");
					$("#pregnancy2").attr("disabled", "disabled");
				} else if(data.pregnancyStatus == 2) {
					$("#pregnancy0").attr("disabled", "disabled");
					$("#pregnancy1").attr("disabled", "disabled");
				}
				$("#week").attr("disabled", "disabled");
				if(data.week != -1) {
					//孕周
					$("#week").val(data.week);
				} else {
					$("#week").val("");
				}
				//身长
				$("#fetalHeight").val(data.fetalHeight);
				//顶臀长
				$("#bothArm").val(data.bothArm);
				//体重
				$("#fetalWeight").val(data.fetalWeight);
				//双顶径
				$("#bothNeck").val(data.bothNeck);
				//宝宝发育状况
				$("#weekDescription").val(data.weekDescription);
			}
		});
	}
	layer.open({
		title:'宝宝发育变化',
  		type: 1,
  		area: ['840px'], //宽高
  		content: $('.normal_form'),
  		btn: ['保存'],
  		yes: function(index){
  			//获取被选中Radio的Value值
  			var value = $('input[name="Pregnancystate"]:checked').val();
  			//孕周
  			var week = $("#week").val();
  			if(value != 2) { //备孕期
  				if(week == "") {
  	  				$("#error").html("孕周范围不能为空！");
  	  				$("#error").show();
  	  				return;
  	  			} else if(week<0 || week>40) {
  	  				$("#error").html("孕周范围只允许0-40！");
  	  				$("#error").show();
  	  				return;
  	  			}
  			} else {
  				week = -1;
  			}
  			//身长
  			var fetalHeight = $("#fetalHeight").val();
  			//顶臀长
  			var bothArm = $("#bothArm").val();
  			//体重
  			var fetalWeight = $("#fetalWeight").val();
  			//双顶径
  			var bothNeck = $("#bothNeck").val();
  			//宝宝发育状况
  			var weekDescription = $("#weekDescription").val();
  			if(weekDescription == "") {
  				$("#error").html("宝宝发育状况概述不能为空！");
  				$("#error").show();
  				return;
  			} 
  			if(weekDescription.length<60) {
  				$("#error").html("宝宝发育状况字数限制60-200之间");
  				$("#error").show();
  				return;
  			}
  			//隐藏
  			$("#error").hide();
  			//参数
  			var param = {
  				"week":week,
  				"fetalHeight":fetalHeight,
  				"bothArm":bothArm,
  				"fetalWeight":fetalWeight,
  				"bothNeck":bothNeck,
  				"weekDescription":weekDescription,
  				"updateImage":0,
  				"pregnancyStatus":value,
  				"identifying":opration
  			};
  			//转json字符串
  			param = JSON.stringify(param);
  			//保存
  			$.ajax({
  				url: path + "/baby/savePrepnancy",
  				type: "POST",
  				contentType: "application/json;charset=utf-8",
  				dataType: "json",//从服务器端返回的数据类型
  				data: param,
  				//async: false,//同步
  				success: function(json) {
  					if(json.code == 1) {
  						layer.close(index);
  						getPage(curr);
  						layer.msg(json.msgbox, {
  			      			icon: 1,
  			      			shade: [0.5,'#000'],
  			      			btn: ['确定']
  			    		});
  					} else {
  						$("#error").html(json.msgbox);
  						$("#error").show();
  					}
  				}
  			});
  			
    	}
	});
}
//删除
function del(week, temp) {
	layer.confirm("要删除这条记录么？", {
		but:["确定", "取消"]
	}, function(index) {
		//删除孕期详情
		$.ajax({
			url: path + "/baby/deletePrepnancy",
			type: "get",
			dataType: "json",//从服务器端返回的数据类型
			data: {"week":week},
			success: function(json) {
				if(json.code == 1) {
					getPage(curr);
					layer.msg(json.msgbox, {
		      			icon: 1,
		      			shade: [0.5,'#000'],
		      			btn: ['确定']
		    		});
				} else {
					layer.msg(json.msgbox, {
		      			icon: 2,
		      			shade: [0.5,'#000'],
		      			btn: ['确定']
		    		});
				}
			}
		});
	}, function(index) {
		
	});
}