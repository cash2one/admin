<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>话题组-评论管理</title>
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
<body>
<div class="container">
<%-- 	<jsp:include page="../../common/top.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 话题组 > 评论管理</p>
		
		<div class="content-search-top ">
            <div class="float-r">
            <div class="searchGroup">
                <input class="shortinput float-l borRightNone" placeholder="搜索评论内容、用户昵称" id="keyword"/>
              <!--   <input class="shortinput float-r" placeholder="搜索评论内容、用户昵称" id="like_comment_content" style="width: 230px;" /> -->
                <button class="search-icon float-r" id="searchComment"></button>
            </div>
            </div>
			<div class="clearfix"></div>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>评论时间</th>
						<th>用户昵称</th>
						<th>评论内容</th>
						<th>回复对象</th>
						<th>帖子</th>
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
<div class="showBox">
    <div class="panel top-text">
        <p id="debatepost_content"></p>
    </div>
    <div class="SBimgBox">
    </div>
</div>
<%-- <div class="normal_form2">
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
</div> --%>
	<script src="${pageContext.request.contextPath}/assets/js/iscroll-zoom.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/hammer.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/lrz.all.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.photoClip.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/sociality/comment/comment.js"></script>
</body>
</html>