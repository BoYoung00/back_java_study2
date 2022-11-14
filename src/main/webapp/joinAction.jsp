<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDto" %>
<%@ page import="user.UserDao" %>
<%@ page import="java.io.PrintWriter" %>

<%
    request.setCharacterEncoding("UTF-8");

    String userID = null;
    String userPassword=null;

    if (request.getParameter("userID") != null) {
        userID=(String)request.getParameter("userID");
    }

    if (request.getParameter("userPassword") != null) {
        userPassword=(String)request.getParameter("userPassword");
    }
    if (userID.equals("")||userPassword.equals("")) {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('입력이 안된 사항이 있습니다.')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }

    UserDto dto = new UserDto(userID,userPassword);
    UserDao dao = new UserDao();
    int insert = dao.addUser(dto);

    if (insert==1) {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('회원가입 성공')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }
%>