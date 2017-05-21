<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> -->
<script>
  $(function(){
    comment_list();
    
    $("#btnReply").click(function(){
      document.form1.action="${path}/board_servlet/reply.do";
      document.form1.submit();
    });
    
    $("#btnList").click(function(){
      location.href="${path}/board_servlet/list.do";
    });
    
    $("#btnEdit").click(function(){
      document.form1.action="${path}/board_servlet/passwd_check.do";
      document.form1.submit();
    });
    
    $("#btnSave").click(function(){
      var param= "board_num=${dto.num}&writer="+$("#writer").val()+"&content="+$("#content").val();
      $.ajax({
        type: "post",
        url: "${path}/board_servlet/comment_add.do",
        data: param,
        success: function(){
          alert("댓글이 저장되었습니다.");
          $("#writer").val("");
          $("#content").val("");
          comment_list();
        }
      });
    });
  });
  
  function comment_list(){
    var param="num="+${dto.num};
    $.ajax({
      type: "post",
      url: "${path}/board_servlet/commentList.do",
      data: param,
      success: function(result){
        $("#commentList").html(result);
      }
    });
  };
</script>
</head>
<body>
  <h2>게시물 상세화면</h2>
  <form name="form1" method="post">
    <table border="1" width="700px">
      <tr>
        <td>날짜</td>
        <td>${dto.reg_date}</td>
        <td>조회수</td>
        <td>${dto.readcount}</td>
      </tr>
      <tr>
        <td>이름</td>
        <td colspan="3">${dto.writer}</td>
      </tr>
      <tr>
        <td>제목</td>
        <td colspan="3">${dto.subject}</td>
      </tr>
      <tr>
        <td>본문</td>
        <td colspan="3">${dto.content}</td>
      </tr>
      <tr>
        <td>첨부파일</td>
        <td colspan="3">
          <c:if test="${dto.filesize > 0}">
            ${dto.filename} ( ${dto.filesize} bytes )
            <a href="${path}/board_servlet/download.do?num=${dto.num}">[다운로드]</a>
          </c:if>
        </td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td colspan="3" align="center">
          <input type="password" name="passwd">
          <input type="button" value="게시물 수정" id="btnEdit">
          <input type="hidden" name="num" value="${dto.num}">
          <c:if test="${param.message == 'error'}">
            <br>
            <span style="color:red">비밀번호가 일치하지 않습니다.</span>
          </c:if>
        </td>
      </tr>
      <tr>
        <th colspan="4">
          <input type="button" value="목록으로" id="btnList">
          <input type="button" value="답글달기" id="btnReply">
        </th>
      </tr>
    </table>
    <!-- 댓글 쓰기 -->
    <table border="0" width="700px">
      <tr>
        <td><input id="writer" placeholder="이름"></td>
        <td rowspan="2">
          <button id="btnSave" type="button">확인</button>
        </td>
      </tr>
      <tr>
        <td>
          <textarea rows="3" cols="80" placeholder="댓글 내용을 입력하세요." id="content"></textarea>
        </td>
      </tr>
    </table>
    <!-- 댓글 목록 -->
    <div id="commentList"></div>
  </form>
</body>
</html>