<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<style>
  table tr td{
    padding: 5px;
  }
</style>
<script>
  $(function(){
    $("#btnWrite").click(function(){
      location.href="${path}/board/write.jsp";
    });
  });
</script>
</head>
<body>
  
  <div class="container">
    <h2>게시판</h2><br>
    <button type="button" id="btnWrite" class="btn btn-primary">글쓰기</button>
    <table border="1" width="900px" class="table table-striped">
      <tr>
        <th>번호</th>
        <th>이름</th>
        <th>제목</th>
        <th>날짜</th>
        <th>조회수</th>
        <th>첨부파일</th>
        <th>다운로드</th>
      </tr>
      <c:forEach var="dto" items="${list}">
        <tr align="center">
          <td>${dto.num}</td>
          <td>${dto.writer}</td>
          <!-- 답변 들여쓰기 -->
          <td align="left">
            <c:forEach begin="1" end="${dto.re_level}">
              &nbsp;&nbsp;
            </c:forEach>
            <a href="${path}/board_servlet/view.do?num=${dto.num}">${dto.subject}</a>
            <c:if test="${dto.comment_count > 0}">
              <span style="color: green;">
                (${dto.comment_count}) 
              </span>
            </c:if>
          </td>
          <td>${dto.reg_date}</td>
          <td>${dto.readcount}</td>
          <td style="text-align: center; width: 80px;">
            <c:if test="${dto.filesize > 0}">
              <a href="${path}/board_servlet/download.do?num=${dto.num}"><img src="${path}/images/file.gif"></a>
            </c:if>
          </td>
          <td>${dto.down}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  
</body>
</html>