/**
 * 产检管理js
 */
$(function(){
	//tips提示框
	$(".tips").click(function(){
		var info = $(this).attr("title");
		layer.tips(info, this, {
			  tips: [2, '#3595CC']
			 // time: 4000
			});
	});
	/**
	 * 删除产检信息
	 */
	$(document).off("click",".deleteExaminationInfo").on("click",".deleteExaminationInfo",function(){
		var id = $(this).attr("infoId");
		//$(this).parent().parent("tr").attr("id","target");
        layer.alert('要删除这条记录么？', {
            icon: 0,
            title: '提示',
            btn:['确定','取消'],
            btnAlign: 'c',
            yes: function(index){
            	layer.close(index);
//              $("#target").remove();
            	$.post(
            		path+"/pregnancy/deleteExaminationInfo",
            		{
            			"examinationInfoId":id
            		},function(ret){
            			layer.alert(ret.msgbox);
            			if(ret.code == 1){
            				setTimeout(function(){
            					window.location.reload();
            				},1000);
            			}
            		}
            	);
            }
        });
	});
	
	/**
	 * 添加/修改产检信息
	 */
	$(document).off("click",".addOrEditExaminationInfo").on("click",".addOrEditExaminationInfo",function(){
		var infoId = $(this).attr("infoId");
		var examNumbers = $(this).attr("examNumbers");
		var startWeek = $(this).attr("startWeek");
		var endWeek = $(this).attr("endWeek");
		var remindWeek = $(this).attr("remindWeek");
		var remind = $(this).attr("remind");
		var type = $(this).attr("type");
		if(type=="edit"){
			$("#infoId").val(infoId);
			$("#examNumbers").val(examNumbers);
			$("#startWeek").val(startWeek);
			$("#endWeek").val(endWeek);
			$("#remindWeek").val(remindWeek);
			$("#remind").val(remind);
		}
		if(type=="add"){
			$("#remind").val("");
			$("#examNumbers").val("");
			$("#startWeek").val("");
			$("#endWeek").val("");
			$("#remindWeek").val("");
		}
		layer.open({
			title:'编辑产检信息',
	  		type: 1,
	  		area: ['600px'], //宽高
	  		content: $('.normal_form'),
	  		btn: ['保存','取消'],
	  		yes: function(index){
	  			var ids = $("#infoId").val();
	  			if(type=="add"){
	  				ids = 0;
	  			}
	  			if($("#examNumbers").val()==null || $("#examNumbers").val()==""){
	  				$("#examNumbers").next("span.errorMsg").text("*请输入产检次数！");
		  				return false;
	  			}else{
	  				$("#examNumbers").next("span.errorMsg").text("");
	  			}
	  			var numbers = parseInt($("#examNumbers").val());
				var re = /^[1-9]+[0-9]*]*$/;
				if(!re.test($("#examNumbers").val())){
					$("#examNumbers").next("span.errorMsg").text("产检次数应该为正整数！");
					return false;
				}
				if(numbers<1 || numbers>13){
					$("#examNumbers").next("span.errorMsg").text("产检次数应在1-13次！");
					return false;
				}
	  			$.post(
	  					path+"/pregnancy/checkExamNumbers",
	  					{
	  						"infoId":ids,
	  						"examNumbers":$("#examNumbers").val()
	  					},function(ret){
	  						if(ret.code==1){
	  							$("#examNumbers").next("span.errorMsg").text("*该次产检信息已存在！");
	  							return false;
	  						}else{
	  							$("#examNumbers").next("span.errorMsg").text("");
	  							var start = parseInt($("#startWeek").val());
	  							var end = parseInt($("#endWeek").val());
	  							var remind = parseInt($("#remindWeek").val());
	  							if(start<0||start>40){
	  				  				$("#startWeek").next("span.errorMsg").text("*孕周范围应在0-40周！");
	  				  				return false;
	  				  			}else{
	  				  				$("#startWeek").next("span.errorMsg").text("");
	  				  			}
	  				  			if(end<0||end>40){
	  				  				$("#endWeek").next("span.errorMsg").text("*孕周范围应在0-40周！");
	  				  				return false;
	  				  			}else{
	  				  				$("#endWeek").next("span.errorMsg").text("");
	  				  			}
	  				  			if(start>end){
	  				  				$("#endWeek").next("span.errorMsg").text("*开始时间应该小于结束时间！");
	  				  				return false;
	  				  			}else{
	  				  				$("#endWeek").next("span.errorMsg").text("");
	  				  			}
	  				  			if(remind<0||remind>40){
	  				  				$("#remindWeek").next("span.errorMsg").text("*孕周范围应在0-40周！");
	  				  				return false;
	  				  			}else{
	  				  				$("#remindWeek").next("span.errorMsg").text("");
	  				  			}
	  				  			if(remind<start||remind>end){
	  				  				$("#remindWeek").next("span.errorMsg").text("*提醒时间应在开始和结束时间范围内！");
	  				  				return false;
	  				  			}else{
	  				  				$("#remindWeek").next("span.errorMsg").text("");
	  				  			}
	  				  			if($("#remind").val().trim()==null || $("#remind").val().trim()==""){
	  				  				$("#remind").next("span.errorMsg").text("*请设置关爱小提示！");
	  				  				return false;
	  				  			}else{
	  				  				$("#remind").next("span.errorMsg").text("");
	  				  			}
	  				  			layer.close(index);
	  				  			$.post(
	  				  				path+"/pregnancy/addOrEditExaminationInfo",
	  				            		{
	  				            			"infoId":ids,
	  				            			"examNumbers":$("#examNumbers").val(),
	  				            			"startWeek":$("#startWeek").val(),
	  				            			"endWeek":$("#endWeek").val(),
	  				            			"remindWeek":$("#remindWeek").val(),
	  				            			"remind":$("#remind").val()
	  				            		},function(ret){
	  				            			layer.alert(ret.msgbox);
	  				            			if(ret.code == 1){
	  				            				setTimeout(function(){
	  				            					window.location.reload();
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
	
	
});