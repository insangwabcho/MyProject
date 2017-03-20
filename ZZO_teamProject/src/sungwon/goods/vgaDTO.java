package sungwon.goods;

public class vgaDTO {
  private int vga_serial;
  private String vga_os;
  private String vga_company;
  private String vga_name;
  private String vga_memory;
  private int vga_ea;
  private int vga_price;
  private String vga_img;
  private int vga_cost;

  public int getVga_serial() {
    return vga_serial;
  }

  public void setVga_serial(int vga_serial) {
    this.vga_serial = vga_serial;
  }

  public String getVga_os() {
    return vga_os;
  }

  public void setVga_os(String vga_os) {
    this.vga_os = vga_os;
  }

  public String getVga_company() {
    return vga_company;
  }

  public void setVga_company(String vga_company) {
    this.vga_company = vga_company;
  }

  public String getVga_name() {
    return vga_name;
  }

  public void setVga_name(String vga_name) {
    this.vga_name = vga_name;
  }

  public String getVga_memory() {
    return vga_memory;
  }

  public void setVga_memory(String vga_memory) {
    this.vga_memory = vga_memory;
  }

  public int getVga_ea() {
    return vga_ea;
  }

  public void setVga_ea(int vga_ea) {
    this.vga_ea = vga_ea;
  }

  public int getVga_price() {
    return vga_price;
  }

  public void setVga_price(int vga_price) {
    this.vga_price = vga_price;
  }

  public String getVga_img() {
    return vga_img;
  }

  public void setVga_img(String vga_img) {
    this.vga_img = vga_img;
  }

  public int getVga_cost() {
    return vga_cost;
  }

  public void setVga_cost(int vga_cost) {
    this.vga_cost = vga_cost;
  }

  @Override
  public String toString() {
    return "vgaDTO [vga_serial=" + vga_serial + ", vga_os=" + vga_os + ", vga_company=" + vga_company + ", vga_name=" + vga_name + ", vga_memory=" + vga_memory + ", vga_ea=" + vga_ea + ", vga_price="
        + vga_price + ", vga_img=" + vga_img + ", vga_cost=" + vga_cost + "]";
  }

  public vgaDTO() {
  }

  public vgaDTO(int vga_serial, String vga_os, String vga_company, String vga_name, String vga_memory, int vga_ea, int vga_price, String vga_img, int vga_cost) {
    super();
    this.vga_serial = vga_serial;
    this.vga_os = vga_os;
    this.vga_company = vga_company;
    this.vga_name = vga_name;
    this.vga_memory = vga_memory;
    this.vga_ea = vga_ea;
    this.vga_price = vga_price;
    this.vga_img = vga_img;
    this.vga_cost = vga_cost;
  }

}