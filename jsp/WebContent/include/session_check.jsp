<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%
  request.setCharacterEncoding("utf-8");
  String userid = (String)session.getAttribute("userid");
  if (userid == null) {
    /* String message="로그인한 사용자만 사용 가능한 화면입니다.";
    message= URLEncoder.encode(message,"utf-8");
    response.sendRedirect("sessionTestForm2.jsp?message="+message); */
%>
<script>
  alert("로그인한 사용자만 사용 가능한 화면입니다.");
  location.href = "sessionTestForm2.jsp";
</script>
<%
  }
%>