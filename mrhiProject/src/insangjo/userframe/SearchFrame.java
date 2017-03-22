package insangjo.userframe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 350);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));

    ArrayList<CartDTO> items = new SearchFrameDAO().dateOrder(userid);

    col = new Vector<>();
    col.add("종류");
    col.add("이름");
    col.add("가격");

    DefaultTableModel model = new DefaultTableModel(data, col);

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    table = new JTable(model);
    scrollPane.setViewportView(table);

    JPanel panel = new JPanel(new GridLayout(2, 0));
    contentPane.add(panel, BorderLayout.NORTH);

    JLabel lblNewLabel = new JLabel(username + " 님 주문내역조회");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    comboBox = new JComboBox();
    comboBox.setModel(new DefaultComboBoxModel(new String[] { "주문 날짜를 선택해주세요" }));
    comboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          data = new SearchFrameDAO().detailOrder(items.get(comboBox.getSelectedIndex() - 1));
          System.out.println(data.toString());
          DefaultTableModel model = new DefaultTableModel(data, col);
          table.setModel(model);
        }
      }
    });
    for (int i = 0; i < items.size(); i++) {
      comboBox.addItem(items.get(i).getBuydate() + ", 주문번호 : " + items.get(i).getOrder_no());
    }
    panel.add(comboBox);
  }
}
