<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>医院管理 > 医院维护</title>
    <jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
	<div class="content">
		<p class="toptitle">医院维护 > 主页设置</p>
		<div class="content-search-top">
			<div class="searchGroup">
				<input class="shortinput float-l" id="keywords" value=""  placeholder="请输入需要查找的医院名称"/> 
				<label class="float-r" > </label>
				<button class="search-icon float-l" id="searchItem"></button>
			</div>
		</div>
		<div class="content-search-top content-searchBox" style="background: #f2f2f2;line-height:65px;padding:0;">
			<div class="current">当前医院：<span class="address"></span></div>
			<div class="number"><span>共 <i class="all"></i> 个功能；已隐藏 <i class="hide"></i> 个功能。</span></div>
			<div class="rank"><button class="red_btn_small" style="line-height:24px;">功能排序</button></div>
			<div class="clear"></div>
		</div>
		
		<div class="bottomBox">
				<div class="tab-but">
					<ul class="tab-ul">
						<a href="javascript:void(0)" class="basicFun">
							<li class="tab_active">基础功能</li>
						</a>
						<a href="javascript:void(0)" class="platformFun">
							<li>平台功能</li>
						</a>
						<a href="javascript:void(0)" class="hospitalFun">
							<li>院内功能</li>
						</a>
					</ul>
				</div>

				<!--左-->
				<div class="base1">
					<div class="basea"></div>
					<div class="pageszhu">
						<div  id="pages1" class="pages"></div>
					</div>	
				</div>
				<!--中-->
				<div class="base2">
					<div class="baseb"></div>
					<div class="pageszhu">
						<div  id="pages2" class="pages"></div>
					</div>
				</div>	
				<!--右-->
				<div class="base3">
					<div class="basec"></div>
					<div class="pageszhu">
						<div  id="pages3" class="pages"></div>
					</div>
				</div>
				
				
			</div>

		</div>
		
		<!------------------------------功能排序弹出层----------------------->
		<div id="mask3">
			<div id="funPopups">
				<button class="close">关闭</button>
				<div class="function">医院共有<span class="total"></span>个功能，已隐藏<span class="ensconce"></span>个功能，有<span class="Sortable"></span>个功能可排序</div>
				<div class="tabDetail">
					<table border="1" id="tab">
						<tr>
							<th>序号</th>
							<th>功能名</th>
							<th>操作</th>
						</tr>
					</table>
				</div>
				<div class="double_btn"><button class="affirm">确认</button><button class="cancel">取消</button></div>
			</div>
		</div>
		
		<!------------------------------添加自定义弹出层----------------------->
		<div id="mask1" style="display:none;">
			<div class="popBox">
				<div style="margin: 20px 20px 0;">自定义功能属于院内功能，目前仅仅支持跳转第三方链接。</div>
				<div id="popups" class="addPopups">
					<div class="funName div_1">功能名称：<input class="ipt1"  type="text" value="" /><p></p></div>
					<div class="funName div_2">url链接：<input class="ipt2" type="text" value="" /><p></p></div>
				</div>
			</div>
		</div>
		
		
		<!------------------------------添加URL弹出层------------------------->
		<div id="link">
			url链接：<input type="text" id='urlva' style="font-size:14px;width:250px;" value="" />
			<p class="titles" style="color:red;font-size:14px;padding:5PX 40px 0 0;display: none;">*请输入正确的url链接</p>
		</div> 
	</div>	
