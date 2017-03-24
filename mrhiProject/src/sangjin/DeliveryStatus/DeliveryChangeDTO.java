package sangjin.DeliveryStatus;

import java.sql.Date;

public class DeliveryChangeDTO {
	private int order_no;
	  private String id;
	  private String name;
	  private String cpu;
	  private String vga;
	  private String main;
	  private String ram;
	  private String hdd;
	  private String ssd;
	  private String ram2;
	  private int total;
	  private String address;
	  private String dvs; //배송상태
	  
	  public DeliveryChangeDTO() {
		  super();
		  // TODO Auto-generated constructor stub
	  }

	public DeliveryChangeDTO(int order_no, String id, String name, String cpu, String vga, String main, String ram,
			String hdd, String ssd, String ram2, int total, String address, String dvs) {
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
		this.address = address;
		this.dvs = dvs;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getVga() {
		return vga;
	}

	public void setVga(String vga) {
		this.vga = vga;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public String getSsd() {
		return ssd;
	}

	public void setSsd(String ssd) {
		this.ssd = ssd;
	}

	public String getRam2() {
		return ram2;
	}

	public void setRam2(String ram2) {
		this.ram2 = ram2;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDvs() {
		return dvs;
	}

	public void setDvs(String dvs) {
		this.dvs = dvs;
	}

	@Override
	public String toString() {
		return "DeliveryChangDTO [order_no=" + order_no + ", id=" + id + ", name=" + name + ", cpu=" + cpu + ", vga="
				+ vga + ", main=" + main + ", ram=" + ram + ", hdd=" + hdd + ", ssd=" + ssd + ", ram2=" + ram2
				+ ", total=" + total + ", address=" + address + ", dvs=" + dvs + "]";
	}
	  
}
