package nashtech.phucldh.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "roleid", unique = true, nullable = false, columnDefinition = "TEXT", length = 5)
	private String roleid;

	@Column(name = "rolename", unique = false, nullable = false, columnDefinition = "TEXT", length = 10)
	private String rolename;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<Account> listAccount = new ArrayList<>();

	public Role() {
	}

	public Role(String roleid, String rolename) {
		this.roleid = roleid;
		this.rolename = rolename;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", rolename=" + rolename + "]";
	}

}
