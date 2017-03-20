package insangjo.userframe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
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

import insangjo.DAO.MainFrameDAO;

public class MainFrame extends JFrame {

  private JPanel contentPane;
  private JLabel lblUserStat;
  private Color colMenu;
  private ImageIcon panelBg;
  private MainFrameDAO orderpageDao;
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
  private int eaNum = 1;
  private JLabel lblCheckCPU;
  private JLabel lblCheckMain;
  private JLabel lblCheckGraphic;
  private JLabel lblCheckRam;
  private JLabel lblCheckHDD;
  private JLabel lblCheckSSD;
  private String id = "dd";

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainFrame frame = new MainFrame("ㅇ");
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

  public MainFrame(String username) {
    usname = username;
    panelBg = new ImageIcon(MainFrame.class.getResource("/insangjo/img/panelBg.jpeg"));
    orderpageDao = new MainFrameDAO();
    col = new Vector<>();
    col.add("종류");
    col.add("이름");
    col.add("수량");
    col.add("가격");
    data = new Vector<>();

    setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/insangjo/img/programIcon.ico")));
    setTitle("comNawa 주문프로그램");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1000, 651);
    setResizable(false);
    contentPane = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(MainFrame.class.getResource("/insangjo/img/mainBg.jpg"));
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
          String a = e.getItem() + "";
          boolean b = lblCheckRam.getText().equals("");
          if (a.equals("추가 SSD")) {
            a = "RAM";
          }
          if (b) {
            JOptionPane.showMessageDialog(MainFrame.this, "기본 메모리카드 먼저 선택해주세요");
            return;
          }
          ArrayList list = orderpageDao.prodList(e.getItem() + "");
          list_1 = new JList(list.toArray());
          list_1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
              try {
                for (int i = 0; i < table.getRowCount(); i++) {
                  if (table.getValueAt(i, 0).equals(cboMenu.getSelectedItem() + "")) {
                    JOptionPane.showMessageDialog(MainFrame.this, "이미 [" + cboMenu.getSelectedItem() + "] 를 선택하셨습니다.");
                    return;
                  }
                }
              } catch (NumberFormatException e1) {
              }
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
    cboMenu.setModel(new DefaultComboBoxModel(new String[] { "부품을 선택해주세요", "CPU", "메인보드", "그래픽카드", "메모리카드", "HDD", "SSD", "추가 RAM" }));
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

    String a = "/Users/insangjo/git/MyProject/ZZO_teamProject/bin/insangjo/img/programIcon.ico";
    lblNewLabel.setText("<html> <img src= \"/Users/insangjo/git/MyProject/ZZO_teamProject/bin/insangjo/img/mainBg.jpg\"></html>");
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
    lblUserStat.setFont(new Font("Lucida Grande", Font.BOLD, 20));
    lblUserStat.setHorizontalAlignment(SwingConstants.CENTER);
    lblUserStat.setBounds(6, 6, 394, 30);
    boxpanel.add(lblUserStat);

