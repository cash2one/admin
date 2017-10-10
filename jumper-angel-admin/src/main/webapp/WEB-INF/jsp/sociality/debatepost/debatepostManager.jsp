<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>话题组-内容管理</title>
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
            left: -104px;
        }
        body button{outline: none;}
        input{outline:none}
        select{outline:none}
        .select_box:focus{
            border:1px solid #e65545;
            -webkit-transition:border linear .2s,-webkit-box-shadow linear .5s;
            -webkit-box-shadow:0 0 2px #e65545;
            -moz-box-shadow: 0 0 2px #e65545;
            box-shadow:0 0 2px #e65545;
        }
        table .tableTime li{
            line-height: 20px;
        }
        table .tableTime li:last-child{
            color: #aaa;
            font-size: 14px;
        }
        body table a{color: #4595e6; transition: .2s;}
        a:active{color: #2ebbe6}
        .shortinput{ width: 400px;padding:0 4px;}
    </style>
</head>
<body>
<div class="container">
	<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 话题组 > 帖子管理</p>
		
		<!-- 统计标签  start -->
		<div id="display" style="display:none;">
			<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
				<div class="datalist margin-r float-l yellowback">
					<p class="datanum currentTopDebatePostCount">0</p>
					<p class="datatitle">帖子数量</p>
				</div>
				<div class="datalist margin-r float-l greenback">
					<p class="datanum currentTopIncreaseDebatePostCount">0</p>
					<p class="datatitle">今日新增帖子数</p>
				</div>
				<div class="datalist float-l blueback">
					<p class="datanum currentTopDebatePostComCount">0</p>
					<p class="datatitle">帖子评论数量</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- 统计标签   end -->
		
        <div style="overflow: hidden;">
            <div class="mod_select float-l content-search-top" style="overflow: visible;">
                <ul>
                    <li>
                        <span class="select_label">选择话题组</span>
                        <div class="select_box" tabindex="0" hidefocus="true" style="outline: none;">
                            <span class="select_txt">未选择</span>
                            <a class="selet_open">
                                <b></b>
                            </a>
                            <div class="option">
                                <label class="opLabel">
                                    <input class="selInput" type="text" style="height:25px;padding-left:4px;">
                                    <img src="${pageContext.request.contextPath}/assets/images/search2.png" class="selImg">
                                </label>
                                <div class="selectBox1" id="select_topic_name">
	                            </div>
                            </div>
                        </div>
                        <br clear="all" />
                    </li>
                </ul>
                <input type="hidden" id="select_value" /><!--模拟select的取值-->
            </div>
            <div class="content-search-top float-r">
                <button class="search-icon float-r" id="search_debatepost_button"></button>
                <div class="float-r">
                    <label class="float-r">
                        <select class="bortNone zhuangtai" id="status">
                            <option value="">全部状态</option>
                            <option value="0">正常</option>
                            <option value="1">推广</option>
                            <option value="2">隐藏</option>
                           <!--  <option value="3">删除</option> -->
                        </select>
                    </label>
                </div>
                <input class="shortinput float-r borRightNone"  placeholder="搜索发帖人、帖子标题、帖子内容" id="keyword"/>
                <div class="clearfix"></div>
            </div>
        </div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>话题组</th>
						<th>内容管理</th>
						<th>评论数</th>
						<th>点赞数</th>
						<th>阅读量</th>
						<th>发帖人</th>
						<th>发帖时间</th>
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

<!-- 弹出层 -->
<div class="showBox">
    <div class="panel top-text">
        <p id="debatepost_content"></p>
    </div>
    <div class="SBimgBox">
    </div>
</div>
<div class="showBox2">
    <div class="panel">
        <table class="table" id="showTable">
            <thead>
            <tr>
                <th>时间</th>
                <th>昵称</th>
                <th>内容</th>
                <th>回复对象</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="comment_tbody">
            </tbody>
        </table>
    </div>
</div>
<script src="${pageContext.request.contextPath}/media/js/sociality/debatepost/debatepost.js"></script>
</body>
</html>