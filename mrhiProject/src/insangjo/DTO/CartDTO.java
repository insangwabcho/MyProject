package insangjo.DTO;

import java.sql.Date;

public class CartDTO {
  private int order_no;
  private String id;
  private String name;
  private int cpu;
  private int vga;
  private int main;
  private int ram;
  private int hdd;
  private int ssd;
  private int ram2;
  private int total;
  private Date buydate;
  private String address;
  private String dvs; //배송상태

  public CartDTO() {
    super();

  }
  public CartDTO(int order_no, String id, String name, int cpu, int vga, int main, int ram, int hdd, int ssd, int ram2, int total, String dvs) {
	    super();
	    this.order_no = order_no;
	    this.id = id;
	    this.name = name;
	    this.cpu = cpu;
	    this.vga = vga;
	    this.main = main;
	    this.ram = ram;
	    this.hdd = hdd;
	    this.ssd = ssd;
	    this.ram2 = ram2;
	    this.total = total;
	    this.dvs = dvs;
	  }

  public CartDTO(int order_no, String id, int cpu, int vga, int main, int ram, int hdd, int ssd, int ram2, int total, Date buydate, String address) {
    super();
    this.order_no = order_no;
    this.id = id;
    this.cpu = cpu;
    this.vga = vga;
    this.main = main;
    this.ram = ram;
    this.hdd = hdd;
    this.ssd = ssd;
    this.ram2 = ram2;
    this.total = total;
    this.buydate = buydate;
    this.address = address;
  }

  public int getOrder_no() {
    return order_no;
  }

  public void setOrder_no(int order_no) {
    this.order_no = order_no;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCpu() {
    return cpu;
  }

  public void setCpu(int cpu) {
    this.cpu = cpu;
  }

  public int getVga() {
    return vga;
  }

  public void setVga(int vga) {
    this.vga = vga;
  }

  public int getMain() {
    return main;
  }

  public void setMain(int main) {
    this.main = main;
  }

  public int getRam() {
    return ram;
  }

  public void setRam(int ram) {
    this.ram = ram;
  }

  public int getHdd() {
    return hdd;
  }

  public void setHdd(int hdd) {
    this.hdd = hdd;
  }

  public int getSsd() {
    return ssd;
  }

  public void setSsd(int ssd) {
    this.ssd = ssd;
  }

  public int getRam2() {
    return ram2;
  }

  public void setRam2(int ram2) {
    this.ram2 = ram2;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public Date getBuydate() {
    return buydate;
  }

  public void setBuydate(Date buydate) {
    this.buydate = buydate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
	return name;
  }
  public void setName(String name) {
	this.name = name;
  }
  public String getDvs() {
	return dvs;
  }
  public void setDvs(String dvs) {
	this.dvs = dvs;
  }
@Override
  public String toString() {
    return "CartDTO [order_no=" + order_no + ", id=" + id + ", cpu=" + cpu + ", vga=" + vga + ", main=" + main + ", ram=" + ram + ", hdd=" + hdd + ", ssd=" + ssd + ", ram2=" + ram2 + ", total="
        + total + ", buydate=" + buydate + ", address=" + address + "]";
  }

}
