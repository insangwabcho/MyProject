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

public class rootFrame extends JFrame {

  private JPanel contentPane;
  private JTextField textField;

  public rootFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 704, 376);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JLabel label = new JLabel("청라점 관리자님 반갑습니당");
    label.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    contentPane.add(label, BorderLayout.NORTH);

    JPanel panel = new JPanel(new GridLayout(3, 0));
    contentPane.add(panel, BorderLayout.EAST);

    JButton btnNewButton = new JButton("재고관리 / 추가삭제");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new sungwon.goods.Management().setVisible(true);
      }
    });
    panel.add(btnNewButton);

    JButton btnNewButton_1 = new JButton("월별매출");
    panel.add(btnNewButton_1);

    JButton btnNewButton_2 = new JButton("배송관리");
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
  }

}
