
package sangjin.Client;

import java.awt.Color;//
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sungwon.DB.DB;


public class Join extends JFrame {
	private JPanel contentPane;
	private JTextField tfJid; // 사용자 입력 아이디
	private JTextField tfJname; // 사용자 입력 이름
	private JTextField tfJtel2; // 사용자 입력 폰번호2
	private JTextField tfJtel3; // 사용자 입력 폰번호3 폰번호=1+2+3
	private JTextField tfJemail1; // 사용자 입력 이메일1
	private JTextField tfJemail2; // 사용자 입력 이메일2 이메일=1+@+2
	private final ButtonGroup btnGruop = new ButtonGroup(); // 성별 버튼그룹
	private JLabel lblJTF; // 비밀번호 일치판별 라벨
	private JPasswordField tfJpassword1; // 사용자가 입력하는 비밀번호
	private JPasswordField tfJpassword2; // 사용자가 입력하는 비밀번호 확인
	private JoinDAO dao;
	private String Sex; // 라디오박스의 값을 담을 String 변수명
	private JComboBox cbday;
	private JComboBox cbmonth;
	private JComboBox cbyear;
	private JTextField tfJaddress1;
	private JTextField tfJaddress2;
	private JComboBox cbtel1;
	private boolean b=false;

	public Join() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 786);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("아이디*");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label.setBounds(40, 103, 120, 18);
		contentPane.add(label);

		JLabel label_1 = new JLabel("비밀번호*");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_1.setBounds(40, 160, 120, 18);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("비밀번호 확인*");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_2.setBounds(40, 220, 120, 18);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("이메일*");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_3.setBounds(40, 280, 62, 18);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("이름*");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_4.setBounds(40, 340, 120, 18);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("생년월일*");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_5.setBounds(40, 400, 120, 18);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("성별*");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_6.setBounds(40, 460, 62, 18);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("휴대폰번호*");
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_7.setBounds(40, 520, 120, 18);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("주소*");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_8.setBounds(40, 580, 62, 18);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("정보입력");
		label_9.setFont(new Font("Dialog", Font.BOLD, 20));
		label_9.setBounds(40, 30, 130, 35);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("@");
		label_10.setFont(new Font("굴림", Font.PLAIN, 15));
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(287, 280, 31, 18);
		contentPane.add(label_10);

		tfJid = new JTextField();
		tfJid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()== KeyEvent.VK_SPACE || arg0.getKeyCode()==KeyEvent.VK_SHIFT){
					JOptionPane.showMessageDialog(Join.this, "공백 및 특수문자 입력불가");
					tfJid.setText("");
				}
			}
		});
		tfJid.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJid.setBounds(170, 99, 270, 24);
		contentPane.add(tfJid);
		tfJid.setColumns(10);

		tfJpassword1 = new JPasswordField();
		tfJpassword1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_SPACE){
					JOptionPane.showMessageDialog(Join.this, "공백 입력불가");
					tfJpassword1.setText("");
				}
			}
		});
		tfJpassword1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJpassword1.setBounds(170, 159, 270, 24);
		contentPane.add(tfJpassword1);

		// 비밀번호 확인 입력시 판독 이벤트처리
		tfJpassword2 = new JPasswordField();
		tfJpassword2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_SPACE){
					JOptionPane.showMessageDialog(Join.this, "공백 입력불가");
					tfJpassword2.setText("");
				}
			}
		});
		tfJpassword2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJpassword2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String p1 = String.valueOf(tfJpassword1.getPassword()); // 비밀번호
				String p2 = String.valueOf(tfJpassword2.getPassword()); // 비밀번호 확인
				if (p1.trim().length() > 5) { // 비밀번호가 3글자 이상일때
					if (p2.equals(p1)) { // 같을때
						lblJTF.setText("V");
						lblJTF.setForeground(Color.green);
						lblJTF.setFont(new Font("굴림", Font.BOLD, 18));
					} else { // 다를때
						lblJTF.setText("X");
						lblJTF.setForeground(Color.RED);
						lblJTF.setFont(new Font("굴림", Font.BOLD, 18));
					}
				} else { // 비밀번호가 4자리 미만일때
					lblJTF.setText("6자리 이상 입력");
					lblJTF.setForeground(Color.RED);
					lblJTF.setFont(new Font("굴림", Font.BOLD, 15));
				}
			}
		});
		tfJpassword2.setBounds(170, 219, 270, 24);
		contentPane.add(tfJpassword2);

		tfJemail1 = new JTextField();
		tfJemail1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJemail1.setBounds(170, 277, 116, 24);
		contentPane.add(tfJemail1);
		tfJemail1.setColumns(10);

		tfJemail2 = new JTextField();
		tfJemail2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJemail2.setBounds(324, 277, 116, 24);
		contentPane.add(tfJemail2);
		tfJemail2.setColumns(10);

		tfJname = new JTextField();
		tfJname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_SPACE){
					int index= tfJname.getText().length();
					String a= tfJname.getText().substring(0,index-1);
					tfJname.setText(a);
				}
			}
		});
		tfJname.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJname.setBounds(170, 337, 270, 24);
		contentPane.add(tfJname);
		tfJname.setColumns(10);

		tfJtel2 = new JTextField();
		tfJtel2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJtel2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				int len = tfJtel2.getText().length(); // 입력한 문자열의 길이를 담음
				if (len == 3)
					tfJtel3.requestFocus(); // 4자리 입력했을시 포커스 이동
			}
		});
		tfJtel2.setBounds(270, 517, 70, 24);
		contentPane.add(tfJtel2);
		tfJtel2.setColumns(10);

		tfJtel3 = new JTextField();
		tfJtel3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJtel3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				int len = tfJtel3.getText().length(); // 입력한 문자열의 길이를 담음
				if (len == 3)
					tfJaddress1.requestFocus(); // 4자리 입력했을시 주소입력란으로 포커스 이동
			}
		});
		tfJtel3.setBounds(370, 517, 70, 24);
		contentPane.add(tfJtel3);
		tfJtel3.setColumns(10);;

		// 아이디 중복확인 이벤트
		JButton btnTest = new JButton("중복확인");
		btnTest.setBackground(SystemColor.control);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b=true;
				// 사용자가 입력한 아이디
				String Jid = tfJid.getText();

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					conn = DB.comCon();
					String sql = "select * from member where id=?";
					pstmt = conn.prepareStatement(sql);

					if (Jid.trim().length() >= 5 && Jid.trim().length() < 15) { // 아이디가 5이상 15미만일떄																		// 5자리이상
						pstmt.setString(1, Jid); // 첫번째 물음표(아이디)
						rs = pstmt.executeQuery();
						if (rs.next()) { // DB에 중복되는 아이디가 있으면
							JOptionPane.showMessageDialog(Join.this, "이미 존재하는 아이디입니다.");
						} else { // DB에 중복되는 아이디가 없으면
							JOptionPane.showMessageDialog(Join.this, "사용 가능한 아이디입니다.");
						}
					} else { // 아이디가 5자리 미만이거나 15자리를 초과할때
						JOptionPane.showMessageDialog(Join.this, "5자리 이상 15자리 미만으로 입력해 주세요.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						if (conn != null)
							conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnTest.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnTest.setBounds(454, 97, 103, 27);
		contentPane.add(btnTest);

		lblJTF = new JLabel();
		lblJTF.setBounds(454, 220, 115, 23);
		contentPane.add(lblJTF);

		// 확인버튼을 눌렀을때 이벤트처리
		JButton btnSave = new JButton("확인");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!b){
					JOptionPane.showMessageDialog(Join.this, "아이디 중복확인을 해주세요.");
					return;
				}
				// 사용자가 입력한 값
				String id = tfJid.getText();
				String password = String.valueOf(tfJpassword2.getPassword());
				String email = (tfJemail1.getText() + "@" + tfJemail2.getText());
				String name = tfJname.getText();
				Date birth = Date.valueOf((String.valueOf(cbyear.getSelectedItem()+"-"+cbmonth.getSelectedItem()+"-"+cbday.getSelectedItem()))); 
				String sex = Sex; // 라디오박스에서 리턴받은 값을 대입
				String tel = (String.valueOf(cbtel1.getSelectedItem())+"-"+ tfJtel2.getText()+"-"+ tfJtel3.getText());
				String address = (tfJaddress1.getText()+", "+tfJaddress2.getText()); //주소,상세주소
				dao = new JoinDAO();
				int result = dao.insertMember(new JoinDTO(id, password, email, name, birth, sex, tel, address));
				if (result == 1) {
					JOptionPane.showMessageDialog(Join.this, "회원가입 완료");
					setVisible(false); // 창을 닫음
				} else {
					JOptionPane.showMessageDialog(Join.this, "입력되지 않은 정보가있습니다.");
				}
			}
		});
		btnSave.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnSave.setBounds(224, 678, 116, 35);
		contentPane.add(btnSave);

		// 콤보박스로 이메일 주소자동 선택
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.control);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = comboBox.getSelectedItem().toString();
				if (address.equals("naver.com")) {
					tfJemail2.setText("naver.com");
				} else if (address.equals("hanmail.net")) {
					tfJemail2.setText("hanmail.com");
				} else if (address.equals("google.com")) {
					tfJemail2.setText("google.com");
				} else if (address.equals("직접입력")) {
					tfJemail2.setText("");
					tfJemail2.requestFocus();
				}
			}
		});
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "선택하기", "naver.com", "hanmail.net", "google.com", "직접입력" }));
		comboBox.setBounds(454, 277, 103, 24);
		contentPane.add(comboBox);
		// 라디오 버튼을 그룹으로 묶어서 String값 대입
		JRadioButton radioBtnMale = new JRadioButton("남");
		radioBtnMale.setBackground(Color.WHITE);
		radioBtnMale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Sex = "남";
			}
		});
		radioBtnMale.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnGruop.add(radioBtnMale);
		radioBtnMale.setBounds(170, 456, 60, 27);
		contentPane.add(radioBtnMale);

		JRadioButton radioBtnFemale = new JRadioButton("여");
		radioBtnFemale.setBackground(Color.WHITE);
		radioBtnFemale.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Sex = "여";
			}
		});
		radioBtnFemale.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btnGruop.add(radioBtnFemale);
		radioBtnFemale.setBounds(287, 456, 60, 27);
		contentPane.add(radioBtnFemale);

		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(241, 520, 25, 18);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1.setBounds(342, 520, 25, 18);
		contentPane.add(lblNewLabel_1);

		cbyear = new JComboBox();
		cbyear.setBackground(SystemColor.control);
		cbyear.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cbyear.setBounds(170, 397, 99, 24);
		contentPane.add(cbyear);
		for (int i = 2017; i > 1920; i--) {
			cbyear.addItem(i);
		}

		cbmonth = new JComboBox(); //월별 날짜계산
		cbmonth.setBackground(SystemColor.control);
		cbmonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int d = cbmonth.getSelectedIndex();
					cbday.setFont(new Font("굴림", Font.PLAIN, 15));
					cbday.setBounds(370, 397, 70, 24);
					contentPane.add(cbday);
					cbday.removeAllItems();
					if (d == 0 || d == 2 || d == 4 || d == 6 || d == 7 || d == 9 || d == 11) {
						cbday.removeAllItems();
						for (int i = 1; i <= 31; i++) {
							cbday.addItem(i);
						}
					} else if (d == 3 || d == 5 || d == 8 || d == 10) {
						cbday.removeAllItems();
						for (int i = 1; i <= 30; i++) {
							cbday.addItem(i);
						}
					} else if (d == 1) {
						cbday.removeAllItems();
						for (int i = 1; i <= 28; i++) {
							cbday.addItem(i);
						}
					}
				}
			}
		});
		cbmonth.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cbmonth.setBounds(287, 397, 70, 24);
		contentPane.add(cbmonth);
		
		cbday = new JComboBox();
		cbday.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cbday.setBackground(SystemColor.control);
		cbday.setBounds(371, 397, 69, 24);
		contentPane.add(cbday);
		
		JLabel lblNewLabel_2 = new JLabel("상세주소*");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(40, 616, 90, 18);
		contentPane.add(lblNewLabel_2);
		
		tfJaddress1 = new JTextField();
		tfJaddress1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJaddress1.setBounds(170, 577, 270, 24);
		contentPane.add(tfJaddress1);
		tfJaddress1.setColumns(10);
		
		tfJaddress2 = new JTextField();
		tfJaddress2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJaddress2.setBounds(170, 613, 270, 24);
		contentPane.add(tfJaddress2);
		tfJaddress2.setColumns(10);
		
		cbtel1 = new JComboBox();
		cbtel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfJtel2.requestFocus();
			}
		});
		cbtel1.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "016", "017", "018", "019"}));
		cbtel1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cbtel1.setBounds(170, 517, 70, 24);
		contentPane.add(cbtel1);
		for (int i = 1; i <= 12; i++) {
			cbmonth.addItem(i);
		}

	}
}
