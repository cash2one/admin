<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Insert title here</title> -->
</head>
<body>
	<div class="container">
	<div class="content">
		<p class="toptitle">首页管理 > 孕期百科 > 模块与标签</p>
		<div class="panel top-text">
			<p>孕期百科是公司自建内容的孕期知识库。每一篇百科内容匹配一个标签。每一个标签属于一个内容模块。</p>
		</div>
		<div class="contentdata">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum">${classCount}</p>
				<p class="datatitle">当前总内容模块数</p>
			</div>
			<div class="datalist margin-r float-l greenback">
				<p class="datanum">${typeCount}</p>
				<p class="datatitle">总内容标签数</p>
			</div>
			<div class="clearfix"></div>
		</div>

		<div class="tab-but">
			<ul class="tab-ul">
				<a href="${pageContext.request.contextPath}/encyclopedia/forwardQuestionClassOrTypeList?type=questionClass" class="grey"><li class='<c:if test="${type=='questionClass'}">tab_active</c:if>'>模块管理</li></a>
				<a href="${pageContext.request.contextPath}/encyclopedia/forwardQuestionClassOrTypeList?type=questionType" class="grey"><li class='<c:if test="${type=='questionType'}">tab_active</c:if>'>标签管理</li></a>
			</ul>
		</div>

		<div class="content-search-top">
			<input class="shortinput float-l" id="keywords" placeholder="输入标签名关键词" />
			<button class="search-icon float-l" id="searchBtn"></button>
			<div class="float-r">
				选择所属模块：
				<label class="label-r select_shelter">
					<select id="questionClass" autocomplete="off">
						<option value="0">显示所有</option>
						<c:forEach items="${classList}" var="em">
							<option value="${em.id}">${em.name}</option>
						</c:forEach>
					</select>
				</label>
				<button class="red_btn_big addOrEditQuestionType" type="add" style="+padding: 8px 0;_padding: 8px 0;">+ 新增标签</button>
			</div>
		</div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>序号</th>
						<th>标签名称</th>
						<th>所属模块</th>
						<th>是否公开</th>
						<th>创建时间</th>
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
    				<label class="label">标签名称</label>
    				<input type="text" class="shortinput" id="typeName"/>
    			</li>
    			<li>
    				<label class="label">所属模块</label>
    				<label class="label labradio">
    					<select id="questionClass1">
    					</select>
    				</label>
    			</li>
    			<li>
    				<label class="label">标签内容</label>
    				<label class="label labradio select_shelter">
    					<select id="isVisible">
    						<option value="0">公开</option>
    						<option value="1">隐藏</option>
    					</select>
    				</label>
    			</li>
    		</ul>
    	</form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/encyclopedia/question_type.js"></script>
</body>
</html>