package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "order_id")
	private Integer orderid;

	@Column(name = "item_id")
	private Integer itemid;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private Float price;

	@Column(name = "item_property", unique = false, nullable = false, length = 100)
	private String itemproperty;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	public OrderDetail() {
	}

	public OrderDetail(Integer id, Integer order_id, Integer item_id, int amount, Float price, String item_property,
					   LocalDateTime createdate) {
		this.id = id;
		this.orderid = order_id;
		this.itemid = item_id;
		this.amount = amount;
		this.price = price;
		this.itemproperty = item_property;
		this.createdate = createdate;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrder_id() {
		return orderid;
	}

	public void setOrder_id(Integer order_id) {
		this.orderid = order_id;
	}

	public Integer getItem_id() {
		return itemid;
	}

	public void setItem_id(Integer item_id) {
		this.itemid = item_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getItem_property() {
		return itemproperty;
	}

	public void setItem_property(String item_property) {
		this.itemproperty = item_property;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Order_Detail [id=" + id + ", order_id=" + orderid + ", item_id=" + itemid + ", amount="
				+ amount + ", price=" + price + ", item_property=" + itemproperty + ", createdate=" + createdate + "]";
	}

}
