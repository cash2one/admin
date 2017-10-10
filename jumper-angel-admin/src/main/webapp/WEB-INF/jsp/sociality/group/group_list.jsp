<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"></jsp:include>
    <meta charset="UTF-8">
    <title>健康指导-宝宝发育变化</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/zui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/orange.css">
	
</head>
<body>
<div class="container">
	<%-- <jsp:include page="../../common/top.jsp"/> --%>
	<%-- <jsp:include page="../../common/left.jsp"/> --%>
	<div class="content">
        <p class="toptitle">社交管理 > 交流圈 > 圈子管理</p>
        
		<!-- 统计标签  start -->
		<div class="contentdata" style="margin-top:0;margin-bottom: 30px;">
			<div class="datalist margin-r float-l yellowback">
				<p class="datanum group_count">0</p>
				<p class="datatitle">交流圈总数</p>
			</div>
			<div class="datalist margin-r float-l greenback">
				<p class="datanum group_user_count">0</p>
				<p class="datatitle">交流圈总人数</p>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- 统计标签   end -->        
        
        <div class="content-search-top">
            <input class="shortinput float-l borRightNone group-name" placeholder="输入标题关键词"/>  
                
           <label class="float-l borRightNone group-type" >
                <select class="bortNone zhuangtai"  id="data_Status"style="left: -210px;">
                    <option value="">全部状态</option>
                    <option value="1" >正常</option>
                    <option value="2" >禁用</option>
                </select>
            </label>

            <label class="float-l">
                <select class="bortNone zhuangtai" id="allow_Add" style="width: -110px;left: -102px">
                    <option value="">全部类型</option>
                    <option value="0">公开圈子</option>
                    <option value="1">非公开圈子</option>
                </select>
            </label>             
            <button class="search-icon float-l"></button>
            <!-- <button class="red_btn_big float-r" onClick="addGroup();">+ 新建圈子</button> -->
            <button class="red_btn_big float-r" onClick="ShowDiv(1)">+ 新建圈子</button>
            <div class="clearfix"></div>
        </div>
        <div class="panel">
            <table class="table">
                <thead>
                <tr>
                    <th>圈子名</th>
                    <th>简介</th>
                    <th>二维码</th>
                    <th>创建人<img src=""></th>
                    <th>创建时间</th>
                    <th>人数</th>
                    <th>状态</th>
                    <th>圈子类型</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tbody"></tbody>
            </table>
        </div>
        <div id="pages"></div>
    </div>
    </div>
    <!--------------------  弹出层     ---------------------- --->
	<div class="normal_form2">
	    <form >
	        <ul>
	            <li>
	                <label class="label">圈子名称</label>
	                <input type="text" class="longinput" id="group_Name" placeholder="字符长度为30个字节"/>
	                <p class="labtext red" style="display: none;" id="groupname_error">超出长度限制!</p>
	            </li>
	           <!--  <li>
	                <label class="label">设置圈子封面</label>
	                <input type="file" class="longinput" id="up_Photo" placeholder="尺寸建议100*100"/>
	                <p class="labtext red" style="display: none;" id="up_Photo">尺寸建议100*100</p>
	            </li> -->
	            <li>
					<label class="label label-t">设置圈子封面</label>
					<label class="label labradio">
						<div class="lab_right" align="left">
							<input type="hidden" id="coverUrl" class="orangeUploder" />
						</div>
					</label>
					<p class="labtext red">* 尺寸建议100*100</p>
					<!-- <p class="labtext red" style="display: none;" id="upload_success">上传成功！</p> -->
				</li>
	            <li>
	                <label class="label">圈子简介 </label>
	                <textarea  class="pop_textarea"  id="groupBriefIntr" style="vertical-align: top;margin-top: -4px;"
	                          placeholder="最多可输入150个字"></textarea>      
	                <p class="labtext red" style="display: none;" id="groupBriefIntr_error">超出长度限制!</p>
	            </li>
	            <li>
	               <label class="label label-t" style="width: 120px">设为非公开圈子</label>
               	   <input class="labtext red" type="checkbox" id="allow_Add_input" name="1" value="1" style="margin-left: 20px;vertical-align: middle;"/>
                   <p class="labtext red" style="display: inline-block;margin-left: 20px;color: #999;">
              		      勾选后，该交流圈在APP端不能被搜索，用户只能扫码加入</p>
	            </li>
	        </ul>
	        <input type="hidden" name="id" id="_id" >
	    </form>
	</div>
</body>
	<script src="${pageContext.request.contextPath}/assets/js/zui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/lib/plupload/plupload.full.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/upload.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/sociality/group/group.js"></script>
</html>