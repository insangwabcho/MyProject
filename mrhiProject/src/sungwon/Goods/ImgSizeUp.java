package sungwon.Goods;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ImgSizeUp extends JFrame {

	private  JPanel contentPane;
	private  JScrollPane scrollPane; 
	private  JLabel lblsizeupimg;
	//확대버튼클릭시 이미지 프레임 생성
	public ImgSizeUp(ImageIcon originimg) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();;
		scrollPane.setBounds(0, 0, 990, 850);
		contentPane.add(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(40);
		
		lblsizeupimg = new JLabel("");
		scrollPane.setViewportView(lblsizeupimg);
		String a = (String.valueOf(sungwon.DB.DB.class.getResource("img"))).replaceAll("file:", "") ;
		a= a.replaceAll("/bin/", "/src/");
		Image sizesrc = originimg.getImage();
		Image newsize = sizesrc.getScaledInstance(990, 7000, Image.SCALE_AREA_AVERAGING);
		ImageIcon img= new ImageIcon(newsize); 
		lblsizeupimg.setIcon(img);
	}
}
