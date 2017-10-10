<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>其它-回收站</title>
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
	<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 其它 > 回收站</p>
		
		<div class="content-search-top ">
            <div class="float-r">
            <div class="searchGroup">
                <input class="shortinput float-l borRightNone" placeholder="搜索用户昵称、话题标题" id="keyword"/>
              <!--   <input class="shortinput float-r" placeholder="搜索评论内容、用户昵称" id="like_comment_content" style="width: 230px;" /> -->
                <button class="search-icon float-r" id="searchComment"></button>
            </div>
            </div>
			<div class="clearfix"></div>
		</div>
		<div class="tab-but">
			<ul class="tab-ul">
				<a href="${pageContext.request.contextPath}/sociality/recycleManager/recycleDebatepost" class="grey"><li>帖子回收站</li></a>
				<a href="${pageContext.request.contextPath}/sociality/recycleManager/recycleComment" class="grey"><li class='tab_active'>评论回收站</li></a>
			</ul>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>用户昵称</th>
						<th>评论内容</th>
						<th>回复对象</th>
						<th>帖子</th>
						<th>评论时间</th>
						<th>删除时间</th>
						<th>删除人员</th>
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
	<script src="${pageContext.request.contextPath}/assets/js/iscroll-zoom.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/hammer.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/lrz.all.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.photoClip.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/sociality/recycle/recycleComment.js"></script>
</body>
</html>