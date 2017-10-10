<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>健康指导-宝宝发育变化</title>
    <jsp:include page="../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
	
		<div class="content">
			<p class="toptitle">首页管理 > 健康指导 > 孕期健康指导</p>
			<div class="panel top-text">
				<p>宝宝发育变化是显示在首页孕周日历下的指导内容。备孕期对应1条健康指导文案；“孕0周”至“孕40周”分别对应孕期的，已生育状态与“孕40周”内容保持一致。</p>
			</div>
			<div class="content-search-top">
				<div class="float-r">
					<button class="red_btn_big" onClick="ShowDiv(1, -1)">+ 新增</button>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="panel">
				<table class="table"> 
					<thead>
						<tr>
							<th>时间</th>
							<th>身长 (cm)</th>
							<th>顶臀长 (cm)</th>
							<th>体重 (g)</th>
							<th>双顶径 (cm)</th>
							<th>本周宝宝发育概述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody"></tbody>
				</table>
			</div>
			<div id="page" align="right"></div>
		</div>
	</div>

	<!--------------------  弹出层     ---------------------- --->
    <div class="normal_form">
    	<form>
    		<label class="labtext red" id="error" style="display: none;"></label>
    		<ul>
    			<li>
    				<label class="label">孕期状态</label>
    				<label class="label labradio"><input type="radio" name="Pregnancystate" checked="checked" id="pregnancy2" value="2" />备孕期</label>
    				<label class="label"><input type="radio" name="Pregnancystate" id="pregnancy0" value="0" />怀孕期</label>
    				<label class="label"><input type="radio" name="Pregnancystate" disabled="disabled" id="pregnancy1" value="1" />已有宝宝</label>
    			</li>
    			<li>
    				<label class="label">孕周时间 </label>
    				孕 <input type="number" class="pagejump" id="week" /> 周 
    				<p class="labtext red">*选择备孕期时，可不填</p>
    			</li>
    			<li>
    				<label class="label label-t">生长数据 </label>
    				<label class="label labradio">
	    				<div class="lab_right">
	    					<label class="label labradio"> 身长<input type="text" class="labshortnum" id="fetalHeight" /> cm</label>
	    					<label class="label labradio"> 顶臀长<input type="text" class="labshortnum" id="bothArm" /> cm</label>
	    					<label class="label labradio"> 体重<input type="text" class="labshortnum" id="fetalWeight" /> g</label>
	    					<label class="label labradio"> 双顶径<input type="text" class="labshortnum" id="bothNeck" /> cm</label>
	    				</div>
   				 	</label>
    			</li>
    			<li>
    				<label class="label label-t">宝宝发育状况 </label>
    				<textarea class="pop_textarea" id="weekDescription" maxlength="200" ></textarea>
    				<p class="labtext red">* 建议文案字数控制在60-200之间</p>
    			</li>
    		</ul>
		</form>
	</div>
</body>
<script src="${pageContext.request.contextPath}/media/js/health/baby_growth.js"></script>
</html>