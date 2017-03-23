package insangjo.userframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import insangjo.DAO.OrderFrameDAO;

public class OrderFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private JLabel lblName;
  private JTextField tfAddress;
  private JLabel lblTotalPrice;
  private int userno;
  private String address;
  private OrderFrameDAO ofDao;

  public OrderFrame(int userno, String username, DefaultTableModel model, String totalprice, String userid, String defaultAddress) {
    this.userno = userno;
    address = defaultAddress;
    //setIconImage(Toolkit.getDefaultToolkit().getImage(OrderFrame.class.getResource("/mainFrame/img/programIcon.ico")));
    setTitle("주문하기");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(120, 120, 450, 440);
    setResizable(false);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(255, 255, 255));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    ofDao = new OrderFrameDAO();

    JLabel label = new JLabel("님이 선택하신 물품");
    label.setFont(new Font("굴림", Font.PLAIN, 15));
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

    JLabel lblAddress = new JLabel(defaultAddress);
    lblAddress.setFont(new Font("굴림", Font.BOLD, 14));
    lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
    lblAddress.setBounds(6, 230, 438, 32);
    contentPane.add(lblAddress);

    lblName = new JLabel(username);
    lblName.setFont(new Font("굴림", Font.PLAIN, 15));
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
        }
      }
    });
    buttonGroup.add(rbtnNewaddress);
    rbtnNewaddress.setBounds(353, 196, 91, 23);
    contentPane.add(rbtnNewaddress);

    tfAddress = new JTextField();
    tfAddress.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        System.out.println("dd");
        address = tfAddress.getText();
      }
    });
    tfAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
    tfAddress.setHorizontalAlignment(SwingConstants.CENTER);
    tfAddress.setBounds(6, 234, 438, 26);
    contentPane.add(tfAddress);
    tfAddress.setColumns(10);

    JLabel label_1 = new JLabel("배송 요청사항");
    label_1.setForeground(Color.PINK);
    label_1.setHorizontalAlignment(SwingConstants.CENTER);
    label_1.setFont(new Font("굴림", Font.PLAIN, 15));
    label_1.setBounds(167, 274, 130, 16);
    contentPane.add(label_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(6, 292, 438, 70);
    contentPane.add(scrollPane_1);

    JTextArea textArea = new JTextArea();
    scrollPane_1.setViewportView(textArea);

    JButton btnOk = new JButton("주문 확정");
    btnOk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        StringBuilder sb = new StringBuilder("select ");
        StringBuilder sb1 = new StringBuilder();
        String[] option = { "c.serial cpu ", "m.serial 메인보드 ", "v.serial 그래픽카드 ", "r.serial 메모리카드 ", "h.serial HDD ", "s.serial SSD ", "rtwo.serial 추가RAM " };
        String[] where = { " c.name=", " m.name=", " v.name=", " r.name=", " h.name=", " s.name=", " rtwo.name=" };
        //select option[] from 
        int aCount = 0;

        String[] append = new String[model.getRowCount()];
        for (int k = 0; k < append.length; k++) {
          append[k] = "";
        }
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
          switch (model.getValueAt(i, 0) + "") {
          case "CPU":
            append[i] = "CPU";
            list.add("CPU");
            aCount++;
            break;
          case "메인보드":
            append[i] = "메인보드";
            list.add("메인보드");
            aCount++;
            break;
          case "그래픽카드":
            append[i] = "그래픽카드";
            list.add("그래픽카드");
            aCount++;
            break;
          case "메모리카드":
            append[i] = "메모리카드";
            list.add("메모리카드");
            aCount++;
            break;
          case "HDD":
            append[i] = "HDD";
            list.add("HDD");
            aCount++;
            break;
          case "SSD":
            append[i] = "SSD";
            list.add("SSD");
            aCount++;
            break;
          case "추가 RAM":
            append[i] = "추가RAM";
            list.add("추가RAM");
            aCount++;
            break;
          }
          items.add(model.getValueAt(i, 1) + "");
        }

        int select = aCount;
        for (int i = 0; i < append.length; i++) {
          switch (append[i]) {
          case "CPU":
            sb.append(option[0]);
            sb1.append(where[0] + "'" + items.get(i) + "'");
            aCount--;
            break;
          case "메인보드":
            sb.append(option[1]);
            sb1.append(where[1] + "'" + items.get(i) + "'");
            aCount--;
            break;
          case "그래픽카드":
            sb.append(option[2]);
            sb1.append(where[2] + "'" + items.get(i) + "'");
            aCount--;
            break;
          case "메모리카드":
            sb.append(option[3]);
            sb1.append(where[3] + "'" + items.get(i) + "'");
            aCount--;
            break;
          case "HDD":
            sb.append(option[4]);
            sb1.append(where[4] + "'" + items.get(i) + "'");
            aCount--;
            break;
          case "SSD":
            sb.append(option[5]);
            sb1.append(where[5] + "'" + items.get(i) + "'");
            aCount--;
            break;
          case "추가RAM":
            sb.append(option[6]);
            sb1.append(where[6] + "'" + items.get(i) + "'");
            aCount--;
            break;
          }
          if (aCount != 0) {
            sb.append(",");
            sb1.append(" and");
          }

        } //
        int totalPrice = Integer.parseInt(totalprice.replaceAll("[^0-9]", ""));
        System.out.println(items.toString());
        System.out.println(list.toString());
        int result = ofDao.addOrder(sb.toString(), sb1.toString(), items.size(), list, userid, totalPrice, address);
        if (result == 1) {
          if (ofDao.addDelevery(ofDao.getOrderNum() - 1) == 1)
            JOptionPane.showMessageDialog(OrderFrame.this, "주문이 성공하였습니다");
          else
            JOptionPane.showMessageDialog(OrderFrame.this, "주문이 실패하였습니다");
        }
        dispose();
      }
    });
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
    lblTotalPrice.setFont(new Font("굴림", Font.PLAIN, 15));
    lblTotalPrice.setBounds(94, 195, 74, 23);
    contentPane.add(lblTotalPrice);

    tfAddress.setVisible(false);
  }
}
