<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天使医生运营后台</title>
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/weblogo.png">
</head>
<frameset rows="40, *" id="frame_main" border="0">
    <frame src="${pageContext.request.contextPath}/main/top" noresize="noresize" name="header">
    <frameset cols="281, *">
        <frame src="${pageContext.request.contextPath}/main/left" name="left" noresize="noresize" />
        <frame src="${pageContext.request.contextPath}/userStatistics/forwardUserStatistics" name="list" noresize="noresize">
    </frameset>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>