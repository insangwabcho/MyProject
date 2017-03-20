package Client;

import java.sql.Date;

public class JoinDTO {
	private String id; //아이디
	private String passward; //비번
	private String email; //이메일
	private String uname; //회원이름
	private Date birth; //생년월일
	private String sex; //성별
	private String tel; //폰번호
	private String address; //주소

	//생성자
	public JoinDTO() {
	}
	public JoinDTO(String id, String passward, String email, String uname, Date birth, String sex,
			String tel, String address) {
		this.id = id;
		this.passward = passward;
		this.email = email;
		this.uname = uname;
		this.birth = birth;
		this.sex = sex;
		this.tel = tel;
		this.address = address;
	}
	
	//getter,setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//toString()
	@Override
	public String toString() {
		return "JoinDTO id=" + id + ", passward=" + passward + ", email=" + email + ", uname="
				+ uname + ", sex=" + sex + ", tel=" + tel + ", address=" + address;
	}
}
