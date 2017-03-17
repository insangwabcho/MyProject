package DTO;

public class OrderpageDTO {
  private String pname;
  private String name;
  private String generation;
  private int ea;
  private int price;
  private int img_path;

  public OrderpageDTO() {
    super();

  }

  public OrderpageDTO(String pname) {
    super();
    this.pname = pname;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  @Override
  public String toString() {
    return "ProdlistDTO [pname=" + pname + ", name=" + name + ", generation=" + generation + ", ea=" + ea + ", price=" + price + ", img_path=" + img_path + "]";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGeneration() {
    return generation;
  }

  public void setGeneration(String generation) {
    this.generation = generation;
  }

  public int getEa() {
    return ea;
  }

  public void setEa(int ea) {
    this.ea = ea;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public OrderpageDTO(String pname, String name, String generation, int ea, int price, int img_path) {
    super();
    this.pname = pname;
    this.name = name;
    this.generation = generation;
    this.ea = ea;
    this.price = price;
    this.img_path = img_path;
  }

  public int getImg_path() {
    return img_path;
  }

  public void setImg_path(int img_path) {
    this.img_path = img_path;
  }

}
