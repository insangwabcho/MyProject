package exam;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GuGuClient extends JFrame {

  private JPanel contentPane;
  private JComboBox cboDan;
  private JTextArea taResult;

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

  /**
   * Create the frame.
   */
  public GuGuClient() {
    setTitle("구구단 Client");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 164, 287);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblNewLabel = new JLabel("단을 입력하세요");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(6, 6, 91, 16);
    contentPane.add(lblNewLabel);

    cboDan = new JComboBox();
    cboDan.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {

        //e.getStateChange() 콤보박스의 상태가 바뀔 때
        //ItemEvent.SELECTED  선택
        //ItemEvent.DESELECTED 선택 해제

        if (e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("콤보박스 선택");
          int idx = cboDan.getSelectedIndex();
          String dan = cboDan.getSelectedItem().toString();

          //taResult.setText("인덱스:" + idx + "\n" + "내용:" + dan + "\n");

          try {
            Socket socket = new Socket("localhost", 9999);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.write(idx);
            //System.out.println(dis.readUTF());

          } catch (Exception e1) {
            e1.printStackTrace();
          }
        } // try-catch

      } // itemStateChanged(e)
    });
    cboDan.setModel(new DefaultComboBoxModel(new String[] { "select", "2", "3", "4", "5", "6", "7", "8", "9" }));
    cboDan.setBounds(100, 2, 58, 27);
    contentPane.add(cboDan);

    taResult = new JTextArea();
    taResult.setEditable(false);
    taResult.setBounds(6, 34, 152, 225);
    contentPane.add(taResult);

  }
}