    JLabel lblNewLabel_1 = new JLabel("현재 장바구니 목록");
    lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setBounds(6, 35, 394, 16);
    boxpanel.add(lblNewLabel_1);

    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(6, 63, 394, 233);
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
        if (model.getRowCount() == 0) {
          JOptionPane.showMessageDialog(MainFrame.this, "장바구니에 상품이 없습니다");
          return;
        }
        if (lblCheckCPU.getText().equals("선택됨") && lblCheckGraphic.getText().equals("선택됨") && lblCheckHDD.getText().equals("선택됨") && lblCheckMain.getText().equals("선택됨")
            && lblCheckRam.getText().equals("선택됨") && lblCheckSSD.getText().equals("선택됨")) {
        }
        else {
          int result = JOptionPane.showConfirmDialog(MainFrame.this, "선택되지 않은 옵션이 있습니다. 계속하시겠습니까?");
          if (result == JOptionPane.OK_OPTION) {
          }
          else
            return;
        }
        OrderFrame f = new OrderFrame(1, username, model, lblTotalPirce.getText(), id);
        f.setVisible(true);
      }
    });
    btnOk.setBounds(325, 308, 75, 29);
    boxpanel.add(btnOk);

    JButton button = new JButton("초기화");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        data = new Vector<>();
        model = new DefaultTableModel(data, col);
        table.setModel(model);
        lblTotalPirce.setText("");
        lblCheckCPU.setText("");
        lblCheckGraphic.setText("");
        lblCheckHDD.setText("");
        lblCheckMain.setText("");
        lblCheckRam.setText("");
        lblCheckSSD.setText("");
      }
    });
    button.setBounds(141, 308, 80, 29);
    boxpanel.add(button);

    JLabel lblNewLabel_3 = new JLabel("총 금액");
    lblNewLabel_3.setBounds(6, 308, 37, 16);
    boxpanel.add(lblNewLabel_3);

    lblTotalPirce = new JLabel("");
    lblTotalPirce.setBounds(51, 308, 96, 16);
    boxpanel.add(lblTotalPirce);

    JButton button_1 = new JButton("상품삭제");
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() == -1) {
          JOptionPane.showMessageDialog(MainFrame.this, "삭제하실 상품을 선택해주세요");
        }
        else {
          model.removeRow(table.getSelectedRow());
          table.setModel(model);
        }
      }
    });
    button_1.setBounds(233, 308, 80, 29);
    boxpanel.add(button_1);

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
        items.add((list_2.getSelectedValue() + "").substring(start, end).trim());

        Vector item = orderpageDao.cartAdd(items, ea);
        refreshTable(item);

        list_2 = new JList();
        scrollPane_2.setViewportView(list_2);
      }
    });
    btnSave.setBounds(576, 165, 188, 44);
    option2Panel.add(btnSave);

    lblEa = new JLabel("1");
    lblEa.setHorizontalAlignment(SwingConstants.RIGHT);
    lblEa.setBounds(644, 165, 36, 16);

    JPanel panel = new JPanel(new GridLayout(2, 0)) {
      @Override
      protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(MainFrame.class.getResource("/insangjo/img/panelBg.jpeg"));
        g.drawImage(icon.getImage(), 0, 0, null);
        setOpaque(false);

        super.paintComponent(g);
      }
    };

    panel.setBounds(6, 154, 532, 66);
    option2Panel.add(panel);

    JLabel lblcpu = new JLabel("  CPU :");
    lblcpu.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblcpu);

    lblCheckCPU = new JLabel("");
    lblCheckCPU.setForeground(Color.RED);
    lblCheckCPU.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckCPU);

    JLabel lblNewLabel_6 = new JLabel("메인보드 : ");
    lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_6);

    lblCheckMain = new JLabel("");
    lblCheckMain.setForeground(Color.RED);
    lblCheckMain.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckMain);

    JLabel lblNewLabel_8 = new JLabel("그래픽카드 : ");
    lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_8);

    lblCheckGraphic = new JLabel("");
    lblCheckGraphic.setForeground(Color.RED);
    lblCheckGraphic.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckGraphic);

    JLabel lblNewLabel_10 = new JLabel("  메모리카드 : ");
    lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_10);

    lblCheckRam = new JLabel("");
    lblCheckRam.setForeground(Color.RED);
    lblCheckRam.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckRam);

    JLabel lblNewLabel_9 = new JLabel("HDD : ");
    lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_9);

    lblCheckHDD = new JLabel("");
    lblCheckHDD.setForeground(Color.RED);
    lblCheckHDD.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckHDD);

    JLabel lblNewLabel_12 = new JLabel("SSD : ");
    lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_12);

    lblCheckSSD = new JLabel("");
    lblCheckSSD.setForeground(Color.RED);
    lblCheckSSD.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckSSD);

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
    HashSet<String> hs = new HashSet<>();
    int modelCount = table.getRowCount();
    int result = 0;
    for (int i = 0; i < modelCount; i++) {
      result += Integer.parseInt(table.getValueAt(i, 3) + "");
      hs.add(table.getValueAt(i, 0) + "");
    }

    lblTotalPirce.setText(result + " 원");

    lblCheckCPU.setText("");
    lblCheckGraphic.setText("");
    lblCheckHDD.setText("");
    lblCheckMain.setText("");
    lblCheckRam.setText("");
    lblCheckSSD.setText("");
    for (String t : hs) {
      switch (t) {
      case "CPU":
        lblCheckCPU.setText("선택됨");
        break;
      case "메인보드":
        lblCheckMain.setText("선택됨");
        break;
      case "그래픽카드":
        lblCheckGraphic.setText("선택됨");
        break;
      case "메모리카드":
        lblCheckRam.setText("선택됨");
        break;
      case "HDD":
        lblCheckHDD.setText("선택됨");
        break;
      case "SSD":
        lblCheckSSD.setText("선택됨");
        break;
      }
    }
  }

}
