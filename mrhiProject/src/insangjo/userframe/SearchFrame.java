package insangjo.userframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import insangjo.DAO.OrderFrameDAO;
import insangjo.DAO.SearchFrameDAO;
import insangjo.DTO.CartDTO;
import sangjin.DeliveryStatus.DeliveryChangeDAO;

public class SearchFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;
  private JComboBox comboBox;
  private Vector data, col;
  private String order_no;
  private JTextField tfstatus;
  private DeliveryChangeDAO dcdao;

  public SearchFrame(String userid, String username) {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 500, 377);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    SearchFrameDAO searchframeDao = new SearchFrameDAO();
    ArrayList<CartDTO> items = searchframeDao.dateOrder(userid);

    col = new Vector<>();
    col.add("종류");
    col.add("이름");
    col.add("가격");

    DefaultTableModel model = new DefaultTableModel(data, col);
    contentPane.setLayout(null);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(5, 53, 472, 218);
    contentPane.add(scrollPane);

    table = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table.setOpaque(true);
    table.setBackground(new Color(255, 255, 255));
    scrollPane.setViewportView(table);

    JPanel panel = new JPanel(new GridLayout(2, 0));
    panel.setBounds(5, 5, 472, 48);
    contentPane.add(panel);

    JLabel lblNewLabel = new JLabel(username + " 님 주문내역조회");
    lblNewLabel.setOpaque(true);
    lblNewLabel.setForeground(new Color(255, 255, 255));
    lblNewLabel.setBackground(new Color(0, 51, 255));
    lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    JLabel lblTotal = new JLabel("");
    lblTotal.setBackground(new Color(255, 255, 255));
    lblTotal.setBounds(117, 4, 156, 24);
    comboBox = new JComboBox();
    comboBox.setOpaque(true);
    comboBox.setForeground(new Color(0, 51, 255));
    comboBox.setBackground(new Color(255, 255, 255));
    comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
    comboBox.setModel(new DefaultComboBoxModel(new String[] { "주문 날짜를 선택해주세요" }));
    comboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          String a = comboBox.getSelectedItem() + "";
          int index = Integer.parseInt(a.substring(a.length() - 1));
          data = searchframeDao.detailOrder(items.get(comboBox.getSelectedIndex() - 1), index);
          order_no = (comboBox.getSelectedItem() + "").substring(19);
          dcdao = new DeliveryChangeDAO();
          String status = String.valueOf(dcdao.statusMember(order_no));
          tfstatus.setText(status);
          if (tfstatus.getText().equals("[배송대기]")) {
            tfstatus.setText("배송대기");
            tfstatus.setForeground(Color.red);
          }
          else if (tfstatus.getText().equals("[배송중]")) {
            tfstatus.setText("배송중");
            tfstatus.setForeground(Color.ORANGE);
          }
          else if (tfstatus.getText().equals("[배송완료]")) {
            tfstatus.setText("배송완료");
            tfstatus.setForeground(new Color(47, 157, 39));
          }
          DefaultTableModel model = new DefaultTableModel(data, col);
          table.setModel(model);
          table.getColumn("이름").setPreferredWidth(200);

          int total = 0;
          for (int i = 0; i < table.getRowCount(); i++) {
            total += Integer.parseInt(model.getValueAt(i, 2) + "");
          }
          lblTotal.setText(total + " 원");
        }
      }
    });
    for (int i = 0; i <= items.size() - 1; i++) {
      comboBox.addItem(items.get(i).getBuydate() + ", 주문번호 : " + items.get(i).getOrder_no());
    } //
    panel.add(comboBox);

    JPanel panel_1 = new JPanel();
    panel_1.setBackground(new Color(255, 255, 255));
    panel_1.setBounds(5, 271, 472, 59);
    contentPane.add(panel_1);
    panel_1.setLayout(null);

    JLabel lblNewLabel_1 = new JLabel("총 금액 : ");
    lblNewLabel_1.setBounds(0, 0, 157, 27);
    lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_1);

    lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblTotal);

    JButton btnNewButton = new JButton("주문취소");
    btnNewButton.setOpaque(true);
    btnNewButton.setBackground(new Color(0, 51, 255));
    btnNewButton.setForeground(new Color(255, 255, 255));
    btnNewButton.setBounds(314, 2, 157, 55);
    btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (tfstatus.getText().equals("배송중")) {
          JOptionPane.showMessageDialog(SearchFrame.this, "이미 배송이 시작된 상품입니다.");
          return;
        }
        else if (tfstatus.getText().equals("배송완료")) {
          JOptionPane.showMessageDialog(SearchFrame.this, "이미 배송이 완료되었습니다.");
          return;
        }
        String[] yn = { "예", "아니오" };
        if (JOptionPane.showOptionDialog(SearchFrame.this, "주문을 취소하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, yn, null) == 0) {

          OrderFrameDAO ofdao = new OrderFrameDAO();
          String t = comboBox.getSelectedItem() + "";
          int index = t.indexOf("주문번호 : ");
          t = t.substring(index);
          t = t.replaceAll("[^0-9]", "");
          t = t.trim();

          ofdao.getEA(t);
          searchframeDao.cancelcartOrder(order_no);
          JOptionPane.showMessageDialog(SearchFrame.this, "주문이 취소되었습니다");
          dispose();
        }
      }
    });
    panel_1.add(btnNewButton);

    JLabel lblNewLabel_2 = new JLabel("배송상태 :");
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_2.setBounds(32, 29, 78, 18);
    panel_1.add(lblNewLabel_2);

    tfstatus = new JTextField();
    tfstatus.setBackground(new Color(255, 255, 255));
    tfstatus.setHorizontalAlignment(SwingConstants.CENTER);
    tfstatus.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    tfstatus.setEditable(false);
    tfstatus.setBounds(117, 30, 156, 24);
    panel_1.add(tfstatus);
    tfstatus.setColumns(10);
  }
}
