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
		<p class="toptitle">首页管理 > 孕期百科 > 内容库管理</p>
		<div class="content-search-top">
			<button class="red_btn_big addOrEditQuestion" type="add" style="+padding: 8px 0;_padding: 8px 0;">+ 新增内容</button>
			<div class="float-r">
				<input class="shortinput float-l" placeholder="输入标题关键词" id="keywords" style="width: 250px; margin-right:4px;"/>
				<label class="label-r select_shelter">
					<select id="questionClass" autocomplete="off">
						<option value="0">显示所有模块</option>
						<c:forEach items="${classList}" var="em">
							<option value="${em.id}">${em.name}</option>
						</c:forEach>
						
					</select>
					<select id="questionType">
						
					</select>
				</label>
				<button class="search-icon" id="searchBtn"></button>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>序号</th>
						<th>所属标签</th>
						<th>标题</th>
						<th>url</th>
						<th>状态</th>
						<th>创建时间</th>
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

<!--------------------  弹出层     ---------------------- --->
     <div class="normal_form">
    	<form>
    		<ul>
    			<li>
    				<label class="label">标题</label>
    				<input type="text" class="longinput" id="title" style="width: 75%;"/>
    			</li>
    			<li>
    				<label class="label">选择模块</label>
    				<label class="label labradio">
    					<select id="questionClass1">
    						
    					</select>
    				</label>
    			</li>
    			<li>
    				<label class="label">所属标签</label>
    				<label class="label labradio select_shelter">
    					<select id="questionType1">
    					
    					</select>
    				</label>
    			</li>
    			<li>
    				<label class="label label-t">内容 </label>
    				<textarea class="pop_textarea" id="content" name="content" style="padding:0;display:inline-block;"></textarea>
    			</li>
    			<li>
    				<label class="label label-t">内容概要 </label>
    				<textarea class="pop_textarea" id="introduction"></textarea>
    			</li>
    			<li>
    				<label class="label">主题图</label>
    				<!-- <input type="text" class="shortinput" id="imgUrl" readonly="readonly"/>
    				<input type='file' name='file' id="imageUrl" value="" class='layui-upload-file'> -->
    				<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="imgUrl" class="orangeUploder" />
						</div>
					</label>
    				<!-- <button class="red_btn_big">选择图片</button> -->
    				<p class="labtext red">* 每一篇百科需配一张主题图</p>
    			</li>
    			<li>
    				<label class="label">是否公开</label>
    				<label class="label labradio select_shelter">
    					<select id="status">
    						<option value="0">公开</option>
    						<option value="1">隐藏</option>
    					</select>
    				</label>
    			</li>
    		</ul>
    	</form>
	</div>
	
	
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/encyclopedia/question.js"></script>
</body>
</html>