<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>系统登录</title>
		<link rel="icon" href="${pageContext.request.contextPath}/assets/images/weblogo.png">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login/css.css">
		<style>
			.logintitle{color: #FFFFFF; text-align: center;margin: 140px 0 50px 0;}
			.loginbox{width: 560px;height: 360px;border-radius:5px;margin: auto;background:#f2f2f2;}
			.loginbox-title{height: 120px;border-radius: 5px 5px 0 0;padding: 0 25px 15px 25px;background: url(${pageContext.request.contextPath}/assets/images/loginlogo.png) no-repeat;}
			.form-top-left {float: left;width: 75%;padding-top: 25px;}
			.form-top-left p{ padding-top: 20px;color: #999;}
			.loginform {padding: 25px 25px 30px 25px;text-align: left;}
			.loginform input{ width:510px ; height:50px;line-height:50px;padding: 0 20px;margin-bottom: 20px;font-size: 15px;}
			.loginform button{ width:510px ;height:50px;font-size: 17px; font-weight: bold;}
			.login-footer{ text-align: center; margin-top:100px ;color: #fff;}
		</style>
	</head>
	<body style="background:url(${pageContext.request.contextPath}/assets/images/loginbackground.jpg);">
		<div id="login">
			<div class="logintitle">
				<h1 style="font-weight: normal;"><strong>天使医生</strong> 运营后台</h1>
			</div>
			<div class="loginbox">
				<div class="loginbox-title">
					<div class="form-top-left">
						<h3>用户登录</h3>
						<p>请输入用户名和密码以登录</p>
						<!--此处可显示错误提示，样式示例为下一行-->
						<p class="red" id="error"></p>
					</div>
				</div>
				<div class="loginform">
					<input type="text" id="username" placeholder="用户名/手机号码"/>
					<input type="password" id="password" placeholder="6-12位密码" />
					<button class="red_btn_big" onclick="login()">登 录</button>
				</div>
			</div>
			<div class="login-footer">
				<p>Copyright © 2017 天使医生</p>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		//绝对路径
		var path = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/media/js/login.js"></script>
</html>