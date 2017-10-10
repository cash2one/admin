<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Insert title here</title> -->
<style>
	.label-r{margin-right: 13px;}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.autocomplete.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="content">
		<p class="toptitle">数据中心 > 推广统计 > 院区查询</p>
        <div class="content-search-top">

                <div class="float-l">
                    <label class="label-r select_shelter"> 医院：
                        <input class="shortinput" style="width: 180px;" type="text" id="keywords"/>
                        <input type="hidden" id="hospName"/>
                    </label>
                </div>
                <div class="laydateBox">
                    <input id="start" style="width: 140px;" class="laydate-icon" type="text" placeholder="开始时间">
                    <input id="end" style="width: 140px;" class="laydate-icon" type="text" placeholder="结束时间">
                    <input type="hidden" id="startTime"/>
                    <input type="hidden" id="endTime"/>
                </div>
                <button class="blue_btn_big" style="+padding: 8px 0;_padding: 8px 0;float: left;" id="searchBtn">查询</button>
                <div class="clearfix"></div>
                <p class="ann">请在输入框输入需要查询的医院、起始时间。</p>
            </div>
        <div class="content2">
            <div class="contentdata1" style="margin-bottom: 80px;">
                <div class="content-search-top" style="padding-top: 0;">
                    <p class="top-text float-l" style="margin: 0; padding-left:15px ;">当前医院：
                        <span id="curr_hospital" style="color: #333"></span>
                        <span style="margin-left: 60px;">查询时段：<span id="search_time" style="color: #333"></span></span>
                    </p>
                </div>
                <div class="datalist3 margin-r2 float-l redback">
                    <div class="tongji_box_icon2"></div>
                    <p class="datatitle margin-l">总用户数</p>
                    <p class="datanum margin-l margin-t2" id="total_count"></p>
                </div>
                <div class="datalist3 margin-r2 float-l yellowback">
                    <div class="tongji_box_icon2 bg_png2_user2"></div>
                    <p class="datatitle margin-l">新增用户量</p>
                    <p class="datanum margin-l margin-t2" id="add_count"></p>
                </div>
                <div class="datalist3 float-l margin-r2 blueback">
                    <div class="tongji_box_icon2 bg_png2_user4"></div>
                    <p class="datatitle margin-l">胎心监护服务次数</p>
                    <p class="datanum margin-l margin-t2" id="heartmonitor_ount"></p>
                </div>
                <div class="datalist3 margin-r2 float-l yellowback">
                    <div class="tongji_box_icon2 bg_png2_user8"></div>
                    <p class="datatitle margin-l">设备租赁服务次数</p>
                    <p class="datanum margin-l margin-t2" id="lease_count"></p>
                </div>
                <div class="datalist3 float-l greenback">
                    <div class="tongji_box_icon2 bg_png2_user7"></div>
                    <p class="datatitle margin-l">营养管理服务人数</p>
                    <p class="datanum margin-l margin-t2" id="outpatient_count"></p>
                </div>
            </div>
            <div class="content-search-top" style="padding-top: 0;">
                <p class="top-text float-l" style="margin: 0; padding-left:15px; width: 100%">用户详情：
                    <span style="margin-left: 60px;">
                        <span style="color: #333">初次关联用户 <span id="firstbind_count"></span> 人，</span>
                        
                    </span>
                    <span style="color: #333">总计关联行为 <span id="totalbind_count"></span> 次；</span>
                    <span style="color: #333">其中<span id="oldfirstbind_count"></span>人通过老版本APP注册。</span>
                    <!-- <label class="float-r checkBox1" >
                        <input type="checkbox" />
                        只显示初次关联用户
                    </label> -->
                </p>
            </div>
            <div class="tableBox" style="margin-top: 40px;">
                <ul class="tableTab">
                	<input type="hidden" id="current_identity" value="0"/>
                    <li><a class="active userListBtn" href="javascript:void(0);" currentIdentity="0">孕期用户</a></li>
                    <li><a class="userListBtn" href="javascript:void(0);" currentIdentity="1">产后用户</a></li>
                </ul>
                <div class="panel">
                <table class="table">
                    <thead class="thFS">
                        <tr >
                            <th>序号</th>
                            <th>用户姓名</th>
                            <th>手机号</th>
                            <th>年龄</th>
                            <th class="expectOrBaby">预产期</th>
                            <th>注册时间</th>
                            <th>关联时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                   		
					</tbody>
                </table>
                <div class="d">

                </div>
            </div>
            <div id="pages" align="right"></div>
            </div>
        </div>
	</div>
</div>


	<!--------------------  弹出层     ---------------------- --->
	<div class="userDate" style="display: none;">
	   <table class="tableWin">
	       <tbody id="table_left">
	       
	       </tbody>
	   </table>
	    <table class="tableWin" style="margin-right: 0;">
	        <tbody id="table_right">
	        
	        </tbody>
	    </table>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/statistics/statistics_detail.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/laydate/laydate.js"></script>
	<script>
	//日期
    var start_date = {
        elem: '#start',
        format: 'YYYY-MM-DD',
        max: laydate.now(), //设定最大日期为当前日期
        istoday: false,
        choose: function(datas){
        	end_date.max = laydate.now();
        	end_date.min = datas; //开始日选好后，重置结束日的最小日期
        	end_date.start = datas;//将结束日的初始值设定为开始日
        }
    };
    var end_date = {
        elem: '#end',
        format: 'YYYY-MM-DD',
        max: laydate.now(), //设定最大日期为当前日期
        istoday: false,
        choose: function(datas){
        	start_date.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
   laydate(start_date);laydate(end_date);

</script>
</body>
</html>