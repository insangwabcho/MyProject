package sungwon.goods;

public class cpuDTO {

  private int cpu_serial;
  private String cpu_name;
  private String cpu_company;
  private String cpu_generation;
  private int cpu_ea;
  private int cpu_price;
  private String cpu_img;
  private int cpu_cost;

  public int getCpu_serial() {
    return cpu_serial;
  }

  public void setCpu_serial(int cpu_serial) {
    this.cpu_serial = cpu_serial;
  }

  public String getCpu_name() {
    return cpu_name;
  }

  public void setCpu_name(String cpu_name) {
    this.cpu_name = cpu_name;
  }

  public String getCpu_company() {
    return cpu_company;
  }

  public void setCpu_company(String cpu_company) {
    this.cpu_company = cpu_company;
  }

  public String getCpu_generation() {
    return cpu_generation;
  }

  public void setCpu_generation(String cpu_generation) {
    this.cpu_generation = cpu_generation;
  }

  public int getCpu_ea() {
    return cpu_ea;
  }

  public void setCpu_ea(int cpu_ea) {
    this.cpu_ea = cpu_ea;
  }

  public int getCpu_price() {
    return cpu_price;
  }

  public void setCpu_price(int cpu_price) {
    this.cpu_price = cpu_price;
  }

  public String getCpu_img() {
    return cpu_img;
  }

  public void setCpu_img(String cpu_img) {
    this.cpu_img = cpu_img;
  }

  public int getCpu_cost() {
    return cpu_cost;
  }

  public void setCpu_cost(int cpu_cost) {
    this.cpu_cost = cpu_cost;
  }

  @Override
  public String toString() {
    return "cpuDTO [cpu_serial=" + cpu_serial + ", cpu_name=" + cpu_name + ", cpu_company=" + cpu_company + ", cpu_generation=" + cpu_generation + ", cpu_ea=" + cpu_ea + ", cpu_price=" + cpu_price
        + ", cpu_img=" + cpu_img + ", cpu_cost=" + cpu_cost + "]";
  }

  public cpuDTO() {
  }

  public cpuDTO(int cpu_serial, String cpu_name, String cpu_company, String cpu_generation, int cpu_ea, int cpu_price, String cpu_img, int cpu_cost) {
    super();
    this.cpu_serial = cpu_serial;
    this.cpu_name = cpu_name;
    this.cpu_company = cpu_company;
    this.cpu_generation = cpu_generation;
    this.cpu_ea = cpu_ea;
    this.cpu_price = cpu_price;
    this.cpu_img = cpu_img;
    this.cpu_cost = cpu_cost;
  }

}
