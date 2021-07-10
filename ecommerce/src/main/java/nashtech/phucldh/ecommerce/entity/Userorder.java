package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userorder")
public class Userorder {

	@Id
	@Column(name = "orderid")
	private UUID orderid;

	@Column(name = "customerid", nullable = false, length = 30)
	private UUID customerid;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "datedelivery", nullable = false, length = 20)
	private String datedelivery;

	@Column(name = "paymentmethod", nullable = false, length = 10)
	private String paymentmethod;

	@Column(name = "totalprice")
	private Float totalprice;

	@Column(name = "status", nullable = false, length = 20)
	private String status;

	@Column(name = "couponid")
	private UUID couponid;

	public Userorder() {
	}

	public Userorder(UUID orderid, UUID customerid, Timestamp createdate, String datedelivery, String paymentmethod,
			Float totalprice, String status, UUID couponid) {
		this.orderid = orderid;
		this.customerid = customerid;
		this.createdate = createdate;
		this.datedelivery = datedelivery;
		this.paymentmethod = paymentmethod;
		this.totalprice = totalprice;
		this.status = status;
		this.couponid = couponid;
	}

	public UUID getOrderid() {
		return orderid;
	}

	public void setOrderid(UUID orderid) {
		this.orderid = orderid;
	}

	public UUID getCustomerid() {
		return customerid;
	}

	public void setCustomerid(UUID customerid) {
		this.customerid = customerid;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getDatedelivery() {
		return datedelivery;
	}

	public void setDatedelivery(String datedelivery) {
		this.datedelivery = datedelivery;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public Float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getCouponid() {
		return couponid;
	}

	public void setCouponid(UUID couponid) {
		this.couponid = couponid;
	}

	@Override
	public String toString() {
		return "Userorder [orderid=" + orderid + ", customerid=" + customerid + ", createdate=" + createdate
				+ ", datedelivery=" + datedelivery + ", paymentmethod=" + paymentmethod + ", totalprice=" + totalprice
				+ ", status=" + status + ", couponid=" + couponid + "]";
	}

}
