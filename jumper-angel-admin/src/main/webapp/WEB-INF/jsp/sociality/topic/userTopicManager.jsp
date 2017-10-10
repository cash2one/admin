<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>话题组-成员管理</title>
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
        input{outline:none}
        select{outline:none}
        table .tableTime li{
            line-height: 20px;
        }
        table .tableTime li:last-child{
            color: #aaa;
            font-size: 14px;
        }
        #checkboxTopic{
       	    margin-left: 20px;
       	    margin-top:30px;
    		font-size: 14px;
    		
        }
        #topicForm input{
        	width:20px;
        	height:20px;
        	vertical-align: middle;
        }
        
       #topicForm label{
          	margin:20px;
          	font-size:18px;
     	  	width:170px;
     	  	display: inline-block;
     	  	cursor: pointer;
       }
    </style>
</head>
<body>
<div class="container">
	<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 话题组 > 成员管理</p>
		
		<!-- 统计标签  start -->
		<div id="display" style="display:none;">
			<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
				<div class="datalist margin-r float-l yellowback">
					<p class="datanum current_user_count">0</p>
					<p class="datatitle">话题组人数</p>
				</div>
				<div class="datalist margin-r float-l greenback">
					<p class="datanum current_increase_count">0</p>
					<p class="datatitle">今日新增人数</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- 统计标签   end -->
		
        <div style="overflow: hidden;">
            <div class="mod_select content-search-top" style="overflow: visible;">
                <ul>
                    <li>
                        <span class="select_label">选择话题组</span>
                        <div class="select_box" tabindex="0" hidefocus="true">
                            <span class="select_txt">全部话题组</span><a class="selet_open"><b></b></a>
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
                <input type="hidden" id="select_value"/>
            </div>
            <div class="content-search-top float-r">
                <button class="search-icon float-r" id="search_user_topic_button"></button>
                <div class="float-r">
                    <label class="float-r">
                        <select class="bortNone zhuangtai" id="search_is_black_list">
                            <option value="">全部状态</option>
                            <option value="0">正常</option>
                            <option value="1">禁用中</option>
                        </select>
                    </label>
                </div>
                <input class="shortinput float-r borRightNone" placeholder="搜索用户名、昵称" id="search_nick_name_or_mobile"/>
                <div class="clearfix"></div>
            </div>
        </div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>话题组</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>加入时间</th>
						<th>最后一次发帖时间</th>
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
<!--------------------  弹出层     ---------------------- --->
<div  class="normal_form2">
    <ul>
        <li>
            <label class="label">选择话题组:</label>
            <div id=checkboxTopic></div>
        </li>
    </ul>
    <input type="hidden" id="mobile" value=""/>
</div>
<script src="${pageContext.request.contextPath}/media/js/sociality/topic/userTopic.js"></script>
</body>
</html>