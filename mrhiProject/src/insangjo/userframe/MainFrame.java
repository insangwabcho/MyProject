package insangjo.userframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import insangjo.DAO.MainFrameDAO;
import insangjo.DAO.SearchFrameDAO;
import kwanwoo.MultiClient;
import sangjin.Client.Login;
import sangjin.Client.UpdateJoin;

public class MainFrame extends JFrame {

  private JLabel lblUserStat;
  private Color colMenu;
  private ImageIcon panelBg;
  private MainFrameDAO orderpageDao;
  private JList list_1;
  private JList list_2;
  private JTable table;
  private Vector data, col;
  private JLabel lblTotalPirce;
  private DefaultTableModel model;
  private JLabel lblOptionTitle;
  private JComboBox cboMenu;
  private JLabel lblEa;
  private JLabel lblCheckCPU;
  private JLabel lblCheckMain;
  private JLabel lblCheckGraphic;
  private JLabel lblCheckRam;
  private JLabel lblCheckHDD;
  private JLabel lblCheckSSD;
  private JScrollPane scrollPane_2;
  private JScrollPane scrollPane;
  private JPanel contentPane;
  private JPanel selectPanel;
  private JPanel detailPanel;
  private JPanel boxpanel;
  private JPanel optionPanel;
  private JPanel option2Panel;
  private JPanel panel;
  private JScrollPane scrollPane_1;
  private JLabel lblDetail;
  private JTextField textField;
  private JTextArea textArea;

