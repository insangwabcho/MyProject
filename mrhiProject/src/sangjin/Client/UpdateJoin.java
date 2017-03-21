
package sangjin.Client;

import java.awt.Color;
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
import java.sql.Date;
import java.util.Vector;

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

import insangjo.userframe.MainFrame;


public class UpdateJoin extends JFrame {
	private JPanel contentPane;
	private JTextField tfJid; // 사용자 입력 아이디
	private JTextField tfJname; // 사용자 입력 이름
	private JTextField tfJtel2; // 사용자 입력 폰번호2
	private JTextField tfJtel3; // 사용자 입력 폰번호3 폰번호=1+2+3
	private JTextField tfJemail1; // 사용자 입력 이메일1
	private JTextField tfJemail2; // 사용자 입력 이메일2 이메일=1+@+2
	private JLabel lblJTF; // 비밀번호 일치판별 라벨
	private JPasswordField tfJpassword1; // 사용자가 입력하는 비밀번호
	private JPasswordField tfJpassword2; // 사용자가 입력하는 비밀번호 확인
	private JoinDAO dao;
	private JComboBox cbtel1;
	private JTextField tfbirth;
	private JTextField tfJaddress1;
	private JTextField tfJaddress2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Join frame = new Join();
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
	public UpdateJoin(String Lid) {
		dao=new JoinDAO();
		Vector v=dao.List(Lid);
		setTitle("정보수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 692);
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

		JLabel label_7 = new JLabel("휴대폰번호*");
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_7.setBounds(40, 460, 120, 18);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("주소*");
		label_8.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_8.setBounds(40, 520, 62, 18);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("정보입력");
		label_9.setFont(new Font("Dialog", Font.BOLD, 20));
		label_9.setBounds(40, 30, 130, 35);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("@");
		label_10.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(287, 280, 31, 18);
		contentPane.add(label_10);

		tfJid = new JTextField(String.valueOf(v.get(0)));
		tfJid.setEditable(false);
		tfJid.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJid.setBounds(170, 99, 270, 24);
		contentPane.add(tfJid);
		tfJid.setColumns(10);

		tfJpassword1 = new JPasswordField();
		tfJpassword1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJpassword1.setBounds(170, 159, 270, 24);
		contentPane.add(tfJpassword1);

		// 비밀번호 확인 입력시 판독 이벤트처리
		tfJpassword2 = new JPasswordField();
		tfJpassword2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJpassword2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String p1 = String.valueOf(tfJpassword1.getPassword()); // 비밀번호
				String p2 = String.valueOf(tfJpassword2.getPassword()); // 비밀번호
																		// 확인
				if (p1.trim().length() > 5) { // 비밀번호가 3글자 이상일때
					if (p2.equals(p1)) { // 같을때
						lblJTF.setText("V");
						lblJTF.setForeground(Color.green);
						lblJTF.setFont(new Font("맑은 고딕", Font.BOLD, 18));
					} else { // 다를때
						lblJTF.setText("X");
						lblJTF.setForeground(Color.RED);
						lblJTF.setFont(new Font("맑은 고딕", Font.BOLD, 18));
					}
				} else { // 비밀번호가 4자리 미만일때
					lblJTF.setText("6자리 이상 입력");
					lblJTF.setForeground(Color.RED);
					lblJTF.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				}
			}
		});
		tfJpassword2.setBounds(170, 219, 270, 24);
		contentPane.add(tfJpassword2);
		String[] email=String.valueOf(v.get(1)).split("@");
		tfJemail1 = new JTextField(email[0]);
		tfJemail1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJemail1.setBounds(170, 277, 116, 24);
		contentPane.add(tfJemail1);
		tfJemail1.setColumns(10);

		tfJemail2 = new JTextField(email[1]);
		tfJemail2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJemail2.setBounds(324, 277, 116, 24);
		contentPane.add(tfJemail2);
		tfJemail2.setColumns(10);

		tfJname = new JTextField(String.valueOf(v.get(2)));
		tfJname.setEditable(false);
		tfJname.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJname.setBounds(170, 337, 270, 24);
		contentPane.add(tfJname);
		tfJname.setColumns(10);
		String[] tel=String.valueOf(v.get(4)).split("-");
		tfJtel2 = new JTextField(tel[1]);
		tfJtel2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJtel2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				int len = tfJtel2.getText().length(); // 입력한 문자열의 길이를 담음
				if (len == 3)
					tfJtel3.requestFocus(); // 4자리 입력했을시 포커스 이동
			}
		});
		tfJtel2.setBounds(270, 457, 70, 24);
		contentPane.add(tfJtel2);
		tfJtel2.setColumns(10);

		tfJtel3 = new JTextField(tel[2]);
		tfJtel3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJtel3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				int len = tfJtel3.getText().length(); // 입력한 문자열의 길이를 담음
				if (len == 3)
					tfJaddress1.requestFocus(); // 4자리 입력했을시 주소입력란으로 포커스 이동
			}
		});
		tfJtel3.setBounds(370, 457, 70, 24);
		contentPane.add(tfJtel3);
		tfJtel3.setColumns(10);;

		lblJTF = new JLabel();
		lblJTF.setBounds(454, 220, 115, 23);
		contentPane.add(lblJTF);

		// 확인버튼을 눌렀을때 이벤트처리
		JButton btnSave = new JButton("수정");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 값
				String password = String.valueOf(tfJpassword2.getPassword()); //비번
				String email = (tfJemail1.getText() + "@" + tfJemail2.getText()); //이메일
				Date birth = Date.valueOf(tfbirth.getText()); //생년월일
				String tel = (String.valueOf(cbtel1.getSelectedItem())+"-"+ tfJtel2.getText()+"-"+ tfJtel3.getText());
				String address = (tfJaddress1.getText()+" "+tfJaddress2.getText()); //주소
				String id =tfJid.getText();
				dao = new JoinDAO();
				int result = dao.updateMember(new JoinDTO(password, email, birth, tel, address, id));
				if (result == 1) {
					JOptionPane.showMessageDialog(UpdateJoin.this, "정보수정 완료");
					setVisible(false); // 창을 닫음
				} else {
					JOptionPane.showMessageDialog(UpdateJoin.this, "입력되지 않은 정보가있습니다.");
				}
			}
		});
		btnSave.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnSave.setBounds(170, 598, 116, 35);
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

		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(240, 462, 25, 18);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(342, 462, 25, 18);
		contentPane.add(lblNewLabel_1);
		
		tfJaddress1 = new JTextField(String.valueOf(v.get(5)));
		tfJaddress1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tfJaddress1.setBounds(170, 517, 270, 24);
		contentPane.add(tfJaddress1);
		tfJaddress1.setColumns(10);
		
		cbtel1 = new JComboBox();
		cbtel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfJtel2.requestFocus();
			}
		});
		cbtel1.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "016", "017", "018", "019"}));
		cbtel1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cbtel1.setBounds(170, 457, 70, 24);
		contentPane.add(cbtel1);
		
		tfbirth = new JTextField(String.valueOf(v.get(3)));
		tfbirth.setEditable(false);
		tfbirth.setBounds(170, 399, 270, 24);
		contentPane.add(tfbirth);
		tfbirth.setColumns(10);
		
		JLabel tfad = new JLabel("상세주소*");
		tfad.setBounds(40, 550, 84, 18);
		contentPane.add(tfad);
		
		tfJaddress2 = new JTextField();
		tfJaddress2.setBounds(170, 547, 270, 24);
		contentPane.add(tfJaddress2);
		tfJaddress2.setColumns(10);
		
		JButton btnNewButton = new JButton("회원탈퇴");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] yn={"예", "아니오"};
				if(JOptionPane.showOptionDialog(UpdateJoin.this, "정말 탈퇴하시겠습니까?", "경고",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, yn, null)==0){
					dao.deleteMember(Lid);
					dispose();
				}
			}
		});
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnNewButton.setBounds(324, 598, 116, 35);
		contentPane.add(btnNewButton);

	}
}
