<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>医院管理 > 医院维护</title>
    <jsp:include page="../../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	<div class="content">
		<p class="toptitle">医院维护 > 医院列表</p>
		<button class="float-r red_btn_big addHospital" type="add" style="+padding: 8px 0;_padding: 8px 0;">+ 添加新医院</button>
		<div class="tab-but">
			<ul class="tab-ul">
				<a href="${pageContext.request.contextPath}/hospital/hospitalList/hospitalList" class="grey"><li class='tab_active' style="width: 15%;">未开通网络医院<br>（<span class="mok"></span>家）</li></a>
				<a href="${pageContext.request.contextPath}/hospital/hospitalList/hospitalOpenedList" class="grey"><li style="width: 15%;">已开通网络医院<br>（<span class="moj"></span>家）</li></a>
			</ul>
		</div>
		<div class="content-search-top">
				<div class="float-r">
					<label class="label-r select_shelter">
	                    <select id="province">
	                        <option value="0">--- 请选择省份 ---</option>
							<c:forEach items="${proList }" var="pro">
								<option value="${pro.id }" <c:if test="${pro.id == proId }">selected</c:if> >${pro.province_name }</option>
							</c:forEach>
	                    </select>
	                </label>
	                <label class="label-r select_shelter">
	                    <select id="city">
	                        <option value="0">--- 请选择城市 ---</option>
	                    </select>
	                </label>
					<input class="shortinput" id="keywords" value="" placeholder="请输入医院名称" style="width: 200px;margin-right:4px;"/> 
					<button class="search-icon" id="searchItem" style="margin-top:-5px"></button>
				</div>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr >
						<th>序    号</th>
						<th>医院名称</th>
						<th>医院图片</th>
						<th>省    份</th>
						<th>城    市</th>
						<th>添加时间</th>
						<th>操    作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
		<div id="pages" align="right"></div>
	</div>
	<!-- </form> -->
</div>
<!-- 添加或编辑弹框 -->
<div class="normal_form addOrEdit">
   	<form class="tanks">
   		<ul>
   			<li>
   				<label class="label">医院名称<span class="red">*</span></label>
   				<input type="text" placeholder="请输入医院全称" class="longinput" style="width: 75%;" id="names"/>
   				<!-- <span class="errorMsg" style="color:red;font-size:14px;margin-left:140px;"></span> -->
   				<p class="thisyy"></p>
   			</li>
   			<li>
   				<label class="label" style="margin-top: -60px;">医院简介<span class="red">*</span></label>
   				<textarea rows="3" cols="87" maxlength="300" style="width:75%" placeholder="请输入医院简介，不少于24字，不超过300字。" name="intro" id="introduction" ></textarea>
   				<p class="thisjj"></p>
   			</li>
   			<li>
				<label class="label">医院照片</label>
    				<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="uploadfile" class="orangeUploder" />
						</div>
					</label>
   				<!-- <p class="labtext red">* 尺寸建议640*300</p> -->
   			</li>
   			<li>
   				<label class="label label-t">所属省份 <span class="red">*</span></label>
   				<label class="label labradio">
   					<!-- <select id="channels">
   						
   					</select> -->
   					<select id="province2">
	                        <option value="0">--- 请选择省份 ---</option>
							<c:forEach items="${proList }" var="pro">
								<option value="${pro.id }" <c:if test="${pro.id == proId }">selected</c:if> >${pro.province_name }</option>
							</c:forEach>
	                </select>
   				</label>
   				<p class="thissf"></p>
   			</li>
   			<li>
   				<label class="label label-t">所在城市 <span class="red">*</span></label>
   				<label class="label labradio">
   					<!-- <select id="city">
   						
   					</select> -->
   					<select id="city2">
	                        <option value="0">--- 请选择城市 ---</option>
	                   </select>
   				</label>
   				<p class="thiscs"></p>
   			</li>
   			<li>
   				<label class="label label-t">详细地址 <span class="red">*</span></label>
   				<textarea class="pop_textarea" placeholder="请输入医院的详细地址，不少于6个字" name="" id="address" style="padding:0;display:inline-block;"></textarea>
   				<p class="thisdz"></p>
   			</li>
   			<li>
   				<label class="label" style="margin-top:-140px">科室<span class="red">*</span></label>
   				<div id="pregnantStagehtml" style="display: inline-block;">
   					<ul id="majorId" style="width:680px;margin-top: -6px;overflow:hidden;">
   						
   					</ul>
   				</div>
   				<p class="thisks"></p>
   			</li>
   		</ul>
   	</form>
</div>
<!--------------------  弹出层     ---------------------- --->
<div class="normal_form2">
	<div class="norconter">
		<ul>
			<li><label>医院用户名：</label><input type="text" id="name" value="" maxlength="20" placeholder="设置医院用户名，1-20个英文或数字" class="inpform"></li>
			<!-- <li><label>昵称：</label><input type="text" value="" id="nname" placeholder="请输入昵称" class="inpform"></li>
			<li><label>手机号码：</label><input type="text" value="" id="phone" placeholder="请输入手机号码" class="inpform"></li> -->
			<li><label>登录密码：</label><input type="password" value="" id="passwordNamea" placeholder="设置密码，1-16个英文字母或数字" class="inpform"></li>
			<li><label>确认密码：</label><input type="password" value="" id="passwordNameb" placeholder="确认医院登录密码" class="inpform"></li>
		</ul>
		<p class="pthis" style="color:red;padding:10px  0  0 140px"></p>
	</div>
</div>
</body>
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/hospital/hospital/hospitalNotOpenedList.js"></script>
</html>
