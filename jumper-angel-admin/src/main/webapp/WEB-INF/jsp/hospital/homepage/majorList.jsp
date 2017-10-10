<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>科室维护 > 科室列表</title>
    <jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div class="content">
		<p class="toptitle">科室维护 > 科室列表</p>
		<button class="float-r red_btn_big addHospital" data-type="0" style="+padding: 8px 0;_padding: 8px 0;">+ 添加新科室</button>
		<div class="content-search-top">
			<div class="searchGroup">
				<input class="shortinput float-l" id="keywords" value="" placeholder="输入科室名称"/> 
					<label class="float-r" > </label>
				<button class="search-icon float-l" id="searchItem"></button>
			</div>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr >
						<th>序号</th>
						<th>科室名称</th>
						<th>科室图片</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
		<div class="footer-page">
			<div id="page" align="right"></div>
		</div>
	</div>
</div>
<!--------------------  弹出层     ---------------------- --->
<!-- <div class="normal_form">
	<table class="con_table">
		<tbody>
			<tbody id="msg_content"></tbody>
		</tbody>
	</table>
</div> -->

<!--------------------  弹出层     ---------------------- --->
<div class="normal_form2">
	<div class="norconter">
		<ul>
			<li><label>科室名称：</label><input type="text" id="name" value="" maxlength="15" placeholder="请输入科室名称" class="inpform"></li>
			<li>
				<label class="label">科室图片:</label>
    				<label class="label labradio" style="width:105px;text-align:center;border: 1px solid #ccc">
						<div class="lab_right" align="left">
							<input type="hidden" id="uploadfile" class="orangeUploder" />
						</div>
				</label>
			</li>
		</ul>
		<p class="pthis" style="color:red;padding:10px  0  0 140px"></p>
	</div>
</div>

</body>
<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
<script src="${pageContext.request.contextPath}/media/js/hospital/hospital/majorList.js"></script>
</html>
