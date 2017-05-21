<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>

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
  
  <%
  //업로드 디렉토리 지정
  String upload_path="e:/upload";
  // 1024Byte => 1KB, 1024KB => 1MB, 1024MB => 1GB
  int size= 10*1024*1024;
  String name= "";
  String subject= "";
  String filename= "";
  String filename2= "";
  int filesize= 0;
  int filesize2= 0;
  try{
    //request 객체를 확장한 클래스
    //new MultipartRequest (request객체, 업로드디렉토리, 최대사이즈, 파일이름 인코딩 바식, 중복파일이름 규칙)
    MultipartRequest multi= new MultipartRequest(request, upload_path, size, "utf-8", new DefaultFileRenamePolicy());
    name= multi.getParameter("name");
    subject= multi.getParameter("subject");
    
    Enumeration files= multi.getFileNames();
    
    String file1= (String)files.nextElement(); //1번파일
    String file2= (String) files.nextElement(); //2번파일
    
    filename= multi.getFilesystemName(file1);
    File f1= multi.getFile(file1); //파일이름
    filesize= (int)f1.length(); //파일사이즈
    
    filename2= multi.getFilesystemName(file2);
    File f2= multi.getFile(file2);
    filesize2= (int) f2.length();
    
  }catch(Exception e){
    e.printStackTrace();
  }
  %>
  
  이름 : <%=name %><br>
  제목 : <%=subject %><br>
  파일1 이름: <%=filename %><br>
  파일1 크기: <%=filesize %><br>
  파일2 이름: <%=filename2 %><br>
  파일2 크기: <%=filesize2 %><br>
  
</body>
</html>