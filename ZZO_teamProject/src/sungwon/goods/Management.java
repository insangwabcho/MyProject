package sungwon.goods;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Management extends JFrame {

  private JPanel contentPane;
  private goodsDAO dao;
  private JTable table;
  private JTextField tfea;
  private String name;
  private JPanel panel_ea;
  private JButton btnadd;
  private JList list;
  private JTabbedPane tabbedPane;
  private JPanel panel_item;
  private JLabel lblKategorie;
  private JScrollPane scrollPane, scrollPane2;
  private Vector col, col2;
  private JLabel lblname;
  private JLabel lblcost;
  private JLabel lblNewLabel_1;
  private ImageIcon logo;
  private JLabel lblimg;
  private JLabel lblNewLabel_4;
  private JTextField tfcompany2;
  private JTextField tfname2;
  private JTextField tfSpec;
  private JTextField tfcost2;
  private JTextField tfea2;
  private JTextField tfSpec2;
  private JTable table2;
  private JLabel lblspec2;
  private JLabel lblspec1;
  private JButton btnSave;
  private JTextField textField;
  private JLabel lblNewLabel_3;

  /**
   * Launch the application.
   */
  //  public static void main(String[] args) {
  //    EventQueue.invokeLater(new Runnable() {
  //      public void run() {
  //        try {
  //          Management frame = new Management();
  //          frame.setVisible(true);
  //        } catch (Exception e) {
  //          e.printStackTrace();
  //        }
  //      }
  //    });
  //  }

  /**
   * Create the frame.
   */
  public Management() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 796, 462);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(166, 10, 602, 403);
    contentPane.add(tabbedPane);

    panel_ea = new JPanel();
    tabbedPane.addTab("재고관리", null, panel_ea, null);
    panel_ea.setLayout(null);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(12, 74, 573, 290);
    panel_ea.add(scrollPane);

    lblname = new JLabel("ㅡ");
    lblname.setHorizontalAlignment(SwingConstants.CENTER);
    lblname.setBounds(81, 10, 367, 21);
    panel_ea.add(lblname);

    tfea = new JTextField();
    tfea.setFont(new Font("굴림", Font.BOLD, 12));
    tfea.setText("0");
    // TextField 포커스 이벤트
    tfea.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent arg0) {
        tfea.setText("");
      }
    });
    tfea.setHorizontalAlignment(SwingConstants.TRAILING);
    tfea.setBounds(460, 10, 108, 21);
    panel_ea.add(tfea);
    tfea.setColumns(10);
    // 선택한 갯수만큼 원가 계산
    tfea.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          int row = table.getSelectedRow();
          Object value = table.getValueAt(row, 4);
          int price = Integer.valueOf(value.toString());
          int ea = Integer.valueOf(tfea.getText());
          lblcost.setText(price * ea + " 원");
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(Management.this, "상품을 선택하세요");
        }
      }
    });

    panel_item = new JPanel();
    tabbedPane.addTab("물품 추가", null, panel_item, null);
    panel_item.setLayout(null);

    lblimg = new JLabel("이미지 등록");
    lblimg.setHorizontalAlignment(SwingConstants.CENTER);
    lblimg.setBounds(12, 5, 142, 137);
    panel_item.add(lblimg);

    lblNewLabel_4 = new JLabel("제조사");
    lblNewLabel_4.setBounds(160, 10, 60, 25);
    panel_item.add(lblNewLabel_4);

    tfcompany2 = new JTextField();
    tfcompany2.setBounds(240, 10, 140, 25);
    panel_item.add(tfcompany2);
    tfcompany2.setColumns(10);

    JLabel lblNewLabel_5 = new JLabel("모델명");
    lblNewLabel_5.setBounds(160, 40, 60, 25);
    panel_item.add(lblNewLabel_5);

    tfname2 = new JTextField();
    tfname2.setBounds(240, 40, 140, 25);
    panel_item.add(tfname2);
    tfname2.setColumns(10);

    JLabel lblNewLabel_6 = new JLabel("원가");
    lblNewLabel_6.setBounds(160, 130, 60, 25);
    panel_item.add(lblNewLabel_6);

    tfSpec = new JTextField();
    tfSpec.setBounds(240, 70, 140, 25);
    panel_item.add(tfSpec);
    tfSpec.setColumns(10);

    JLabel lblNewLabel_7 = new JLabel("개수");
    lblNewLabel_7.setBounds(160, 160, 60, 25);
    panel_item.add(lblNewLabel_7);

    tfcost2 = new JTextField();
    tfcost2.setBounds(240, 130, 140, 25);
    panel_item.add(tfcost2);
    tfcost2.setColumns(10);

    JButton btnimage = new JButton("사진 등록");
    btnimage.setBounds(22, 157, 125, 23);
    panel_item.add(btnimage);

    lblspec1 = new JLabel("세대");
    lblspec1.setBounds(160, 70, 60, 25);
    panel_item.add(lblspec1);

    tfea2 = new JTextField();
    tfea2.setBounds(240, 160, 140, 25);
    panel_item.add(tfea2);
    tfea2.setColumns(10);

    lblspec2 = new JLabel("-");
    lblspec2.setBounds(160, 100, 60, 25);
    panel_item.add(lblspec2);

    tfSpec2 = new JTextField();
    tfSpec2.setEditable(false);
    tfSpec2.setBounds(240, 100, 140, 25);
    panel_item.add(tfSpec2);
    tfSpec2.setColumns(10);

    btnSave = new JButton("저장");
    btnSave.setBounds(418, 5, 142, 91);
    panel_item.add(btnSave);

    scrollPane2 = new JScrollPane();
    scrollPane2.setBounds(12, 190, 573, 174);
    panel_item.add(scrollPane2);

    lblKategorie = new JLabel("카테고리");
    lblKategorie.setForeground(Color.BLACK);
    lblKategorie.setBackground(Color.WHITE);
    lblKategorie.setOpaque(true);
    lblKategorie.setFont(new Font("굴림", Font.BOLD, 20));
    lblKategorie.setHorizontalAlignment(SwingConstants.CENTER);
    lblKategorie.setBounds(0, 111, 160, 25);
    contentPane.add(lblKategorie);

    dao = new goodsDAO();
    // 테이블1 객체 생성
    table = new JTable();
    scrollPane.setViewportView(table);
    // 테이블2 객체생성
    table2 = new JTable();
    scrollPane2.setViewportView(table2);

    textField = new JTextField();
    textField.setHorizontalAlignment(SwingConstants.TRAILING);
    textField.setText("0 원");
    textField.setEditable(false);
    textField.setBounds(420, 150, 135, 20);
    panel_item.add(textField);
    textField.setColumns(10);

    lblNewLabel_3 = new JLabel("판매가=(원가*0.2)+원가");
    lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 13));
    lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_3.setBounds(420, 130, 140, 20);
    panel_item.add(lblNewLabel_3);

    // Jlist에 DB값 출력
    ArrayList<String> jlistList = dao.jlistList();
    list = new JList(jlistList.toArray());
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list.setFont(new Font("굴림", Font.PLAIN, 12));
    list.setBounds(0, 136, 160, 122);
    contentPane.add(list);
    list.setSelectedIndex(0);
    // 컬럼값삽입
    col = new Vector();
    col.add("품번");
    col.add("제조사");
    col.add("상품명");
    col.add("갯수");
    col.add("원가");

    col2 = new Vector();
    col2.add("품번");
    col2.add("제조사");
    col2.add("상품명");
    col2.add("세대");
    col2.add("원가");
    name = "CPU";
    refreshTable();
    refreshTable2();

    // 리스트 선택시 테이블 목록 변환이벤트
    list.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
          if (list.getSelectedIndex() == 0) {
            name = "CPU";
            col2.clear();
            col2.add("품번");
            col2.add("제조사");
            col2.add("상품명");
            col2.add("세대");
            col2.add("원가");
            lblspec1.setText("세대");
            tfSpec2.setEditable(false);

          }
          else if (list.getSelectedIndex() == 1) {
            name = "HDD";
            col2.clear();
            col2.add("품번");
            col2.add("제조사");
            col2.add("상품명");
            col2.add("용량");
            col2.add("원가");
            lblspec1.setText("용량");
            tfSpec2.setEditable(false);
          }
          else if (list.getSelectedIndex() == 2) {
            name = "MAIN";
            col2.clear();
            col2.add("품번");
            col2.add("소켓");
            col2.add("제조사");
            col2.add("상품명");
            col2.add("원가");
            lblspec1.setText("소켓");
            tfSpec2.setEditable(false);
          }
          else if (list.getSelectedIndex() == 3) {
            name = "RAM";
            col2.clear();
            col2.add("품번");
            col2.add("제조사");
            col2.add("상품명");
            col2.add("용량");
            col2.add("원가");
            lblspec1.setText("용량");
            tfSpec2.setEditable(false);
          }
          else if (list.getSelectedIndex() == 4) {
            name = "SSD";
            col2.clear();
            col2.add("품번");
            col2.add("제조사");
            col2.add("상품명");
            col2.add("용량");
            col2.add("원가");
            lblspec1.setText("용량");
            tfSpec2.setEditable(false);
          }
          else if (list.getSelectedIndex() == 5) {
            name = "VGA";
            col2.clear();
            col2.add("품번");
            col2.add("OS");
            col2.add("제조사");
            col2.add("상품명");
            col2.add("메모리");
            col2.add("원가");
            lblspec1.setText("OS");
            lblspec2.setText("메모리");
            tfSpec2.setEditable(true);
          }
          refreshTable();
          refreshTable2();
        }
      }

    });
    // scrollPane.setViewportView(table);
    // scrollPane2.setViewportView(table2);

    btnadd = new JButton("저장");
    btnadd.setBounds(452, 41, 116, 23);
    panel_ea.add(btnadd);

    lblcost = new JLabel("0 원");
    lblcost.setHorizontalAlignment(SwingConstants.TRAILING);
    lblcost.setBounds(290, 43, 150, 21);
    panel_ea.add(lblcost);

    JLabel lblNewLabel = new JLabel("금액 :");
    lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(221, 43, 57, 21);
    panel_ea.add(lblNewLabel);

    lblNewLabel_1 = new JLabel("상품명 :");
    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
    lblNewLabel_1.setBounds(12, 10, 57, 21);
    panel_ea.add(lblNewLabel_1);

    ImageIcon tmplogo = new ImageIcon("d:\\comnawa\\comnawalogo.png");
    try {
      Image imageSrc = tmplogo.getImage();
      Image newImage = imageSrc.getScaledInstance(160, 90, Image.SCALE_AREA_AVERAGING);
      logo = new ImageIcon(newImage);
      JLabel lbllogo = new JLabel(logo);
      lbllogo.setBounds(0, 10, 160, 90);
      contentPane.add(lbllogo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 저장버튼 클릭시 이벤트발생
    btnadd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!(lblcost.getText().equals("0 원"))) {
          try {
            int no = Integer.valueOf(tfea.getText());
            String item = lblname.getText();
            int row = table.getSelectedRow();
            Object value = table.getValueAt(row, 3);
            int tmpea = Integer.valueOf((value + ""));
            if (tmpea != 0) {
              dao.updateEa(name, no, tmpea, item);
              JOptionPane.showMessageDialog(Management.this, "저장완료");
              tfea.setText("0");
              lblcost.setText("0 원");
              lblname.setText("ㅡ");
              refreshTable();
            }
          } catch (Exception e2) {
            JOptionPane.showMessageDialog(Management.this, "상품과 갯수를 확인해주세요");
            System.out.println("오류 발생");
          }

        }
        else {
          JOptionPane.showMessageDialog(Management.this, "상품과 갯수를 확인해주세요");
          System.out.println("d오류");
        }
      }
    });
  }

  // 화면 재출력 메소드
  public void refreshTable() {
    DefaultTableModel model = new DefaultTableModel(dao.jtableList(name), col);
    table.setModel(model);
    model.fireTableDataChanged();
    resizeColumnWidth(table);

    // 테이블 내용 가운데 정렬하기
    DefaultTableCellRenderer right = new DefaultTableCellRenderer();
    right.setHorizontalAlignment(SwingConstants.RIGHT);
    DefaultTableCellRenderer center = new DefaultTableCellRenderer();
    center.setHorizontalAlignment(SwingConstants.CENTER);
    TableColumnModel cell = table.getColumnModel();
    cell.getColumn(3).setCellRenderer(right);
    cell.getColumn(4).setCellRenderer(right);
    cell.getColumn(0).setCellRenderer(center);
    cell.getColumn(1).setCellRenderer(center);

    // 테이블 마우스 이벤트
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int row = table.getSelectedRow();
        Object value = table.getValueAt(row, 2);
        String tmpname = value + "";
        lblname.setText(tmpname);
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        Object value = table.getValueAt(row, 2);
        String tmpname = value + "";
        lblname.setText(tmpname);
      }
    });
    // 테이블 키보드 이벤트
    table.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        int row = table.getSelectedRow();

        if (e.getKeyText(e.getKeyCode()).equals("UP")) {
          row++;
        }
        else if (e.getKeyText(e.getKeyCode()).equals("DOWN")) {
          row--;
        }
        Object value = table.getValueAt(row, 2);
        String tmpname = value + "";
        lblname.setText(tmpname);
      }
    });
  }

  public void refreshTable2() {
    DefaultTableModel model2 = new DefaultTableModel(dao.jtable2List(name), col2);
    table2.setModel(model2);
    model2.fireTableDataChanged();
    resizeColumnWidth(table2);

    // 테이블 내용 가운데 정렬하기
    DefaultTableCellRenderer right = new DefaultTableCellRenderer();
    right.setHorizontalAlignment(SwingConstants.RIGHT);
    DefaultTableCellRenderer center = new DefaultTableCellRenderer();
    center.setHorizontalAlignment(SwingConstants.CENTER);
    TableColumnModel cell = table2.getColumnModel();
    if (table2.getColumnName(1).equals("OS")) {
      cell.getColumn(3).setCellRenderer(center);
      cell.getColumn(4).setCellRenderer(center);
      cell.getColumn(5).setCellRenderer(right);
    }
    else {
      cell.getColumn(2).setCellRenderer(center);
      cell.getColumn(3).setCellRenderer(center);
      cell.getColumn(4).setCellRenderer(right);

    }

    cell.getColumn(0).setCellRenderer(center);
    cell.getColumn(1).setCellRenderer(center);
    cell.getColumn(2).setCellRenderer(center);
    int lastno = table2.getColumnCount();
  }

  // 셀크기 자동정렬 메소드
  public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
      int width = 50;
      for (int row = 0; row < table.getRowCount(); row++) {
        TableCellRenderer renderer = table.getCellRenderer(row, column);
        Component comp = table.prepareRenderer(renderer, row, column);
        width = Math.max(comp.getPreferredSize().width + 1, width);
      }
      columnModel.getColumn(column).setPreferredWidth(width);
    }
  }
}
