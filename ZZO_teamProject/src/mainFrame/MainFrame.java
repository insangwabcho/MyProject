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
  private Color colMenu;

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
    contentPane.setBackground(new Color(244, 188, 192));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    colMenu = new Color(240, 210, 210);

    JPanel selectPanel = new JPanel();
    selectPanel.setBackground(colMenu);
    selectPanel.setBounds(6, 6, 168, 266);
    contentPane.add(selectPanel);
    selectPanel.setLayout(null);

    JComboBox cboMenu = new JComboBox();
    cboMenu.setModel(new DefaultComboBoxModel(new String[] { "부품을 선택해주세요", "CPU", "메인보드", "그래픽카드", "메모리카드" }));
    cboMenu.setBounds(6, 6, 156, 32);
    selectPanel.add(cboMenu);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(6, 49, 156, 206);
    selectPanel.add(scrollPane);

    JList list = new JList();
    scrollPane.setViewportView(list);

    JPanel detailPanel = new JPanel();
    detailPanel.setBackground(colMenu);
    detailPanel.setBounds(383, 6, 611, 616);
    contentPane.add(detailPanel);

    JLabel lblNewLabel = new JLabel("제품 상세페이지");
    detailPanel.add(lblNewLabel);

    JPanel boxpanel = new JPanel();
    boxpanel.setBackground(colMenu);
    boxpanel.setBounds(6, 276, 373, 346);
    contentPane.add(boxpanel);
    boxpanel.setLayout(null);

    lblUserStat = new JLabel("조인상님! 어서오세요!");
    lblUserStat.setFont(new Font("consolas", Font.BOLD, 20));
    lblUserStat.setHorizontalAlignment(SwingConstants.CENTER);
    lblUserStat.setBounds(6, 6, 355, 30);
    boxpanel.add(lblUserStat);

    JLabel lblNewLabel_1 = new JLabel("현재 장바구니 목록");
    lblNewLabel_1.setFont(new Font("consolas", Font.PLAIN, 15));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setBounds(6, 35, 355, 16);
    boxpanel.add(lblNewLabel_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(78, 63, 283, 229);
    boxpanel.add(scrollPane_1);

    JButton btnNewButton = new JButton("완료");
    btnNewButton.setBounds(279, 303, 82, 29);
    boxpanel.add(btnNewButton);

    JButton button = new JButton("초기화");
    button.setBounds(185, 303, 82, 29);
    boxpanel.add(button);

    JButton button_1 = new JButton("장바구니");
    button_1.setBounds(78, 303, 88, 29);
    boxpanel.add(button_1);

    JPanel optionPanel = new JPanel();
    optionPanel.setBackground(colMenu);
    optionPanel.setBounds(179, 6, 200, 266);
    contentPane.add(optionPanel);
  }
}
