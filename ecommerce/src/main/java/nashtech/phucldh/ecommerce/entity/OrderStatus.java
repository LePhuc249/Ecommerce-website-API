package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_status")
public class OrderStatus {

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

	public OrderStatus() {
	}

	public OrderStatus(Integer id, String name, Integer createby, LocalDateTime createdate, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.createby = createby;
		this.createdate = createdate;
		this.isDeleted = isDeleted;
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


	@Override
	public String toString() {
		return "Order_Status [id=" + id + ", name=" + name + ", createby=" + createby + ", createdate=" + createdate
				+ ", isDeleted=" + isDeleted + "]";
	}

}
