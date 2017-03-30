package insangjo.img;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class SetImageIcon {

  public ImageIcon getScaleImg(URL url, int width, int height) {
    ImageIcon imgicon = new ImageIcon(url);
    Image t = imgicon.getImage();
    imgicon = new ImageIcon(t.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
    return imgicon;
  }

  public ImageIcon getDetailImg(URL url, int width) {
    ImageIcon imgicon = new ImageIcon(url);
    Image t = imgicon.getImage();
    imgicon = new ImageIcon(t.getScaledInstance(width, t.getHeight(null) - 40, Image.SCALE_SMOOTH));
    return imgicon;
  }
}
