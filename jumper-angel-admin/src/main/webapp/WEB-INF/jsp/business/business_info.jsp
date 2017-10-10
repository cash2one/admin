<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>业务管理</title>
    <jsp:include page="../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <button id="list_menu_btn"></button>
	<div class="content">
		<p class="toptitle">开放用户管理    》用户列表</p>
		<div class="content-search-top">
			<div class="searchGroup">
				<input class="shortinput float-l borRightNone" placeholder="请输入开发商名称" id="businessName" /> 
				<label class="float-r" > </label>
				<button class="search-icon float-r" onclick="initData()"></button>
			</div>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr >
						<th>开发商名称</th>
						<th>邮箱</th>
						<th>添加时间</th>
						<th>心电仪数</th>
						<th>胎心仪数</th>
						<th>血糖仪数</th>
						<th>血压仪数</th>
						<th>血氧仪数</th>
						<th>体温计数</th>
						<th>体重秤数</th>
						<th>用户状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>
		</div>
		<div id="page" align="right"></div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/media/js/business/business_info.js"></script>
</html>
