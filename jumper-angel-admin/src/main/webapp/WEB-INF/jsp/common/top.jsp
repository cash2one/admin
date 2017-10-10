<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/head.jsp"></jsp:include>
<!-- 头部 -->
<div class="topUp">
   <!--  <ul class="topUp_ul">
        <li>天使医生后台管理</li>
        <li></li>
        <li>公告：您有一条来自汪星球的未读消息~~~!</li>
    </ul> -->
     <ul class="topUp_ul float-l" style="width: 308px;">
        <li class="topUp_ul_first"><img class="topLogo" src="${pageContext.request.contextPath}/assets/images/logo.svg.png"/>天使医生后台管理</li>
        <li class="topUp_ul_two"></li>
    </ul>
    <div class="marqueeBox float-l">
        <marquee class="topMarquee" onMouseOver=this.stop(); onMouseOut=this.start();><span class="iconimg notice gonggao"></span>公告：欢迎使用新版天使医生运营后台~~~!</marquee>
    </div>
    <div class="topUpright float-r">
        <ul style="width: 260px">
            <li><a class="tuichu" href="${pageContext.request.contextPath}/login/logout">退出</a></li>
            <li>欢迎：<a href="javascript:void(0);" style="cursor:default;">【${sessionScope.user.name}】</a></li>
        </ul>
    </div>
    <p class="topUp_ul_two float-r"></p>
</div>
<script>
function logout() {
	window.parent.location.href=path+"/login.jsp";
}
</script>