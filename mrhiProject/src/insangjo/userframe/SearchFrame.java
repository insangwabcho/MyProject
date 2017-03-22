package insangjo.userframe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import insangjo.DAO.SearchFrameDAO;
import insangjo.DTO.CartDTO;

public class SearchFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;
  private JComboBox comboBox;

  public SearchFrame(String userid, String username) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 350);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.CENTER);

    table = new JTable();
    scrollPane.setViewportView(table);

    JPanel panel = new JPanel(new GridLayout(2, 0));
    contentPane.add(panel, BorderLayout.NORTH);

    JLabel lblNewLabel = new JLabel(username + " 님 주문내역조회");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    comboBox = new JComboBox();
    comboBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {

        }
      }
    });
    ArrayList<CartDTO> items = new SearchFrameDAO().dateOrder(userid);
    for (int i = 0; i < items.size(); i++) {
      comboBox.addItem(items.get(i).getBuydate() + "");
    }
    panel.add(comboBox);
  }
}
