<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/layui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/mainPage.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/css.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/selectInput.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font/iconfont.css" />
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/layer.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/layui.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/style.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/selectInput.js"></script>
<script src="${pageContext.request.contextPath}/media/js/pregnancy/pregnancy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/lay/modules/element.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/lay/modules/laypage.js"></script>
<script type="text/javascript">
	//绝对路径
	var path = "${pageContext.request.contextPath}";
	//每页显示的记录数
	var everyPage = 15;
	/**
	 * 时间转换
	 * @param shijianchuo 时间戳
	 * @returns {String}
	 */
	function dateFormat(shijianchuo) {
		//shijianchuo是整数，否则要parseInt转换
		var time = new Date(shijianchuo);
		var y = time.getFullYear();
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mm = time.getMinutes();
		var s = time.getSeconds();
		//return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
		return y+'-'+add0(m)+'-'+add0(d);
	}
	function add0(m) {
		return m<10?'0'+m:m 
	}
</script>