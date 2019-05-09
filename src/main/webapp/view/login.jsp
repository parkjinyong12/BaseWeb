<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body onload="document.f.j_username.focus();">
    <h3>Login with Username and Password</h3>
    <form name="f" action="j_spring_security_check.do" method="POST">
        <table>
            <tbody>
                <tr>
                    <td>User:</td>
                    <td><input type="text" name="j_username" value=""></td>                    
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="j_password"></td>
                    <td rowspan="2"><input name="submit" type="submit" value="Login"></td>
                </tr>           
            </tbody>
        </table>
    </form>
</body>
</html>