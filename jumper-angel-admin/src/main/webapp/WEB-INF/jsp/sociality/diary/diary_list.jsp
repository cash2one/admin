<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>健康指导-宝宝发育变化</title>
    <jsp:include page="../../common/head.jsp"/>
</head>
<body>
	<div class="content">
		<p class="toptitle">社交管理 > 宝妈日记 > 日记管理</p>
		
		<div class="content-search-top" >
			<p class="top-text float-l" style="margin: 0; padding-left:15px ;">宝妈日记是一项用户私人属性的功能，可以根据用户名查找对应用户的日记。</p>
			<button class="search-icon float-r search_data"></button>
			<input class="shortinput float-r" placeholder="输入用户名" style="width: 230px;" />
		</div>
		<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum diary_count">0</p>
				<p class="datatitle">当前日记总数</p>
			</div>
			<div class="datalist margin-r float-l greenback">
				<p class="datanum lately_count">0</p>
				<p class="datatitle">近30天新增日记数</p>
			</div>
			<div class="datalist float-l blueback">
				<p class="datanum user_count">0</p>
				<p class="datatitle">当前日记用户数</p>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<p class="diarytitle">日记数量最多的5名用户情况</p>
		<div class="panel margin-b">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户名</th>
						<th>日记数量</th>
						<th>最近发表时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="user_diary_list"></tbody>
			</table>
			<div id="user_pages"></div>
		</div>
		<p class="diarytitle">最新发布的5条日记</p>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户名</th>
						<th>发表内容</th>
						<th>发表时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="diary_list"></tbody>
			</table>
			<div id="diary_pages"></div>
		</div>
	</div>
	<div class="normal_form">
    	<form>
    		<ul>
    			<li>
    				<label class="label">用户名 </label><input type="text" class="shortinput user_name"/>
    			</li>
    			<li>
    				<label class="label">发布时间 </label><input type="text" class="shortinput add_time"/>
    			</li>
    			<li>
    				<label class="label label-t">日记内容 </label><textarea class="pop_textarea content"></textarea>
    			</li>
    			<li>
    				<label class="label label-t">配图 </label>
    				<span class="imgs"></span>
    			</li>
    			<li>
    				<label class="label label-t">日记状态 </label><span class="is_del"></span>
    			</li>
    		</ul>
		</form>
	</div>
</body>
	<script src="${pageContext.request.contextPath}/media/js/sociality/diary/diray_list.js"></script>
</html>