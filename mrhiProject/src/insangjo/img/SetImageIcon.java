package insangjo.img;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SetImageIcon {

  public ImageIcon getScaleImg(String url, int width, int height) {
    String a = (String.valueOf(sungwon.ImgPath.class.getResource("img"))).replaceAll("file:", "") + url;
    ImageIcon imgicon = new ImageIcon(a);
    Image t = imgicon.getImage();
    imgicon = new ImageIcon(t.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
    return imgicon;
  }

  public ImageIcon getDetailImg(String url, int width) {
    String a = (String.valueOf(sungwon.ImgPath.class.getResource("img"))).replaceAll("file:", "") + url;
    ImageIcon imgicon = new ImageIcon(a);
    Image t = imgicon.getImage();
    imgicon = new ImageIcon(t.getScaledInstance(width, t.getHeight(null) - 40, Image.SCALE_SMOOTH));
    return imgicon;
  }
}
