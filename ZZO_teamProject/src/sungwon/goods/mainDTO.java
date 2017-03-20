package sungwon.goods;

public class mainDTO {

  private int main_serial;
  private String main_name;
  private String main_company;
  private String main_socket;
  private int main_ea;
  private int main_price;
  private String main_img;
  private int main_cost;

  public int getMain_serial() {
    return main_serial;
  }

  public void setMain_serial(int main_serial) {
    this.main_serial = main_serial;
  }

  public String getMain_name() {
    return main_name;
  }

  public void setMain_name(String main_name) {
    this.main_name = main_name;
  }

  public String getMain_company() {
    return main_company;
  }

  public void setMain_company(String main_company) {
    this.main_company = main_company;
  }

  public String getMain_socket() {
    return main_socket;
  }

  public void setMain_socket(String main_socket) {
    this.main_socket = main_socket;
  }

  public int getMain_ea() {
    return main_ea;
  }

  public void setMain_ea(int main_ea) {
    this.main_ea = main_ea;
  }

  public int getMain_price() {
    return main_price;
  }

  public void setMain_price(int main_price) {
    this.main_price = main_price;
  }

  public String getMain_img() {
    return main_img;
  }

  public void setMain_img(String main_img) {
    this.main_img = main_img;
  }

  public int getMain_cost() {
    return main_cost;
  }

  public void setMain_cost(int main_cost) {
    this.main_cost = main_cost;
  }

  @Override
  public String toString() {
    return "mainDTO [main_serial=" + main_serial + ", main_name=" + main_name + ", main_company=" + main_company + ", main_socket=" + main_socket + ", main_ea=" + main_ea + ", main_price="
        + main_price + ", main_img=" + main_img + ", main_cost=" + main_cost + "]";
  }

  public mainDTO(int main_serial, String main_name, String main_company, String main_socket, int main_ea, int main_price, String main_img, int main_cost) {
    super();
    this.main_serial = main_serial;
    this.main_name = main_name;
    this.main_company = main_company;
    this.main_socket = main_socket;
    this.main_ea = main_ea;
    this.main_price = main_price;
    this.main_img = main_img;
    this.main_cost = main_cost;
  }

  public mainDTO() {
  }

}