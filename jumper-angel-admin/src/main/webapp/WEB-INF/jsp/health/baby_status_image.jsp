<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>健康指导-孕周宝宝状态图</title>
    <jsp:include page="../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	
	<div class="content">
		<p class="toptitle">首页管理 > 健康指导 > 孕期状态图</p>
		<div class="panel top-text" style="margin-bottom: 30px;">
			<p>孕周宝宝状态图是显示在App首页孕周日历中的宝宝图片，孕期共40张，备孕期1张。</p>
			<p>请勿轻易修改！如需更换图片，请联系研发进行切图。上传的尺寸为400*400，主体部分为正圆形，四个边角透明。</p>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>孕周</th>
						<th>图片预览</th>
						<th>图片地址</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
		<div id="page" align="right"></div>
	</div>
</div>
</body>
<script src="${pageContext.request.contextPath}/media/js/health/baby_status_image.js"></script>
</html>