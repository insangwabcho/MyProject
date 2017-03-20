package Client;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DB.DB;
import insangjo.adminfame.rootFrame;
import insangjo.userframe.MainFrame;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfLid;
	private JButton btnJoin;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblResult;
	private Join join;
	private JPasswordField tfLpassword;
	public static String name,id;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		setTitle("ComNawa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfLid = new JTextField();
		tfLid.setFont(new Font("굴림", Font.PLAIN, 18));
		tfLid.setToolTipText("");
		tfLid.setBounds(224, 156, 320, 50);
		contentPane.add(tfLid);
		tfLid.setColumns(10);
		
		//로그인
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한 아이디,비번
				String Lid = tfLid.getText();
				String Lpassword = String.valueOf(tfLpassword.getPassword());
				Connection conn=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				try {
					conn=DB.comCon();
					String sql = "select * from member where id=? and password=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, Lid); //첫번째 물음표(아이디)		  
					pstmt.setString(2, Lpassword); //두번째 물음표(비번)
					rs=pstmt.executeQuery();
					if(rs.next()){
						JoinDAO dao=new JoinDAO();
						name=dao.returnName(Lid); //이름 리턴
						id=dao.returnID(Lid); //아이디 리턴
						if(Lid.equals("root")){
							new rootFrame().setVisible(true);
							dispose();
						}else{
							new MainFrame(name,id).setVisible(true); //메인프레임을 띄움
							dispose();
						}
					}else{
						lblResult.setText("아이디 또는 비밀번호가 틀립니다.");
					} 
				} catch (Exception e1) {
						e1.printStackTrace();
				} finally {
					try {
						if(pstmt!=null) pstmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					try {
						if(conn!=null) conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		tfLpassword = new JPasswordField();
		tfLpassword.setBounds(224, 239, 320, 50);
		contentPane.add(tfLpassword);
		btnLogin.setFont(new Font("굴림", Font.PLAIN, 16));
		btnLogin.setBounds(254, 340, 120, 40);
		contentPane.add(btnLogin);
		
		btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join join=new Join();
				join.setVisible(true);
			}
		});
		btnJoin.setFont(new Font("굴림", Font.PLAIN, 16));
		btnJoin.setBounds(403, 340, 120, 40);
		contentPane.add(btnJoin);
		
		lblResult = new JLabel("");
		lblResult.setFont(new Font("굴림", Font.PLAIN, 15));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(254, 303, 269, 25);
		contentPane.add(lblResult);
		
		lblNewLabel = new JLabel("아이디");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel.setBounds(92, 152, 135, 57);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(92, 234, 135, 57);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("COMNAWA");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 50));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(128, 43, 389, 84);
		contentPane.add(lblNewLabel_2);
	}
}
