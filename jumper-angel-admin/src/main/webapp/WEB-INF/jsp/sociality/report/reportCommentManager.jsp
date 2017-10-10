<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>其他-举报中心-被举报的评论</title>
    <jsp:include page="../../common/head.jsp"/>
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
            width: 100px;
            position: absolute;
            left: -100px;
            border: none;
            height: 36px;
            top: 2px;
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
        body tr.ReadOnly td{color: #aaaaaa; background: #f5f5f5}
        body table a{color: #4595e6; transition: .2s;}
        body table a:active{color: #2ebbe6}
        /*弹窗样式*/
        .tab-but2 .tab-ul2 a {
            display: inline-block;
            width: 120px;
            text-align: center;
            cursor: pointer;
            padding: 10px 0;
            font-weight: bold;
            color: #333;
        }
        body .tab-ul2 .tab_active a{color: #e65545;}
         .content-search-top {
        	width:390px;
        }
        body select.zhuangtai{
        	margin-top:-1px!important;
        }
    </style>
</head>
<body>
<div class="container">
	<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<%-- <jsp:include page="../../common/left.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 其他 > 举报中心</p>
        <div class="tab-but2">
            <ul class="tab-ul2">
                <li><a href="${pageContext.request.contextPath}/sociality/reprot/debatepost/index">被举报的话题</a></li>
                <li class="tab_active"><a href="${pageContext.request.contextPath}/sociality/reprot/comment/index">被举报的评论</a></li>
                <li><a href="${pageContext.request.contextPath}/sociality/reprot/user/index">被举报的用户</a></li>
            </ul>
        </div>
        <div style="overflow: hidden;">
            <div class="content-search-top float-l" style="width: 398px!important;">
                <input class="shortinput float-l borRightNone" placeholder="输入标题关键词" id="keyword"/>
                <div class="float-r">
                    <label class="float-l">
                        <select class="bortNone zhuangtai" id="dataStatus">
                            <option value="">全部状态</option>
                            <option value="1">审核中</option>
                            <option value="2">隐藏</option>
                        </select>
                    </label>
                    <button class="search-icon float-l" id="searchReportComment"></button>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="panel">
			<table class="table">
				<thead>
					<tr>
						<th>评论内容</th>
						<th>话题标题</th>
						<th>评论人</th>
						<th>举报次数</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
                
				</tbody>
			</table>
		</div>
		<div id="page">
			
		</div>
	</div>
</div>
<!-- 显示帖子详情 -->
<div class="showBox">
    <div class="panel top-text">
        <p id="debatepost_content"></p>
    </div>
    <div class="SBimgBox">
      	
    </div>
</div>
<!-- 显示评论详情 -->
<div class="showBox2">
    <div class="panel">
        <table class="table" id="showTable">
            <thead>
                <tr>
                    <th>举报时间</th>
                    <th>举报原因</th>
                    <th>举报人</th>
                </tr>
            </thead>
            <tbody id="reportTbody">
               
            </tbody>
        </table>
    </div>
</div>
<script src="${pageContext.request.contextPath}/media/js/sociality/report/reportComment.js"></script>
</body>
</html>