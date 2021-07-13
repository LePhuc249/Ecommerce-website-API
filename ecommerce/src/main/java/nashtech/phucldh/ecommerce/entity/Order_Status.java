package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;
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
@Table(name = "order_status")
public class Order_Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false, length = 50)
	private String name;

	@Column(name = "create_by")
	private Integer createby;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "isDeleted")
	private boolean isDeleted;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order_status")
	private List<Account_Order> listAccount_Order = new ArrayList<>();

	public Order_Status() {
	}

	public Order_Status(Integer id, String name, Integer createby, LocalDateTime createdate, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.createby = createby;
		this.createdate = createdate;
		this.isDeleted = isDeleted;
	}

	public Order_Status(Integer id, String name, Integer createby, LocalDateTime createdate, boolean isDeleted,
			List<Account_Order> listAccount_Order) {
		this.id = id;
		this.name = name;
		this.createby = createby;
		this.createdate = createdate;
		this.isDeleted = isDeleted;
		this.listAccount_Order = listAccount_Order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCreateby() {
		return createby;
	}

	public void setCreateby(Integer createby) {
		this.createby = createby;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Account_Order> getListAccount_Order() {
		return listAccount_Order;
	}

	public void setListAccount_Order(List<Account_Order> listAccount_Order) {
		this.listAccount_Order = listAccount_Order;
	}

	@Override
	public String toString() {
		return "Order_Status [id=" + id + ", name=" + name + ", createby=" + createby + ", createdate=" + createdate
				+ ", isDeleted=" + isDeleted + ", listAccount_Order=" + listAccount_Order + "]";
	}

}
