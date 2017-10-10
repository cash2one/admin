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
		<p class="toptitle">首页管理 > 广告管理 > 广告列表</p>
		<div class="content-search-top">
			<input class="shortinput float-l" id="keywords" placeholder="请输入广告描述"/>
			<button class="search-icon float-l" id="searchBtn"></button>
			<div class="float-r">
				<button class="red_btn_big addOrEditAdvertiseInfo" type="add" style="+padding: 8px 0;_padding: 8px 0;">+ 新增广告</button>
			</div>
		</div>
		
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>序号</th>
						<th>描述</th>
						<th>图片</th>
						<th>链接地址</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>
		</div>
		<div id="pages" align="right">
			
		</div>
		
	</div>
</div>

<!--------------------  弹出层     ------------------------->
     <div class="normal_form">
    	<form>
    		<ul>
    			<li>
    				<label class="label">描述</label>
    				<textarea class="pop_textarea" id="description"></textarea>
    			</li>
    			<li>
    				<label class="label">图片</label>
    				<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="imgUrl" class="orangeUploder" />
						</div>
					</label>
    			</li>
    			<li>
    				<label class="label">链接</label>
    				<input type="text" value="" id="webUrl"  class="longinput" style="width: 75%;"/>
    			</li>
    			<li>
    				<label class="label">是否为活动链接</label>
    				<label class="label labradio select_shelter">
    					<select id="isActivity">
    						<option value="1">是</option>
    						<option value="0">否</option>
    					</select>
    					
    				</label>
    			</li>
    		</ul>
    	</form>
	</div>
	
	
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/advertise/advertise.js"></script>
</body>
</html>