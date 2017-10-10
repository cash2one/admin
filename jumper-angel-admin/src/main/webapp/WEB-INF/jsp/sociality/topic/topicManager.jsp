<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../common/head.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>话题组-小组管理</title>
    <style>
        label{
            width: inherit;
            margin-right: -1px;
            position: relative;
        }
        body .bortNone{
            border:none;
        }
        body select.zhuangtai{
            left: -163px;
        }
        input{outline:none}
        select{outline:none}
        table .tableTime li{
            line-height: 20px;
        }
        table .tableTime li:last-child{
            color: #aaa;
            font-size: 14px;
        }
        .labtext{margin-left: 120px}
        #clipArea {
			margin: 20px;
			height: 360px;
		}
		/*#file,
		#clipBtn {
			margin: 20px;
		}*/
		#view {
			width: 250px;
			height: 100px;
			display: block;
		}
		.datalist{
			width:23.5%!important;
		}
    </style>
</head>
<body>
<div class="container">
<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 话题组 > 小组管理</p>
		
		<!-- 统计标签  start -->
		<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum top_count">0</p>
				<p class="datatitle">话题组总数</p>
			</div>
			<div class="datalist float-l margin-r blueback">
				<p class="datanum debatepost_count">0</p>
				<p class="datatitle">帖子总数</p>
			</div>
			<div class="datalist margin-r float-l redback">
				<p class="datanum increase_count">0</p>
				<p class="datatitle">今日新增帖子总数</p>
			</div>
			<div class="datalist float-l greenback">
				<p class="datanum user_count">0</p>
				<p class="datatitle">话题组总人数</p>
			</div>
			
			<div class="clearfix"></div>
		</div>
		<!-- 统计标签   end -->
		
		<div class="content-search-top">
            <div class="searchGroup">
                <input class="shortinput float-l borRightNone" placeholder="输入标题关键词" id="like_topic_name"/>
                <label class="float-r">
                    <select class="bortNone zhuangtai" id="is_delete" style="left:-168px;">
                        <option value="">全部状态</option>
                        <option value="0">正常</option>
                        <option value="1">禁用中</option>
                    </select>
                </label>
                <button class="search-icon float-r" id="searchTopic"></button>
            </div>
            <button class="red_btn_big float-r" onclick="addTopic()">+ 新建话题组</button>
			<div class="clearfix"></div>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>位置</th>
						<th>话题组名</th>
						<th>简介</th>
						<th>创建人</th>
						<th>创建时间</th>
						<th>人数</th>
						<th>话题数</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody  id="tbody">
					
				</tbody>
			</table>
		</div>
		<div id="page">
		
		</div>
	</div>
</div>
<!--------------------  弹出层     ---------------------- --->
<div class="normal_form2">
    <ul>
        <li>
            <label class="label">话题组名称</label>
            <input type="text" class="longinput" id="topic_name" placeholder="字符长度为12字符"/>
            <p class="labtext red" id="topic_name_error">超出长度限制!</p>
        </li>
        <li>
            <label class="label label-t">话题组主题图</label>
            <div style="padding-left: 139px;height:100px;margin-top: -15px;margin-bottom:30px;position:relative;">
            	<img src="" id="view"  style="" alt="" />
            	<br />
            	<input   type="file" id="file" class="red_btn_small2" style="position:absolute;left:140px;top:107px;width: 100px;opacity:0;height: 26px;z-index:9;" value="上传图片"/>
            	<button  class="red_btn_small2" style="position:absolute;left:140px;top:107px;width: 100px;height: 26px;z-index: 1;" >上传图片</button>
            	<img src="${pageContext.request.contextPath}/assets/images/delete.gif"  class="imghover" style="width:11px;height: 11px;position:absolute;left:397px;top:1px;z-index: 9;cursor:pointer" alt="点击删除图片" />
            </div>
            <p class="labtext red" style="padding-top:10px" id="thematic_img_error">最多1张（图片比例：640*300）</p>
        </li>
        <li style="margin-top: 20px;">
            <label class="label">话题组简介</label>
            <textarea class="pop_textarea" style="vertical-align: top;margin-top: -4px;" placeholder="最多可输入200个字" id="topic_profile"></textarea>
            <p class="labtext red" id="topic_profile_error">超出长度限制!</p>
        </li>
    </ul>
    <input type="hidden" id="thematic_img" value=""/>
    <input type="hidden" id="topic_id" value=""/>
</div>
<div class="normal_form3" style="display: none;">
    	<div id="clipArea"></div>
		<!--<input type="file" id="file">-->
		<!--<button id="clipBtn">截取</button>-->
</div>
	<script src="${pageContext.request.contextPath}/assets/js/iscroll-zoom.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/hammer.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/lrz.all.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.photoClip.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/sociality/topic/topic.js"></script>
</body>
</html>