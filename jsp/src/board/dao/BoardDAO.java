package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import sqlmap.MybatisManager;

public class BoardDAO {

  private static BoardDAO instance;

  public static BoardDAO getInstance() {
    if (instance == null) {
      instance = new BoardDAO();
    }
    return instance;
  } // getInstance()

  private BoardDAO() {
  }
  
  //답변글 저장
  public void reply(BoardDTO dto){
    SqlSession session= null;
    try {
      session= MybatisManager.getInstance().openSession();
      session.insert("board.reply", dto);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session!= null) session.close();
    }
  }
  
  //답글들의 출력 순서 조정
  public void updateStep(int ref, int re_step){
    SqlSession session= null;
    try {
      session= MybatisManager.getInstance().openSession();
      BoardDTO dto= new BoardDTO();
      dto.setRef(ref);
      dto.setRe_step(re_step);
      session.update("board.updateStep", dto);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session!= null) session.close();
    }
  }
  
  public String passwdCheck(int num, String passwd){
    String result= null;
    SqlSession session= null;
    try {
      session= MybatisManager.getInstance().openSession();
      Map<String, Object> map= new HashMap<>();
      map.put("num", num);
      map.put("passwd", passwd);
      result= session.selectOne("board.pass_check", map);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session!= null) session.close();
    }
    return result;
  }
  
  public List<BoardCommentDTO> commentList(int num){
    List<BoardCommentDTO> list= null;
    SqlSession session= null;
    try {
      session= MybatisManager.getInstance().openSession();
      list= session.selectList("board.commentList",num);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session!= null) session.close();
    }
    return list;
  }

  //댓글 쓰기
  public void commentAdd(BoardCommentDTO dto){
    SqlSession session= null;
    try {
      session= MybatisManager.getInstance().openSession();
      session.insert("board.commentAdd", dto);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session!= null) session.close();
    }
  }
  
  public BoardDTO view(int num) {
    BoardDTO dto = null;
    SqlSession session = null;
    try {
      session = MybatisManager.getInstance().openSession();
      dto= session.selectOne("board.view",num);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null)
        session.close();
    }
    return dto;
  }

  public void plusReadCount(int num, HttpSession httpSession) {
    SqlSession session = null;
    try {
      session = MybatisManager.getInstance().openSession();
      // 최근 조회 시간 확인
      long update_time = 0;
      if (httpSession.getAttribute("update_time_" + num) != null) {
        update_time = (long) httpSession.getAttribute("update_time_" + num);
      }
      // 현재 시각
      long current_time = System.currentTimeMillis();
      //최근 조회 시간을 시준으로 일정 시간이 경과했으면
      if (current_time - update_time > 1000*5){
        //조회수 증가 처리
        session.update("board.plusReadCount", num);
        session.commit();
        //최근 조회 시간을 업데이트
        httpSession.setAttribute("update_time_"+num, current_time);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null)
        session.close();
    }
  }

  public void plusDown(int num) {
    SqlSession session = null;
    try {
      session = MybatisManager.getInstance().openSession();
      session.update("board.plusDown", num);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null)
        session.close();
    }
  }

  public String getFileName(int num) {
    String result = null;
    SqlSession session = null;
    try {
      session = MybatisManager.getInstance().openSession();
      result = session.selectOne("board.getFileName", num);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null)
        session.close();
    }
    return result;
  }

  public void insert(BoardDTO dto) {
    SqlSession session = null;
    try {
      session = MybatisManager.getInstance().openSession();
      // 비속어 필터링
      String[] bad_words = { "대출", "저리", "광고", "히오스", "시공" };
      for (String str : bad_words) {
        if (dto.getSubject().indexOf(str) != -1) {
          String subject = dto.getSubject();
          subject = subject.replace(str, "고운말");
          dto.setSubject(subject);
        }
      }

      session.insert("board.insert", dto);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null)
        session.close();
    }
  }

  public List<BoardDTO> list() {
    List<BoardDTO> list = null;
    SqlSession session = null;
    try {
      session = MybatisManager.getInstance().openSession();
      list = session.selectList("board.list");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null)
        session.close();
    }
    return list;
  } // list()
  
  public void deleteBoard(int num){
    SqlSession session= null;
    try {
      session= MybatisManager.getInstance().openSession();
      session.delete("board.delete",num);
      session.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session!= null) session.close();
    }
  }
}
