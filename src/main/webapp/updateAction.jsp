<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDto" %>
<%@ page import="user.UserDao" %>
<%@ page import="java.io.PrintWriter" %>

<%
    request.setCharacterEncoding("UTF-8");

    String UserId = null;
    String updateUserPw = null;

    if (request.getParameter("UserId") != null) {
        UserId=(String)request.getParameter("UserId");
    }

    if (request.getParameter("updateUserPw") != null) {
        updateUserPw=(String)request.getParameter("updateUserPw");
    }

    if (UserId.equals("") || updateUserPw.equals("")) {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('입력이 안된 사항이 있습니다.')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }

    UserDao dao = new UserDao();
    UserDto dto = new UserDto(UserId, updateUserPw);
    int insert = dao.update(dto);

    if (insert==1) {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('수정 성공')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    } else {
        PrintWriter script=response.getWriter();
        script.println("<script>");
        script.println("alert('일치하는 아이디가 없습니다')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }
%>