package insangjo.userframe;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import insangjo.DAO.SearchFrameDAO;
import insangjo.DTO.CartDTO;

public class SearchFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;
  private JComboBox comboBox;
  private Vector data, col;

  public SearchFrame(String userid, String username) {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 500, 350);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));
    SearchFrameDAO searchframeDao = new SearchFrameDAO();
    ArrayList<CartDTO> items = searchframeDao.dateOrder(userid);

    col = new Vector<>();
    col.add("종류");
    col.add("이름");
    col.add("가격");

    DefaultTableModel model = new DefaultTableModel(data, col);

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    table = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    scrollPane.setViewportView(table);

    JPanel panel = new JPanel(new GridLayout(2, 0));
    contentPane.add(panel, BorderLayout.NORTH);

    JLabel lblNewLabel = new JLabel(username + " 님 주문내역조회");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    JLabel lblTotal = new JLabel("");
    comboBox = new JComboBox();
    comboBox.setModel(new DefaultComboBoxModel(new String[] { "주문 날짜를 선택해주세요" }));
    comboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          String a = comboBox.getSelectedItem() + "";
          int index = Integer.parseInt(a.substring(a.length() - 1));
          data = searchframeDao.detailOrder(items.get(comboBox.getSelectedIndex() - 1), index);
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
    }
    panel.add(comboBox);

    JPanel panel_1 = new JPanel(new GridLayout(0, 3));
    contentPane.add(panel_1, BorderLayout.SOUTH);

    JLabel lblNewLabel_1 = new JLabel("총 금액 : ");
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_1);

    lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblTotal);

    JButton btnNewButton = new JButton("창 닫기");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    panel_1.add(btnNewButton);
  }
}
