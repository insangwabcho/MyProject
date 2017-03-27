
package kwanwoo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiServer {
  //  public static void main(String[] args) {
  //    MultiServer ms = new MultiServer(); //서버객체
  //    ms.init();//실행
  //  }

  HashMap hm;
  ServerSocket serverSocket = null;
  Socket socket = null;
  HashMap idList = null;//

  //생성자
  public MultiServer() {
    hm = new HashMap();
    Collections.synchronizedMap(hm);
    idList = new HashMap<>();
  }

  public void init() {
    try {
      serverSocket = new ServerSocket(9999);
      System.out.println("서버가 시작되었습니다.");
      while (true) {//서버가 실행되는 동안 클라이언트들의 접속을 기다림.
        socket = serverSocket.accept();
        //클라이언트의 접속을 기다리다가 접속이 되면 Socket객체를 생성		
        System.out.println(socket.getInetAddress() + ":" + socket.getPort());
        //클라이언트 정보(ip,port)출력
        Thread msr = new MultiServerRec(socket);//쓰레드생성
        msr.start();//쓰레드사용
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getId(String ip) {
    String id = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "select id from member ";
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    return id;
  }

  //접속 된 모든 클라이언트들에게 메시지를 전달.
  public void sendAllMsg(String msg) {
    //출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
    Iterator it = hm.keySet().iterator();

    while (it.hasNext()) {
      try {
        DataOutputStream it_out = (DataOutputStream) hm.get(it.next());
        it_out.writeUTF(msg);

      } catch (Exception e) {
        System.out.println("예외 : " + e);
      }
    }
  }

  public void sendMsg(String name) {

  }

  //client로부터 읽어온 메시지를 다른 client(socket)에 보내는 역할을 하는 method	
  class MultiServerRec extends Thread {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    //생성자
    public MultiServerRec(Socket socket) {
      this.socket = socket;
      try {
        //소캣으로부터 입력스트림을 얻는다.
        in = new DataInputStream(socket.getInputStream());
        //출력스트림
        out = new DataOutputStream(socket.getOutputStream());
      } catch (Exception e) {
        System.out.println("예외 : " + e);
      }
    }

    public void run() {//thread를 사용하기위해서 run메소드 재정의
      String name = ""; //클라이언트로부터 받은 이름을 저장할 변수.
      try {
        name = in.readUTF();//client에서 처음으로보내는 메시지는 client가 사용할 이름.
        idList = new HashMap<>();

        //sendAllMsg(name + "님이 입장하셨습니다.");
        //현재객체가 가지고 있는 소캣을 제외하고 다른 소켓들에게 접속을 알림.
        hm.put(name, out);//해쉬맵에 키를  name으로  출력스트림 객체를 저장.
        //System.out.println("현재 접속자 수는" + hm.size() + "명 입니다.");
        while (in != null) {//입력스트림이 null이 아니면 반복
          sendAllMsg(in.readUTF());//현재 소켓에서 읽어본메시지를 해쉬맵에 저장된 모든
          //출력스트림으로 보낸다.
        }
      } catch (Exception e) {
        System.out.println(e + "---->");
      } finally {
        //예외가 발샐할 때 퇴장, 해쉬맵에서 해당 데이터 제거.
        //보통 종료하거나 나가면 java.net.SocketException: 예외발생
        hm.remove(name);
        sendAllMsg(name + "님이 퇴장하셨습니다.");
        //System.out.println("현재 접속자 수는" + hm.size() + "명 입니다.");
      }
    }
  }
}
