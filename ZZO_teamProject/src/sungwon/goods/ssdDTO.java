package sungwon.goods;

public class ssdDTO {

  private int ssd_serial;
  private String ssd_name;
  private String ssd_company;
  private String ssd_volume;
  private int ssd_ea;
  private int ssd_price;
  private String ssd_img;
  private int ssd_cost;

  public int getSsd_serial() {
    return ssd_serial;
  }

  public void setSsd_serial(int ssd_serial) {
    this.ssd_serial = ssd_serial;
  }

  public String getSsd_name() {
    return ssd_name;
  }

  public void setSsd_name(String ssd_name) {
    this.ssd_name = ssd_name;
  }

  public String getSsd_company() {
    return ssd_company;
  }

  public void setSsd_company(String ssd_company) {
    this.ssd_company = ssd_company;
  }

  public String getSsd_volume() {
    return ssd_volume;
  }

  public void setSsd_volume(String ssd_volume) {
    this.ssd_volume = ssd_volume;
  }

  public int getSsd_ea() {
    return ssd_ea;
  }

  public void setSsd_ea(int ssd_ea) {
    this.ssd_ea = ssd_ea;
  }

  public int getSsd_price() {
    return ssd_price;
  }

  public void setSsd_price(int ssd_price) {
    this.ssd_price = ssd_price;
  }

  public String getSsd_img() {
    return ssd_img;
  }

  public void setSsd_img(String ssd_img) {
    this.ssd_img = ssd_img;
  }

  public int getSsd_cost() {
    return ssd_cost;
  }

  public void setSsd_cost(int ssd_cost) {
    this.ssd_cost = ssd_cost;
  }

  @Override
  public String toString() {
    return "ssdDTO [ssd_serial=" + ssd_serial + ", ssd_name=" + ssd_name + ", ssd_company=" + ssd_company + ", ssd_volume=" + ssd_volume + ", ssd_ea=" + ssd_ea + ", ssd_price=" + ssd_price
        + ", ssd_img=" + ssd_img + ", ssd_cost=" + ssd_cost + "]";
  }

  public ssdDTO() {
  }

  public ssdDTO(int ssd_serial, String ssd_name, String ssd_company, String ssd_volume, int ssd_ea, int ssd_price, String ssd_img, int ssd_cost) {
    super();
    this.ssd_serial = ssd_serial;
    this.ssd_name = ssd_name;
    this.ssd_company = ssd_company;
    this.ssd_volume = ssd_volume;
    this.ssd_ea = ssd_ea;
    this.ssd_price = ssd_price;
    this.ssd_img = ssd_img;
    this.ssd_cost = ssd_cost;
  }

}