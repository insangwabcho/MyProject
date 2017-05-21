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
</head>
<body>

  <form method="post" enctype="multipart/form-data" action="upload_result.jsp">
    이름 : <input name="name"><br>
    제목 : <input name="subject"><br>
    파일1 : <input type="file" name="file1"><br>
    파일2 : <input type="file" name="file2"><br>
    <input type="submit" value="업로드">
  </form>

</body>
</html>