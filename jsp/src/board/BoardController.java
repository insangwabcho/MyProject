package board;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.io.File;
import java.io.FileInputStream;

import board.dao.BoardDAO;
import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import common.Constants;

@WebServlet("/board_servlet/*")
//http://localhost:8080/project이름/board_servlet/모든주소
//http://localhost:8080/web03_jsp/board_servlet/list.do
public class BoardController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String url = request.getRequestURI();
    //web03_jsp/board_servlet/list.do
    String contextPath = request.getContextPath();

    BoardDAO dao = BoardDAO.getInstance();

    if (url.indexOf("list.do") != -1) {
      // dao에서 자료 요청
      List<BoardDTO> list = dao.list();
      // 출력페이지로 넘기기 전에 저장
      request.setAttribute("list", list);
      // 출력 페이지로 포워딩
      String page = "/board/list.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(page);
      rd.forward(request, response);

    } else if (url.indexOf("insert.do") != -1) {
      
      // 파일 업로드 처리
      File uploadDir = new File(Constants.UPLOAD_PATH);
      if (!uploadDir.exists()) { // 디렉토리가 존재하지 않으면
        uploadDir.mkdir(); // 디렉토리 생성
      }
      // request,업로드경로,업로드제한용량,문자셋,중복파일처리규칙
      MultipartRequest multi = new MultipartRequest(request, Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8",
          new DefaultFileRenamePolicy());
      String filename = "";
      int filesize = 0;
      try {
        // 첨부파일 배열(집합)
        Enumeration files = multi.getFileNames();
        while (files.hasMoreElements()) { // 다음 요소가 있으면
          String file1 = (String) files.nextElement();
          // 첨부파일의 이름
          filename = multi.getFilesystemName(file1);
          File f1 = multi.getFile(file1);
          if (f1 != null) {
            filesize = (int) f1.length(); // 파일의 크기
          }
          // 첨부파일의 확장자 검사
          int start = filename.lastIndexOf(".") + 1;
          String ext = filename.substring(start, filename.length());
          if (ext.equals("jsp") || ext.equals("exe")) {
            f1.delete();
            response.sendRedirect(contextPath + "/board/write.jsp?message=error");
            return;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 폼에서 입력한 값들
      String writer = multi.getParameter("writer");
      String subject = multi.getParameter("subject");
      String content = multi.getParameter("content");
      String passwd = multi.getParameter("passwd");
      String ip = request.getRemoteAddr(); // ip주소
      String[] bad_ips = { "192.168.0.22", "192.168.0.123" };
      for (String str : bad_ips) {
        if (str.equals(ip)) {
          response.sendRedirect(contextPath + "/board/writer.jsp?message=bad");
          return;
        }
      }
      BoardDTO dto = new BoardDTO();
      dto.setWriter(writer);
      dto.setSubject(subject);
      dto.setContent(content);
      dto.setPasswd(passwd);
      dto.setIp(ip);
      if (filename == null || filename.trim().equals("")) {
        filename = "-";
      }
      dto.setFilename(filename);
      dto.setFilesize(filesize);
      // 테이블에 저장
      dao.insert(dto);
      // 목록으로 이동
      String page = contextPath + "/board_servlet/list.do";
      response.sendRedirect(page);

    } else if (url.indexOf("view.do") != -1) {
      // 게시물 번호
      int num = Integer.parseInt(request.getParameter("num"));
      // 조회수 증가 처리
      dao.plusReadCount(num, request.getSession());
      // dao에 자료 요청
      BoardDTO dto = dao.view(num);
      // 출력 페이지로 포워딩
      request.setAttribute("dto", dto);
      RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
      rd.forward(request, response);

    } else if (url.indexOf("commentList.do") != -1) {
      // 게시물 번호
      int num = Integer.parseInt(request.getParameter("num"));
      // dao에 댓글 목록 요청
      List<BoardCommentDTO> list = dao.commentList(num);
      // 출력 페이지로 이동
      request.setAttribute("list", list); // 자료를 requst에 저장
      String page = "/board/comment_list.jsp"; // 이동 페이지 주소
      RequestDispatcher rd = request.getRequestDispatcher(page);
      rd.forward(request, response); // 페이지 포워딩

    } else if (url.indexOf("comment_add.do") != -1) {
      BoardCommentDTO dto = new BoardCommentDTO();
      int board_num = Integer.parseInt(request.getParameter("board_num"));
      String writer = request.getParameter("writer");
      String content = request.getParameter("content");
      dto.setBoard_num(board_num);
      dto.setWriter(writer);
      dto.setContent(content);
      // dao에 댓글 저장 요청
      dao.commentAdd(dto);
      // 페이지 이동은 없음(ajax에서 처리)

    } else if (url.indexOf("download.do") != -1) {
      // 게시물 번호
      int num = Integer.parseInt(request.getParameter("num"));
      // 게시물 번호에 해당하는 첨부파일 이름 조회
      String filename = dao.getFileName(num);
      // 서버의 파일을 읽어서 클라이언트로 복사
      String path = Constants.UPLOAD_PATH + filename;
      byte b[] = new byte[4096]; // byte배열(추천)
      FileInputStream fis = new FileInputStream(path);
      // 마임타입(파일의 종류)
      String mimeType = getServletContext().getMimeType(path);
      if (mimeType == null) {
        mimeType = "application/octet-stream;charset=utf-8";
      }
      // 첨부파일에 특수문자, 한글이 포함될 경우의 처리
      // 스트링.getBytes("문자셋") 스트링을 바이트 배열로 변환
      // new String(바이트배열, "문자셋") 바이트배열을 스트링으로 변환
      filename = new String(filename.getBytes("ms949"), "8859_1");
      // 헤더구성 (첨부파일의 첨부)
      response.setHeader("Content-Disposition", "attachment;filename=" + filename);
      ServletOutputStream out = response.getOutputStream();
      int numRead;
      while (true) {
        numRead = fis.read(b, 0, b.length);
        if (numRead == -1)
          break;
        out.write(b, 0, numRead);
      }
      out.flush();
      out.close();
      fis.close();
      // 다운로드 횟수 증가 처리
      dao.plusDown(num);

    } else if (url.indexOf("reply.do") != -1) {
      int num = Integer.parseInt(request.getParameter("num"));
      BoardDTO dto = dao.view(num);
      dto.setSubject("Re:" + dto.getSubject());
      dto.setContent("=====게시물의 내용=====\n" + dto.getContent());
      request.setAttribute("dto", dto);
      String page = "/board/reply.jsp";
      RequestDispatcher rd = request.getRequestDispatcher(page);
      
      rd.forward(request, response);
    } else if (url.indexOf("passwd_check.do") != -1) {
      // 게시물 번호, 패스워드
      int num = Integer.parseInt(request.getParameter("num"));
      String passwd = request.getParameter("passwd");
      // dao에 체크 요청
      String result = dao.passwdCheck(num, passwd);
      String page = "";
      if (result != null) { // 패스워드가 맞을시
        page = "/board/edit.jsp"; // 편집 페이지 주소
        request.setAttribute("dto", dao.view(num));
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
      } else { // 틀렸을경우 다시 view.do 페이지로
        page = contextPath + "/board_servlet/view.do?num=" + num + "&message=error";
        response.sendRedirect(page);
      }
      // 페이지 이동 처리

    } else if (url.indexOf("update.do") != -1) {

    } else if (url.indexOf("delete.do") != -1) {
      
    } else if (url.indexOf("search.do") != -1) {

    } else if (url.indexOf("insertReply.do")!= -1){
      int num= Integer.parseInt(request.getParameter("num"));
      BoardDTO dto= dao.view(num);
      //게시물 그룹 아이디 (원글의 num)
      int ref= dto.getRef();
      //같은 게시물 그룹 내에서의 출력 순서
      int re_step= dto.getRe_step()+1;
      //답변 단계
      int re_level= dto.getRe_level()+1;
      //답변글 작성 내용
      String writer= request.getParameter("writer");
      String subject= request.getParameter("subject");
      String content= request.getParameter("content");
      String passwd= request.getParameter("passwd");
      dto.setWriter(writer);
      dto.setSubject(subject);
      dto.setContent(content);
      dto.setPasswd(passwd);
      dto.setRef(ref);
      dto.setRe_level(re_level);
      dto.setRe_step(re_step);
      //첨부파일의 정보
      dto.setFilename("-");
      dto.setFilesize(0);
      dto.setDown(0);
      
      dao.updateStep(ref, re_step);
      dao.reply(dto);
      String page= "/board_servlet/list.do";
      response.sendRedirect(contextPath+page);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
