<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Insert title here</title> -->
<jsp:include page="../common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/zui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/orange.css">
<style type="text/css">
	.layui-layer{
		z-index:22!important;
	}
	.layui-layer-shade{
		z-index:2!important;
	}
	/* .layui-layer-btn1{background:#DC6666!important; color:#fff!important; border: 1px solid #DC6666!important;} */
</style>
</head>
<body>
	<div class="container">
	<div class="content">
		<p class="toptitle">首页管理 > 资讯订阅 > 文章管理</p>
		<div class="tab-but">
			<ul class="tab-ul">
				<a href="javascript:void(0);" type="yes" class="grey isPublish isPublishYes"><li class="tab_active">已发布</li></a>
				<a href="javascript:void(0);" type="no" class="grey isPublish isPublishNo"><li >未发布</li></a>
				<input type="hidden" id="isPublish" value="${isPublish}"/>
			</ul>
		</div>
		<div class="content-search-top">
			<button class="red_btn_big addOrEditNewsInformation" type="add" style="+padding: 8px 0;_padding: 8px 0;">+ 新增文章</button>
			
			<div class="float-r">
				<input class="shortinput float-l" id="keywords" placeholder="输入标题关键词" style="width: 250px;margin-right:4px;"/>
				<label class="label-r select_shelter">
					<select id="chanels" autocomplete="off">
						<option value="0">显示所有频道</option>
						<c:forEach items="${newsChanels}" var="chanel">
							<option value="${chanel.id}">${chanel.chanelName}</option>
						</c:forEach>
					</select>
					<select id="periods" autocomplete="off">
						<option value="0">显示全部目标群体</option>
						<c:forEach items="${pregnantStage}" var="stage">
							<option value="${stage.type}">${stage.desc}</option>
						</c:forEach>
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
						<th>文章标题</th>
						<th>所属频道</th>
						<th>目标读者</th>
						<th>题图</th>
						<th>总阅读量</th>
						<th>昨日阅读量</th>
						<th>上周阅读量</th>
						<th>上月阅读量</th>
						<th>分享数</th>
						<th>评论数</th>
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

<!--------------------  弹出层     ---------------------- --->
	<div class="pop_none pop_talking" style="margin: 20px;">
		<div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>评论内容</th>
						<th>评论时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="commentsBody">
				</tbody>
			</table>	
		</div>	
	</div>
	
	<!-- 添加或编辑弹框 -->
	<div class="normal_form addOrEdit">
    	<form>
    		<ul>
    			<li>
    				<label class="label">标题<span class="red">*</span></label>
    				<input type="text" class="longinput" style="width: 75%;" id="title"/>
    				<span class="errorMsg" style="color:red;font-size:14px;margin-left:140px;"></span>
    			</li>
    			<li>
    				<label class="label">选择频道<span class="red">*</span></label>
    				<label class="label labradio">
    					<select id="channels">
    						
    					</select>
    				</label>
    			</li>
    			<li>
    				<label class="label">作者</label>
    				<input type="text" class="shortinput" id="sourceFrom"/>
    			</li>
    			<li>
    				<label class="label">作者头像</label>
    				<!-- <input type="text" class="shortinput" id="fromLogoUrl" readonly="readonly"/>
    				<input type='file' name='file1' id="logoUrl" class='layui-upload-file'> -->
    				<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="logoUrl" class="orangeUploder" />
						</div>
					</label>
    				<p class="labtext red">* 尺寸建议80*80</p>
    			</li>
    			<li>
    				<label class="label label-t">简介 <span class="red">*</span></label>
    				<input type="text" class="shortinput" id="introduct" style="width: 75%;"/>
    				<p class="labtext red">* 简介内容建议至少24个字</p>
    			</li>
    			<li>
    				<label class="label label-t">内容 <span class="red">*</span></label>
    				<textarea class="pop_textarea" name="content" id="content" style="padding:0;display:inline-block;"></textarea>
    			</li>
    			<li>
    				<label class="label">主题图<span class="red">*</span></label>
    				<!-- <input type="text" class="shortinput" id="imageUrl" readonly="readonly"/>
    				<input type='file' name='file2' id="imgUrl" value="" class='layui-upload-file'> -->
    				<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="imgUrl" class="orangeUploder" />
						</div>
					</label>
    				<p class="labtext red">* 尺寸建议640*300</p>
    			</li>
    			<li>
    				<label class="label">目标用户<span class="red">*</span></label>
    				<div id="pregnantStagehtml" style="display: inline-block;">
    				</div>
    				<p class="labtext red">* 目标用户必须设置一个选项。全选 是指天使医生App的全部用户</p>
    			</li>
    		</ul>
    	</form>
	</div>
	
	<!-- 推送消息通知弹出框 -->
	<div class="normal_form push_message">
    	<form>
    		<ul>
    			<li>
    				<label class="label">标题<span class="red">*</span></label>
    				<input type="text" class="longinput" id="titles" style="width:70%;"/>
    				<span class="errorMsg" style="color:red;font-size:14px;margin-left:140px;"></span>
    			</li>
    			<li>
    				<label class="label">描述<span class="red">*</span></label>
    				<input type="text" class="shortinput" id="descriptions" style="width:70%;"/>
    				<span class="errorMsg" style="color:red;font-size:14px;margin-left:140px;"></span>
    			</li>
    		</ul>
    	</form>
	</div>
	
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/information/news_information.js"></script>
</body>
</html>