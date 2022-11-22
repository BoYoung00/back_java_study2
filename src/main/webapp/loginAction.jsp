<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDto" %>
<%@ page import="user.UserDao" %>
<%@ page import="java.io.PrintWriter" %>

<%
    request.setCharacterEncoding("UTF-8");

    PrintWriter script=response.getWriter();

    String userID = null;
    String userPassword=null;

    if (request.getParameter("userID") != null) {
        userID=(String)request.getParameter("userID");
    }

    if (request.getParameter("userPassword") != null) {
        userPassword=(String)request.getParameter("userPassword");
    }
    if (userID.equals("")||userPassword.equals("")) {
        script.println("<script>");
        script.println("alert('입력이 안된 사항이 있습니다.')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }

    UserDto dto = new UserDto(userID,userPassword);
    UserDao dao = new UserDao();
    int insert = dao.loginUser(dto);

    if (insert==1) {
        script.println("<script>");
        script.println("alert('로그인 성공')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    } else if (insert == 0) {
        script.println("<script>");
        script.println("alert('비밀번호가 틀렸습니다.')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    } else if (insert == -1) {
        script.println("<script>");
        script.println("alert('일치하는 계정이 존재하지 않습니다.')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    } else if (insert == -2){
        script.println("<script>");
        script.println("alert('오류')");
        script.println("location.href='index.jsp'");
        script.println("</script>");
        script.close();
        return;
    }
%>