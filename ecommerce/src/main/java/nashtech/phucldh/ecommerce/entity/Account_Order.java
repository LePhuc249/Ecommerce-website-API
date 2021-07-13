package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_order")
public class Account_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "customer_id", nullable = false, length = 10)
	private Integer customerid;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "date_delivery", nullable = false, length = 20)
	private String datedelivery;

	@Column(name = "payment_method")
	private Integer paymentmethod;

	@Column(name = "total_price")
	private Float totalprice;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "coupon_id")
	private Integer couponid;

	public Account_Order() {
	}

	public Account_Order(Integer id, Integer customerid, LocalDateTime createdate, LocalDateTime updatedate,
			String datedelivery, Integer paymentmethod, Float totalprice, Integer status, Integer couponid) {
		this.id = id;
		this.customerid = customerid;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.datedelivery = datedelivery;
		this.paymentmethod = paymentmethod;
		this.totalprice = totalprice;
		this.status = status;
		this.couponid = couponid;
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

	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public LocalDateTime getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}

	public String getDate_delivery() {
		return datedelivery;
	}

	public void setDate_delivery(String date_delivery) {
		this.datedelivery = date_delivery;
	}

	public Integer getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(Integer paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public Float getTotal_price() {
		return totalprice;
	}

	public void setTotal_price(Float total_price) {
		this.totalprice = total_price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCouponid() {
		return couponid;
	}

	public void setCouponid(Integer couponid) {
		this.couponid = couponid;
	}

	@Override
	public String toString() {
		return "Account_Order [id=" + id + ", customer_id=" + customerid + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + ", date_delivery=" + datedelivery + ", payment_method="
				+ paymentmethod + ", total_price=" + totalprice + ", status=" + status + ", coupon=" + couponid
				+ "]";
	}

}
