package sangjin.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sungwon.Goods.Management;

public class MemberList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Vector col;
	private MemberDAO dao;
	private Management mgn;

	public MemberList() {
		mgn = new Management();
		dao = new MemberDAO();
		col = new Vector();
		col.add("ID");
		col.add("이름");
		col.add("생년월일");
		col.add("성별");
		col.add("E-mail");
		col.add("휴대폰");
		col.add("주소");
		col.add("최근로그인시간");
		col.add("IP");
		DefaultTableModel model=new DefaultTableModel(dao.listMember(), col){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1221, 641);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 128, 1177, 465);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		mgn.resizeColumnWidth(table);
		scrollPane.setViewportView(table);
		
	    JLabel lblNewLabel = new JLabel();
	    lblNewLabel.setBounds(14, 12, 270, 104);
	    ImageIcon tmplogo = new insangjo.img.SetImageIcon().getScaleImg("/comnawalogo.png",lblNewLabel.getWidth(),lblNewLabel.getHeight());
	    lblNewLabel.setIcon(tmplogo);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("회원 리스트");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 51, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setBounds(298, 63, 903, 53);
		contentPane.add(lblNewLabel_1);
	}
}
