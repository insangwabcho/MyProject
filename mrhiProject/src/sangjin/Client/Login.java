package sangjin.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import insangjo.adminfame.rootFrame;
import insangjo.userframe.MainFrame;
import sangjin.DB.DB;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

  private ImageIcon logo;
  private JPanel contentPane;
  private JTextField tfLid;
  private JButton btnJoin;
  private JLabel lblNewLabel;
  private JLabel lblNewLabel_1;
  private JLabel lblmain;
  private JLabel lblResult;
  private Join join;
  private JPasswordField tfLpassword;
  public static String name, id, address;

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
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    tfLid = new JTextField();
    tfLid.setBackground(SystemColor.control);
    tfLid.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
    tfLid.setToolTipText("");
    tfLid.setBounds(224, 156, 320, 50);
    contentPane.add(tfLid);
    tfLid.setColumns(10);

    //로그인
    JButton btnLogin = new JButton("로그인");
    btnLogin.setBackground(SystemColor.control);
    btnLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //사용자가 입력한 아이디,비번
        String Lid = tfLid.getText();
        String Lpassword = String.valueOf(tfLpassword.getPassword());
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
          conn = DB.comCon();
          String sql = "select * from member where id=? and password=?";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, Lid); //첫번째 물음표(아이디)		  
          pstmt.setString(2, Lpassword); //두번째 물음표(비번)
          rs = pstmt.executeQuery();
          if (rs.next()) {
            JoinDAO dao = new JoinDAO();
            name = dao.returnName(Lid); //이름 리턴
            id = dao.returnID(Lid); //아이디 리턴
            address = dao.returnAddress(Lid); //주소 리턴
            if (Lid.equals("root")) { //관리자 아이디면
              new rootFrame().setVisible(true);
              dispose();
            }
            else { //일반 사용자면
              new MainFrame(name, id, address).setVisible(true); //메인프레임을 띄움
              dispose();
            }
          }
          else {
            lblResult.setText("아이디 또는 비밀번호가 틀립니다.");
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

    tfLpassword = new JPasswordField();
    tfLpassword.addKeyListener(new KeyAdapter() {
    	@Override
    	public void keyPressed(KeyEvent e) {
    		if(e.getKeyCode()==10){
    			btnLogin.doClick();
    		}
    	}
    });
    tfLpassword.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
    tfLpassword.setBackground(SystemColor.control);
    tfLpassword.setBounds(224, 239, 320, 50);
    contentPane.add(tfLpassword);
    btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    btnLogin.setBounds(254, 340, 120, 40);
    contentPane.add(btnLogin);

    btnJoin = new JButton("회원가입");
    btnJoin.setBackground(SystemColor.control);
    btnJoin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Join join = new Join();
        join.setVisible(true);
      }
    });
    btnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 16));
    btnJoin.setBounds(403, 340, 120, 40);
    contentPane.add(btnJoin);

    lblResult = new JLabel("");
    lblResult.setFont(new Font("굴림", Font.PLAIN, 15));
    lblResult.setHorizontalAlignment(SwingConstants.CENTER);
    lblResult.setBounds(254, 303, 269, 25);
    contentPane.add(lblResult);

    lblNewLabel = new JLabel("아이디");
    lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
    lblNewLabel.setBounds(92, 152, 135, 57);
    contentPane.add(lblNewLabel);

    lblNewLabel_1 = new JLabel("비밀번호");
    lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
    lblNewLabel_1.setBounds(92, 234, 135, 57);
    contentPane.add(lblNewLabel_1);

    //ImageIcon tmplogo = new ImageIcon("d:\\comnawa\\comnawalogo.png");
    String a = (String.valueOf(DB.class.getResource("img/comnawalogo.png"))).replaceAll("file:", "");
    ImageIcon tmplogo = new ImageIcon(a);

    try {
      Image imageSrc = tmplogo.getImage();
      Image newImage = imageSrc.getScaledInstance(320, 132, Image.SCALE_AREA_AVERAGING);
      logo = new ImageIcon(newImage);
    } catch (Exception e) {
      e.printStackTrace();
    }
    lblmain = new JLabel(logo);
    lblmain.setFont(new Font("굴림", Font.BOLD, 50));
    lblmain.setHorizontalAlignment(SwingConstants.CENTER);
    lblmain.setBounds(224, 12, 320, 132);
    contentPane.add(lblmain);
  }
}
