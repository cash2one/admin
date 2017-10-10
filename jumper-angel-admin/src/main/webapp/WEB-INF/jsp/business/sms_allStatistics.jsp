<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/zui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/orange.css">
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
		<p class="toptitle">业务管理 > 短信统计 > 医院列表</p>
        <div class="content-search-top">
            <div class="float-l">
                <label class="label-r select_shelter"> 医院：
                    <input class="shortinput" style="width: 180px;" type="text" id="hospName"/>
                </label>
            </div>
            <button class="blue_btn_big" style="+padding: 8px 0;_padding: 8px 0;float: left;" id="searchBtn">查询</button>
            <div class="clearfix"></div>
        </div>
        <div class="tableBox">
		    <div class="panel">
			<table class="table">
				<thead class="thFS">
					<tr >
						<th>医院名称</th>
						<th>发出短信条数</th>
						<th>发送成功</th>
						<th>发送失败</th>
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

	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/business/sms_statistics_all.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/laydate/laydate.js"></script>
	<script>
    laydate({
        elem: '#pointTime',
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        festival: true, //显示节日
        max: laydate.now(), //设定最大日期为当前日期
        choose: function(datas){ //选择日期完毕的回调

        }
    });

</script>
</body>
</html>