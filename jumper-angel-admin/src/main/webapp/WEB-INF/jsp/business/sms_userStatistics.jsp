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
	<input type="hidden" name="hospId" class="hospId" value="${hospId}" />
	<input type="hidden" name="appid" class="appid" value="${appid}" />
	<input type="hidden" name="hospName" class="hospName" value="${hospName}" />
	<div class="content">
		<p class="toptitle">业务管理 > 短信统计 > 医院列表 > 用户短信详情</p>

		<div class="content-search-top">
			<input id="mobile" class="shortinput float-l" placeholder="输入手机号" style="width: 250px;margin-right: 20px;">
			<div class="laydateBox">
				<input id="start" style="width: 140px;" class="laydate-icon" type="text" placeholder="开始时间">
                    <input id="end" style="width: 140px;" class="laydate-icon" type="text" placeholder="结束时间">
                    <input type="hidden" id="startTime"/>
                    <input type="hidden" id="endTime"/>
			</div>
			<button class="blue_btn_big" style="+padding: 8px 0;_padding: 8px 0;float: left;" id="searchBtn">查询</button>
			<div class="clearfix"></div>
		</div>
		<p class="top-text" style="margin-bottom: 20px;margin-top:0;">短信发送情况:</p>
		<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum" id="totalSend"></p>
				<p class="datatitle">总发送短信</p>
			</div>
			<div class="datalist margin-r float-l greenback">
				<p class="datanum" id="totalSuccess"></p>
				<p class="datatitle">发送成功</p>
			</div>
			<div class="datalist float-l blueback">
				<p class="datanum" id="totalFailure"></p>
				<p class="datatitle">发送失败</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<p class="top-text" style="margin-bottom: 20px;margin-top:0;">用户详情:</p>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户手机号</th>
						<th>发送时间</th>
						<th>状态</th>
						<th>发送内容</th>
					</tr>
				</thead>
				<tbody id="tbody">
				
				</tbody>
			</table>
			<div id="sms_user"></div>
		</div>
        <div id="page" align="right">
            <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-0">
                <span class="layui-laypage-curr"><em class="layui-laypage-em"></em><em>1</em></span>
                <a href="javascript:;" data-page="2">2</a>
                <a href="javascript:;" data-page="3">3</a>
                <a href="javascript:;" data-page="4">4</a>
                <a href="javascript:;" data-page="5">5</a>
                <span>…</span>
                <a href="javascript:;" class="layui-laypage-last" title="尾页" data-page="1691">末页</a>
                <a href="javascript:;" class="layui-laypage-next" data-page="2">下一页</a>
                <span class="layui-laypage-total">到第 <input type="number" min="1" onkeyup="this.value=this.value.replace(/\D/, '');" value="1" class="layui-laypage-skip"> 页 <button type="button" class="layui-laypage-btn">确定</button>
                </span>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/business/sms_statistics_user.js"></script>
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