<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>咨询管理-全部咨询</title>
    <jsp:include page="../common/head.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <button id="list_menu_btn"></button>
	<div class="content">
		<p class="toptitle">短信管理    》 验证码管理</p>
		<div class="content-search-top">
			<div class="searchGroup">
				<input class="shortinput float-l borRightNone" placeholder="请输入手机号" id="mobile" /> 
					<label class="float-r" > </label>
				<button class="search-icon float-r" onclick="initData()"></button>
			</div>
		</div>
		<div class="panel">
			<table class="table">
				<thead>
					<tr >
						<th>手机号码</th>
						<th>验证码</th>
						<th>发送时间</th>
					</tr>
				</thead>
				<tbody id="tbody">
					
				</tbody>
			</table>
		</div>
		<div id="page" align="right"></div>
	</div>
</div>
</body>
<script type="text/javascript">
//总页数
var totalPage = 0;
$(document).ready(function() {
	//显示数据
	initData();
});
//显示数据
function initData() {
	//请求总页数
	$.ajax({
		url: path + "/verified/findUserVerifiedCode",
		type: "get",
		dataType: "json",//从服务器端返回的数据类型
		data: {"pageIndex":1, "everyPage":everyPage, "first":true, "mobile":$("#mobile").val()},
		async: false,//同步
		success: function(json) {
			totalPage = json.data;
		}
	});
	//显示分页
	layui.use(["laypage"], function() {
		var laypage = layui.laypage;
		layui.laypage({
		    cont: "page",
		    pages: totalPage,
		   	skip: true,
		   	jump: function(obj) {
		   	 	//得到了当前页，用于向服务端请求对应数据
		   	    var curr = obj.curr;
			   	//json参数
		   	 	var data = {
		   	 		"pageIndex":curr,
		   	 		"everyPage":everyPage,
		   	 		"first": false,
		   	 		"mobile":$("#mobile").val()
		   	 	};
		   	 	//请求宝宝发育变化
		   	 	$.ajax({
		   			url: path + "/verified/findUserVerifiedCode",
		   			type: "get",
		   			dataType: "json",//从服务器端返回的数据类型
		   			data: data,
		   			async: false,
		   			success: function(json) {
		   				var data = json.data;
		   				var html = "";
		   				for(var i=0; i<data.length; i++) {
		   					html+="<tr>";
	   						html+="<td>"+data[i].mobile.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2')+"</td>";
	   						html+="<td>"+data[i].code+"</td>";
	   						html+="<td>"+data[i].createTime+"</td>";
   							html+="</tr>";
		   				}
		   				$("#tbody").html(html);
		   			}
		   		});
	   		}
	  	});
	});
}
</script>
</html>
