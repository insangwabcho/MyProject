package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DAO.OrderpageDAO;

public class MainFrame extends JFrame {

  private JPanel contentPane;
  private JLabel lblUserStat;
  private Color colMenu;
  private ImageIcon panelBg;
  private OrderpageDAO orderpageDao;
  private JList list_1;
  private JScrollPane scrollPane;
  private JTextField tfEa;
  private JList list_2;
  private JScrollPane scrollPane_2;
  private JTable table;
  private Vector data, col;
  private JLabel lblTotalPirce;
  private DefaultTableModel model;

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
    panelBg = new ImageIcon(MainFrame.class.getResource("/mainFrame/img/panelBg.jpeg"));
    orderpageDao = new OrderpageDAO();
    col = new Vector<>();
    col.add("종류");
    col.add("이름");
    col.add("수량");
    col.add("가격");
    data = new Vector<>();

    setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/mainFrame/img/programIcon.ico")));
    setTitle("comNawa 주문프로그램");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1000, 651);
    setResizable(false);
    contentPane = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(MainFrame.class.getResource("/mainFrame/img/mainBg.jpg"));
        g.drawImage(icon.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };
    //    contentPane.setBackground(new Color(244, 188, 192));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    colMenu = new Color(240, 210, 210);

    JPanel selectPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        g.drawImage(panelBg.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };
    //selectPanel.setBackground(colMenu);
    selectPanel.setBounds(6, 6, 168, 266);
    contentPane.add(selectPanel);
    selectPanel.setLayout(null);

    JComboBox cboMenu = new JComboBox();
    cboMenu.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {

          ArrayList list = orderpageDao.prodList(e.getItem() + "");
          list_1 = new JList(list.toArray());
          list_1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
              if (e.getValueIsAdjusting()) {
                tfEa.isEnabled();
                ArrayList items = orderpageDao.optionList(list_1.getSelectedValue() + "");
                list_2 = new JList(items.toArray());
                scrollPane_2.setViewportView(list_2);
              }
            }
          });
          scrollPane.setViewportView(list_1);
        }
      }
    });
    cboMenu.setModel(new DefaultComboBoxModel(new String[] { "부품을 선택해주세요", "CPU", "메인보드", "그래픽카드", "메모리카드", "HDD", "SSD" }));
    cboMenu.setBounds(6, 6, 156, 32);
    selectPanel.add(cboMenu);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(6, 49, 156, 206);
    selectPanel.add(scrollPane);

    list_1 = new JList();
    scrollPane.setViewportView(list_1);

    JPanel detailPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        g.drawImage(panelBg.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };
    //detailPanel.setBackground(colMenu);
    detailPanel.setBounds(418, 6, 576, 616);
    contentPane.add(detailPanel);

    JLabel lblNewLabel = new JLabel("제품 상세페이지");
    detailPanel.add(lblNewLabel);

    JPanel boxpanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        g.drawImage(panelBg.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };
    //boxpanel.setBackground(colMenu);
    boxpanel.setBounds(6, 276, 406, 346);
    contentPane.add(boxpanel);
    boxpanel.setLayout(null);

    lblUserStat = new JLabel("조인상님! 어서오세요!");
    lblUserStat.setFont(new Font("consolas", Font.BOLD, 20));
    lblUserStat.setHorizontalAlignment(SwingConstants.CENTER);
    lblUserStat.setBounds(6, 6, 394, 30);
    boxpanel.add(lblUserStat);

    JLabel lblNewLabel_1 = new JLabel("현재 장바구니 목록");
    lblNewLabel_1.setFont(new Font("consolas", Font.PLAIN, 15));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setBounds(6, 35, 394, 16);
    boxpanel.add(lblNewLabel_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(6, 63, 394, 229);
    boxpanel.add(scrollPane_1);

    model = new DefaultTableModel(data, col);
    table = new JTable(model);
    table.setFont(new Font("consolas", Font.PLAIN, 12));
    scrollPane_1.setViewportView(table);

    JButton btnOk = new JButton("완료");
    btnOk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    btnOk.setBounds(279, 303, 108, 29);
    boxpanel.add(btnOk);

    JButton button = new JButton("초기화");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        data = new Vector<>();
        model = new DefaultTableModel(data, col);
        table.setModel(model);
        lblTotalPirce.setText("");
      }
    });
    button.setBounds(159, 304, 108, 29);
    boxpanel.add(button);

    JLabel lblNewLabel_3 = new JLabel("총 금액");
    lblNewLabel_3.setBounds(6, 308, 37, 16);
    boxpanel.add(lblNewLabel_3);

    lblTotalPirce = new JLabel("");
    lblTotalPirce.setBounds(51, 308, 96, 16);
    boxpanel.add(lblTotalPirce);

    JPanel optionPanel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        g.drawImage(panelBg.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };
    //optionPanel.setBackground(colMenu);
    optionPanel.setBounds(179, 6, 233, 266);
    contentPane.add(optionPanel);
    optionPanel.setLayout(null);

    JLabel lblNewLabel_2 = new JLabel("상세 옵션 선택");
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2.setBounds(6, 6, 221, 16);
    optionPanel.add(lblNewLabel_2);

    JPanel option2Panel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        g.drawImage(panelBg.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };

    option2Panel.setBounds(6, 34, 221, 226);
    optionPanel.add(option2Panel);
    option2Panel.setLayout(null);

    JLabel lblOptionTitle = new JLabel("Intel CPU");
    lblOptionTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblOptionTitle.setBounds(6, 6, 209, 16);
    option2Panel.add(lblOptionTitle);

    scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(6, 34, 209, 119);
    option2Panel.add(scrollPane_2);

    list_2 = new JList();
    scrollPane_2.setViewportView(list_2);

    JLabel lblNewLabel_4 = new JLabel("주문하실 갯수 : ");
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_4.setBounds(26, 165, 92, 16);
    option2Panel.add(lblNewLabel_4);

    tfEa = new JTextField();
    tfEa.setHorizontalAlignment(SwingConstants.RIGHT);
    tfEa.setBounds(130, 160, 50, 26);
    option2Panel.add(tfEa);
    tfEa.setColumns(2);

    JLabel lblNewLabel_5 = new JLabel("ea");
    lblNewLabel_5.setBounds(182, 165, 19, 16);
    option2Panel.add(lblNewLabel_5);

    JButton btnSave = new JButton("장바구니에 담기");
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (tfEa.getText().length() == 0) {
          JOptionPane.showMessageDialog(MainFrame.this, "수량을 선택하지 않으셨습니다");
          return;
        }
        String ea = tfEa.getText().replaceAll("[^0-9]", "");
        ArrayList<String> items = new ArrayList<>();
        items.add(cboMenu.getSelectedItem() + "");
        items.add(list_1.getSelectedValue() + "");
        items.add(list_2.getSelectedValue() + "");

        Vector item = orderpageDao.cartAdd(items, ea);
        refreshTable(item);
      }
    });
    btnSave.setBounds(53, 191, 117, 29);
    option2Panel.add(btnSave);

  }

  public void refreshTable(Vector item) {
    String result = item.toString();
    result = result.replaceAll("\\[", "");
    result = result.replaceAll("\\]", "");
    String[] arr = result.split(",");

    Vector t = new Vector<>();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = arr[i].trim();
      t.add(arr[i]);
    }

    model.addRow(t);
    table.setModel(model);
    lblTotalChange();
  }

  public void lblTotalChange() {
    int modelCount = table.getRowCount();
    int result = 0;
    for (int i = 0; i < modelCount; i++) {
      result += Integer.parseInt(table.getValueAt(i, 3) + "");
    }

    lblTotalPirce.setText(result + " 원");
  }
}
