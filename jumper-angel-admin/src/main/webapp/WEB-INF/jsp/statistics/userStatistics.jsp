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
</head>
<body>
	<div class="container">
		<div class="content">
		<p class="toptitle">数据中心 > 用户统计 > 数据概览</p>
		<div class="contentdata1">
            <div class="content-search-top" >
                <p class="top-text float-l" id="dateT" style="margin: 0; padding-left:15px ;">时间加载中……</p>
            </div>
			<div class="datalist2 margin-r2 float-l redback">
				<div class="tongji_box_icon2"></div>
				<p class="datatitle margin-l">总用户数</p>
				<p class="datanum margin-l margin-t2">${totalCount}</p>
			</div>
			<div class="datalist2 margin-r2 float-l yellowback">
				<div class="tongji_box_icon2 bg_png2_user2"></div>
				<p class="datatitle margin-l">今日注册量</p>
				<p class="datanum margin-l margin-t2">${todayRegCount}</p>
			</div>
			<div class="datalist2 margin-r2 float-l greenback">
				<div class="tongji_box_icon2 bg_png2_user3"></div>
				<p class="datatitle margin-l">今日活跃</p>
				<p class="datanum margin-l margin-t2">${todayLoginCount}</p>
			</div>
			<div class="datalist2 margin-r2 float-l yellowback">
				<div class="tongji_box_icon2 bg_png2_user9"></div>
				<p class="datatitle margin-l">网络医院数量</p>
				<p class="datanum margin-l margin-t2">${monitorHospitalCount}</p>
			</div>
			<div class="datalist2 float-l blueback">
				<div class="tongji_box_icon2 bg_png2_user5"></div>
				<p class="datatitle margin-l">医生数量</p>
				<p class="datanum margin-l margin-t2">${doctorCount}</p>
			</div>
		</div>
		<div class="contentdata1">
            <div class="content-search-top" >
                <p class="top-text float-l" style="margin: 0; padding-left:15px ;">天使医生实时业务统计</p>
            </div>
			<div class="datalist2 margin-r2 float-l border1" style="text-align: center">
				<p class="datatitle2 color666">免费咨询</p>
				<p class="datanum margin-t2 yellow">${freeConsultCount}</p>
			</div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle2 color666">图文咨询</p>
                <p class="datanum margin-t2 yellow">${tuwenConsultCount}</p>
            </div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle2 color666">私人医生</p>
                <p class="datanum margin-t2 yellow">${privateDoctorCount}</p>
            </div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle2 color666">胎心判读</p>
                <p class="datanum margin-t2 yellow">${heartReadCount}</p>
            </div>
            <div class="datalist2 float-l border1" style="text-align: center">
                <p class="datatitle2 color666">胎心实时监护</p>
                <p class="datanum margin-t2 yellow">${heartMonitorCount}</p>
            </div>
		</div>
        <div class="contentdata1">
            <div class="content-search-top" >
                <p class="top-text float-l" style="margin: 0; padding-left:15px ;">天使医生社群功能统计</p>
            </div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle3 color666">话题组总数</p>
                <p class="datanum margin-t2 blue">${topicGroupCount}</p>
            </div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle3 color666">话题总数</p>
                <p class="datanum margin-t2 blue">${userTopicCount}</p>
            </div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle3 color666">交流圈总数</p>
                <p class="datanum margin-t2 blue">${communicationGroupCount}</p>
            </div>
            <div class="datalist2 margin-r2 float-l border1" style="text-align: center">
                <p class="datatitle3 color666">发言用户总数</p>
                <p class="datanum margin-t2 blue">${speakUserCount}</p>
            </div>
            <div class="datalist2 float-l border1" style="text-align: center">
                <p class="datatitle3 color666">宝妈日记篇数</p>
                <p class="datanum margin-t2 blue">${diaryCount}</p>
            </div>
        </div>
	</div>
</div>

	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/statistics/dateT.js"></script>
</body>
</html>