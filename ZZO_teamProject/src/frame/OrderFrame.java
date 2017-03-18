package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class OrderFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private JLabel lblName;
  private JTextField tfAddress;
  private JLabel lblTotalPrice;
  private int userno;
  private String address;

  public OrderFrame(int userno, String username, DefaultTableModel model, String totalprice) {
    this.userno = userno;

    setIconImage(Toolkit.getDefaultToolkit().getImage(OrderFrame.class.getResource("/mainFrame/img/programIcon.ico")));
    setTitle("주문하기");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(120, 120, 450, 440);
    setResizable(false);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel label = new JLabel("님이 선택하신 물품");
    label.setFont(new Font("Courier New", Font.PLAIN, 15));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setBounds(179, 20, 130, 16);
    contentPane.add(label);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(6, 48, 438, 134);
    contentPane.add(scrollPane);

    table = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    scrollPane.setViewportView(table);

    JLabel lblAddress = new JLabel("인천시 부평구 부평5동 494-7 동일타운 102호");
    lblAddress.setFont(new Font("consolas", Font.BOLD, 14));
    lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
    lblAddress.setBounds(6, 230, 438, 32);
    contentPane.add(lblAddress);

    lblName = new JLabel(username);
    lblName.setFont(new Font("Courier New", Font.PLAIN, 15));
    lblName.setHorizontalAlignment(SwingConstants.CENTER);
    lblName.setBounds(125, 20, 61, 16);
    contentPane.add(lblName);

    JRadioButton rbtnDefaultaddress = new JRadioButton("기존 배송지");
    rbtnDefaultaddress.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (rbtnDefaultaddress.isSelected()) {
          lblAddress.setVisible(true);
          tfAddress.setVisible(false);
          address = lblAddress.getText();
        }
      }
    });
    rbtnDefaultaddress.setSelected(true);
    buttonGroup.add(rbtnDefaultaddress);
    rbtnDefaultaddress.setBounds(250, 196, 91, 23);
    contentPane.add(rbtnDefaultaddress);

    JRadioButton rbtnNewaddress = new JRadioButton("신규 배송지");
    rbtnNewaddress.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (rbtnNewaddress.isSelected()) {
          lblAddress.setVisible(false);
          tfAddress.setVisible(true);
          address = tfAddress.getText();
        }
      }
    });
    buttonGroup.add(rbtnNewaddress);
    rbtnNewaddress.setBounds(353, 196, 91, 23);
    contentPane.add(rbtnNewaddress);

    tfAddress = new JTextField();
    tfAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
    tfAddress.setHorizontalAlignment(SwingConstants.CENTER);
    tfAddress.setBounds(6, 234, 438, 26);
    contentPane.add(tfAddress);
    tfAddress.setColumns(10);

    JLabel label_1 = new JLabel("배송 요청사항");
    label_1.setForeground(Color.PINK);
    label_1.setHorizontalAlignment(SwingConstants.CENTER);
    label_1.setFont(new Font("Courier New", Font.PLAIN, 15));
    label_1.setBounds(167, 274, 130, 16);
    contentPane.add(label_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(6, 292, 438, 70);
    contentPane.add(scrollPane_1);

    JTextArea textArea = new JTextArea();
    scrollPane_1.setViewportView(textArea);

    JButton btnOk = new JButton("주문 확정");
    btnOk.setBounds(327, 374, 117, 29);
    contentPane.add(btnOk);

    JButton btnClose = new JButton("취소");
    btnClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    btnClose.setBounds(192, 374, 117, 29);
    contentPane.add(btnClose);

    JLabel label_2 = new JLabel("총 금액");
    label_2.setHorizontalAlignment(SwingConstants.CENTER);
    label_2.setBounds(26, 195, 74, 23);
    contentPane.add(label_2);

    lblTotalPrice = new JLabel(totalprice);
    lblTotalPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
    lblTotalPrice.setBounds(94, 195, 74, 23);
    contentPane.add(lblTotalPrice);

    tfAddress.setVisible(false);
  }
}
