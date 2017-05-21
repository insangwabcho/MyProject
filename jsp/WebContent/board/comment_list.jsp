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

  <table border="1" width="700px">
    <c:forEach var="row" items="${list}">
      <tr>
        <td>
          ${row.writer}
          ( ${row.reg_date} )<br>
          ${row.content}
        </td>
      </tr>
    </c:forEach>
  </table>

</body>
</html>