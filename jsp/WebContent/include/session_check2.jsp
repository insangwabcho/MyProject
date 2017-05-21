<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<c:if test="${sessionScope.userid eq null }">
  <script>
      alert("  로그인 정보가 없습니다. \n   로그인페이지로 이동합니다.");
      location.href = "${path}/ch10/session_login.jsp";
    </script>
</c:if>