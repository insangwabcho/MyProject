import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GuGuClient extends JFrame {

  private JPanel contentPane;
  private JTextField txtField;
  private int dan;
  private StringBuilder result;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GuGuClient frame = new GuGuClient();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private Connect con;
  private JTextArea txtArea;

  /**
   * Create the frame.
   */
  public GuGuClient() {
    con = new Connect("localhost");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JPanel panel = new JPanel();
    contentPane.add(panel, BorderLayout.NORTH);
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JLabel lblNewLabel = new JLabel("출력할 단수를 입력하세요");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    txtField = new JTextField();
    txtField.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(txtField);
    panel.setLayout(null);
    txtField.setColumns(3);

    JLabel lblNewLabel_1 = new JLabel("단");
    panel.add(lblNewLabel_1);

    JButton btn = new JButton("ㄱㄱ");
    btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //        try {
        //          dan = Integer.parseInt(txtField.getText());
        //        } catch (Exception e2) {
        //          e2.printStackTrace();
        //          JOptionPane.showMessageDialog(txtField, "정수형 숫자만 입력해주세요");
        //          return;
        //        }

        try {
          con.Gugu(Integer.parseInt(txtField.getText()));
        } catch (NumberFormatException e2) {
          JOptionPane.showMessageDialog(contentPane, "정수형숫자만입력!");
          return;
        }

        result = con.getResult();
        txtArea.setText(result.toString());

      }
    });
    panel.add(btn);

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    txtArea = new JTextArea();
    txtArea.setEnabled(false);
    txtArea.setEditable(false);
    scrollPane.setViewportView(txtArea);
    txtField.requestFocus();
  }

  class Connect {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private StringBuilder result = new StringBuilder("");

    public Connect(String ip) {
      try {
        socket = new Socket(ip, 1818);
      } catch (UnknownHostException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public StringBuilder getResult() {
      return result;
    }

    public void Gugu(int dan) {
      try {
        dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(dan);

        dis = new DataInputStream(socket.getInputStream());
        for (int i = 0; i < 9; i++) {
          result.append(dis.readUTF() + "\r\n");
        }
        //System.out.println(result);
        result.append("\r\n");

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

}
