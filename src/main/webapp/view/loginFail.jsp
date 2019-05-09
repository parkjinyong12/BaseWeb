<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Base Web</title>
<script>
	function fnInitEvent() {
		alert("로그인 실패");
		location.href='login.do';
	}
</script>
</head>
<body onload="fnInitEvent();">
    <h3>로그인 실패</h3>   
</body>
</html>