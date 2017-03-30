package sungwon.Ad;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AD extends Thread {
  private ArrayList<ImageIcon> ad_path;
  private String ad_img;
  private String aa;
  private JLabel lab;

  public String getAd_img() {
    return ad_img;
  }

  public void setAd_img(String ad_img) {
    this.ad_img = ad_img;
  }

  public AD(JLabel lab, JTextField tfLid) {
    ad_path = new ArrayList<>();

    URL a = sungwon.DB.DB.class.getResource("img/AD/AD0.jpg");
    ImageIcon tmplogo = new insangjo.img.SetImageIcon().getScaleImg(a, 460, 60);
    ad_path.add(tmplogo);
    a = sungwon.DB.DB.class.getResource("img/AD/AD1.jpg");
    tmplogo = new insangjo.img.SetImageIcon().getScaleImg(a, 460, 60);
    ad_path.add(tmplogo);
    a = sungwon.DB.DB.class.getResource("img/AD/AD2.jpg");
    tmplogo = new insangjo.img.SetImageIcon().getScaleImg(a, 460, 60);
    ad_path.add(tmplogo);
    a = sungwon.DB.DB.class.getResource("img/AD/AD3.jpg");
    tmplogo = new insangjo.img.SetImageIcon().getScaleImg(a, 460, 60);
    ad_path.add(tmplogo);
    a = sungwon.DB.DB.class.getResource("img/AD/AD4.jpg");
    tmplogo = new insangjo.img.SetImageIcon().getScaleImg(a, 460, 60);
    ad_path.add(tmplogo);
    a = sungwon.DB.DB.class.getResource("img/AD/AD5.jpg");
    tmplogo = new insangjo.img.SetImageIcon().getScaleImg(a, 460, 60);
    ad_path.add(tmplogo);
    this.lab = lab;
    Thread th = new Thread(this);
    th.start();
  }

  @Override
  public void run() {
    try {
      while (true) {
        for (int i = 0; i < ad_path.size(); i++) {
          Thread.sleep(3000);
          lab.setIcon(ad_path.get(i));
          //          if (i > ad_path.size()) {
          //            i = 0;
          //          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
