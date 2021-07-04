package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class Orderdetail {

	@Id
	@Column(name = "orderlineid")
	private String orderlineid;

	@Column(name = "orderid")
	private String orderid;

	@Column(name = "itemid")
	private String itemid;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private Float price;

	@Column(name = "itemproperty")
	private String itemproperty;

	@Column(name = "createdate")
	private Timestamp createdate;

	public Orderdetail() {
	}

	public Orderdetail(String orderlineid, String orderid, String itemid, int amount, Float price, String itemproperty,
			Timestamp createdate) {
		this.orderlineid = orderlineid;
		this.orderid = orderid;
		this.itemid = itemid;
		this.amount = amount;
		this.price = price;
		this.itemproperty = itemproperty;
		this.createdate = createdate;
	}

	public String getOrderlineid() {
		return orderlineid;
	}

	public void setOrderlineid(String orderlineid) {
		this.orderlineid = orderlineid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
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

	public String getItemproperty() {
		return itemproperty;
	}

	public void setItemproperty(String itemproperty) {
		this.itemproperty = itemproperty;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "Orderdetail [orderlineid=" + orderlineid + ", orderid=" + orderid + ", itemid=" + itemid + ", amount="
				+ amount + ", price=" + price + ", itemproperty=" + itemproperty + ", createdate=" + createdate + "]";
	}

}