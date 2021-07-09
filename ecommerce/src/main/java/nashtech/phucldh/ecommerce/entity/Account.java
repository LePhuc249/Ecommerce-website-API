package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private UUID id;

	@Column(name = "username", unique = true, nullable = false, columnDefinition = "TEXT", length = 30)
	private String username;

	@Column(name = "password", nullable = false, columnDefinition = "TEXT", length = 100)
	private String password;

	@Column(name = "fullname", nullable = false, columnDefinition = "TEXT", length = 50)
	private String fullname;

	@Column(name = "email", unique = true, nullable = false, columnDefinition = "TEXT", length = 50)
	private String email;

	@Column(name = "phone", unique = true, nullable = false, length = 20)
	private String phone;

	@Column(name = "address", nullable = false, length = 200)
	private String address;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "statusaccount")
	private String statusaccount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	private Role role;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Category> listCategory = new ArrayList<>();

	@OneToMany(mappedBy = "userorderAccount", cascade = CascadeType.ALL)
	private List<Userorder> listUserorder = new ArrayList<>();

	@OneToMany(mappedBy = "feedbackAccount", cascade = CascadeType.ALL)
	private List<Feedback> listFeedback = new ArrayList<>();

	public Account() {
	}

	public Account(UUID id, String username, String password, String fullname, String email, String phone,
			String address, Timestamp createdate, String statusaccount) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.createdate = createdate;
		this.statusaccount = statusaccount;
	}

	public Account(UUID id, String username, String password, String fullname, String email, String phone,
			String address, Timestamp createdate, String statusaccount, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.createdate = createdate;
		this.statusaccount = statusaccount;
		this.role = role;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", createdate=" + createdate
				+ ", statusaccount=" + statusaccount + ", role=" + role + ", listCategory=" + listCategory + "]";
	}

}
