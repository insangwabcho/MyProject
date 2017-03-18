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
  private JList list_2;
  private JScrollPane scrollPane_2;
  private JTable table;
  private Vector data, col;
  private JLabel lblTotalPirce;
  private DefaultTableModel model;
  private JLabel lblOptionTitle;
  private JComboBox cboMenu;
  private String usname;
  private JLabel lblEa;
  private int eaNum;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainFrame frame = new MainFrame("조인상아앙");
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
  }

  public MainFrame(String username) {
    usname = username;

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

    cboMenu = new JComboBox();
    cboMenu.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          ArrayList list = orderpageDao.prodList(e.getItem() + "");
          list_1 = new JList(list.toArray());
          list_1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
              if (e.getValueIsAdjusting()) {
                ArrayList items = orderpageDao.optionList(cboMenu.getSelectedItem() + "", list_1.getSelectedValue() + "");
                list_2 = new JList(items.toArray());
                scrollPane_2.setViewportView(list_2);
                lblOptionTitle.setText(cboMenu.getSelectedItem() + " " + list_1.getSelectedValue());
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
    detailPanel.setBounds(418, 276, 576, 346);
    contentPane.add(detailPanel);

    JLabel lblNewLabel = new JLabel("제품 상세페이지");

    String a = "/Users/insangjo/git/MyProject/ZZO_teamProject/bin/mainFrame/img/programIcon.ico";
    lblNewLabel.setText("<html> <img src= \"/Users/insangjo/git/MyProject/ZZO_teamProject/bin/mainFrame/img/mainBg.jpg\"></html>");
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

    lblUserStat = new JLabel(usname + " 님! 어서오세요!");
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
    table = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table.setFont(new Font("consolas", Font.PLAIN, 12));
    scrollPane_1.setViewportView(table);

    JButton btnOk = new JButton("완료");
    btnOk.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OrderFrame f = new OrderFrame(1, username, model, lblTotalPirce.getText());
        f.setVisible(true);

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
    optionPanel.setBounds(179, 6, 815, 266);
    contentPane.add(optionPanel);
    optionPanel.setLayout(null);

    JLabel lblNewLabel_2 = new JLabel("상세 옵션 선택");
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2.setBounds(6, 6, 803, 16);
    optionPanel.add(lblNewLabel_2);

    JPanel option2Panel = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        g.drawImage(panelBg.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };

    option2Panel.setBounds(6, 34, 803, 226);
    optionPanel.add(option2Panel);
    option2Panel.setLayout(null);

    lblOptionTitle = new JLabel("");
    lblOptionTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblOptionTitle.setBounds(6, 6, 803, 16);
    option2Panel.add(lblOptionTitle);

    scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(6, 34, 791, 119);
    option2Panel.add(scrollPane_2);

    list_2 = new JList();
    scrollPane_2.setViewportView(list_2);

    JLabel lblNewLabel_4 = new JLabel("주문하실 갯수 : ");
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_4.setBounds(550, 165, 92, 16);
    option2Panel.add(lblNewLabel_4);

    JLabel lblNewLabel_5 = new JLabel("ea");
    lblNewLabel_5.setBounds(692, 165, 19, 16);
    option2Panel.add(lblNewLabel_5);

    JButton btnSave = new JButton("장바구니에 담기");
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (lblEa.getText().equals("0")) {
          JOptionPane.showMessageDialog(MainFrame.this, "수량을 선택하지 않으셨습니다");
          return;
        }
        String ea = lblEa.getText().replaceAll("[^0-9]", "");
        ArrayList<String> items = new ArrayList<>();
        items.add(cboMenu.getSelectedItem() + "");
        items.add(list_1.getSelectedValue() + "");

        int start = (list_2.getSelectedValue() + "").indexOf(":") + 1;
        int end = (list_2.getSelectedValue() + "").indexOf(",");
        System.out.println((list_2.getSelectedValue() + "").substring(start, end).trim());
        items.add((list_2.getSelectedValue() + "").substring(start, end).trim());

        Vector item = orderpageDao.cartAdd(items, ea);
        refreshTable(item);
      }
    });
    btnSave.setBounds(582, 191, 117, 29);
    option2Panel.add(btnSave);

    JButton btnEaPlus = new JButton("▲");
    btnEaPlus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (list_2.isSelectionEmpty()) {
          JOptionPane.showMessageDialog(MainFrame.this, "상세페이지의 모델을 먼저 선택해주세요");
          return;
        }
        String selectModel = list_2.getSelectedValue() + "";
        int a = selectModel.indexOf("재고수량 : ");
        selectModel = selectModel.substring(a);
        selectModel = selectModel.replaceAll("[^0-9]", "");
        if (Integer.parseInt(selectModel) > eaNum) {
          eaNum++;
          lblEa.setText(eaNum + "");
          return;
        }
        else
          JOptionPane.showMessageDialog(MainFrame.this, "재고수량 보다 많이 선택하실수 없습니다.");
      }
    });
    btnEaPlus.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
    btnEaPlus.setBounds(723, 154, 15, 15);
    option2Panel.add(btnEaPlus);

    JButton btnEaMinus = new JButton("▼");
    btnEaMinus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (list_2.isSelectionEmpty()) {
          JOptionPane.showMessageDialog(MainFrame.this, "상세페이지의 모델을 먼저 선택해주세요");
          return;
        }
        if (eaNum == 0) {
          JOptionPane.showMessageDialog(MainFrame.this, "0개 이하로 선택하실수 없습니다");
          return;
        }
        else
          eaNum--;
        lblEa.setText(eaNum + "");
      }
    });
    btnEaMinus.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
    btnEaMinus.setBounds(723, 178, 15, 15);
    option2Panel.add(btnEaMinus);

    lblEa = new JLabel("0");
    lblEa.setHorizontalAlignment(SwingConstants.RIGHT);
    lblEa.setBounds(644, 165, 36, 16);
    option2Panel.add(lblEa);

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
