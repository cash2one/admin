<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>咨询管理-问题库</title>
    <jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div class="content">
		<p class="toptitle">咨询管理    》问题库</p>
		<div class="content-search-top">
				<div class="searchGroup">
					<input class="shortinput float-l" id="keywords" value="" placeholder="请输入用户昵称/姓名/手机号" /> 
						<label class="float-r" > </label>
					<button class="search-icon float-l" id="searchItem"></button>
				</div>
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
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
		<div id="page" align="right"></div>
	</div>
</div>
<!--------------------  弹出层     ---------------------- --->
</body>
<script src="${pageContext.request.contextPath}/media/js/hospital/doctor/problemBase.js"></script>
</html>
