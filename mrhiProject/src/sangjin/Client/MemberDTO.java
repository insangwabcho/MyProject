package sangjin.Client;

import java.sql.Date;

public class MemberDTO {
	private String id; //이름
	private String name; //아이디
	private Date birth; //생년월일
	private String sex; //성별
	private String email; //이메일
	private String tel; //폰번호
	private String address; //주소
	private Date log; //마지막 로그인시간
	private String ip; //접속 아이피

	public MemberDTO() {
	}

	public MemberDTO(String name, String id, Date birth, String sex, String email, String tel, String address, Date log,
			String ip) {
		super();
		this.name = name;
		this.id = id;
		this.birth = birth;
		this.sex = sex;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.log = log;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getLog() {
		return log;
	}

	public void setLog(Date log) {
		this.log = log;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "MemberDTO [name=" + name + ", id=" + id + ", birth=" + birth + ", sex=" + sex + ", email=" + email
				+ ", tel=" + tel + ", address=" + address + ", log=" + log + ", ip=" + ip + "]";
	}
}
