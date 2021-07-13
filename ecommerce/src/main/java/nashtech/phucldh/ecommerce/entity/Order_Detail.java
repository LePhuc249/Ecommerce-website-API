package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class Order_Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Account_Order account_Order;

	@Column(name = "item_id")
	private Integer item_id;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private Float price;

	@Column(name = "item_property", unique = false, nullable = false, length = 100)
	private String item_property;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	public Order_Detail() {
	}

	public Order_Detail(Integer id, Account_Order account_Order, Integer item_id, int amount, Float price,
			String item_property, LocalDateTime createdate) {
		this.id = id;
		this.account_Order = account_Order;
		this.item_id = item_id;
		this.amount = amount;
		this.price = price;
		this.item_property = item_property;
		this.createdate = createdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account_Order getAccount_Order() {
		return account_Order;
	}

	public void setAccount_Order(Account_Order account_Order) {
		this.account_Order = account_Order;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
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
		return item_property;
	}

	public void setItem_property(String item_property) {
		this.item_property = item_property;
	}

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Order_Detail [id=" + id + ", account_Order=" + account_Order + ", item_id=" + item_id + ", amount="
				+ amount + ", price=" + price + ", item_property=" + item_property + ", createdate=" + createdate + "]";
	}

}
