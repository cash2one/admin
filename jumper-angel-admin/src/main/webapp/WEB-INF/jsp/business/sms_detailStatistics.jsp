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
	<div class="content">
		<p class="toptitle">业务管理 > 短信统计 > 医院列表 > 短信统计详情</p>

		<p class="top-text" style="margin-bottom: 20px;">当前医院：${hospName}</p>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>发送短信业务渠道</th>
						<th>发出短信条数</th>
						<th>发送成功</th>
						<th>发送失败</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>
			<div id="sms_detail"></div>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/business/sms_statistics_detail.js"></script>
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