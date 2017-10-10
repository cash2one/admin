<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>播放器-电台内容管理</title>
    <jsp:include page="../common/head.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/zui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/orange.css">
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
</head>
<body>
<div class="container">
	<div class="content">
		<p class="toptitle">首页管理 > 播放器 > 电台内容管理</p>
		<div class="panel top-text">
			<p>App端音乐播放顺序：默认按添加时间的先后顺序进行排列，最新时间的排序靠前。</p>
			<p>老版本系统内还有音乐，但新版本起，主推电台（公司自主制作），本页面只管理电台节目内容。</p>
		</div>
		<div class="content-search-top">
			<div class="float-r">
				<button class="red_btn_big" onClick="ShowDiv(1)">上传节目</button>
			</div>
		</div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>序号</th>
						<th>封面</th>
						<th>标题</th>
						<th>所属栏目</th>
						<th>标签</th>
						<th>时长</th>
						<th>上传时间</th>
						<th>状态</th>
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
	<ul>
		<li>
			<label class="label">标题</label>
			<input type="text" class="shortinput" id="name" /><span style="color:red; margin-left: 5px;" id="titleError"></span>
		</li>
		<li>
			<label class="label">文件</label>
			<input type="text" class="shortinput" id="voiceUrl" disabled="disabled" />
			<input type="file" name="file" class='layui-upload-file' lay-type="file" />
		</li>
		<li>
			<label class="label label-t">设置封面</label>
			<label class="label labradio">
				<div class="lab_right" align="left">
					<input type="hidden" id="coverUrl" class="orangeUploder" />
				</div>
			</label>
			
			<p class="labtext red">* 每个音乐内容都可配一张图片,在播放器处显示。图片尺寸建议80*80</p>
		</li>
		<li>
			<label class="label">所属栏目</label>
			<label class="label">
				<select id="category" style="width: 98px;">
					<option value="3">孕期电台</option>
				</select>
			</label>
		</li>
		<li>
			<label class="label">设置标签</label>
			<label class="label">
				<select style="width: 98px;" id="labelId"></select>
			</label>
			或 
			<label class="label labradio"><input type="text" style="height: 40px;margin-left:15px ;" id="labelName" /></label>
  				<label class="label labradio"><button class="blue_btn_big" onclick="addLabel()">新增标签</button></label>
  				<p class="labtext red">* 栏目是必选项，标签是选填项</p>
  			</li>
  			<li>
			<label class="label">已选标签</label>
			<div id="selectedLabel" style="display: inline-block;"></div>
  			</li>
  			<li>
  				<label class="label">节目状态 </label>
  				<label>
  					<input type="checkbox" id="status" /> 上线
  				</label>
  				<p class="labtext red">* 勾选上线后，用户才能在App接收到本期上传的节目</p>
  			</li>
  		</ul>
</div>
<div id="mysong"></div>
</body>
<script src="${pageContext.request.contextPath}/assets/js/jquery.jmp3.js"></script>
<script src="${pageContext.request.contextPath}/media/js/music/music_player.js"></script>
</html>