</body>
<script src="${pageContext.request.contextPath}/media/js/hospital/hospital/basicFunction.js"></script>
<script type="text/javascript">
	$(function() {
	    //搜索框样式
		$("#keywords").bind({
			focus: function() {
				$(this).addClass("activate");
			},
			blur: function() {
				$(this).removeClass("activate");
			}
		});
		
		//切换变色
		$('.tab-but li').on("click", function() {
			$(this).parent().siblings().find("li").removeClass("tab_active");
			$(this).addClass("tab_active");
		});

		//点击左右切换内容
		$('.basicFun').on("click", function() {
			$(".base1").css({display: "block"}).siblings(".base2,.base3").css({display: "none"});
		});
		$('.platformFun').on("click", function() {
			$(".base2").css({display: "block"}).siblings(".base1,.base3").css({display: "none"});
		});
		$('.hospitalFun').on("click", function() {
			$(".base3").css({display: "block"}).siblings(".base2,.base1").css({display: "none"});
		});
		//input获取焦点、失去焦点样式改变
		$(".funName input[type=text]").bind({
			focus: function() {
				$(this).addClass("activate");
			},
			blur: function() {
				$(this).removeClass("activate");
			}
		});
		
		//-------------------------------获取医院列表start-----------------------------
		//当在搜索框中输入文本时请求数据
		var searchText = "";//定义input输入的值
		$("#keywords").bind('input propertychange',function(){
			//searchText = ;
		    //调用函数
		    setTimeout(getHospitalList($("#keywords").val()),500);
		});
		//ajax获取医院列表
		var arr1= [];//定义空数组,装载医院
		var totalPage = "";//返回的医院集合
		var hospitalList = "";//医院列表
		var hospitalId = "";//医院id
		var hospId='';//医院id
		function getHospitalList(keywords){
			$.ajax({
				url:path+"/hospital/homepage/getHospitalList",
				type:"post",
				dataType:"json",
				data:{"keywords":keywords},//将关键字传后台
				async:false,
				success:function(json){
				   var shospitalId=0;
				   if(json.code==1){
				   		totalPage = json.data;
				   		$.each(totalPage,function(){
							hospitalList = this.name;
							shospitalId = this.id;
							if($.inArray(hospitalList, arr1) ==-1) {
								arr1.push(hospitalList,shospitalId);
								$("#keywords").autocomplete({
									source:arr1,
									delay: 50
								});
							}
						});
				   	}
				}
			});
		}
		//-----------------------------获取医院列表end-----------------------------
		
		var searchTxt = "";//定义input的值
		//------------------------------调用医院信息并展示start-------------------
		//当点击搜索框时
		var searchTxtIn = "";
		$('#searchItem').on('click', function() {
			searchTxt = $("#keywords").val();
			onary(searchTxt);
		});
		
		//当点击模糊输入下拉框某个li标签时
		$(document).on("click",".ui-menu-item", function() {
		   searchTxt = $(this).text();
		   onary(searchTxt)
		});
		
		//当点击键盘enter事件时
		$(".searchGroup").on("keydown","#keywords",function(e){
		    searchTxt = $("#keywords").val();
		    if(e.keyCode == 13){
		    	 onary(searchTxt)
		    }
		});	
		//初始方法
		function onary(searchTxt,e){
		    	if($.inArray(searchTxt, arr1) !=-1){
		    		$(".basea,.baseb,.basec").empty();
		    		$(".pages").empty();
					$(".tab-ul a").eq(0).find("li").addClass("tab_active");
					$(".tab-ul a").eq(0).siblings("a").find("li").removeClass("tab_active");
					$(".base1").show().siblings(".base2").siblings(".base3").hide();
					searchTxtIn = arr1.indexOf(searchTxt);
					basicFunctions(arr1[searchTxtIn+1],1);
					getHospitalInformation(arr1[searchTxtIn+1]);
				}else if(searchTxt==''){
					layer.msg('请输入医院名称');
				}else{
					layer.msg('该医院不存在');
					return;
				}
		}
		//------------------------------调用医院信息并展示end-------------------
		
		
		//点击li切换功能模块
		$(".tab-ul a").click(function(){
			var tClass=$(this).attr("class");
			var tHtml=$(".basea").html();
			if(tHtml!=''){
				if(tClass=="platformFun"){
					platformFunctions(arr1[searchTxtIn+1],csod2);
				}else if(tClass=="hospitalFun"){
					hospitalFunctions(arr1[searchTxtIn+1],csod3);
				}
			}
		})
		
		//------------------------------获取医院信息start-------------------			
	    //获取特定医院信息
		var HospitalInfo = "";//医院信息
		var CountNum = "";
		var ConcealNum = "";
		var Name = "";
		var infoma= "";
		function getHospitalInformation(hospitalId){
			$.ajax({
				url:path+"/hospital/homepage/getHospitalInformation",
				type:"get",
				dataType:"json",
				data:{"hospitalId":hospitalId},//将关键字传后台
				async:false,
				success:function(Info){
				   if(Info.code==1){
				   		infoma = Info.data[0];
		   				if(infoma.concealNum || infoma.concealNum==0){
		   					ConcealNum = infoma.concealNum;
		   				}else{
		   					ConcealNum = "";
		   				}
		   				if(infoma.countNum || infoma.countNum==0){
		   					CountNum = infoma.countNum;
		   				}else{
		   					CountNum = "";
		   				}
		   				if(infoma.name){
		   					Name = infoma.name;
		   				}else{
		   					Name = "";
		   				}
		   				$(".address").text(Name);
		   				$(".all").text(CountNum);
		   				$(".hide").text(ConcealNum);
				   }
				}
			});
		}
		//------------------------------获取医院信息结束-------------------	
		
		
		//------------------------------获取医院基础功能列表start-----------------
		
		
		
		
		var totalPage1= 0;
		var csod1=1;
		function basicFunctions(hospitalId,csod1){
			$.ajax({
				url:path+"/hospital/homepage/basicFunctions",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hospitalId,
					"pageIndex":1,
					"everyPage":6,
					"first":true
				},
				async:false, 
				success:function(platform){
					platformInfo =platform.data;
					   if(platform.code==1){
						   totalPage1= platformInfo;
					   }
				}
			});
			layui.use(["laypage"], function() {
				layui.laypage({
				    cont: $('#pages1'),
				    pages: totalPage1,
				    curr: csod1,
				    groups:5,
				   	skip: true,
				   	jump: function(obj) {
				   	 	//得到了当前页，用于向服务端请求对应数据
				   	    curr = obj.curr;
					   	//json参数
				   	 	var data = {
				   	 		"hospitalId":hospitalId,
							"pageIndex":curr,
							"everyPage":6,
							"first":false
				   	 	};
					    $.ajax({
							url:path+"/hospital/homepage/basicFunctions",
							type:"get",
							dataType:"json",
							data:data,//将关键字传后台
							async:false, 	
							success:function(basic){
							   if(basic.code==1){
							   		basicInfo = basic.data;
							   		var html="";
							   		for (var i = 0; i < basicInfo.length; i++) {
							   			var obj1 = basicInfo[i];
							   			//console.log(obj1);
								        hospitalId = obj1.hospitalId;
								        var mId = obj1.moduleId;
							   			var urlStat = obj1.urlStat;
							   			//console.log(obj1.entryStat+"--------------");
							   				html += "<div class='base_1'>";
							   				html += "<div class='base_left'>";
							   				if(obj1.iconImg){
								        		html += "<img src="+obj1.iconImg+">";
								        	}else if(obj1.imgUrl){
								        		html += "<img src="+obj1.imgUrl+">";
								        	}else {
								        		html += "<img src='"+path+"/sfile/image/default_others.png'>";
								        	}
							   			    html += "<p>"+obj1.title+"</p>";
							   			    html += "</div>";
							   			    html += "<div class='base_right'>";
							   			    html += "<div class='base_rightBox'></div>";
							   			    if(mId==1 || mId==2 || mId==5 || mId==7 || mId==8 || mId==10 || mId==11 || mId==12 || mId==4){
												html += "<p class='service_state'>服务状态：<span style='color:#ccc;'>默认开通</span></p>";
											}else if(obj1.closed==0){
							   			    	html += "<p class='service_state'>服务状态：<span style='color:#666;'>已开通</span></p>";
											}else if(obj1.closed==1){
												html += "<p class='service_state'>服务状态：<span style='color:#666;'>未开通</span></p>";
											}
											if(obj1.postionOrder<=0){
												html += "<p class='position_rank'>位置排序：<span style='color:#ccc;'>该功能不可排序</span></p>";
											}else{
												html += "<p class='position_rank'>位置排序：<span>"+obj1.postionOrder+"</span></p>";
											}
											if(mId==1 || mId==2 || mId==3 || mId==4  || mId==10 || mId==6 || mId==11 || mId== 12 || mId==5 || mId==8 ){
												html += "<p class='enter_state'>入口状态：<span style='color:#ccc;'>该功能不可隐藏</span></p>";
											}else if(obj1.entryStat==0){
												html += "<p>入口状态：<input type='radio' name='state' moduleid='"+mId+"' checked class='enterBtn_show'/>显示<input type='radio' name='state' moduleId='"+mId+"' class='enterBtn_hide'/>隐藏</p>";
											}else if(obj1.entryStat==1){
												html += "<p>入口状态：<input type='radio' name='state' moduleid='"+mId+"' class='enterBtn_show'/>显示<input type='radio' name='state' moduleId='"+mId+"' checked class='enterBtn_hide'/>隐藏</p>";
											}
							   				if(obj1.urlStat ==0){
							   					html += "<div class='ipt'>链接：<input type='radio' name='base_"+i+"' checked class='default' urlStat='"+urlStat+"' moduleId='"+mId+"'>默认<input type='radio' urlStat='"+urlStat+"' moduleid='"+mId+"' name='base_"+i+"' class='user_defined'>URL：<input type='text' value='"+obj1.url+"' moduleid='"+mId+"' id='ur'></div>";
											}else{
												html += "<div class='ipt'>链接：<input type='radio' name='base_"+i+"' class='default' urlStat='"+urlStat+"' moduleId='"+mId+"'>默认<input type='radio'  urlStat='"+urlStat+"' moduleid='"+mId+"' name='base_"+i+"' checked class='user_defined'>URL：<input type='text' id='ur' moduleid='"+mId+"' value='"+obj1.url+"'></div>";
											}
							   				
											html += "</div>";
											html += "</div>";
							   				html += "</div>";
								     	   };
								     	   $(".basea").html(html);
										}
									}
							    });
						   	}
			});
		});
			hospId=hospitalId;//医院id
		}
		
		
		
		
		
		//------------------------------获取医院基础功能列表end----------------
		//------------------------------获取医院平台功能列表begin----------------
		var totalPage2 = 0;
		var csod2=1;
		//获取医院平台功能列表
		function platformFunctions(hospitalId,csod2){
			$.ajax({
				url:path+"/hospital/homepage/platformFunctions",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hospitalId,
					"pageIndex":csod2,
					"everyPage":6,
					"first":true
				},
				async:false, 
				success:function(platform){
					platformInfo = platform.data;
					   if(platform.code==1){
						   totalPage2= platformInfo;
					   }
				}
			}); 
			layui.use(["laypage"], function() {
				layui.laypage({
				    cont: $('#pages2'),
				    pages: totalPage2,
				    curr: csod2,
				    groups:5,
				   	jump: function(obj,first) {
				   	 	//得到了当前页，用于向服务端请求对应数据
				   	    curr = obj.curr; 
					   	//json参数
				   	 	var data = {
				   	 		"hospitalId":hospitalId,
							"pageIndex":curr,
							"everyPage":6,
							"first":false
				   	 	};
			
					    $.ajax({
							url:path+"/hospital/homepage/platformFunctions",
							type:"get",
							dataType:"json",
							data:data,
							success:function(platform){
								//console.log(platform.data);
							   platformInfo = platform.data;
							   shospitalId = platformInfo.shospitalId;
							   if(platform.code==1){
							   		var html = "";
							   		for (var i = 0; i < platformInfo.length; i++) {
							   			var obj2 = platformInfo[i];
							   		 	//保存hospitalId、moduleId、id
								        //var hospitalId = obj2.hospitalId;
								        var mId = obj2.moduleId;
								        var urlStat = obj2.urlStat;
								        	html += "<div class='base_1'>";
							   				html += "<div class='base_left'>";
							   			    if(obj2.iconImg){
						   			    		html += "<img src="+obj2.iconImg+">";
							   			    }else if(obj2.imgUrl){
							   			    	html += "<img src="+obj2.imgUrl+">";
							   			    }else{
							   			    	html += "<img src='"+path+"/sfile/image/default_others.png'>";
							   			    }
							   			    html += "<p>"+obj2.title+"</p>";
							   			    html += "</div>";
							   			    html += "<div class='base_right'>";
							   			    html += "<div class='base_rightBox'></div>";
							   			    if(mId==1 || mId==2 || mId==5 || mId==7 || mId==8 || mId==10 || mId==11 || mId==12 || mId==4){
												html += "<p class='service_state'>服务状态：<span style='color:#ccc;'>默认开通</span></p>";
											}else if(obj2.closed==0){
							   			    	html += "<p class='service_state'>服务状态：<span style='color:#666;'>已开通</span></p>";
											}else if(obj2.closed==1){
												html += "<p class='service_state'>服务状态：<span style='color:#666;'>未开通</span></p>";
											}
											if(obj2.postionOrder<=0){
												html += "<p class='position_rank'>位置排序：<span style='color:#ccc;'>该功能不可排序</span></p>";
											}else{
												if(obj2.entryStat==1){
													html += "<p class='position_rank'>位置排序：<span style='color:#ccc;'>该功能已隐藏</span></p>";
												}else{
													html += "<p class='position_rank'>位置排序：<span>"+obj2.postionOrder+"</span></p>";
												}
												
											}
											if(mId==1 || mId==2 || mId==3 || mId==4  || mId==10 || mId==6 || mId==11 || mId== 12 || mId==5 || mId==8){
												html += "<p class='enter_state'>入口状态：<span style='color:#ccc;'>该功能不可隐藏</span></p>";
											}else if(obj2.entryStat ==0) {
												html += "<p>入口状态：<input type='radio' name='flo_"+i+"' moduleId='"+mId+"' checked class='enterBtn_show'/>显示<input type='radio' name='flo_"+i+"' moduleId='"+mId+"' class='enterBtn_hide'/>隐藏</p>";
											}else if(obj2.entryStat ==1){
												html += "<p>入口状态：<input type='radio' name='flo_"+i+"' moduleId='"+mId+"' class='enterBtn_show'/>显示<input type='radio' name='flo_"+i+"' moduleId='"+mId+"' checked class='enterBtn_hide'/>隐藏</p>";
											}
											if(obj2.title=="报告解读" || obj2.title=="胎心监护" || obj2.title=="检查报告"){
												html += "<div class='ipt'>链接：<input type='radio' checked name='def_"+i+"' class='default' moduleId='null'/>默认<div/>";
											}else if(obj2.urlStat ==0){
							   					html += "<div class='ipt'>链接：<input type='radio'  name='plat_"+i+"' checked class='default' urlStat='"+urlStat+"' moduleId='"+mId+"'>默认<input type='radio' name='plat_"+i+"' urlStat='"+urlStat+"' moduleId='"+mId+"' class='user_defined'>URL：<input type='text' moduleid='"+mId+"' value='"+obj2.url+"' id='ur'></div>";
											}else{
												html += "<div class='ipt'>链接：<input type='radio'  name='plat_"+i+"' class='default' urlStat='"+urlStat+"' moduleId='"+mId+"'>默认<input type='radio' name='plat_"+i+"' urlStat='"+urlStat+"' moduleId='"+mId+"' checked  class='user_defined'>URL：<input type='text' moduleid='"+mId+"' id='ur' value='"+obj2.url+"'></div>";
											}
											html += "</div>";
											html += "</div>";
							   				html += "</div>";
							   				
								   		};
								        $(".baseb").html(html);
						   			}
								},error:function(){
							}
						});
				  	}
			  	});
			}); 
	    }
		

		//获取医院院内功能列表
		var totalPage3 = 0;
		var csod3=1;
		
		function hospitalFunctions(hospitalId,csod3){
			$.ajax({
				url:path+"/hospital/homepage/hospitalFunctions",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hospitalId,
					"pageIndex":csod3,
				 	"everyPage":6,
				 	"first":true
				},
				async:false,
				success:function(hospital){
					if(hospital.code==1){
						totalPage3=hospital.data;
					}
				}
			});	
			layui.use(["laypage"], function() {
				layui.laypage({
				    cont: 'pages3',
				    pages: totalPage3,
				    curr: csod3,
				    groups:5,
				   	jump: function(obj,first) {
				   	 	//得到了当前页，用于向服务端请求对应数据
				   	    curr = obj.curr; 
					   	//json参数
				   	 	var data = {
				   	 		"hospitalId":hospitalId,
							"pageIndex":curr,
							"everyPage":6,
							"first":false
				   	 	};
						$.ajax({
							url:path+"/hospital/homepage/hospitalFunctions",
							type:"get",
							dataType:"json",
							data:data,
							async:false,
							success:function(hospital){
							   if(hospital.code==1){
								    shospitalId=hospital.data.shospitalId;
							   		var hospitalInfo =hospital.data;
							   		var html = "";
							   		if(hospitalInfo.length==0){//当最后一页没有数据只显示添加块
							   			html += "<div class='base_1 base_1_box'><span class='append iconfont icon-plus'></span><span class='appendText'>添加医院自定义功能</span></div>"; 
							   		}else{
							   			for (var i = 0; i < hospitalInfo.length; i++) {
								   		 	var obj3 = hospitalInfo[i];
								   		 	//保存hospitalId、moduleId、id
									        hospitalId = obj3.hospitalId;
									        var mId = obj3.moduleId;
								   			var urlStat = obj3.urlStat;
								   				html += "<div class='base_1'>";
								   				html += "<div class='base_left'>";
								   			    if(obj3.iconImg){
							   			    		html += "<img src="+obj3.iconImg+">";
								   			    }else if(obj3.imgUrl){
								   			    	html += "<img src="+obj3.imgUrl+">";
								   			    }else{
								   			    	html += "<img src='"+path+"/sfile/image/default_others.png'>";
								   			    }
								   			    html += "<p>"+obj3.title+"</p>";
								   			    html += "</div>";
								   			    html += "<div class='base_right'>";
								   			    html += "<div class='base_rightBox'></div>";
								   			    if(obj3.functionGroup!=3){
								   			    	if(mId==1 || mId==2 || mId==5 || mId==7 || mId==8 || mId==10 || mId==11 || mId==12 || mId==4){
														html += "<p class='service_state'>服务状态：<span style='color:#ccc;'>默认开通</span></p>";
													}else if(obj3.closed==0){
									   			    	html += "<p class='service_state'>服务状态：<span style='color:#666;'>已开通</span></p>";
													}else if(obj3.closed==1){
														html += "<p class='service_state'>服务状态：<span style='color:#666;'>未开通</span></p>";
													}
								   			    }else if(obj3.functionGroup==3){
								   			    	html += "<p class='service_state'><span style='color:#ccc;'>自定义功能</span></p>";
								   			    }
								   				
												if(obj3.postionOrder<=0){
													html += "<p class='position_rank'>位置排序：<span style='color:#ccc;'>该功能不可排序</span></p>";
												}else{
													if(obj3.entryStat==1){
														html += "<p class='position_rank'>位置排序：<span style='color:#ccc;'>该功能已隐藏</span></p>";
													}else{
														html += "<p class='position_rank'>位置排序：<span>"+obj3.postionOrder+"</span></p>";
													}
												}
												if(mId==1 || mId==2 || mId==3 || mId==4  || mId==10 || mId==6 || obj3==11 || mId== 12 || mId==5 || mId==8){
													html += "<p class='enter_state'>入口状态：<span style='color:#ccc;'>该功能不可隐藏</span></p>";
												}else if(obj3.entryStat ==0) {
													html += "<p>入口状态：<input type='radio' name='state_"+i+"' moduleId='"+mId+"' checked class='enterBtn_show'/>显示<input type='radio' name='state_"+i+"' moduleId='"+mId+"' class='enterBtn_hide'/>隐藏</p>";
												}else if(obj3.entryStat ==1){
													html += "<p>入口状态：<input type='radio' name='state_"+i+"' moduleId='"+mId+"' class='enterBtn_show'/>显示<input type='radio' name='state_"+i+"' moduleId='"+mId+"' checked  class='enterBtn_hide'/>隐藏</p>";
												}
												if(obj3.functionGroup!=3){
													if(obj3.urlStat ==0){
									   					html += "<div class='ipt'>链接：<input type='radio'  name='hos_"+i+"' checked class='default' urlStat='"+urlStat+"' moduleId='"+mId+"'>默认<input type='radio' name='hos_"+i+"' class='user_defined' urlStat='"+urlStat+"' moduleId='"+mId+"'>URL：<input type='text' moduleid='"+mId+"' value='"+obj3.url+"' id='ur'></div>";
													}else{
														html += "<div class='ipt'>链接：<input type='radio'  name='hos_"+i+"' class='default' urlStat='"+urlStat+"' moduleId='"+mId+"'>默认<input type='radio' name='hos_"+i+"' checked class='user_defined' urlStat='"+urlStat+"' moduleId='"+mId+"'>URL：<input type='text' moduleid='"+mId+"' id='ur' value='"+obj3.url+"'></div>";
													}
												}else if(obj3.functionGroup==3){
														html += "<div class='ipt'>链接：<input type='radio' name='hos_"+i+"' checked class='user_defined' urlStat='"+urlStat+"' moduleId='"+mId+"'>URL：<input type='text' id='ur' value='"+obj3.url+"' moduleId='"+mId+"'></div>";
												}
								   				
												html += "</div>";
												if(obj3.functionGroup==3){
								   					html +="<div class='btnBj'><button class='green_btn_small' moduleId='"+mId+"'>编辑</button></div>";
								   				}
												html += "</div>";
								   				html += "</div>";
								   				
									   };
									   if(obj3.lastPage==true){
										   html += "<div class='base_1 base_1_box'><span class='append iconfont icon-plus'></span><span class='appendText'>添加医院自定义功能</span></div>"; 
									   }
							   		}
								   $(".basec").html(html);
							   }
							},error:function(){
								return;
							}
						});
				   	}
				   	});
				});
		}
		//获取当前的模块、当前页
		function active_s(){
			var dId=$(".tab_active").parent("a").attr("class");
			var thistext='';
			var thismk='';
			if(dId=="basicFun"){
				thistext=$("#pages1 .layui-laypage-curr em").eq(1).html();
				if($("#pages1 .layui-laypage-curr em").eq(1).html()==undefined){
					thistext=1;
				}
				thismk=1;
			}else if(dId=="platformFun"){
				thistext=$("#pages2 .layui-laypage-curr em").eq(1).html();
				if($("#pages2 .layui-laypage-curr em").eq(1).html()==undefined){
					thistext=1;
				}
				thismk=2;
			}else if(dId=="hospitalFun"){
				thistext=$("#pages3 .layui-laypage-curr em").eq(1).html();
				if($("#pages3 .layui-laypage-curr em").eq(1).html()==undefined){
					thistext=1;
				}
				thismk=3;
			}
			var datamk=[];
			datamk.push(thistext,thismk);
			return datamk;
		}
		
		
		//--------------------------------修改医院服务链接状态start------------------------------
		//链接---点击默认链接按钮---
		
		$(document).on("click",".default",function() {
		    var _that = $(this);
		    var moduleId =parseInt(_that.attr("moduleId"));
		    var urlStat =_that.attr("urlStat");
		    var url =_that.siblings("#ur").val();
		    if(_that.attr("moduleId")!="null"){
		    	//_that.siblings("#ur").val("");
		    	layer.alert('', {
				      title:'提示',
				      btn:['确认', '取消'],
				      content: '确认切换默认？',
				      btnAlign: 'c',
				      yes: function(s){
				    	  updateHospitalServiceUrlStat(hospId,moduleId,0,url);
				    	  var data=active_s();
				    	  if(data[1]=="1"){
				    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else if(data[1]=="2"){
				    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else{
				    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }
				    	 
				    	  layer.close(s);
				    	  layer.msg('操作成功', {time: 600});
				      },
				      end:function(){
				    	  var data=active_s();
				    	  if(data[1]=="1"){
				    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else if(data[1]=="2"){
				    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else{
				    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }
				      }
				  });
			    
		    }
		    
		});
		
		
		
		//链接---点击自定义链接按钮---
		$(document).on("click",".user_defined,#ur",function(hospitalId) {
			var urls=$(this).siblings("#ur").val();	
			var urlb=$(this).val();
			if($(this).attr("class")=="user_defined"){
				$("#urlva").val(urls);
			}else{
				$("#urlva").val(urlb);
			}
			
			$(".titles").hide();
		    var _that = $(this);
		    var moduleId =_that.attr("moduleId");
		    var urlStat =_that.attr("urlStat");
		    
		    layer.open({
		    	  title:'编辑',
		    	  type: 1,
		    	  area:['400px','250px'],
		    	  content: $('#link'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		    	  btn:['保存','取消'],
		    	  yes:function(index){
		    		  	var urs=/(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
						if(!urs.test($("#urlva").val())){
							$(".titles").show();
							return ;
						}else{
							$(".titles").hide();
							var url = $("#link input").val();
						    _that.siblings("#ur").val(url);
						    updateHospitalServiceUrlStat(hospId,moduleId,1,url);
						      var data=active_s();
					    	  if(data[1]=="1"){
					    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
					    	  }else if(data[1]=="2"){
					    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
					    	  }else{
					    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
					    	  }
						    layer.close(index);
						}
		    	  },end:function(){
		    		  var data=active_s();
			    	  if(data[1]=="1"){
			    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else if(data[1]=="2"){
			    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else{
			    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }
		    	  }
		    	}); 
		 
		});
		//监测url值改变判断正确与否，显示提示语句
		$('#urlva').bind('input propertychange', function() {  
			var urs=/(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
			if($("#urlva").val()!=''){
				if(!urs.test($("#urlva").val())){
					$(".titles").show();
				}else{
					$(".titles").hide();
				}
			}else{
				$(".titles").hide();
			}
		}); 
		
		
		//---------------------------------入口状态显示提示框和隐藏提示框start---------------------------
		$(document).on("change",".enterBtn_show",function() {
		    var that = $(this);
		    var moduId = that.attr("moduleId");
		    var intems=parseInt($("#pages3 .layui-laypage-em").siblings("em").html());
	   	
		    layer.alert('', {
		        title:'提示',
		        btn:['确认', '取消'],
		        content: '确认显示该功能？',
		        btnAlign: 'c',
		        yes: function(index){
		        	updateHospitalState(arr1[searchTxtIn+1],moduId,0,index);
		        	var data=active_s();
			    	  if(data[1]=="1"){
			    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else if(data[1]=="2"){
			    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else{
			    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }
		        },
		        end: function(index){
		        	var data=active_s();
			    	  if(data[1]=="1"){
			    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else if(data[1]=="2"){
			    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else{
			    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }
					return;
				}
		    });
		}); 
		
		$(document).on("change",".enterBtn_hide",function() {
		    var _this = $(this);
		    var modId = _this.attr("moduleId");
		    var intems=parseInt($("#pages3 .layui-laypage-em").siblings("em").html());
		    layer.alert($("#generalize").val(), {
		        title:'提示',
		        btn:['确认', '取消'],
		        content: '确认隐藏该功能？',
		        btnAlign: 'c',
		        yes: function(index2){
		        	
		        	 updateHospitalState(arr1[searchTxtIn+1],modId,1,index2);
		        	  var data=active_s();
			    	  if(data[1]=="1"){
			    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else if(data[1]=="2"){
			    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else{
			    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }
		        },
		        end: function(index){
		        	var data=active_s();
			    	  if(data[1]=="1"){
			    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else if(data[1]=="2"){
			    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }else{
			    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
			    	  }
					return;
				}
		    });
		});
		//修改医院服务入口状态
		function updateHospitalState(hosId,moduId,entryStat,index){
			$.ajax({
				url:path+"/hospital/homepage/updateHospitalServiceEntryStat",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hosId,
					"moduleId":moduId,
					"entryStat":entryStat
				},//将关键字传后台
				async:false,
				success:function(EntryStat){
				   if(EntryStat.code==1){
					   layer.close(index);
					   layer.msg('操作成功', {time: 600});
				   		//console.log(hosId,moduId,entryStat);
				   }
					
				},error:function(json){
					
				}
			});
		}
		//--------------------------------入口状态显示提示框和隐藏提示框end------------------------
	
		
		//修改医院服务链接状态
		function updateHospitalServiceUrlStat(hospitalId,moduleId,urlStat,url){
			$.ajax({
				url:path+"/hospital/homepage/updateHospitalServiceUrlStat",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hospitalId,
					"moduleId":moduleId,
					"urlStat":urlStat,
					"url":url
				},//将关键字传后台
				async:false,
				success:function(UrlStat){
				   if(UrlStat.code==1){
					   return;
				   }
				},error:function(json){
					return;
				}
			});
		}
		//--------------------------------修改医院服务链接状态end-----------------------------
		
		
		//----------------------------------------------获取排序列表start-----------------------------------------------
		//当点功能排序时获取排序列表
		$(".red_btn_small").on('click', function() {
			$("tbody .clearHtml").remove();//一点击就清空上一次内容
			getRankList(arr1[searchTxtIn+1]);//将医院id获取到并传给后台
		});
		//功能排序
		var hospId_rank =0;
		var moduId_rank = 0;
		var postionOrder_rank = 0;
		var obj4 ="";
		var ranklength=0;
		function getRankList(hospitalId){
			$.ajax({
				url:path+"/hospital/homepage/getRankList",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hospitalId
				},//将关键字传后台
				async:false,
				success:function(rank){
				   if(rank.code==1){
				   		rankList = rank.data;
				   		ranklength = rank.data.length;
				   		//将总共功能数，隐藏功能数，可排序功能数显示
			   			$(".total").text(CountNum);
			   			$(".ensconce").text(ConcealNum);
			   			$(".Sortable").text(rankList.length);
			   			var count = 1;
				   		for(var i = 0; i<rankList.length;i++){
				   			obj4 = rankList[i];
				   			hospId_rank = obj4.hospitalId;
				   			moduId_rank = obj4.moduleId;
				   			postionOrder_rank = obj4.postionOrder;
				   			var postionorder=parseInt(i+1)//序列号排序从1开始
				   			var  tr = $("<tr class='clearHtml'></tr>");
				   			tr.appendTo("#tab");
				   				var td1 = $("<td class='number_po'>"+(count++)+"</td>");
				   				var td2 = $("<td hospitalid="+obj4.hospitalId+" moduleid="+obj4.moduleId+" postionorder="+postionorder+" class='biaoti'>"+obj4.title+"</td>");
				   				var td3 = $("<td><span class='forward'>前移</span><span class='retreat'>后移</span></td>");
				   				td1.appendTo(tr);
				   				td2.appendTo(tr);
				   				td3.appendTo(tr);
				   	   }
				   	  
				   }
					
				},error:function(json){
					
				}
			});
		}
		//----------------------------------------------获取排序列表end-----------------------------------------------
		//--------------------------------修改医院服务位置排序start-----------------------------
		//功能弹出层
		$(".rank").on("click",".red_btn_small",function() {
			$("#mask3").fadeIn(500);
		});
		$("#mask3").on("click",".cancel,.close",function(){
			$("#mask3").fadeOut(500);
		});
		$("#mask3").on("click",".affirm",function() {
			var arr = [];
			var a = $(this).parent().siblings(".tabDetail").find(".biaoti");
			for(var i = 0;i<a.length;i++){
				var obj = {};
				obj["hospitalId"] =parseInt(a[i].getAttribute("hospitalid"));
				obj["moduleId"] =parseInt(a[i].getAttribute("moduleid"));
				obj["postionOrder"] =parseInt(a[i].getAttribute("postionorder"));
				arr[i]=obj;
			}
			  updateHospitalServicePostionOrder(arr);
			  var data=active_s();
	    	  if(data[1]=="1"){
	    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
	    	  }else if(data[1]=="2"){
	    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
	    	  }else{
	    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
	    	  }
		});
		
		//前移
		$(document).on("click",".forward",function(){
		    var temp ="";
		    var prev = $(this).parent().parent().prev(".clearHtml").children(".biaoti");
			var current = $(this).parent("td").siblings(".biaoti");
			var postionorder1=$(this).parent().siblings(".biaoti").attr("postionorder");//当前序号
			var postionorder2 = $(this).parent().parent().prev(".clearHtml").children(".biaoti").attr("postionorder");
			var cur_a = $(this).parent("td").siblings(".number_po");
			var cur_b = $(this).parent().parent().prev(".clearHtml").children(".number_po");
			temp = prev;
			//判断当前的不是第一个
			if(postionorder1!=1){
				current.insertAfter(cur_b);
				temp.insertAfter(cur_a);
				//把当前的序号设置给前面的，再把前面的序号设置为当前的序号，相互换一下值
				$(this).parent().siblings(".biaoti").attr("postionorder",postionorder1);
				$(this).parent().parent().prev(".clearHtml").children(".biaoti").attr("postionorder",postionorder2)
			}
			
		});
		//后移
		$(document).on("click",".retreat",function(){
		    var temp1 ="";
			var current = $(this).parent("td").siblings(".biaoti");
			var next = $(this).parent().parent().next(".clearHtml").children(".biaoti");
			var cur_a = $(this).parent("td").siblings(".number_po");
			var cur_b = $(this).parent().parent().next(".clearHtml").children(".number_po");
			var postionorder1=$(this).parent().siblings(".biaoti").attr("postionorder");//当前序号
			var postionorder2 = $(this).parent().parent().next(".clearHtml").children(".biaoti").attr("postionorder");
			temp = next;
			//判断当前不是最后一个
			if(postionorder1!=ranklength){
				current.insertAfter(cur_b);
				temp.insertAfter(cur_a);
				//把当前的序号设置给后面的，再把后面的序号设置为当前的序号，相互换一下值
				$(this).parent().siblings(".biaoti").attr("postionorder",postionorder1);
				$(this).parent().parent().next(".clearHtml").children(".biaoti").attr("postionorder",postionorder2);
			}
			
		});
		//修改医院服务位置排序
		function updateHospitalServicePostionOrder(listMap){
			//console.log(JSON.stringify(listMap));
			var data=JSON.stringify(listMap);
			$.ajax({
				url:path+"/hospital/homepage/updateHospitalServicePostionOrder",
				type:"POST",
				dataType:"json",
				contentType : 'application/json;charset=utf-8', //设置请求头信息 
				data:data,//将关键字传后台
				async:false,
				success:function(PostionOrder){
				   //console.log(PostionOrder.code);
				   if(PostionOrder.code==1){
					   $("#mask3").fadeOut();
				   		return;
				   }
					
				},error:function(json){
					return;
				}
			});
		}
		//--------------------------------修改医院服务位置排序end-----------------------------
		
		//-------------------------添加自定义弹出层start---------------------
		$(".base3").on("click",".append",function() {
			//$("#mask1").fadeIn(500);
			$("#mask1 .div_1  p").html("");
			$("#mask1 .div_2  p").html("");
			$("#mask1 .div_1  .ipt1").val("");
			$("#mask1 .div_2  .ipt2").val("");
			layer.open({
				title:'自定义功能',
		  		type: 1,
		  		area: ['480px','280px'], //宽高
		  		content: $('#mask1'),
		  		btn: ['保存','取消'],
		  		yes: function(sit){
		  			//下面两个是获取弹出层中：功能名称和url链接的值
					var title1 = $("#mask1 .ipt1").val();
					var url1 = $("#mask1 .ipt2").val();
					
					$("#mask1 .div_1").find("p").text("");
					$("#mask1 .div_2").find("p").text("");
					if($("#mask1 .ipt1").val()==""){
						$("#mask1 .div_1").find("p").text("*功能名称不得为空");
						return;
					}else if($("#mask1 .ipt2").val()==""){
						$("#mask1 .div_2").find("p").text("*链接地址不得为空");
						return;
					}else{
		        		addOrUpdateHospitalService(0,arr1[searchTxtIn+1],3,title1,url1,sit);//调用
		        		 var data=active_s();
				    	  if(data[1]=="1"){
				    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else if(data[1]=="2"){
				    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else{
				    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }
						
			        }
		  		},
		    	end:function(){
		    		
		    	}
			});
		});
		//院内功能模块，添加模块的编辑方法
		$(".base3").on("click",".btnBj",function() {
			var moduleId=$(this).find("button").attr("moduleId");
			//清空
			$("#mask1 .div_1  p").html("");
			$("#mask1 .div_2  p").html("");
			// 获取到编辑前的原有数据，写入
			var leftText=$(this).siblings(".base_left").find("p").html();
			var rightText=$(this).siblings(".base_right").find(".ipt").find("#ur").val();
			$("#mask1 .div_1 .ipt1").val(leftText);
			$("#mask1 .div_2 .ipt2").val(rightText);
			layer.open({
				title:'自定义功能',
		  		type: 1,
		  		area: ['480px','280px'], //宽高
		  		content: $('#mask1'),
		  		btn: ['保存','取消'],
		  		yes: function(sits){
		  			//下面两个是获取弹出层中：功能名称和url链接的值
					var title1 = $("#mask1 .ipt1").val();
					var url1 = $("#mask1 .ipt2").val();
					var imgUri =$("")
					$("#mask1 .div_1").find("p").text("");
					$("#mask1 .div_2").find("p").text("");
					if($("#mask1 .ipt1").val()==""){
						$("#mask1 .div_1").find("p").text("*功能名称不得为空");
						return;
					}else if($("#mask1 .ipt2").val()==""){
						$("#mask1 .div_2").find("p").text("*链接地址不得为空");
						return;
					}else{
		        		  addOrUpdateHospitalService(moduleId,arr1[searchTxtIn+1],3,title1,url1,sits);//调用
		        		  var data=active_s();
				    	  if(data[1]=="1"){
				    		  basicFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else if(data[1]=="2"){
				    		  platformFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }else{
				    		  hospitalFunctions(arr1[searchTxtIn+1],data[0]);
				    	  }
			        }
		  		},
		    	end:function(){
		    		
		    	}
			});
		});
		
		//自定义添加，提示处理
		$("#mask1 .div_1 .ipt1").blur(function(){
			var valid=$("#mask1 .div_1 .ipt1").val();
			if(valid=='' || valid!=''){
				$("#mask1 .div_1  p").html("");
			}
		});
		$("#mask1 .div_2 .ipt2").blur(function(){
			var valid=$("#mask1 .div_2 .ipt2").val();
			if(valid=='' || valid!=''){
				$("#mask1 .div_2  p").html("");
			}
		})
		
		//-----------------------添加自定义编辑事件end-------------------------
		
		//添加或修改自定义功能
		function addOrUpdateHospitalService(moduleId,hospitalId,functionGroup,title,url,index){
			$.ajax({
				url:path+"/hospital/homepage/addOrUpdateHospitalService",
				type:"post",
				dataType:"json",
				data:{
					"moduleId":moduleId,
					"hospitalId":hospitalId,
					"functionGroup":functionGroup,
					"title":title,
					"url":url
				},//将关键字传后台
				async:false,
				success:function(add){
				   if(add.code==1){
				   		//console.log(add.code);
				   		layer.close(index);
				   		//console.log(moduleId,hospitalId,functionGroup,title,url);
				   }else if(add.code==0){
					   layer.msg(add.msgbox);
				   }
					
				},error:function(json){
					
				}
			});
		}
		//获取医院服务信息
		function getHospitalService(hospitalId,moduleId){
			$.ajax({
				url:path+"/hospital/homepage/getHospitalService",
				type:"get",
				dataType:"json",
				data:{
					"hospitalId":hospitalId,
					"moduleId":moduleId
				},//将关键字传后台
				async:false,
				success:function(service){
				   if(service.code==1){
				   		hospitalService =service.data;
				   }
					
				},error:function(json){
				
			    }
		   });
	   }
		
		
	});
</script>
</html>
