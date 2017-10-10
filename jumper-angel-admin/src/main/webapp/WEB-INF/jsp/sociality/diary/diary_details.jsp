<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>宝妈日记 - 日记管理 - 用户日记列表</title>
    <jsp:include page="../../common/head.jsp"/>
</head>
<body>
	<input type="hidden" name="id" class="data_id" value="${id}" />
	<input type="hidden" name="id" class="data_name" value="${name}" />
	<div class="content">
		<p class="toptitle">首页管理 > 宝妈日记 > 日记管理 > 用户日记列表</p>
		<div class="panel margin-t">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户名</th>
						<th>发表内容</th>
						<th>发表时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="user_diary_data"></tbody>
			</table>
		</div>
		<div id="user_diary_pages"></div>
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
	<script src="${pageContext.request.contextPath}/media/js/sociality/diary/diray_info.js"></script>
</html>