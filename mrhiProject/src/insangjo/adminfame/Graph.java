package insangjo.adminfame;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Graph extends JFrame {

  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Graph frame = new Graph();
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
  public Graph() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 534, 400);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JPanel graphpane = new JPanel() {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

      }
    };
    graphpane.setBounds(71, 6, 455, 330);
    contentPane.add(graphpane);
    graphpane.setLayout(null);

    JLabel lblMonth1 = new JLabel("월");
    lblMonth1.setHorizontalAlignment(SwingConstants.CENTER);
    lblMonth1.setBounds(84, 348, 61, 16);
    contentPane.add(lblMonth1);

    JLabel lblMonth2 = new JLabel("월");
    lblMonth2.setHorizontalAlignment(SwingConstants.CENTER);
    lblMonth2.setBounds(157, 348, 61, 16);
    contentPane.add(lblMonth2);

    JLabel lblMonth3 = new JLabel("월");
    lblMonth3.setHorizontalAlignment(SwingConstants.CENTER);
    lblMonth3.setBounds(230, 348, 61, 16);
    contentPane.add(lblMonth3);

    JLabel lblMonth4 = new JLabel("월");
    lblMonth4.setHorizontalAlignment(SwingConstants.CENTER);
    lblMonth4.setBounds(303, 348, 61, 16);
    contentPane.add(lblMonth4);

    JLabel lblMonth5 = new JLabel("월");
    lblMonth5.setHorizontalAlignment(SwingConstants.CENTER);
    lblMonth5.setBounds(376, 348, 61, 16);
    contentPane.add(lblMonth5);

    JLabel lblMonth6 = new JLabel("월");
    lblMonth6.setHorizontalAlignment(SwingConstants.CENTER);
    lblMonth6.setBounds(449, 348, 61, 16);
    contentPane.add(lblMonth6);

    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setBounds(6, 283, 61, 16);
    contentPane.add(lblNewLabel);

    JLabel label_4 = new JLabel("New label");
    label_4.setHorizontalAlignment(SwingConstants.CENTER);
    label_4.setBounds(6, 23, 61, 16);
    contentPane.add(label_4);

  }
}
