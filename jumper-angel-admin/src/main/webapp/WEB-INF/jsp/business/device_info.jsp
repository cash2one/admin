<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>业务管理</title>
<jsp:include page="../common/head.jsp"></jsp:include>
<style type="text/css">
.fileclass {
	position: absolute;
	top: 0;
	left: 0;
	width: 240px;
	height: 40px;
	border: 1px solid #ccc;
	z-index: 9;
}

.fileclick {
	position: absolute;
	left: 0;
	top: 0;
	width: 240px;
	height: 40px;
	vertical-align: middle;
	opacity: 0;
	z-index: 99;
}
</style>
</head>
<body>
	<input type="hidden" id="businessId" value="${id}" />
	<div class="container">
		<button id="list_menu_btn"></button>
		<div class="content">
			<p class="toptitle">开放用户管理 》用户列表 》详情</p>
			<div class="content-search-top">
				<div class="layui-input-inline" style="float: left;">
					<label class="layui-form-label" style="width: 90px;">设备类型</label>
					<div class="layui-input-inline">
						<select id="deviceType" style="width: 200px;" name="modules"
							lay-verify="required" lay-search="">
							<option value="-1">全部</option>
							<option value="1">心电仪</option>
							<option value="2">胎心仪</option>
							<option value="3">血糖仪</option>
							<option value="4">血压仪</option>
							<option value="5">体温计</option>
							<option value="6">血氧仪</option>
							<option value="7">体重秤</option>
						</select>
					</div>
				</div>
				<div class="searchGroup" style="margin-left: 20px;">
					<input class="shortinput float-l borRightNone" placeholder="请输入设备序列号或者MAC地址"
						id="mac" /> <label class="float-r"> </label>
					<button class="search-icon float-r" onclick="initData()"></button>
				</div>
				<form id="uploadForm" enctype="multipart/form-data"
					style="display: inline-block; position: relative; margin-left: 20px;">
					<input type="text" class="fileclass" placeholder="请选择excel文件" /> <input
						type="file" id="file" class="fileclick" name="file" /> <input
						type="button" value="导入数据" onclick="importExcel()"
						style="height: 40px; margin-left: 250px; background: #E65545; color: #fff; border-color: #E65545; vertical-align: middle; cursor: pointer;" />
				</form>
				<div style="display: inline; margin-left: 20px;">
					<a
						href="${pageContext.request.contextPath}/assets/file/11755259d27c465f9ad4f84b285a48d7.xlsx">下载模板</a>
				</div>
			</div>

			<div class="panel">
				<table class="table">
					<thead>
						<tr>
							<th>设备名称</th>
							<th>设备序列号</th>
							<th>MAC地址</th>
							<th>设备厂商</th>
							<th>设备类型</th>
							<th>链接类型</th>
							<th>设备状态</th>
							<th>设备出库时间</th>
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
	<div class="normal_form">
		<div style="margin-left: 10px; margin-bottom: 10px; color:red;" id="importInfo"></div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>设备名称</th>
						<th>设备序列号</th>
						<th>MAC</th>
						<th>设备厂商</th>
						<th>设备类型</th>
						<th>链接类型</th>
					</tr>
				</thead>
				<tbody id="deviceData"></tbody>
			</table>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/media/js/business/device_info.js"></script>
</html>
