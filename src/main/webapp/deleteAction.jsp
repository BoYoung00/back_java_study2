<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDto" %>
<%@ page import="user.UserDao" %>
<%@ page import="java.io.PrintWriter" %>

<%
    request.setCharacterEncoding("UTF-8");

    String deleteUserId = null;

    if (request.getParameter("deleteUserId") != null) {
        deleteUserId=(String)request.getParameter("deleteUserId");
    }

    if (deleteUserId.equals("")) {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('아이디를 입력해주세요.')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }

    UserDao dao = new UserDao();
    int insert = dao.deleteuser(deleteUserId);

    if (insert==1) {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('삭제 성공')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }
%>