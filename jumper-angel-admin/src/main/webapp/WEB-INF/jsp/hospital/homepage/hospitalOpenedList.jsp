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
		<button class="float-r red_btn_big addOrEditNewsInformation addHospital"  data-type="add" style="+padding: 8px 0;_padding: 8px 0;">+ 添加新医院</button>
		<div class="tab-but">
			<ul class="tab-ul">
				<a href="${pageContext.request.contextPath}/hospital/hospitalList/hospitalList" class="grey"><li style="width: 15%;">未开通网络医院<br>（<span class="mok"></span>家）</li></a>
				<a href="${pageContext.request.contextPath}/hospital/hospitalList/hospitalOpenedList" class="grey"><li class='tab_active' style="width: 15%;">已开通网络医院<br>（<span class="moj"></span>家）</li></a>
			</ul>
		</div>
		<div class="content-search-top">
				<div class="float-r">
						<!-- <label class="float-r" > </label> -->
					<label class="label-r select_shelter">
						<select id="chanels" autocomplete="off">
							<option value="0">选择服务</option>
							
						</select>
						<select id="province">
	                        <option value="0">--- 请选择省份 ---</option>
							<c:forEach items="${proList }" var="pro">
								<option value="${pro.id }" <c:if test="${pro.id == proId }">selected</c:if> >${pro.province_name }</option>
							</c:forEach>
	                    </select>
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
						<th>医院账号</th>
						<th>已开通服务</th>
						<th>添加时间</th>
						<th>开通时间</th>
						<th>操    作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
		<div id="page" align="right"></div>
	</div>
	<!-- </form> -->
</div>
<!--------------------  弹出层     ---------------------- --->
<div class="normal_form2">
	<div class="norconter">
		<ul>
			<li><label>医院用户名：</label><input type="text" id="name" maxlength="20" value="" placeholder="请设置医院的登录账号" class="inpform"></li>
			<li><label>重设密码：</label><input type="password" value="" id="passwordNamea" placeholder="设置密码，1-16个英文字母或数字" class="inpform"></li>
			<li><label>确认密码：</label><input type="password" value="" id="passwordNameb" placeholder="确认医院登录密码" class="inpform"></li>
			<li><label>账号控制：</label><input style="width:15px;height:16px;margin-top:13px;" name="status"  type="checkbox" value="" id="status" class="">禁用</li>
		</ul>
		<p class="pthis" style="color:red;padding:10px  0  0 140px"></p>
		<div style="font-size:14px;color:#555;padding:25px  68px 0 57px;    box-sizing: border-box;">1. 密码框不变动或为空，表示密码维持原状。若要重设密码，请直接输入新密码<br/>2. 禁用状态下医院管理账号及该账号下生成的子账号将无法登录医院后台。<br/>3. 如果账号解除禁用状态，只解除医院管理员账号的禁用状态。其子账号由管理员在医院后台自行设置是否解除禁用。</div>
	</div>
</div>
<!-- -------------解锁弹窗---------- -->
<div class="normal_form3" style="display:none;">
	<div class="norconter">
		<ul>
			<li><label>医院用户名：</label><input type="text" id="name2" value="" disabled="disabled" maxlength="20" placeholder="请设置医院的登录账号" class="inpform"></li>
			<li class="suodp">该账号因安全风险被锁定，需通过重置密码来解除锁定恢复使用.</li>
			<li><label>重设密码：</label><input type="password" value="" id="passwordName1" placeholder="设置密码，1-16个英文字母或数字" class="inpform"></li>
			<li><label>确认密码：</label><input type="password" value="" id="passwordName2" placeholder="确认医院登录密码" class="inpform"></li>
		</ul>
		
		<p class="pthis2" style="color:red;padding:10px  0  0 140px"></p>
	</div>
</div>
<!-- -------------权限弹窗---------- -->
<div class="normal_form4" style="display:none;">
	<div style="text-align: center;font-size: 18px;padding-top: 40px;">
		<div style="display: inline-block;">胎心监护线上支付:</div>
		<div style="display: inline-block;">
			<input type="radio" style="vertical-align: middle;" id="status_k" name="status_k" value="0"  class=""><span style="margin-right:20px;">关闭</span>
			<input type="radio" style="vertical-align: middle;" id="status_j" name="status_k" value="1"  class=""><span>开通</span>
		</div>
	</div>
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
</body>
<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
<script src="${pageContext.request.contextPath}/media/js/hospital/hospital/hospitalOpenedList.js"></script>
</html>
