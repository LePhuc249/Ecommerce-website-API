package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "createdate")
	private Timestamp createdate;
	
	@Column(name = "statusaccount")
	private String statusaccount;
	
	@Column(name = "roleid")
	private String roleid;

	public Account() {}

	public Account(String username, String password, String fullname, String email, String phone, String address,
			Timestamp createdate, String statusaccount, String roleid) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.createdate = createdate;
		this.statusaccount = statusaccount;
		this.roleid = roleid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getStatusaccount() {
		return statusaccount;
	}

	public void setStatusaccount(String statusaccount) {
		this.statusaccount = statusaccount;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", fullname=" + fullname + ", email="
				+ email + ", phone=" + phone + ", address=" + address + ", createdate=" + createdate
				+ ", statusaccount=" + statusaccount + ", roleid=" + roleid + "]";
	}

}
