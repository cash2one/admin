<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>咨询管理 -我的解答</title>
    <jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div class="content">
		<p class="toptitle">咨询管理    》我的解答</p>
		<div class="content-search-top">
			<div class="searchGroup">
				<input class="shortinput float-l" id="keywords" value="" placeholder="请输入用户名/手机号"/> 
					<label class="float-r" > </label>
				<button class="search-icon float-l" id="searchItem"></button>
			</div>
		</div>
		<div class="tab-but">
			<ul class="tab-ul">
				<a href="${pageContext.request.contextPath}/hospital/doctor/myAnswering" class="grey"><li>回复中</li></a>
				<a href="${pageContext.request.contextPath}/hospital/doctor/myAnswerend" class="grey"><li class='tab_active'>已结束</li></a>
			</ul>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr >
						<th>用户昵称</th>
						<th>用户姓名</th>
						<th>提问内容</th>
						<th>科室</th>
						<th>提问时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tbody id="tbody"></tbody>
				</tbody>
			</table>
		</div>
		<div class="footer-page">
			<div id="page" align="right"></div>
		</div>
	</div>
</div>
<!--------------------  弹出层     ---------------------- --->
<div class="normal_form">
	<table class="con_table">
		<tbody>
			<tbody id="msg_content"></tbody>
		</tbody>
	</table>
</div>
</body>
<script src="${pageContext.request.contextPath}/media/js/hospital/doctor/myAnswerend.js"></script>
<script src="${pageContext.request.contextPath}/media/js/hospital/doctor/showComment.js"></script>
</html>
