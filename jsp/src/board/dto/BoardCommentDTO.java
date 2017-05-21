package board.dto;

public class BoardCommentDTO {

  private int comment_num;
  private int board_num;
  private String writer;
  private String content;
  private String reg_date;

  public BoardCommentDTO() {
    super();
    // TODO Auto-generated constructor stub
  }

  public BoardCommentDTO(int comment_num, int board_num, String writer, String content, String reg_date) {
    super();
    this.comment_num = comment_num;
    this.board_num = board_num;
    this.writer = writer;
    this.content = content;
    this.reg_date = reg_date;
  }

  @Override
  public String toString() {
    return "BoardCommentDTO [comment_num=" + comment_num + ", board_num=" + board_num + ", writer=" + writer
        + ", content=" + content + ", reg_date=" + reg_date + "]";
  }

  public int getComment_num() {
    return comment_num;
  }

  public void setComment_num(int comment_num) {
    this.comment_num = comment_num;
  }

  public int getBoard_num() {
    return board_num;
  }

  public void setBoard_num(int board_num) {
    this.board_num = board_num;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getreg_date() {
    return reg_date;
  }

  public void setreg_date(String reg_date) {
    this.reg_date = reg_date;
  }

}
