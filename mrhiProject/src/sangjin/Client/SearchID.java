package sangjin.Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchID extends JFrame {

	private JPanel contentPane;
	private JTextField tfname;
	private JTextField tfemail;
	private JoinDAO dao;
	private JButton btnIDSearch;

	public SearchID() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 441, 253);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디 찾기");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 51, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 22, 441, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름 :");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(45, 124, 62, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("이메일 :");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(45, 180, 62, 18);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("본인확인 이메일로 찾기");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel_3.setBounds(14, 61, 190, 18);
		panel.add(lblNewLabel_3);
		
		tfname = new JTextField();
		tfname.setBounds(121, 123, 207, 24);
		panel.add(tfname);
		tfname.setColumns(10);
		
		tfemail = new JTextField();
		tfemail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10){
					btnIDSearch.doClick();
				}
			}
		});
		tfemail.setBounds(121, 179, 207, 24);
		panel.add(tfemail);
		tfemail.setColumns(10);
		
		btnIDSearch = new JButton("찾기");
		btnIDSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dao=new JoinDAO();
				String name=tfname.getText();
				String email=tfemail.getText();
				if(dao.searchId(name, email)!=null){
					JOptionPane.showMessageDialog(SearchID.this, "회원님의 아이디는 "+dao.searchId(name, email)+" 입니다.");
				}else{
					JOptionPane.showMessageDialog(SearchID.this, "입력 정보를 다시 확인해주세요.");
				}
			}
		});
		btnIDSearch.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnIDSearch.setBounds(342, 178, 76, 27);
		panel.add(btnIDSearch);
	}
}
