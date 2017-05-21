<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
jstl (Jsp Standard Tag Library, jsp표준태그라이브러리)
  jsp의 확장 태그
taglib prefix="태그 접두어" uri="태그 식별자"
set var="변수명" value="값"
 -->
<!-- EL(Expression Language, 표현언어) -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}" />

<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩은 jquery 기반에서 실행됨 -->
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- 부트스트랩 css -->
<link rel="stylesheet" href="${path}/include/css/bootstrap.css">
<!-- 부트스트랩 javascript -->
<script src="${path}/include/js/bootstrap.js"></script>