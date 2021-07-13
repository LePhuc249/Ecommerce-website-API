package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "customer_id", unique = true, nullable = false)
	private Integer customerid;
	
	@Column(name = "total_price", nullable = false)
	private Float totalprice;
	
	@Column(name = "create_date")
	private LocalDateTime createdate;

	public Cart() {
	}

	public Cart(Integer id, Integer customer_id, Float total_price, LocalDateTime createdate) {
		this.id = id;
		this.customerid = customer_id;
		this.totalprice = total_price;
		this.createdate = createdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomer_id() {
		return customerid;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customerid = customer_id;
	}

	public Float getTotal_price() {
		return totalprice;
	}

	public void setTotal_price(Float total_price) {
		this.totalprice = total_price;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer_id=" + customerid + ", total_price=" + totalprice + ", createdate="
				+ createdate + "]";
	}

}
