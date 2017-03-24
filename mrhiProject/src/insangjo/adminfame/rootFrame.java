package insangjo.adminfame;

import java.awt.BorderLayout;//d
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sangjin.Client.Login;

import java.awt.Color;
import java.awt.SystemColor;

public class rootFrame extends JFrame {

  private JPanel contentPane;
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

    JButton btnNewButton_1 = new JButton("월별매출");
    btnNewButton_1.setOpaque(true);
    btnNewButton_1.setBackground(SystemColor.textHighlight);
    btnNewButton_1.setForeground(new Color(255, 255, 255));
    btnNewButton_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    panel.add(btnNewButton_1);

    JButton btnNewButton_2 = new JButton("배송관리");
    btnNewButton_2.setOpaque(true);
    btnNewButton_2.setBackground(SystemColor.textHighlight);
    btnNewButton_2.setForeground(new Color(255, 255, 255));
    btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new sangjin.Client.DeliveryChangeFrame().setVisible(true);
      }
    });
    panel.add(btnNewButton_2);

    JTextArea textArea = new JTextArea();
    textArea.setText("* 192.168.0.11님이 접속하셨습니다 *\n192.168.0.11 : 혹시 HDD없이 SSD만으로도 부팅이가능한가요?");
    contentPane.add(textArea, BorderLayout.CENTER);

    textField = new JTextField();
    textField.setText("192.168.0.11 님에게 : SSD만있어도 컴퓨터 부팅 가능합니다");
    contentPane.add(textField, BorderLayout.SOUTH);
    textField.setColumns(10);
    
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
  }

}
