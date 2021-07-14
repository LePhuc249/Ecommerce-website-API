package nashtech.phucldh.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "cart_id", nullable = false)
	private Integer cartid;
	
	@Column(name = "product_id", unique = true, nullable = false)
	private Integer itemid;
	
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@Column(name = "price", nullable = false)
	private Float price;

	public CartItem() {
	}

	public CartItem(Integer id, Integer cart_id, Integer item_id, Integer amount, Float price) {
		this.id = id;
		this.cartid = cart_id;
		this.itemid = item_id;
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
		return cartid;
	}

	public void setCart_id(Integer cart_id) {
		this.cartid = cart_id;
	}

	public Integer getItem_id() {
		return itemid;
	}

	public void setItem_id(Integer item_id) {
		this.itemid = item_id;
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
		return "Cart_Item [id=" + id + ", cart_id=" + cartid + ", item_id=" + itemid + ", amount=" + amount
				+ ", price=" + price + "]";
	}

}
