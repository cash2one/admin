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
</head>
<body>
	<div class="container">
		<div class="content">
		<p class="toptitle">数据中心 > 推广统计 > 医院总表</p>
        <div class="content-search-top">
            <div class="float-l">
                <label class="label-r select_shelter"> 医院：
                    <input class="shortinput" style="width: 180px;" type="text" id="keywords"/>
                </label>
            </div>
            <div class="float-l">
                <label class="label-r select_shelter"> 省份：
                    <select id="province">
                        <option value="0">--- 请选择省份 ---</option>
						<c:forEach items="${proList }" var="pro">
							<option value="${pro.id }" <c:if test="${pro.id == proId }">selected</c:if> >${pro.province_name }</option>
						</c:forEach>
                    </select>
                </label>
                <label class="label-r select_shelter"> 城市：
                    <select id="city">
                        <option value="0">--- 请选择城市 ---</option>
                    </select>
                </label>
            </div>
            <div class="laydateBox" style="float: left;">
                <input id="pointTime" class="laydate-icon" style="height: 40px;line-height: 40px;width: 140px;" type="text" placeholder="选择时间"/>
            </div>
            <button class="blue_btn_big" style="+padding: 8px 0;_padding: 8px 0;float: left;" id="searchBtn">查询</button>
            <button class="red_btn_big" id="exportStatisticsData" style="+padding: 8px 0;_padding: 8px 0;float: right;">导出数据</button>
            <div class="clearfix"></div>
        </div>
        <div class="tableBox">
		    <div class="panel">
			<table class="table">
				<thead class="thFS">
					<tr >
						<th>序号</th>
						<th>医院名称</th>
						<th>总用户数</th>
						<th>日新增用户数</th>
						<th>医院问诊服务次数</th>
						<th>设备租赁服务次数</th>
						<th>胎心监护服务次数</th>
						<th>营养门诊服务人数</th>
						<th>近7天新增用户数</th>
						<th>本月新增用户数</th>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/statistics/statistics_all.js"></script>
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