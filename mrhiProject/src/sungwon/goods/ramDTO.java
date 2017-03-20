package sungwon.goods;

public class ramDTO {

  private int ram_serial;
  private String ram_name;
  private String ram_company;
  private String ram_volume;
  private int ram_ea;
  private int ram_price;
  private String ram_img;
  private int ram_cost;

  public int getRam_serial() {
    return ram_serial;
  }

  public void setRam_serial(int ram_serial) {
    this.ram_serial = ram_serial;
  }

  public String getRam_name() {
    return ram_name;
  }

  public void setRam_name(String ram_name) {
    this.ram_name = ram_name;
  }

  public String getRam_company() {
    return ram_company;
  }

  public void setRam_company(String ram_company) {
    this.ram_company = ram_company;
  }

  public String getRam_volume() {
    return ram_volume;
  }

  public void setRam_volume(String ram_volume) {
    this.ram_volume = ram_volume;
  }

  public int getRam_ea() {
    return ram_ea;
  }

  public void setRam_ea(int ram_ea) {
    this.ram_ea = ram_ea;
  }

  public int getRam_price() {
    return ram_price;
  }

  public void setRam_price(int ram_price) {
    this.ram_price = ram_price;
  }

  public String getRam_img() {
    return ram_img;
  }

  public void setRam_img(String ram_img) {
    this.ram_img = ram_img;
  }

  public int getRam_cost() {
    return ram_cost;
  }

  public void setRam_cost(int ram_cost) {
    this.ram_cost = ram_cost;
  }

  @Override
  public String toString() {
    return "ramDTO [ram_serial=" + ram_serial + ", ram_name=" + ram_name + ", ram_company=" + ram_company + ", ram_volume=" + ram_volume + ", ram_ea=" + ram_ea + ", ram_price=" + ram_price
        + ", ram_img=" + ram_img + ", ram_cost=" + ram_cost + "]";
  }

  public ramDTO() {
  }

  public ramDTO(int ram_serial, String ram_name, String ram_company, String ram_volume, int ram_ea, int ram_price, String ram_img, int ram_cost) {
    super();
    this.ram_serial = ram_serial;
    this.ram_name = ram_name;
    this.ram_company = ram_company;
    this.ram_volume = ram_volume;
    this.ram_ea = ram_ea;
    this.ram_price = ram_price;
    this.ram_img = ram_img;
    this.ram_cost = ram_cost;
  }

}