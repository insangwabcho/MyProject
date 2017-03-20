package insangjo.DTO;

public class AddCartDTO {
  private int orderNo;
  private String id;
  private int cpuSerial;
  private int vgaSerial;
  private int mainSerial;
  private int ssdSerial;
  private int hddSerial;
  private int ramSerial;
  private int totalPrice;

  public AddCartDTO(int orderNo, String id, int cpuSerial, int vgaSerial, int mainSerial, int ssdSerial, int hddSerial, int ramSerial, int totalPrice) {
    super();
    this.orderNo = orderNo;
    this.id = id;
    this.cpuSerial = cpuSerial;
    this.vgaSerial = vgaSerial;
    this.mainSerial = mainSerial;
    this.ssdSerial = ssdSerial;
    this.hddSerial = hddSerial;
    this.ramSerial = ramSerial;
    this.totalPrice = totalPrice;
  }

  public AddCartDTO() {
    super();

  }

  @Override
  public String toString() {
    return "AddCartDTO [orderNo=" + orderNo + ", id=" + id + ", cpuSerial=" + cpuSerial + ", vgaSerial=" + vgaSerial + ", mainSerial=" + mainSerial + ", ssdSerial=" + ssdSerial + ", hddSerial="
        + hddSerial + ", ramSerial=" + ramSerial + ", totalPrice=" + totalPrice + "]";
  }

  public int getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(int orderNo) {
    this.orderNo = orderNo;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCpuSerial() {
    return cpuSerial;
  }

  public void setCpuSerial(int cpuSerial) {
    this.cpuSerial = cpuSerial;
  }

  public int getVgaSerial() {
    return vgaSerial;
  }

  public void setVgaSerial(int vgaSerial) {
    this.vgaSerial = vgaSerial;
  }

  public int getMainSerial() {
    return mainSerial;
  }

  public void setMainSerial(int mainSerial) {
    this.mainSerial = mainSerial;
  }

  public int getSsdSerial() {
    return ssdSerial;
  }

  public void setSsdSerial(int ssdSerial) {
    this.ssdSerial = ssdSerial;
  }

  public int getHddSerial() {
    return hddSerial;
  }

  public void setHddSerial(int hddSerial) {
    this.hddSerial = hddSerial;
  }

  public int getRamSerial() {
    return ramSerial;
  }

  public void setRamSerial(int ramSerial) {
    this.ramSerial = ramSerial;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

}
