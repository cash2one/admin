<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  
        body table a{color: #4595e6; transition: .2s;}
        a:active{color: #2ebbe6}
        .showBox3 table{
            width: 100%;
            font-size: 14px;
            text-align: center;
        }
        .showBox3 input[type='radio']{
            width:16px;
            height: 16px;
            margin-right: 4px;
            vertical-align: top;
        }
        .showBox3 input[type='radio']:focus{
            box-shadow: none;
        }
        .showBox3 label{cursor: pointer;}
        .showBox3 table tr{height: 120px;}
        
    </style>
</head>
<body>
<div class="container">
	<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<div class="content">
		<p class="toptitle">社交管理 > 交流圈 > 成员管理</p>
		
		<!-- 统计标签  start -->
		<div id="display" style="display:none;">
		<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum currentGroupCount">0</p>
				<p class="datatitle">交流圈人数</p>
			</div>
			<div class="datalist margin-r float-l greenback">
				<p class="datanum currentGroupIncreaseCount">0</p>
				<p class="datatitle">今日新增人数</p>
			</div>
			<div class="datalist float-l blueback">
				<p class="datanum currentGroupSpeakCount">0</p>
				<p class="datatitle">今日发言人数</p>
			</div>
			<div class="clearfix"></div>
		</div>
		</div>
		<!-- 统计标签   end -->		
		
        <div style="overflow: hidden;">
            <div class="mod_select content-search-top" style="overflow: visible;">
                <ul>
                    <li>
                        <span class="select_label">选择所属圈子名称</span>
                        <div class="select_box" tabindex="0" hidefocus="true">
                            <span class="select_txt">未选择</span><a class="selet_open"><b></b></a>
                            <div class="option">
                                <label class="opLabel">
                                    <input class="selInput" type="text" style="height:25px;padding-left:4px;">
                                    <img src="${pageContext.request.contextPath}/assets/images/search2.png" class="selImg">
                                </label>
                                <div class="selectBox1" id="select_group_name">
	                            </div>
                            </div>
                        </div>
                        <br clear="all" />
                    </li>
                </ul>
                <input type="hidden" id="select_value"/>
            </div>
            <div class="content-search-top float-r">
                <button class="search-icon float-r" id="search_user_group_button"></button>
                <div class="float-r">
                    <label class="float-r">
                        <select class="bortNone zhuangtai" id="search_isblacklist"style="left: -210px;">
                            <option value="">全部状态</option>
                            <option value="0">正常</option>
                            <option value="1">禁用</option>
                        </select>
                    </label>
                    
                <label class="float-l">
                <select class="bortNone zhuangtai" id="search_isKeeper_list" style="width: -50px;left:-100px">
                    <option value="">账号类型</option>
                    <option value="2">管理员</option>
                    <option value="1">孕妈</option>
                    <option value="3">医生</option>
                    <option value="4">宝妈</option>
                    <option value="5">备孕</option>
                   <!--是否管理员（1：孕妈  2：是管理员  3：医生  4.宝妈 5.备孕）--> 
                </select>
            </label>    
                </div>
                <input class="shortinput float-r borRightNone" placeholder="搜索用户名" id="search_nick_name_or_mobile"/>
                <div class="clearfix"></div>
            </div>
        </div>
		<div class="panel">
			<table class="table"> 
				<thead>
					<tr>
						<th>用户名</th>
						<th>昵称</th>
						<th>身份</th>
						<th>加入时间</th>
						<th>最后一次发言时间</th>
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
	
<!--------------------  弹出层     ---------------------- --->

<div class="showBox">
    <div class="panel top-text">
        <p></p>
    </div>
    <div class="SBimgBox">
    </div>
</div>
<div class="showBox2">
    <div class="panel">
    </div>
</div>
<div class="showBox3" style="display:none">
    <table>
        <tbody>
            <tr>
                <td>角色</td>
                <td><label><input type="radio" name="radio_role" class="showBox3_input" value="2"/>管理员</label></td>
                <td><label><input type="radio" name="radio_role" class="showBox3_input" value="3"/>医生</label></td>
            </tr>
        </tbody>
    </table>
</div>
</div>
<script src="${pageContext.request.contextPath}/media/js/sociality/group/group_listuserr.js"></script>
</body>
</html>