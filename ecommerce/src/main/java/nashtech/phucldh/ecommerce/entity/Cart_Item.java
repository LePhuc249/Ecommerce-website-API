package nashtech.phucldh.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class Cart_Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "cart_id", nullable = false)
	private Integer cart_id;
	
	@Column(name = "item_id", unique = true, nullable = false)
	private Integer item_id;
	
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@Column(name = "price", nullable = false)
	private Float price;

	public Cart_Item() {
	}

	public Cart_Item(Integer id, Integer cart_id, Integer item_id, Integer amount, Float price) {
		this.id = id;
		this.cart_id = cart_id;
		this.item_id = item_id;
		this.amount = amount;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart_Item [id=" + id + ", cart_id=" + cart_id + ", item_id=" + item_id + ", amount=" + amount
				+ ", price=" + price + "]";
	}

}
