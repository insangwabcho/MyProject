package insangjo.userframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SearchFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          SearchFrame frame = new SearchFrame("asdasd", "홍길동");
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public SearchFrame(String userid, String username) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 350);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));

    JScrollPane scrollPane = new JScrollPane();
    contentPane.add(scrollPane, BorderLayout.WEST);

    JPanel panel = new JPanel(new GridLayout(2, 0));
    contentPane.add(panel, BorderLayout.NORTH);

    JLabel lblNewLabel = new JLabel(username + " 님 주문내역조회");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(lblNewLabel);

    JComboBox comboBox = new JComboBox();
    panel.add(comboBox);

    JScrollPane scrollPane_1 = new JScrollPane();
    contentPane.add(scrollPane_1, BorderLayout.CENTER);

    table = new JTable();
    scrollPane_1.setViewportView(table);

    JPanel panel_1 = new JPanel();
    contentPane.add(panel_1, BorderLayout.SOUTH);
    panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    JLabel lblNewLabel_1 = new JLabel("현재 배송상태 : ");
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblNewLabel_1);

    JLabel lblStatus = new JLabel("배송 중");
    lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
    panel_1.add(lblStatus);

    JButton btnNewButton = new JButton("돌아가기");
    panel_1.add(btnNewButton);
  }
}
