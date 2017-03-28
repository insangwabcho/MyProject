package insangjo.adminfame;

import java.awt.BorderLayout;//d
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import kwanwoo.PushMsg;
import sangjin.Client.Login;
import sangjin.Member.MemberList;

public class rootFrame extends JFrame {

  private JPanel contentPane;
  private ArrayList<String> idList;
  private JTextArea textArea;
  private JTextField textField;

  public rootFrame() {

    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 704, 393);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JPanel panel = new JPanel(new GridLayout(3, 0));
    contentPane.add(panel, BorderLayout.EAST);

    textArea = new JTextArea();

    JButton btnNewButton = new JButton("재고관리 / 추가삭제");
    btnNewButton.setOpaque(true);
    btnNewButton.setForeground(new Color(255, 255, 255));
    btnNewButton.setBackground(SystemColor.textHighlight);
    btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new sungwon.Goods.Management().setVisible(true);
      }
    });
    panel.add(btnNewButton);

    JButton btnNewButton_2 = new JButton("배송관리");
    btnNewButton_2.setOpaque(true);
    btnNewButton_2.setBackground(SystemColor.textHighlight);
    btnNewButton_2.setForeground(new Color(255, 255, 255));
    btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new sangjin.DeliveryStatus.DeliveryChangeFrame().setVisible(true);
      }
    });
    panel.add(btnNewButton_2);

    JButton btnlist = new JButton("회원목록");
    btnlist.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new MemberList().setVisible(true);
      }
    });
    btnlist.setOpaque(true);
    btnlist.setBackground(SystemColor.textHighlight);
    btnlist.setForeground(new Color(255, 255, 255));
    btnlist.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    panel.add(btnlist);

    idList = new ArrayList<>();

    JPanel panel_1 = new JPanel();
    panel_1.setBackground(new Color(0, 51, 255));
    contentPane.add(panel_1, BorderLayout.NORTH);

    JLabel label = new JLabel("                          관리자 모드                                              ");
    label.setForeground(new Color(255, 255, 255));
    label.setBackground(new Color(0, 51, 255));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
    panel_1.add(label);

    JButton btnlogout = new JButton("로그아웃");
    btnlogout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sangjin.Client.Login f = new Login();
        f.setVisible(true);
        dispose();
      }
    });
    btnlogout.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    panel_1.add(btnlogout);

    JPanel panel_2 = new JPanel(new GridLayout(0, 2));
    contentPane.add(panel_2, BorderLayout.SOUTH);

    textField = new JTextField();
    panel_2.add(textField);
    textField.setColumns(10);

    JButton btnSend = new JButton("Send");
    btnSend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] arr = new String[2];
        try {
          String msg = textField.getText();
          int index = msg.indexOf(" ");
          arr[0] = msg.substring(1, index);
          arr[1] = msg.substring(index + 1);
        } catch (ArrayIndexOutOfBoundsException e2) {
          JOptionPane.showMessageDialog(rootFrame.this, "메세지 형식을 지켜주세요 \n /보낼아이디 메세지");
          return;
        }
        kwanwoo.PushMsg pm = new PushMsg("root", arr[1], arr[0]);

        textArea.append(arr[0] + " 님에게 : " + arr[1] + "\n");
        textField.setText("");
      }
    });
    panel_2.add(btnSend);

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    scrollPane.setViewportView(textArea);
    Thread th = new kwanwoo.PullMsg(textArea, "root");
    th.start();
  }
}
