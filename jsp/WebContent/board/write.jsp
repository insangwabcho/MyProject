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
<style>
  table tr td {
    padding: 10px;
  }
</style>
<script>
  $(function(){
    $("#btnSave").click(function(){
      //첨부파일의 확장자 검사
      var filename= form1.file1.value;
      var start=filename.lastIndexOf(".")+1;
      if (start!= -1){
        var ext= filename.substring(start, filename,length);
        if (ext=="jsp" || ext=="exe"){
          alert("업로드할 수 없는 파일입니다.");
          return;
        }
      }
    });
  });
</script>
</head>
<body>
  
  <h2>글쓰기</h2><br>
  <form name="form1" id="form1" method="post" action="${path}/board_servlet/insert.do"
  enctype="multipart/form-data">
    <table border="1" width="700px">
      <tr>
        <td>이름</td>
        <td><input name="writer" id="writer"></td>
      </tr>
      <tr>
        <td>제목</td>
        <td><input name="subject" size="60"></td>
      </tr>
      <tr>
        <td>본문</td>
        <td><textarea rows="5" cols="60" name="content"></textarea></td>
      </tr>
      <tr>
        <td>첨부파일</td>
        <td><input type="file" name="file1"></td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td><input type="password" name="passwd"><input style="margin: 5px;" type="submit" id="btnSave" value="작성 완료"></td>
      </tr>
      <c:if test="${param.message == 'error'}">
        <tr>
          <td colspan="2" align="center"><span style="color: red;">파일 업로드중 오류가 발생했습니다. ( 지정되지 않은 파일형식 )</span></td>
        </tr>
      </c:if>
      <c:if test="${param.message == 'bad'}">
        <tr>
          <td colspan="2" align="center"><span style="color: red;">This IP is Banned!</span></td>
        </tr>
      </c:if>
    </table>
  </form>

</body>  
</html>