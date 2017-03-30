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
    textArea.setEditable(false);

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

    JPanel panel_2 = new JPanel(new GridLayout(0, 3));
    contentPane.add(panel_2, BorderLayout.SOUTH);

    JButton btnSend = new JButton("Send");

    textField = new JTextField();
    textField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnSend.doClick();
      }
    });
    panel_2.add(textField);
    textField.setColumns(10);

    btnSend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] arr = new String[2];
        String msg = textField.getText();
        try {
          int index = msg.indexOf(" ");//
          arr[0] = msg.substring(1, index);
          arr[1] = msg.substring(index + 1);
          for (int i = 0; i < idList.size(); i++) {
            if (arr[0].equals(idList.get(i)))
              ;
            else {
              JOptionPane.showMessageDialog(rootFrame.this, "회원목록에 존재하지 않는 회원입니다.");
              return;
            }
            System.out.println(idList.get(i));
          }
        } catch (ArrayIndexOutOfBoundsException e2) {
          int result = JOptionPane.showConfirmDialog(rootFrame.this, "전체회원에게 공지사항을 보내시겠습니까?");
          if (result == JOptionPane.YES_OPTION) {
            kwanwoo.PushMsg pm = new PushMsg("root", msg, "all_members");
            textField.setText("");
            return;
          }
          return;
        } catch (StringIndexOutOfBoundsException e3) {
          int result = JOptionPane.showConfirmDialog(rootFrame.this, "전체회원에게 공지사항을 보내시겠습니까?");
          if (result == JOptionPane.YES_OPTION) {
            kwanwoo.PushMsg pm = new PushMsg("root", msg, "all_members");
            textField.setText("");
            return;
          }
          return;
        }
        kwanwoo.PushMsg pm = new PushMsg("root", arr[1], arr[0]);

        textArea.append(arr[0] + " 님에게 : " + arr[1] + "\n");
        textField.setText("");
      }
    });
    panel_2.add(btnSend);

    JButton delGongji = new JButton("공지사항삭제");
    delGongji.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int result = new kwanwoo.PushMsg().delGongji();
        if (result == 1) {
          JOptionPane.showMessageDialog(rootFrame.this, "공지사항이 삭제되었습니다");
        }
      }
    });
    panel_2.add(delGongji);

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    scrollPane.setViewportView(textArea);
    Thread th = new kwanwoo.PullMsg(textArea, "root", idList);
    th.start();
  }
}
