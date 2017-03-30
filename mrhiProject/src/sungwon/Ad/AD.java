package sungwon.Ad;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

  public AD(JLabel lab) {
    ad_path = new ArrayList<>();
    insangjo.img.SetImageIcon sii = new insangjo.img.SetImageIcon();
    URL url = sungwon.DB.DB.class.getResource("img/AD/AD0.JPG");
    ad_path.add(sii.getScaleImg(url, lab.getWidth(), lab.getHeight()));
    url = sungwon.DB.DB.class.getResource("img/AD/AD1.JPG");
    ad_path.add(sii.getScaleImg(url, lab.getWidth(), lab.getHeight()));
    url = sungwon.DB.DB.class.getResource("img/AD/AD2.JPG");
    ad_path.add(sii.getScaleImg(url, lab.getWidth(), lab.getHeight()));
    url = sungwon.DB.DB.class.getResource("img/AD/AD3.JPG");
    ad_path.add(sii.getScaleImg(url, lab.getWidth(), lab.getHeight()));
    url = sungwon.DB.DB.class.getResource("img/AD/AD4.JPG");
    ad_path.add(sii.getScaleImg(url, lab.getWidth(), lab.getHeight()));
    url = sungwon.DB.DB.class.getResource("img/AD/AD5.JPG");
    ad_path.add(sii.getScaleImg(url, lab.getWidth(), lab.getHeight()));

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
          if (i > ad_path.size()) {
            i = 0;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
