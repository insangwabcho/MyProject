package insangjo.userframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DeleteFrame extends JFrame {

  private JPanel contentPane;
  private DefaultTableModel modiModel;

  /**
   * Create the frame.
   */
  public DeleteFrame(DefaultTableModel model, int selectedrow, String name, String ea) {

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(236, 308, 300, 100);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblSelect = new JLabel(name);
    lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
    lblSelect.setBounds(6, 6, 206, 21);
    contentPane.add(lblSelect);

    JLabel lblNewLabel = new JLabel("삭제하실 수량");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(16, 34, 86, 38);
    contentPane.add(lblNewLabel);

    JLabel lblEa = new JLabel("0");
    lblEa.setHorizontalAlignment(SwingConstants.CENTER);
    lblEa.setBounds(114, 34, 61, 38);
    contentPane.add(lblEa);

    JButton btnNewButton = new JButton("▲");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (Integer.parseInt(lblEa.getText()) == Integer.parseInt(ea)) {
          JOptionPane.showMessageDialog(DeleteFrame.this, "장바구니에 담으신 수량 내에서 선택해주세요 (수량 : " + ea + ")");
          return;
        }
        int t = Integer.parseInt(lblEa.getText());
        t++;
        lblEa.setText(t + "");
      }
    });
    btnNewButton.setBounds(177, 35, 19, 16);
    contentPane.add(btnNewButton);

    JButton button = new JButton("▼");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (Integer.parseInt(lblEa.getText()) == 0) {
          JOptionPane.showMessageDialog(DeleteFrame.this, "0개 이하로 설정하실수 없습니다");
          return;
        }
        int t = Integer.parseInt(lblEa.getText());
        t--;
        lblEa.setText(t + "");
      }
    });
    button.setBounds(177, 56, 19, 16);
    contentPane.add(button);

    JButton btnNewButton_1 = new JButton("완료");
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (Integer.parseInt(ea) == Integer.parseInt(lblEa.getText())) {
          model.removeRow(selectedrow);
          modiModel = model;
          dispose();
        }

      }
    });
    btnNewButton_1.setBounds(221, 6, 73, 66);
    contentPane.add(btnNewButton_1);

    setResizable(false);
  }

  public DefaultTableModel getModel() {
    return modiModel;
  }
}
