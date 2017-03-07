import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GuGuServer extends JFrame {

  private JPanel contentPane;
  private JButton btnStart;
  static JLabel label;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GuGuServer frame = new GuGuServer();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public GuGuServer() throws Exception {
    setTitle("구구단서버/server");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 200, 70);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    btnStart = new JButton("Server On/Off");
    btnStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //System.out.println("서버스타트");

        if (label.getText().equals("On"))
          System.exit(0);

        Thread thh = new LabControll();
        thh.start();

      }
    });

    label = new JLabel("Off");
    contentPane.add(label);
    contentPane.add(btnStart);
  }

  class SvStart {
    private ServerSocket svSocket;
    private Socket socket;
    private InetAddress getIP;

    public SvStart() {
      try {
        svSocket = new ServerSocket(1818);
      } catch (IOException e) {
        e.printStackTrace();
      }

      while (true) {
        try {
          socket = svSocket.accept();
          getIP = socket.getInetAddress();

          System.out.println(Thread.currentThread().toString());
          Thread th = new Gugu(socket, getIP);
          th.start();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  class LabControll extends Thread {

    @Override
    public void run() {
      super.run();
      if (GuGuServer.label.getText().equals("Off"))
        GuGuServer.label.setText("On");
      else GuGuServer.label.setText("Off");

      new SvStart();

    }

  }

  class Gugu extends Thread {
    private Socket soc;
    private DataInputStream dis;
    private DataOutputStream dos;
    private InetAddress ip;
    private int dan = 0;

    public Gugu(Socket socket, InetAddress ip) throws IOException {
      soc = socket;
      this.ip = ip;
    }

    @Override
    public void run() {
      super.run();
      label.setText("On");
      try {
        dis = new DataInputStream(soc.getInputStream());
        dos = new DataOutputStream(soc.getOutputStream());
        while (true) {
          try {
            dan = dis.readInt();
          } catch (EOFException e1) {
            JOptionPane.showMessageDialog(contentPane, ip + "컴퓨터 접속 종료");
          }
          for (int i = 0; i < 9; i++) {
            dos.writeUTF(dan + " * " + (i + 1) + " = " + dan * (i + 1));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

}