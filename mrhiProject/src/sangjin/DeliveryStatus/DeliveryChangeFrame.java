package sangjin.DeliveryStatus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sungwon.DB.DB;

public class DeliveryChangeFrame extends JFrame {

  private JPanel contentPane;
  private JTable table;//
  private JTextField tfdvs;
  private JTextField tfid;
  private JTextField tfname;
  private JTextField tfaddress;
  private JTextField tfcpu;
  private JTextField tfvga;
  private JTextField tfram;
  private JTextField tfram2;
  private JTextField tfhdd;
  private JTextField tfssd;
  private JTextField tfmain;
  private JTextField tftotal;
  private DeliveryChangeDAO dcdao;
  private Vector data, col;
  private ImageIcon logo;
  private DeliveryChangeDTO dto;
  private StatusDTO sttdto;
  private StatusDAO sttdao;
  private JComboBox cbstt;

  
  public DeliveryChangeFrame() {
  	setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 849, 582);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(255, 255, 255));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(425, 123, 397, 391);
    contentPane.add(scrollPane);

    //테이블의 컬럼 정의
    col = new Vector();
    col.add("주문번호");
    col.add("아이디");
    col.add("주문일자");
    col.add("배송상태");
    dcdao = new DeliveryChangeDAO();
    DefaultTableModel model = new DefaultTableModel(dcdao.list("%"), col){
    	 @Override
    	    public boolean isCellEditable(int row, int column) {
    	       //all cells false
    	       return false;
    	    }
    };


    table = new JTable(model);
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent arg0) {
        int order_no = Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0) + "");
        dto = dcdao.detailCart(order_no);
        tfname.setText(dto.getName());
        tfid.setText(dto.getId());
        tfaddress.setText(dto.getAddress());
        tfcpu.setText(dto.getCpu());
        tfvga.setText(dto.getVga());
        tfram.setText(dto.getRam());
        tfram2.setText(dto.getRam2());
        tfhdd.setText(dto.getHdd());
        tfssd.setText(dto.getSsd());
        tfmain.setText(dto.getMain());//
        tftotal.setText(String.valueOf(dto.getTotal()));
        tfdvs.setText(dto.getDvs());
        if (tfdvs.getText().equals("배송대기")) {
            tfdvs.setText("배송대기");
            tfdvs.setForeground(Color.red);
          }
          else if (tfdvs.getText().equals("배송중")) {
            tfdvs.setText("배송중");
            tfdvs.setForeground(Color.ORANGE);
          }
          else if (tfdvs.getText().equals("배송완료")) {
            tfdvs.setText("배송완료");
            tfdvs.setForeground(new Color(47, 157, 39));
          }
      }
    });
    scrollPane.setViewportView(table);
    table.setModel(model);

    JLabel lblNewLabel = new JLabel("배송상태 :");
    lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
    lblNewLabel.setBounds(425, 43, 91, 33);
    contentPane.add(lblNewLabel);

    tfdvs = new JTextField();
    tfdvs.setBackground(Color.WHITE);
    tfdvs.setEditable(false);
    tfdvs.setFont(new Font("맑은 고딕", Font.BOLD, 18));
    tfdvs.setBounds(518, 44, 104, 31);
    contentPane.add(tfdvs);
    tfdvs.setColumns(10);

    JComboBox cbdvs = new JComboBox();
    cbdvs.setModel(new DefaultComboBoxModel(new String[] {"배송대기", "배송중", "배송완료"}));
    cbdvs.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
    cbdvs.setBounds(636, 45, 91, 31);
    contentPane.add(cbdvs);

    String a = (String.valueOf(DB.class.getResource("img/comnawalogo.png"))).replaceAll("file:", "");
    ImageIcon tmplogo = new ImageIcon(a);

    try {
      Image imageSrc = tmplogo.getImage();
      Image newImage = imageSrc.getScaledInstance(284, 92, Image.SCALE_AREA_AVERAGING);
      logo = new ImageIcon(newImage);
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lbllogo = new JLabel(logo);
    lbllogo.setHorizontalAlignment(SwingConstants.CENTER);
    lbllogo.setBounds(14, 0, 284, 92);
    contentPane.add(lbllogo);

    JLabel lblNewLabel_2 = new JLabel("아이디 : ");
    lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_2.setBounds(34, 140, 62, 18);
    contentPane.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("이름 : ");
    lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_3.setBounds(34, 170, 62, 18);
    contentPane.add(lblNewLabel_3);

    JLabel lblNewLabel_4 = new JLabel("주소 : ");
    lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_4.setBounds(34, 200, 62, 18);
    contentPane.add(lblNewLabel_4);

    JLabel lblNewLabel_5 = new JLabel("구매내역");
    lblNewLabel_5.setOpaque(true);
    lblNewLabel_5.setBackground(new Color(0, 51, 255));
    lblNewLabel_5.setForeground(new Color(255, 255, 255));
    lblNewLabel_5.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_5.setBounds(14, 230, 387, 24);
    contentPane.add(lblNewLabel_5);

    JLabel lblNewLabel_6 = new JLabel("CPU : ");
    lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_6.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_6.setBounds(34, 260, 62, 18);
    contentPane.add(lblNewLabel_6);

    JLabel lblNewLabel_7 = new JLabel("VGA : ");
    lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_7.setBounds(34, 290, 62, 18);
    contentPane.add(lblNewLabel_7);

    JLabel lblNewLabel_8 = new JLabel("RAM : ");
    lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_8.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_8.setBounds(34, 320, 62, 18);
    contentPane.add(lblNewLabel_8);

    JLabel lblNewLabel_9 = new JLabel("RAM2 : ");
    lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_9.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_9.setBounds(34, 350, 62, 18);
    contentPane.add(lblNewLabel_9);

    JLabel lblNewLabel_10 = new JLabel("HDD : ");
    lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_10.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_10.setBounds(34, 380, 62, 18);
    contentPane.add(lblNewLabel_10);

    JLabel lblNewLabel_11 = new JLabel("SSD : ");
    lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_11.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_11.setBounds(34, 410, 62, 18);
    contentPane.add(lblNewLabel_11);

    JLabel lblNewLabel_12 = new JLabel("MAIN : ");
    lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_12.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_12.setBounds(34, 440, 62, 18);
    contentPane.add(lblNewLabel_12);

    JLabel lblNewLabel_13 = new JLabel("");
    lblNewLabel_13.setOpaque(true);
    lblNewLabel_13.setBackground(new Color(0, 51, 255));
    lblNewLabel_13.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_13.setBounds(13, 470, 386, 2);
    contentPane.add(lblNewLabel_13);

    JLabel lblNewLabel_15 = new JLabel("합계 :");
    lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
    lblNewLabel_15.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_15.setBounds(34, 492, 62, 18);
    contentPane.add(lblNewLabel_15);

    tfid = new JTextField();
    tfid.setEditable(false);
    tfid.setBounds(110, 139, 268, 24);
    contentPane.add(tfid);
    tfid.setColumns(10);

    tfname = new JTextField();
    tfname.setEditable(false);
    tfname.setColumns(10);
    tfname.setBounds(110, 169, 268, 24);
    contentPane.add(tfname);

    tfaddress = new JTextField();
    tfaddress.setEditable(false);
    tfaddress.setColumns(10);
    tfaddress.setBounds(110, 199, 268, 24);
    contentPane.add(tfaddress);

    tfcpu = new JTextField();
    tfcpu.setEditable(false);
    tfcpu.setColumns(10);
    tfcpu.setBounds(110, 260, 268, 24);
    contentPane.add(tfcpu);

    tfvga = new JTextField();
    tfvga.setEditable(false);
    tfvga.setColumns(10);
    tfvga.setBounds(110, 289, 268, 24);
    contentPane.add(tfvga);

    tfram = new JTextField();
    tfram.setEditable(false);
    tfram.setColumns(10);
    tfram.setBounds(110, 319, 268, 24);
    contentPane.add(tfram);

    tfram2 = new JTextField();
    tfram2.setEditable(false);
    tfram2.setColumns(10);
    tfram2.setBounds(110, 349, 268, 24);
    contentPane.add(tfram2);

    tfhdd = new JTextField();
    tfhdd.setEditable(false);
    tfhdd.setColumns(10);
    tfhdd.setBounds(110, 379, 268, 24);
    contentPane.add(tfhdd);

    tfssd = new JTextField();
    tfssd.setEditable(false);
    tfssd.setColumns(10);
    tfssd.setBounds(110, 409, 268, 24);
    contentPane.add(tfssd);

    tfmain = new JTextField();
    tfmain.setEditable(false);
    tfmain.setColumns(10);
    tfmain.setBounds(110, 439, 268, 24);
    contentPane.add(tfmain);

    tftotal = new JTextField();
    tftotal.setEditable(false);
    tftotal.setColumns(10);
    tftotal.setBounds(110, 490, 239, 24);
    contentPane.add(tftotal);

    JLabel lblNewLabel_16 = new JLabel("원");
    lblNewLabel_16.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel_16.setBounds(351, 492, 18, 18);
    contentPane.add(lblNewLabel_16);

    JLabel label = new JLabel("고객정보");
    label.setBackground(new Color(0, 51, 255));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setOpaque(true);
    label.setForeground(new Color(255, 255, 255));
    label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    label.setBounds(14, 98, 387, 24);
    contentPane.add(label);

    JLabel lblNewLabel_1 = new JLabel("New label");
    lblNewLabel_1.setBackground(new Color(0, 51, 255));
    lblNewLabel_1.setOpaque(true);
    lblNewLabel_1.setBounds(12, 98, 2, 373);
    contentPane.add(lblNewLabel_1);

    JLabel label_1 = new JLabel("New label");
    label_1.setOpaque(true);
    label_1.setBackground(new Color(0, 51, 255));
    label_1.setBounds(399, 98, 2, 373);
    contentPane.add(label_1);

    
    cbstt = new JComboBox();
	cbstt.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if( e.getStateChange() == ItemEvent.SELECTED ){
				String status=(String)cbstt.getSelectedItem();
				DefaultTableModel model=new DefaultTableModel(dcdao.list(status), col){
					 @Override
					    public boolean isCellEditable(int row, int column) {
					       //all cells false
					       return false;
					    }
				};
				table.setModel(model);
			}
		}
	});
    DefaultListCellRenderer dlcr=new DefaultListCellRenderer();
    dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
    cbstt.setRenderer(dlcr);
    cbstt.setFont(new Font("맑은 고딕", Font.BOLD, 17));
    
    JButton btnSave = new JButton("저장");
    btnSave.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String status=cbdvs.getSelectedItem().toString();
    		System.out.println(status);
    		int order_no=Integer.valueOf(model.getValueAt(table.getSelectedRow(), 0)+"");
			int result = dcdao.updateMember(status,order_no);
			if (result == 1) {
				JOptionPane.showMessageDialog(DeliveryChangeFrame.this, "변경완료");
				DefaultTableModel model = new DefaultTableModel(dcdao.list(status), col){
					 @Override
					    public boolean isCellEditable(int row, int column) {
					       //all cells false
					       return false;
					    }
				};
				table.setModel(model);
				tfdvs.setText("");
				refreshcombo();
			}
    	}
    });
    btnSave.setBounds(741, 47, 79, 27);
    contentPane.add(btnSave);
    
    

    cbstt.setOpaque(true);
    cbstt.setBackground(Color.BLUE);
    cbstt.setForeground(Color.WHITE);
    cbstt.setBounds(425, 98, 397, 24);
    contentPane.add(cbstt);//
	
    //콤보박스에 바인딩
	sttdao=new StatusDAO();
	refreshcombo();
	
  }
  void refreshcombo(){
		ArrayList<StatusDTO> list=sttdao.list();
		cbstt.removeAllItems();
		cbstt.addItem("전체배송현황");
		for(StatusDTO dto : list) {
			cbstt.addItem(dto.getStatus());
		}
  }
}
