<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
		<p class="toptitle">首页管理 > 孕期管理 > 产检管理</p>
		<div class="content-search-top">
			<div class="float-r">
				<button class="red_btn_big addOrEditExaminationInfo" type="add" style="+padding:8px 0 ;_padding:8px 0 ;">+ 新增产检信息 </button>
			</div>
		</div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>次数</th>
						<th>开始周</th>
						<th>结束周</th>
						<th>提醒周</th>
						<th>关爱小提示</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pregAnteExaminationInfoList}" var="examinationInfo">
					<tr id="target">
						<td>${examinationInfo.examinationNumbers}</td>
						<td>${examinationInfo.startWeek}周</td>
						<td>${examinationInfo.endWeek}周</td>
						<td>${examinationInfo.remindWeek}周</td>
						<td class="table-longtext">
							<c:if test="${fn:length(examinationInfo.remind)>20}">
								${fn:substring(examinationInfo.remind,0,20)}...<a href="javascript:void(0);" title="${examinationInfo.remind}" class="blue tips" >[全文]</a>
								<!-- data-toggle="popover" title="${examinationInfo.remind}" data-placement="bottom" -->
							</c:if>
							<c:if test="${fn:length(examinationInfo.remind)<=20}">
								${examinationInfo.remind}
							</c:if>
						</td>
						<td>
							<fmt:formatDate value="${examinationInfo.addTime}" type="both" pattern="yyyy-MM-dd HH:mm"/> 
						</td>
						<td>
							<button class="blue_btn_small addOrEditExaminationInfo" type="edit" infoId="${examinationInfo.id}" 
							examNumbers="${examinationInfo.examinationNumbers}" 
							startWeek="${examinationInfo.startWeek}" endWeek="${examinationInfo.endWeek}" 
							remindWeek="${examinationInfo.remindWeek}" remind="${examinationInfo.remind}">编辑</button>
							<button class="red_btn_small deleteExaminationInfo" infoId="${examinationInfo.id}">删除</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!--------------------  弹出层     ---------------------- --->
    <div class="normal_form">
    	<form>
    		<ul>
    			<input type="hidden" id="infoId" value=""/>
    			<li>
    				<label class="label">产检次数</label>
    				第 <input type="number" class="pagejump" id="examNumbers"/> 次产检<span class="errorMsg" style="color:red;font-size:14px;margin-left:10px;"></span>
    			</li>
    			<li>
    				<label class="label">开始时间</label>
    				孕 <input type="number" class="pagejump" id="startWeek"/> 周<span class="errorMsg" style="color:red;font-size:14px;margin-left:10px;"></span>
    				<!-- <p class="labtext red">* 孕周时间填写范围：0-39周</p> -->
    			</li>
    			<li>
    				<label class="label">结束时间</label>
    				孕 <input type="number" class="pagejump" id="endWeek"/> 周<span class="errorMsg" style="color:red;font-size:14px;margin-left:10px;"></span>
    			</li>
    			<li>
    				<label class="label">提醒时间</label>
    				孕 <input type="number" class="pagejump" id="remindWeek"/> 周<span class="errorMsg" style="color:red;font-size:14px;margin-left:10px;"></span>
    				<p class="labtext red">* 根据这里的时间，会在对应孕周向用户推送产检提醒</p>
    			</li>
    			<li>
    				<label class="label label-t">关爱小提示 </label>
    				<textarea class="pop_textarea" id="remind" style="resize: none;"></textarea>
    				<span class="errorMsg" style="color:red;font-size:14px;margin-left:136px;"></span>
    			</li>
    		</ul>
    	</form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnancy/pregnancy.js"></script>
</body>
</html>