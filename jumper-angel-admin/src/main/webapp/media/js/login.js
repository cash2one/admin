//登录
function login() {
	//用户名
	var username = $("#username").val();
	//密码
	var password = $("#password").val();
	if(username == "") {
		$("#error").html("用户名不能为空！");
		return;
	}
	if(password == "") {
		$("#error").html("密码不能为空！");
		return;
	}
	//验证通过
	$("#error").html("");
	//参数
	var param = {
		"username":username,
		"password":password
	};
	//转json字符串
	param = JSON.stringify(param);
	//登录
	$.ajax({
		url: path + "/login/userLogin",
		type: "POST",
		contentType: "application/json;charset=utf-8",
		dataType: "json",//从服务器端返回的数据类型
		data: param,
		success: function(json) {
			if(json.code == 1) {
				$("#error").html("");
				window.location.href = path+"/main/main";
			} else {
				$("#error").html(json.msgbox);
				return;
			}
		}
	});
}

/*huangzg 2016-01-14 add 
 * 添加键盘时间
 */
$(function (){
	$(window).keydown(function(event){
		if(event.which == 13){
			login();
		}
	});
});