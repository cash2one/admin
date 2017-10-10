<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>Insert title here</title> -->
<jsp:include page="../common/head.jsp"></jsp:include>
</head>
<body>
	<div class="container">
	<div class="content">
		<p class="toptitle">首页管理 > 孕期管理 > 产检项目管理</p>
		<div class="content-search-top">
			<input class="shortinput float-l" id="keywords" value="" placeholder="请输入产检项目关键词"/>
			<button class="search-icon float-l" id="searchItem"></button>
			<div class="float-r">
				选择产检次数：
				<label class="label-r select_shelter">
					<select id="changeItem" autocomplete="off">
						<option value="0">显示所有</option>
						<c:forEach items="${examList}" var="exam">
							<option value="${exam.id}">第${exam.examinationNumbers}次产检</option>
						</c:forEach>
					</select>
				</label>
				<button class="red_btn_big addOrEditExamItem" type="add" style="+:padding8px 0 ;_padding:8px 0 ;">+ 新增产检项目</button>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>所属产检次数</th>
						<th>项目名称</th>
						<th>详情描述</th>
						<th>添加时间</th>
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
    				<input type="hidden" id="itemId" value="" />
    				<label class="label">选择产检次数</label>
    				<label class="label select_shelter">
    					<select id="examInfoId">
    					
    					</select>
    				</label>
    			</li>
    			<li>
    				<label class="label">项目名称</label>
    				<input type="text" class="shortinput" id="itemName"/><br>
    				<span class="errorMsg" style="color:red;font-size:14px;margin-left:140px;"></span>
    				<p class="labtext red">* 同一次产检下项目名称不能重复</p>
    			</li>
    			<li>
    				<label class="label label-t">详情描述 </label>
    				<textarea class="pop_textarea" id="itemContent" style="resize: none;"></textarea>
    				<span class="errorMsg" style="color:red;font-size:14px;margin-left:10px;"></span>
    				<p class="labtext red">* 本页所有内容均为必填项</p>
    			</li>
    		</ul>
    	</form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnancy/pregnancy_item.js"></script>
</body>
</html>