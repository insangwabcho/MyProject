package sungwon.goods;

public class hddDTO {
  private int hdd_serial;
  private String hdd_name;
  private String hdd_company;
  private String hdd_volume;
  private int hdd_ea;
  private int hdd_price;
  private String hdd_img;
  private int hdd_cost;

  public int getHdd_serial() {
    return hdd_serial;
  }

  public void setHdd_serial(int hdd_serial) {
    this.hdd_serial = hdd_serial;
  }

  public String getHdd_name() {
    return hdd_name;
  }

  public void setHdd_name(String hdd_name) {
    this.hdd_name = hdd_name;
  }

  public String getHdd_company() {
    return hdd_company;
  }

  public void setHdd_company(String hdd_company) {
    this.hdd_company = hdd_company;
  }

  public String getHdd_volume() {
    return hdd_volume;
  }

  public void setHdd_volume(String hdd_volume) {
    this.hdd_volume = hdd_volume;
  }

  public int getHdd_ea() {
    return hdd_ea;
  }

  public void setHdd_ea(int hdd_ea) {
    this.hdd_ea = hdd_ea;
  }

  public int getHdd_price() {
    return hdd_price;
  }

  public void setHdd_price(int hdd_price) {
    this.hdd_price = hdd_price;
  }

  public String getHdd_img() {
    return hdd_img;
  }

  public void setHdd_img(String hdd_img) {
    this.hdd_img = hdd_img;
  }

  public int getHdd_cost() {
    return hdd_cost;
  }

  public void setHdd_cost(int hdd_cost) {
    this.hdd_cost = hdd_cost;
  }

  @Override
  public String toString() {
    return "hddDTO [hdd_serial=" + hdd_serial + ", hdd_name=" + hdd_name + ", hdd_company=" + hdd_company + ", hdd_volume=" + hdd_volume + ", hdd_ea=" + hdd_ea + ", hdd_price=" + hdd_price
        + ", hdd_img=" + hdd_img + ", hdd_cost=" + hdd_cost + "]";
  }

  public hddDTO() {
  }

  public hddDTO(int hdd_serial, String hdd_name, String hdd_company, String hdd_volume, int hdd_ea, int hdd_price, String hdd_img, int hdd_cost) {
    super();
    this.hdd_serial = hdd_serial;
    this.hdd_name = hdd_name;
    this.hdd_company = hdd_company;
    this.hdd_volume = hdd_volume;
    this.hdd_ea = hdd_ea;
    this.hdd_price = hdd_price;
    this.hdd_img = hdd_img;
    this.hdd_cost = hdd_cost;
  }

}
