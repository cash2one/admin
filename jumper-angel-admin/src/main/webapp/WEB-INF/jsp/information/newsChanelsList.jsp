<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/zui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/orange.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Insert title here</title> -->
</head>
<body>
	<div class="container">
	<div class="content">
		<p class="toptitle">首页管理 > 资讯订阅 > 频道管理</p>
		<div class="panel top-text">
			<p>用户根据频道进行内容订阅。</p>
			<p>除了孕期知识外，所有频道都可以删除。删除后频道下的文章，会归入孕期知识频道。</p>
		</div>
		<div class="content-search-top">
			<div class="float-r">
				<button class="red_btn_big addOrEditNewsChanels" type="add">+ 新增频道</button>
			</div>
		</div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>位置</th>
						<th>频道名称</th>
						<th>频道描述</th>
						<th>标签</th>
						<th>默认订阅</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
										
				</tbody>
			</table>
		</div>
		<div id="pages" align="right"></div>
		
	</div>
</div>

<!--------------------  弹出层     ---------------------- --->
     <div class="normal_form">
    	<form>
    		<ul>
    			<li>
    				<input type="hidden" id="chanelId" value=""/>
    				<label class="label">频道名称</label>
    				<input type="text" id="chanelName" class="shortinput"/>
    			</li>
    			<li>
    				<label class="label label-t">频道描述 </label>
    				<textarea class="pop_textarea" id="chanelDesc" style="resize:none;"></textarea>
    			</li>
    			<li>
    				<label class="label label-t">频道图片 </label>
    				<!-- <input type="text" id="imgUrl" class="shortinput" readonly="readonly"/>
    				<input type='file' name='file1' id="images" class='layui-upload-file'> -->
    				<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="imgUrl" class="orangeUploder" />
						</div>
					</label>
    			</li>
    			<li>
    				<label class="label label-t">默认订阅 </label>
    				<select id="isDefaultSub">
    					<option value="1">是</option>
    					<option value="0">否</option>
    				</select>
    			</li>
    			<li>
    				<label class="label">频道标签</label>
    				<input type="text" class="shortinput new_tab" value="" name="new_tab" id="channel_tab"/>
    				<span class="addIcon addtab" style="cursor: pointer;">+</span>
    			</li>
			</ul>
    				<div class="tab_list" id="chanelLabel">
    					<!--
                        	<span class="blueback pop_tab">
    						孕期运动
    						<a href="" class="pop_tab_delete">×</a>
    					</span>
    					<span class="blueback pop_tab">
    						孕期运动
    						<a href="" class="pop_tab_delete">×</a>
    					</span>
    					<span class="blueback pop_tab">
    						孕期运动
    						<a href="" class="pop_tab_delete">×</a>
    					</span>
                        -->
    				</div>
    	</form>
	</div>
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/information/news_chanels.js"></script>
</body>
</html>