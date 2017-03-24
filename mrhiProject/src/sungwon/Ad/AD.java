package sungwon.Ad;

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
    ad_path.add(sii.getScaleImg("/AD/AD0.PNG", lab.getWidth(), lab.getHeight()));
    ad_path.add(sii.getScaleImg("/AD/AD1.PNG", lab.getWidth(), lab.getHeight()));
    ad_path.add(sii.getScaleImg("/AD/AD2.PNG", lab.getWidth(), lab.getHeight()));
    ad_path.add(sii.getScaleImg("/AD/AD3.PNG", lab.getWidth(), lab.getHeight()));
    ad_path.add(sii.getScaleImg("/AD/AD4.PNG", lab.getWidth(), lab.getHeight()));
    ad_path.add(sii.getScaleImg("/AD/AD5.PNG", lab.getWidth(), lab.getHeight()));

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