  public MainFrame(String username, String id, String address) {

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
    setBounds(100, 100, 1350, 768);
    setResizable(false);

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu mnNewMenu = new JMenu("파일");
    menuBar.add(mnNewMenu);

    JMenu mnNewMenu_1 = new JMenu("테마");
    menuBar.add(mnNewMenu_1);

    JMenu mnNewMenu_3 = new JMenu("배경 테마 변경");
    mnNewMenu_1.add(mnNewMenu_3);

    JMenuItem mntmNewMenuItem_1 = new JMenuItem("단색");
    mntmNewMenuItem_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        contentPane.setBackground(new Color(255, 255, 255));

      }
    });
    mnNewMenu_3.add(mntmNewMenuItem_1);

    JMenuItem mntmNewMenuItem_2 = new JMenuItem("노랑노랑");
    mnNewMenu_3.add(mntmNewMenuItem_2);

    JMenu mnNewMenu_2 = new JMenu("정보");
    menuBar.add(mnNewMenu_2);

    JMenuItem mntmNewMenuItem = new JMenuItem("컴나와는?");
    mntmNewMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(MainFrame.this, "홍홍홍홍홍홍홍홍홍ㅇㅇㅇㅇㅇ");
      }
    });
    mnNewMenu_2.add(mntmNewMenuItem);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(255, 255, 255));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    colMenu = new Color(240, 210, 210);

    selectPanel = new JPanel();
    selectPanel.setBackground(new Color(0, 51, 255));
    selectPanel.setBounds(6, 71, 168, 266);
    contentPane.add(selectPanel);
    selectPanel.setLayout(null);

    lblDetail = new JLabel();
    JScrollPane scrollPane_3 = new JScrollPane();
    scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane_3.getVerticalScrollBar().setUnitIncrement(30);
    scrollPane_3.setViewportView(lblDetail);
    detailPanel = new JPanel();
    detailPanel.setBackground(Color.white);//
    detailPanel.setBounds(506, 349, 838, 346);
    contentPane.add(detailPanel);

    cboMenu = new JComboBox();
    cboMenu.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
    cboMenu.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          if (cboMenu.getSelectedIndex() == 0) {
            list_1 = new JList();
            list_1.setBackground(new Color(20, 105, 230));
            scrollPane.setViewportView(list_1);
            return;
          }
          String a = e.getItem() + "";
          boolean b = lblCheckRam.getText().equals("");
          if (a.equals("추가 SSD")) {
            a = "RAM";
          }
          if (b && (e.getItem() + "").equals("추가 SSD")) {
            JOptionPane.showMessageDialog(MainFrame.this, "기본 메모리카드 먼저 선택해주세요");
            return;
          }
          ArrayList list = orderpageDao.prodList(e.getItem() + "");
          list_1 = new JList(list.toArray());
          ListSelectionListener list_1listener = new ListSelectionListener() {
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
                list_2.addListSelectionListener(new ListSelectionListener() {
                  public void valueChanged(ListSelectionEvent e) {
                    //요기
                    if (e.getValueIsAdjusting()) {
                      int start = (list_2.getSelectedValue() + "").indexOf(":") + 1;
                      int end = (list_2.getSelectedValue() + "").indexOf(",");
                      int serial = Integer.parseInt((list_2.getSelectedValue() + "").substring(start, end).trim());
                      String kind = "";
                      switch (cboMenu.getSelectedItem() + "") {
                      case "CPU":
                        kind = "cpu";
                        break;
                      case "메인보드":
                        kind = "main";
                        break;
                      case "그래픽카드":
                        kind = "vga";
                        break;
                      case "추가 RAM":
                      case "메모리카드":
                        kind = "ram";
                        break;
                      case "HDD":
                        kind = "hdd";
                        break;
                      case "SSD":
                        kind = "ssd";
                        break;
                      }
                      String path = new MainFrameDAO().getImgPath(kind, serial);
                      ImageIcon icon = new insangjo.img.SetImageIcon().getDetailImg(path, scrollPane_3.getWidth() - 15);
                      lblDetail.setIcon(icon);
                      scrollPane_3.getVerticalScrollBar().setValue(0);
                    }
                  }
                });
                scrollPane_2.setViewportView(list_2);
                lblOptionTitle.setText(cboMenu.getSelectedItem() + " " + list_1.getSelectedValue());
              }
            }
          };
          list_1.setBackground(new Color(20, 105, 230));
          list_1.setForeground(Color.white);
          list_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
              if (e.getKeyCode() == 10) {

              }
            }
          });
          list_1.addListSelectionListener(list_1listener);
          scrollPane.setViewportView(list_1);
        }
      }
    });
    cboMenu.setModel(new DefaultComboBoxModel(new String[] { "[부품 선택]", "CPU", "메인보드", "그래픽카드", "메모리카드", "HDD", "SSD", "추가 RAM" }));
    cboMenu.setBounds(6, 6, 156, 32);
    selectPanel.add(cboMenu);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(0, 49, 168, 217);
    selectPanel.add(scrollPane);

    list_1 = new JList();
    list_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    list_1.setBackground(Color.WHITE);
    list_1.setForeground(Color.white);
    scrollPane.setViewportView(list_1);

    detailPanel.setLayout(new BorderLayout(0, 0));

    boxpanel = new JPanel();
    boxpanel.setBackground(new Color(0, 51, 255));
    boxpanel.setBounds(6, 349, 486, 346);
    contentPane.add(boxpanel);
    boxpanel.setLayout(null);

    JLabel lblNewLabel_1 = new JLabel("현재 장바구니 목록");
    lblNewLabel_1.setForeground(new Color(255, 255, 255));
    lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setBounds(6, 12, 474, 16);
    boxpanel.add(lblNewLabel_1);

    scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(6, 40, 474, 256);
    boxpanel.add(scrollPane_1);

    model = new DefaultTableModel(data, col);
    table = new JTable(model) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
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
        OrderFrame f = new OrderFrame(1, username, model, lblTotalPirce.getText(), id, address);
        f.setVisible(true);

      }
    });
    btnOk.setBounds(397, 308, 75, 29);
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
    button.setBounds(200, 308, 80, 29);
    boxpanel.add(button);

    JLabel lblNewLabel_3 = new JLabel("총 금액");
    lblNewLabel_3.setForeground(new Color(255, 255, 255));
    lblNewLabel_3.setBounds(6, 314, 75, 16);
    boxpanel.add(lblNewLabel_3);

    lblTotalPirce = new JLabel("");
    lblTotalPirce.setForeground(new Color(255, 255, 255));
    lblTotalPirce.setBounds(86, 314, 107, 16);
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
    button_1.setBounds(283, 308, 110, 29);
    boxpanel.add(button_1);

    optionPanel = new JPanel();
    optionPanel.setBackground(new Color(0, 51, 255));
    optionPanel.setBounds(180, 71, 692, 266);
    contentPane.add(optionPanel);
    optionPanel.setLayout(null);

    JLabel lblNewLabel_2 = new JLabel("상세 옵션 선택");
    lblNewLabel_2.setForeground(new Color(255, 255, 255));
    lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_2.setBounds(0, 6, 687, 26);
    optionPanel.add(lblNewLabel_2);

    option2Panel = new JPanel();
    option2Panel.setBackground(Color.white);
    option2Panel.setBounds(0, 34, 692, 232);
    optionPanel.add(option2Panel);
    option2Panel.setLayout(null);

    lblOptionTitle = new JLabel("");
    lblOptionTitle.setHorizontalAlignment(SwingConstants.CENTER);
    lblOptionTitle.setBounds(6, 6, 679, 16);
    option2Panel.add(lblOptionTitle);

    scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(6, 30, 679, 123);
    option2Panel.add(scrollPane_2);

    list_2 = new JList();
    list_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    scrollPane_2.setViewportView(list_2);

    JButton btnSave = new JButton("장바구니에 담기");
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        ArrayList<String> items = new ArrayList<>();
        items.add(cboMenu.getSelectedItem() + "");
        items.add(list_1.getSelectedValue() + "");

        int start = (list_2.getSelectedValue() + "").indexOf(":") + 1;
        int end = (list_2.getSelectedValue() + "").indexOf(",");
        items.add((list_2.getSelectedValue() + "").substring(start, end).trim());

        Vector item = orderpageDao.cartAdd(items, 1 + "");
        refreshTable(item);

        list_2 = new JList();
        scrollPane_2.setViewportView(list_2);
      }
    });
    btnSave.setBounds(541, 165, 144, 55);
    option2Panel.add(btnSave);

    lblEa = new JLabel("1");
    lblEa.setHorizontalAlignment(SwingConstants.RIGHT);
    lblEa.setBounds(644, 165, 36, 16);

    panel = new JPanel(new GridLayout(2, 6));
    panel.setBackground(Color.white);
    panel.setBounds(6, 165, 523, 55);
    option2Panel.add(panel);

    JLabel lblcpu = new JLabel("  CPU :");
    lblcpu.setFont(new Font("Dialog", Font.BOLD, 13));
    lblcpu.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblcpu);

    lblCheckCPU = new JLabel("");
    lblCheckCPU.setFont(new Font("Dialog", Font.BOLD, 13));
    lblCheckCPU.setForeground(Color.RED);
    lblCheckCPU.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckCPU);

    JLabel lblNewLabel_6 = new JLabel("메인보드 : ");
    lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 13));
    lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_6);

    lblCheckMain = new JLabel("");
    lblCheckMain.setFont(new Font("Dialog", Font.BOLD, 13));
    lblCheckMain.setForeground(Color.RED);
    lblCheckMain.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckMain);

    JLabel lblNewLabel_8 = new JLabel("그래픽카드 : ");
    lblNewLabel_8.setFont(new Font("Dialog", Font.BOLD, 13));
    lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_8);

    lblCheckGraphic = new JLabel("");
    lblCheckGraphic.setFont(new Font("Dialog", Font.BOLD, 13));
    lblCheckGraphic.setForeground(Color.RED);
    lblCheckGraphic.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckGraphic);

    JLabel lblNewLabel_10 = new JLabel("  메모리카드 : ");
    lblNewLabel_10.setFont(new Font("Dialog", Font.BOLD, 13));
    lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_10);

    lblCheckRam = new JLabel("");
    lblCheckRam.setFont(new Font("Dialog", Font.BOLD, 13));
    lblCheckRam.setForeground(Color.RED);
    lblCheckRam.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckRam);

    JLabel lblNewLabel_9 = new JLabel("HDD : ");
    lblNewLabel_9.setFont(new Font("Dialog", Font.BOLD, 13));
    lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_9);

    lblCheckHDD = new JLabel("");
    lblCheckHDD.setFont(new Font("Dialog", Font.BOLD, 13));
    lblCheckHDD.setForeground(Color.RED);
    lblCheckHDD.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckHDD);

    JLabel lblNewLabel_12 = new JLabel("SSD : ");
    lblNewLabel_12.setFont(new Font("Dialog", Font.BOLD, 13));
    lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(lblNewLabel_12);

    lblCheckSSD = new JLabel("");
    lblCheckSSD.setFont(new Font("Dialog", Font.BOLD, 13));
    lblCheckSSD.setForeground(Color.RED);
    lblCheckSSD.setHorizontalAlignment(SwingConstants.LEFT);
    panel.add(lblCheckSSD);

    LineBorder lb = new LineBorder(Color.black);
    lb.createBlackLineBorder();
    scrollPane_2.setBorder(lb);
    scrollPane.setBorder(lb);
    contentPane.setBorder(lb);
    selectPanel.setBorder(lb);
    detailPanel.setBorder(lb);
    detailPanel.add(scrollPane_3, BorderLayout.CENTER);
    boxpanel.setBorder(lb);
    optionPanel.setBorder(lb);
    option2Panel.setBorder(lb);
    panel.setBorder(lb);
    scrollPane_1.setBorder(lb);
    //
    lblUserStat = new JLabel(username + "님 반갑습니다!!");
    lblUserStat.setBounds(582, 12, 358, 47);
    contentPane.add(lblUserStat);
    lblUserStat.setFont(new Font("Lucida Grande", Font.BOLD, 20));
    lblUserStat.setHorizontalAlignment(SwingConstants.CENTER);

    JButton btnUpdate = new JButton("정보수정");
    btnUpdate.setBounds(1098, 18, 117, 27);
    contentPane.add(btnUpdate);

    JButton btnLogout = new JButton("로그아웃");
    btnLogout.setBounds(1227, 18, 117, 27);
    contentPane.add(btnLogout);

    JButton btnSearch = new JButton("주문내역조회");
    btnSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (new SearchFrameDAO().checkOrder(id) == 0) {
          JOptionPane.showMessageDialog(MainFrame.this, "주문하신 내역이 없습니다.");//
          return;
        }
        else {
          SearchFrame f = new SearchFrame(id, username);
          f.setVisible(true);//sdfhsdfslkj
        }
      }
    });
    btnSearch.setBounds(969, 18, 117, 27);
    contentPane.add(btnSearch);

    JLabel lblLogo = new JLabel("New label");
    lblLogo.setBounds(6, 12, 168, 47);
    lblLogo.setIcon(getScaleImg("/comnawalogo.png", lblLogo.getWidth(), lblLogo.getHeight()));

    contentPane.add(lblLogo);

    JLabel lblAd = new JLabel(new insangjo.img.SetImageIcon().getScaleImg("/AD/AD5.PNG/", 390, 60));
    lblAd.setBounds(180, 5, 390, 60);
    new sungwon.Ad.AD(lblAd);

    contentPane.add(lblAd);

    kwanwoo.MultiClient mc = new MultiClient();

    JInternalFrame internalFrame = new JInternalFrame("실시간 상담");
    internalFrame.setResizable(false);
    internalFrame.setClosable(false);
    internalFrame.setBounds(884, 71, 460, 266);
    contentPane.add(internalFrame);
    internalFrame.getContentPane().setLayout(null);

    JScrollPane scrollPane_4 = new JScrollPane();
    scrollPane_4.setBounds(6, 6, 424, 178);
    internalFrame.getContentPane().add(scrollPane_4);

    textArea = new JTextArea();
    scrollPane_4.setViewportView(textArea);

    textField = new JTextField();
    textField.setBounds(6, 188, 331, 26);
    internalFrame.getContentPane().add(textField);
    textField.setColumns(10);

    JButton btnSend = new JButton("전송");
    btnSend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          mc.chatClient(id, textField.getText(), textArea, "root");
          textField.setText("");
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    });
    btnSend.setBounds(349, 188, 81, 29);
    internalFrame.getContentPane().add(btnSend);
    internalFrame.setVisible(true);
    btnLogout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sangjin.Client.Login f = new Login();
        f.setVisible(true);
        dispose();
      }
    });
    btnUpdate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        Conf f = new Conf(new MainFrameDAO().getPwd(id), id, MainFrame.this);
        f.setVisible(true); //dd
      }
    });

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

  class Conf extends JFrame {
    JPanel panel;
    JLabel label;
    JButton btn;

    public Conf(String pwd, String id, MainFrame mf) {
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setResizable(false);
      setSize(300, 105);

      label = new JLabel("비밀번호를 입력해주세요", JLabel.CENTER);
      label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

      panel = new JPanel(new BorderLayout());
      JPasswordField fieldPwd = new JPasswordField();
      fieldPwd.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == 10) { //비밀번호 텍스트 필드에서 엔터키를 입력하면
            btn.doClick(); //확인버튼클
          }
        }
      });

      btn = new JButton("확인");
      btn.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (String.valueOf(fieldPwd.getPassword()).equals(pwd)) {
            new UpdateJoin(id, mf).setVisible(true);
            dispose();
          }
          else {
            JOptionPane.showMessageDialog(Conf.this, "잘못된 비밀번호입니다 다시 시도해주세요");
            dispose();
          }
        }
      });

      panel.add(label, "North");
      panel.add(fieldPwd, "Center");
      panel.add(btn, "South");

      add(panel);
      setVisible(true);
    }

  }

  public void closeMainFrame() {
    new sangjin.Client.Login().setVisible(true);
    dispose();
  }

  public ImageIcon getScaleImg(String url, int width, int height) {
    String a = (String.valueOf(sungwon.DB.DB.class.getResource("img"))).replaceAll("file:", "") + url;
    ImageIcon imgicon = new ImageIcon(a);
    Image t = imgicon.getImage();
    imgicon = new ImageIcon(t.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
    return imgicon;
  }
}
