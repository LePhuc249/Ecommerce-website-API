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
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "customer_id", unique = true, nullable = false)
	private Integer customer_id;
	
	@Column(name = "total_price", nullable = false)
	private Float total_price;
	
	@Column(name = "create_date")
	private LocalDateTime createdate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
	private List<Cart> listCart = new ArrayList<>();

	public Cart() {
	}

	public Cart(Integer id, Integer customer_id, Float total_price, LocalDateTime createdate) {
		this.id = id;
		this.customer_id = customer_id;
		this.total_price = total_price;
		this.createdate = createdate;
	}

	public Cart(Integer id, Integer customer_id, Float total_price, LocalDateTime createdate, List<Cart> listCart) {
		this.id = id;
		this.customer_id = customer_id;
		this.total_price = total_price;
		this.createdate = createdate;
		this.listCart = listCart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Float total_price) {
		this.total_price = total_price;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public List<Cart> getListCart() {
		return listCart;
	}

	public void setListCart(List<Cart> listCart) {
		this.listCart = listCart;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer_id=" + customer_id + ", total_price=" + total_price + ", createdate="
				+ createdate + ", listCart=" + listCart + "]";
	}

}
