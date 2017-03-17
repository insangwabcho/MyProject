package mainFrame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

  private JPanel contentPane;
  private JLabel lblUserStat;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainFrame frame = new MainFrame();
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
  public MainFrame() {
    setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/mainFrame/icons8-windows-8-computer-hardware-monitor.ico")));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1000, 650);
    setResizable(false);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(90, 220, 230));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel leftPanel = new JPanel();
    leftPanel.setBackground(new Color(180, 220, 250));
    leftPanel.setBounds(6, 6, 365, 266);
    contentPane.add(leftPanel);
    leftPanel.setLayout(null);

    JComboBox cboMenu = new JComboBox();
    cboMenu.setModel(new DefaultComboBoxModel(new String[] { "부품을 선택해주세요", "CPU", "메인보드", "그래픽카드", "메모리카드" }));
    cboMenu.setBounds(6, 6, 147, 32);
    leftPanel.add(cboMenu);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(6, 49, 147, 206);
    leftPanel.add(scrollPane);

    JList list = new JList();
    scrollPane.setViewportView(list);

    JPanel centerPanel = new JPanel();
    centerPanel.setBackground(new Color(180, 220, 250));
    centerPanel.setBounds(383, 6, 611, 616);
    contentPane.add(centerPanel);

    JLabel lblNewLabel = new JLabel("제품 상세페이지");
    centerPanel.add(lblNewLabel);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(180, 220, 250));
    panel.setBounds(6, 284, 367, 338);
    contentPane.add(panel);
    panel.setLayout(null);

    lblUserStat = new JLabel("조인상님! 어서오세요!");
    lblUserStat.setFont(new Font("consolas", Font.BOLD, 20));
    lblUserStat.setHorizontalAlignment(SwingConstants.CENTER);
    lblUserStat.setBounds(6, 6, 355, 30);
    panel.add(lblUserStat);

    JLabel lblNewLabel_1 = new JLabel("장바구니 목록");
    lblNewLabel_1.setFont(new Font("consolas", Font.PLAIN, 15));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setBounds(6, 35, 355, 16);
    panel.add(lblNewLabel_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(78, 63, 283, 229);
    panel.add(scrollPane_1);

    JButton btnNewButton = new JButton("완료");
    btnNewButton.setBounds(279, 303, 82, 29);
    panel.add(btnNewButton);

    JButton button = new JButton("초기화");
    button.setBounds(185, 303, 82, 29);
    panel.add(button);

    JButton button_1 = new JButton("장바구니...");
    button_1.setBounds(78, 303, 88, 29);
    panel.add(button_1);
  }
}
