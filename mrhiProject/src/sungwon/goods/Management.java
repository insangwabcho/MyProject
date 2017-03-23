package sungwon.goods;

//22�� ������ ���� - �̹��� ����� ������ lblimg �ʱ�ȭ
//				*�ø����ȣ �߰��� ������ �߰��� ���� �Ұ��� ����
//               �����ǿ��� ���̺� ���ý� ������ ��Ÿ��������
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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

	private JTable table, table2, table3;
	private JList list;
	private JPanel contentPane, panel_ea;
	private Vector col, col2;
	private JLabel lblname1, lblcost1, lblimg, lblspec2_2, lblspec1_2, lblimg3, lblspec1_3, lblspec2_3;
	private JTextField tfcompany2, tfname2, tfspec1_2, tfcost2, tfea2, tfspec2_2, tfprice2, tfserial3, tfname3,
			tfcompany3, tfspec1_3, tfspec2_3, tfcost3, tfprice3, tfea1;
	private goodsDAO dao;
	private String name, img_path;
	private ImageIcon logo, img;
	private JTextField tfea3;
	private int table3num;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Management frame = new Management();
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
	public Management() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 462);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("���� ���", Font.PLAIN, 12));
		tabbedPane.setBounds(166, 10, 602, 403);
		contentPane.add(tabbedPane);

		panel_ea = new JPanel();
		tabbedPane.addTab("������", null, panel_ea, null);
		panel_ea.setLayout(null);

		JPanel panel_item = new JPanel();
		tabbedPane.addTab("��ǰ �߰�", null, panel_item, null);
		panel_item.setLayout(null);

		JPanel panel_delete = new JPanel();
		tabbedPane.addTab("��ǰ ����", null, panel_delete, null);
		panel_delete.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(12, 74, 573, 290);
		panel_ea.add(scrollPane1);

		lblname1 = new JLabel("��");
		lblname1.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblname1.setHorizontalAlignment(SwingConstants.CENTER);
		lblname1.setBounds(81, 10, 367, 21);
		panel_ea.add(lblname1);

		tfea1 = new JTextField();
		tfea1.setFont(new Font("���� ���", Font.BOLD, 12));
		tfea1.setText("0");

		tfea1.setHorizontalAlignment(SwingConstants.TRAILING);
		tfea1.setBounds(460, 10, 108, 21);
		panel_ea.add(tfea1);
		tfea1.setColumns(10);
		// �Է��� ������ŭ ���� ���
		tfea1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int row = table.getSelectedRow();
					Object value = table.getValueAt(row, 4);
					int price = Integer.valueOf(value.toString());
					int ea = Integer.valueOf(tfea1.getText());
					lblcost1.setText(price * ea + " ��");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(Management.this, "��ǰ�� �����ϼ���");
				}
			}
		});
		// TextField ��Ŀ�� �̺�Ʈ
		tfea1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				tfea1.setText("");
			}
		});

		lblimg = new JLabel("�̹��� ���");
		lblimg.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblimg.setHorizontalAlignment(SwingConstants.CENTER);
		lblimg.setBounds(12, 5, 142, 137);
		panel_item.add(lblimg);

		JLabel lblcompany2 = new JLabel("������");
		lblcompany2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblcompany2.setBounds(160, 10, 60, 25);
		panel_item.add(lblcompany2);

		tfcompany2 = new JTextField();
		tfcompany2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfcompany2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfcompany2.setBounds(240, 10, 140, 25);
		panel_item.add(tfcompany2);
		tfcompany2.setColumns(10);

		JLabel lblname2 = new JLabel("�𵨸�");
		lblname2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblname2.setBounds(160, 40, 60, 25);
		panel_item.add(lblname2);

		tfname2 = new JTextField();
		tfname2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfname2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfname2.setBounds(240, 40, 140, 25);
		panel_item.add(tfname2);
		tfname2.setColumns(10);

		JLabel lblcost2 = new JLabel("����");
		lblcost2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblcost2.setBounds(160, 130, 60, 25);
		panel_item.add(lblcost2);

		tfspec1_2 = new JTextField();
		tfspec1_2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfspec1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfspec1_2.setBounds(240, 70, 140, 25);
		panel_item.add(tfspec1_2);
		tfspec1_2.setColumns(10);

		JLabel lblea2 = new JLabel("����");
		lblea2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblea2.setBounds(160, 160, 60, 25);
		panel_item.add(lblea2);

		tfcost2 = new JTextField();
		tfcost2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfcost2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfcost2.setText("0");
		// ���� �Է½� �ǸŰ� ���
		tfcost2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!(tfcost2.getText().equals(""))) {
					String tmpcost = tfcost2.getText();
					int cost = Integer.valueOf(tmpcost);
					double tmpprice = (double) ((cost * 0.2) + cost);
					int price = (int) Math.ceil(tmpprice);
					tfprice2.setText(String.valueOf(price));
				}
			}
		});
		// ��Ŀ�� ���ý� �۾� �ʱ�ȭ
		tfcost2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfcost2.setText("");
			}
		});
		tfcost2.setBounds(240, 130, 140, 25);
		panel_item.add(tfcost2);
		tfcost2.setColumns(10);

		JButton btnimage = new JButton("���� ���");
		btnimage.setFont(new Font("���� ���", Font.PLAIN, 12));
		btnimage.setBounds(22, 157, 125, 23);
		panel_item.add(btnimage);
		// �̹��� ���� �̺�Ʈ
		btnimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(Management.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					try {
						BufferedImage bi = ImageIO.read(file);
						File copyfile = new File(name+"/"+file.getName());
						ImageIO.write(bi, "jpg", copyfile);
						ImageIcon icon = new ImageIcon(ImageIO.read(copyfile));
						Image imageSrc = icon.getImage();
						Image imageNew = imageSrc.getScaledInstance(142, 137, Image.SCALE_AREA_AVERAGING);
						icon = new ImageIcon(imageNew);
						lblimg.setIcon(icon);
						img_path = name+"/"+file.getName();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		lblspec1_2 = new JLabel("����");
		lblspec1_2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblspec1_2.setBounds(160, 70, 60, 25);
		panel_item.add(lblspec1_2);

		tfea2 = new JTextField();
		tfea2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfea2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfea2.setText("");
			}
		});
		tfea2.setText("0");
		tfea2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfea2.setBounds(240, 160, 140, 25);
		panel_item.add(tfea2);
		tfea2.setColumns(10);

		lblspec2_2 = new JLabel("-");
		lblspec2_2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblspec2_2.setBounds(160, 100, 60, 25);
		panel_item.add(lblspec2_2);

		tfspec2_2 = new JTextField();
		tfspec2_2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfspec2_2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfspec2_2.setEditable(false);
		tfspec2_2.setBounds(240, 100, 140, 25);
		panel_item.add(tfspec2_2);
		tfspec2_2.setColumns(10);

		JButton btnsave2 = new JButton("����");
		btnsave2.setFont(new Font("���� ���", Font.PLAIN, 12));
		btnsave2.setBounds(418, 5, 142, 90);
		panel_item.add(btnsave2);
		// table2 ���� ��ư Ŭ���� �̺�Ʈ�߻�
		btnsave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int serial = table2.getRowCount() + 1;
					String name2 = tfname2.getText();
					String company = tfcompany2.getText();
					company.toUpperCase();
					String spec1 = tfspec1_2.getText();
					spec1.toUpperCase();
					String spec2 = "1TB";
					if (name.equals("VGA")) {
						spec2 = tfspec2_2.getText();
						spec2.toUpperCase();
					}
					int cost = Integer.valueOf(tfcost2.getText());
					int price = Integer.valueOf(tfprice2.getText());
					int ea = Integer.valueOf(tfea2.getText());
					String img = img_path;
					dao.insertGoods(name, serial, name2, company, spec1, spec2, cost, price, ea, img);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(Management.this, "�߰��� ������ ��� �������ּ���");
				}
				JOptionPane.showMessageDialog(Management.this, "����Ǿ����ϴ�.");
				tfname2.setText("");
				tfcompany2.setText("");
				tfspec1_2.setText("");
				tfspec2_2.setText("");
				tfcost2.setText("0");
				tfea2.setText("0");
				lblimg.setIcon(null);
				lblimg.setText("�̹��� ���");

				refreshTable();
				refreshTable2();
			}
		});

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(12, 190, 573, 174);
		panel_item.add(scrollPane2);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(12, 162, 573, 200);
		panel_delete.add(scrollPane3);

		JLabel lblKategorie = new JLabel("ī�װ�");
		lblKategorie.setForeground(Color.BLACK);
		lblKategorie.setBackground(Color.WHITE);
		lblKategorie.setOpaque(true);
		lblKategorie.setFont(new Font("���� ���", Font.BOLD, 20));
		lblKategorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblKategorie.setBounds(0, 111, 160, 25);
		contentPane.add(lblKategorie);

		dao = new goodsDAO();
		table = new JTable();
		table.setFont(new Font("���� ���", Font.PLAIN, 12));
		// ���̺�1 ���콺���ý� �𵨸� ����̺�Ʈ
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				Object value = table.getValueAt(row, 2);
				String tmpname = value + "";
				lblname1.setText(tmpname);
				tfea1.setText("0");
				lblcost1.setText("0��");
			}
		});
		// ���̺�1 Ű���弱�ý� �𵨸� ����̺�Ʈ
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int row = table.getSelectedRow();

				if (e.getKeyText(e.getKeyCode()).equals("UP")) {
					row++;
				} else if (e.getKeyText(e.getKeyCode()).equals("DOWN")) {
					row--;
				}
				Object value = table.getValueAt(row, 2);
				String tmpname = value + "";
				lblname1.setText(tmpname);
				tfea1.setText("0");
				lblcost1.setText("0��");
			}
		});
		scrollPane1.setViewportView(table);

		table2 = new JTable();
		scrollPane2.setViewportView(table2);

		table3 = new JTable();
		// ���̺�3 ���콺���ý� ��� ����̺�Ʈ
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				table3num = table3.getSelectedRow() + 1;
				tfserial3.setText((dao.selectDelete(name, table3num).get(0)));
				tfname3.setText((dao.selectDelete(name, table3num).get(2)));
				tfcompany3.setText((dao.selectDelete(name, table3num).get(1)));
				tfspec1_3.setText((dao.selectDelete(name, table3num).get(3)));
				tfea3.setText((dao.selectDelete(name, table3num).get(4)));
				tfcost3.setText((dao.selectDelete(name, table3num).get(5)));
				tfprice3.setText((dao.selectDelete(name, table3num).get(6)));
				String directory = dao.selectDelete(name, table3num).get(7);
				if (name.equals("VGA")) {
					tfspec2_3.setText((dao.selectDelete(name, table3num).get(4)));
					tfea3.setText((dao.selectDelete(name, table3num).get(5)));
					tfcost3.setText((dao.selectDelete(name, table3num).get(6)));
					tfprice3.setText((dao.selectDelete(name, table3num).get(7)));
					directory = dao.selectDelete(name, table3num).get(8);
				}
				ImageIcon tmpImg = new ImageIcon(directory);
				try {
					Image imageSrc = tmpImg.getImage();
					Image newImage = imageSrc.getScaledInstance(142, 153, Image.SCALE_AREA_AVERAGING);
					img = new ImageIcon(newImage);
					lblimg3.setIcon(img);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ���̺�3 Ű�����Է½� ��� ����̺�Ʈ
		table3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int num = table3.getSelectedRow() + 1;
				tfserial3.setText((dao.selectDelete(name, num).get(0)));
				tfname3.setText((dao.selectDelete(name, num).get(2)));
				tfcompany3.setText((dao.selectDelete(name, num).get(1)));
				tfspec1_3.setText((dao.selectDelete(name, num).get(3)));
				tfea3.setText((dao.selectDelete(name, num).get(4)));
				tfcost3.setText((dao.selectDelete(name, num).get(5)));
				tfprice3.setText((dao.selectDelete(name, num).get(6)));
				String directory = dao.selectDelete(name, num).get(7);
				if (name.equals("VGA")) {
					tfspec2_3.setText((dao.selectDelete(name, num).get(4)));
					tfea3.setText((dao.selectDelete(name, num).get(5)));
					tfcost3.setText((dao.selectDelete(name, num).get(6)));
					tfprice3.setText((dao.selectDelete(name, num).get(7)));
					directory = dao.selectDelete(name, num).get(8);
				}
				ImageIcon tmpImg = new ImageIcon(directory);
				try {
					Image imageSrc = tmpImg.getImage();
					Image newImage = imageSrc.getScaledInstance(142, 153, Image.SCALE_AREA_AVERAGING);
					img = new ImageIcon(newImage);
					lblimg3.setIcon(img);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		table3.setFont(new Font("���� ���", Font.PLAIN, 12));
		scrollPane3.setViewportView(table3);

		tfprice2 = new JTextField();
		tfprice2.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfprice2.setHorizontalAlignment(SwingConstants.TRAILING);
		tfprice2.setText("0");
		tfprice2.setEditable(false);
		tfprice2.setBounds(420, 150, 135, 20);
		panel_item.add(tfprice2);
		tfprice2.setColumns(10);

		JLabel lblprice2 = new JLabel("�ǸŰ�=(����*0.2)+����");
		lblprice2.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblprice2.setHorizontalAlignment(SwingConstants.CENTER);
		lblprice2.setBounds(420, 130, 140, 20);
		panel_item.add(lblprice2);

		// Jlist�� DB�� ���
		ArrayList<String> jlistList = dao.jlistList();
		list = new JList(jlistList.toArray());
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(20, 105, 230));
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setFont(new Font("���� ���", Font.BOLD, 12));
		list.setBounds(0, 136, 160, 122);
		contentPane.add(list);
		list.setSelectedIndex(0);
		// �÷�������
		col = new Vector();
		col.add("ǰ��");
		col.add("������");
		col.add("��ǰ��");
		col.add("����");
		col.add("����");

		col2 = new Vector();
		col2.add("ǰ��");
		col2.add("������");
		col2.add("��ǰ��");
		col2.add("����");
		col2.add("����");
		name = "CPU";
		refreshTable();
		refreshTable2();
		// ����Ʈ ���ý� ���̺� ��� ��ȯ�̺�Ʈ
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					if (list.getSelectedIndex() == 0) {
						name = "CPU";
						col2.clear();
						col2.add("ǰ��");
						col2.add("������");
						col2.add("��ǰ��");
						col2.add("����");
						col2.add("����");
						lblspec1_2.setText("����");
						tfspec2_2.setEditable(false);
						lblspec1_3.setText("����");
						lblspec2_3.setText("-");
						tfspec2_3.setText("");
					} else if (list.getSelectedIndex() == 1) {
						name = "HDD";
						col2.clear();
						col2.add("ǰ��");
						col2.add("������");
						col2.add("��ǰ��");
						col2.add("�뷮");
						col2.add("����");
						lblspec1_2.setText("�뷮");
						tfspec2_2.setEditable(false);
						lblspec1_3.setText("�뷮");
						lblspec2_3.setText("-");
						tfspec2_3.setText("");
					} else if (list.getSelectedIndex() == 2) {
						name = "MAIN";
						col2.clear();
						col2.add("ǰ��");
						col2.add("����");
						col2.add("������");
						col2.add("��ǰ��");
						col2.add("����");
						lblspec1_2.setText("����");
						tfspec2_2.setEditable(false);
						lblspec1_3.setText("����");
						lblspec2_3.setText("-");
						tfspec2_3.setText("");
					} else if (list.getSelectedIndex() == 3) {
						name = "RAM";
						col2.clear();
						col2.add("ǰ��");
						col2.add("������");
						col2.add("��ǰ��");
						col2.add("�뷮");
						col2.add("����");
						lblspec1_2.setText("�뷮");
						tfspec2_2.setEditable(false);
						lblspec1_3.setText("�뷮");
						lblspec2_3.setText("-");
						tfspec2_3.setText("");
					} else if (list.getSelectedIndex() == 4) {
						name = "SSD";
						col2.clear();
						col2.add("ǰ��");
						col2.add("������");
						col2.add("��ǰ��");
						col2.add("�뷮");
						col2.add("����");
						lblspec1_2.setText("�뷮");
						tfspec2_2.setEditable(false);
						lblspec1_3.setText("�뷮");
						lblspec2_3.setText("-");
						tfspec2_3.setText("");
					} else if (list.getSelectedIndex() == 5) {
						name = "VGA";
						col2.clear();
						col2.add("ǰ��");
						col2.add("OS");
						col2.add("������");
						col2.add("��ǰ��");
						col2.add("�޸�");
						col2.add("����");
						lblspec1_2.setText("OS");
						lblspec2_2.setText("�޸�");
						tfspec2_2.setEditable(true);
						lblspec1_3.setText("OS");
						lblspec2_3.setText("�޸�");
					}
					refreshTable();
					refreshTable2();
				}
			}

		});

		JButton btnadd1 = new JButton("����");
		btnadd1.setFont(new Font("���� ���", Font.PLAIN, 12));
		btnadd1.setBounds(452, 41, 116, 23);
		panel_ea.add(btnadd1);
		// �����ư Ŭ���� �̺�Ʈ�߻�
		btnadd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(lblcost1.getText().equals("0 ��"))) {
					try {
						int no = Integer.valueOf(tfea1.getText());
						int row = table.getSelectedRow();
						int serial = table.getSelectedRow() + 1;
						Object value = table.getValueAt(row, 3);
						int tmpea = Integer.valueOf((value + ""));
						if (tmpea != 0) {
							dao.updateEa(name, no, tmpea, serial);
							JOptionPane.showMessageDialog(Management.this, "����Ϸ�");
							tfea1.setText("0");
							lblcost1.setText("0 ��");
							lblname1.setText("��");
							refreshTable();
							refreshTable2();
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(Management.this, "��ǰ�� ������ Ȯ�����ּ���");
					}
				} else {
					JOptionPane.showMessageDialog(Management.this, "��ǰ�� ������ Ȯ�����ּ���");
				}
			}
		});

		lblcost1 = new JLabel("0 ��");
		lblcost1.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblcost1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblcost1.setBounds(290, 43, 150, 21);
		panel_ea.add(lblcost1);

		JLabel lblprice1 = new JLabel("�ݾ� :");
		lblprice1.setFont(new Font("���� ���", Font.BOLD, 12));
		lblprice1.setHorizontalAlignment(SwingConstants.CENTER);
		lblprice1.setBounds(221, 43, 57, 21);
		panel_ea.add(lblprice1);

		JLabel lblNewLabel_1 = new JLabel("��ǰ�� :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 12));
		lblNewLabel_1.setBounds(12, 10, 57, 21);
		panel_ea.add(lblNewLabel_1);

		lblimg3 = new JLabel("����");
		lblimg3.setHorizontalAlignment(SwingConstants.CENTER);
		lblimg3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblimg3.setBounds(12, 5, 142, 153);
		panel_delete.add(lblimg3);

		JLabel lblserial3 = new JLabel("��ǰ ��ȣ");
		lblserial3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblserial3.setBounds(170, 5, 60, 25);
		panel_delete.add(lblserial3);

		tfserial3 = new JTextField();
		tfserial3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfserial3.setEditable(false);
		tfserial3.setBounds(250, 5, 105, 25);
		panel_delete.add(tfserial3);
		tfserial3.setColumns(10);

		JLabel lblname3 = new JLabel("�𵨸�");
		lblname3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblname3.setBounds(170, 35, 60, 25);
		panel_delete.add(lblname3);

		tfname3 = new JTextField();
		tfname3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfname3.setEditable(false);
		tfname3.setBounds(250, 35, 315, 25);
		panel_delete.add(tfname3);
		tfname3.setColumns(10);

		JLabel lblcompany3 = new JLabel("������");
		lblcompany3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblcompany3.setBounds(170, 65, 60, 25);
		panel_delete.add(lblcompany3);

		tfcompany3 = new JTextField();
		tfcompany3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfcompany3.setEditable(false);
		tfcompany3.setBounds(250, 65, 105, 25);
		panel_delete.add(tfcompany3);
		tfcompany3.setColumns(10);

		lblspec1_3 = new JLabel("����");
		lblspec1_3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblspec1_3.setBounds(170, 95, 60, 25);
		panel_delete.add(lblspec1_3);

		tfspec1_3 = new JTextField();
		tfspec1_3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfspec1_3.setEditable(false);
		tfspec1_3.setBounds(250, 95, 105, 25);
		panel_delete.add(tfspec1_3);
		tfspec1_3.setColumns(10);

		lblspec2_3 = new JLabel("-");
		lblspec2_3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblspec2_3.setBounds(170, 125, 60, 25);
		panel_delete.add(lblspec2_3);

		tfspec2_3 = new JTextField();
		tfspec2_3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfspec2_3.setEditable(false);
		tfspec2_3.setBounds(250, 125, 105, 25);
		panel_delete.add(tfspec2_3);
		tfspec2_3.setColumns(10);

		JButton btnDelete = new JButton("����");
		btnDelete.setFont(new Font("���� ���", Font.PLAIN, 12));
		btnDelete.setBounds(405, 5, 160, 25);
		panel_delete.add(btnDelete);
		// ���� ��ư �Է½� �̺�Ʈ �߻�
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tmpea = Integer.valueOf(tfea3.getText());
				int serial = Integer.valueOf(tfserial3.getText());
				if (tmpea != 0) {
					String[] str = { "��", "�ƴϿ�" };
					if (JOptionPane.showOptionDialog(Management.this, "��������ֽ��ϴ�.\n���������Ͻðڽ��ϱ�?", "����Ȯ��",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, str, str[0]) == 0) {
						dao.deleteGoods(name, serial);
						tfserial3.setText("");
						tfname3.setText("");
						tfcompany3.setText("");
						tfspec1_3.setText("");
						tfspec2_3.setText("");
						tfea3.setText("");
						tfcost3.setText("");
						tfprice3.setText("");
						lblimg3.setIcon(null);
						lblimg3.setText("����");
						JOptionPane.showMessageDialog(Management.this, "������ �Ϸ�Ǿ����ϴ�.");
						refreshTable();
						refreshTable2();
					}
				} else {
					dao.deleteGoods(name, serial);
					tfserial3.setText("");
					tfname3.setText("");
					tfcompany3.setText("");
					tfspec1_3.setText("");
					tfspec2_3.setText("");
					tfea3.setText("");
					tfcost3.setText("");
					tfprice3.setText("");
					lblimg3.setText("");
					JOptionPane.showMessageDialog(Management.this, "������ �Ϸ�Ǿ����ϴ�.");
					refreshTable();
					refreshTable2();
				}
				//�߰� ����� �����Ǹ� �ڿ��� �ϳ��� serial�� ���
				for (int i = serial; i <= table3.getRowCount(); i++) {
					int Aserial = i;
					int Bserial = i+1;
					dao.updateserial(name, Aserial, Bserial);
					refreshTable();
					refreshTable2();
				}
			}

		});

		JLabel lblcost3 = new JLabel("����");
		lblcost3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblcost3.setHorizontalAlignment(SwingConstants.LEFT);
		lblcost3.setBounds(390, 95, 60, 25);
		panel_delete.add(lblcost3);

		tfcost3 = new JTextField();
		tfcost3.setHorizontalAlignment(SwingConstants.TRAILING);
		tfcost3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfcost3.setEditable(false);
		tfcost3.setBounds(460, 95, 105, 25);
		panel_delete.add(tfcost3);
		tfcost3.setColumns(10);

		JLabel lblprice3 = new JLabel("�ǸŰ�");
		lblprice3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblprice3.setBounds(390, 125, 60, 25);
		panel_delete.add(lblprice3);

		tfprice3 = new JTextField();
		tfprice3.setHorizontalAlignment(SwingConstants.TRAILING);
		tfprice3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfprice3.setEditable(false);
		tfprice3.setBounds(460, 125, 105, 25);
		panel_delete.add(tfprice3);
		tfprice3.setColumns(10);

		JLabel lblea3 = new JLabel("���");
		lblea3.setFont(new Font("���� ���", Font.PLAIN, 12));
		lblea3.setBounds(390, 65, 60, 25);
		panel_delete.add(lblea3);

		tfea3 = new JTextField();
		tfea3.setHorizontalAlignment(SwingConstants.TRAILING);
		tfea3.setFont(new Font("���� ���", Font.PLAIN, 12));
		tfea3.setEditable(false);
		tfea3.setBounds(460, 65, 105, 25);
		panel_delete.add(tfea3);
		tfea3.setColumns(10);

		ImageIcon tmplogo = new ImageIcon("logo.png");
		try {
			Image imageSrc = tmplogo.getImage();
			Image newImage = imageSrc.getScaledInstance(160, 90, Image.SCALE_AREA_AVERAGING);
			logo = new ImageIcon(newImage);
			JLabel lbllogo = new JLabel(logo);
			lbllogo.setFont(new Font("���� ���", Font.PLAIN, 12));
			lbllogo.setBounds(0, 10, 160, 90);
			contentPane.add(lbllogo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// table1 ����� �޼ҵ�
	public void refreshTable() {
		DefaultTableModel model = new DefaultTableModel(dao.jtableList(name), col);
		table.setModel(model);
		model.fireTableDataChanged();
		resizeColumnWidth(table);

		// ���̺� ���� ��� �����ϱ�
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cell = table.getColumnModel();
		cell.getColumn(3).setCellRenderer(right);
		cell.getColumn(4).setCellRenderer(right);
		cell.getColumn(0).setCellRenderer(center);
		cell.getColumn(1).setCellRenderer(center);
	}

	// table2,3 ����� �޼ҵ�
	public void refreshTable2() {
		DefaultTableModel model2 = new DefaultTableModel(dao.jtable2List(name), col2);
		table2.setModel(model2);
		resizeColumnWidth(table2);

		DefaultTableModel model3 = new DefaultTableModel(dao.jtable2List(name), col2);
		table3.setModel(model3);
		resizeColumnWidth(table3);

		// ���̺� ���� ��� �����ϱ�
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel cell = table2.getColumnModel();
		TableColumnModel cell2 = table3.getColumnModel();
		if (table2.getColumnName(1).equals("OS")) {
			cell.getColumn(3).setCellRenderer(center);
			cell.getColumn(4).setCellRenderer(center);
			cell.getColumn(5).setCellRenderer(right);
			cell2.getColumn(3).setCellRenderer(center);
			cell2.getColumn(4).setCellRenderer(center);
			cell2.getColumn(5).setCellRenderer(right);
		} else {
			cell.getColumn(2).setCellRenderer(center);
			cell.getColumn(3).setCellRenderer(center);
			cell.getColumn(4).setCellRenderer(right);
			cell2.getColumn(2).setCellRenderer(center);
			cell2.getColumn(3).setCellRenderer(center);
			cell2.getColumn(4).setCellRenderer(right);
		}
		cell.getColumn(0).setCellRenderer(center);
		cell.getColumn(1).setCellRenderer(center);
		cell.getColumn(2).setCellRenderer(center);
		cell2.getColumn(0).setCellRenderer(center);
		cell2.getColumn(1).setCellRenderer(center);
		cell2.getColumn(2).setCellRenderer(center);
	}

	// ��ũ�� �ڵ����� �޼ҵ�
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
