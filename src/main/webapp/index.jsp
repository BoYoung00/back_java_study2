<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="user.UserDto" %>
<%@ page import="user.UserDao" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="joinAction.jsp" method="post">
    <input type="text" name="userID">
    <input type="password" name="userPassword">
    <input type="submit" value="회원가입">
</form>

<br>

<form action="deleteAction.jsp" method="post">
    <input type="text" name="deleteUserId">
    <input type="submit" value="삭제">
</form>

<br>

<form action="updateAction.jsp" method="post">
    <input type="text" name="UserId">
    <input type="text" name="updateUserPw">
    <input type="submit" value="수정">
</form>

<br>

<div>
    <%
        UserDao userDAO = new UserDao();
        List<UserDto> list = userDAO.getvalue();

        for (int i=0; i<list.size(); i++) {
    %>
    <table border="1px">
        <thead>
            <th>아이디</th>
            <th>비밀번호</th>
        </thead>
        <tbody>
        <tr>
            <td><%=list.get(i).getUserID() %></td>
            <td><%=list.get(i).getUserPassword()%></td>
        </tr>
        </tbody>
    </table>
    <%
        }
    %>
</div>
</body>
</html>