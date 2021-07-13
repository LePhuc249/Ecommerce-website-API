package nashtech.phucldh.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account_status")
public class Account_Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "status", unique = true, nullable = false, length = 20)
	private String status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account_status")
	private List<Account> listAccount = new ArrayList<>();

	public Account_Status() {
	}

	public Account_Status(Integer id, String status) {
		this.id = id;
		this.status = status;
	}

	public Account_Status(Integer id, String status, List<Account> listAccount) {
		this.id = id;
		this.status = status;
		this.listAccount = listAccount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	@Override
	public String toString() {
		return "Account_Status [id=" + id + ", status=" + status + ", listAccount=" + listAccount + "]";
	}

}
