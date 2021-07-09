package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userorder")
public class Userorder {

	@Id
	@Column(name = "orderid")
	private String orderid;

	@Column(name = "customerid", nullable = false, columnDefinition = "TEXT", length = 30)
	private String customerid;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "datedelivery", nullable = false, columnDefinition = "TEXT", length = 20)
	private String datedelivery;

	@Column(name = "paymentmethod", nullable = false, columnDefinition = "TEXT", length = 10)
	private String paymentmethod;

	@Column(name = "totalprice")
	private Float totalprice;

	@Column(name = "status", nullable = false, columnDefinition = "TEXT", length = 20)
	private String status;

	@Column(name = "couponid")
	private String couponid;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponid")
	private Coupons userorderCoupons;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderstatusid")
	private Orderstatus userorderOrderstatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
	private Paymentmethod userorderPaymentmethod;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
	private Account userorderAccount;
	
	@OneToMany(mappedBy = "productCoupons", cascade = CascadeType.ALL)
	private List<Orderdetail> listCoupons = new ArrayList<>();
	
	@OneToMany(mappedBy = "feedbackUserorder", cascade = CascadeType.ALL)
	private List<Feedback> listFeedback = new ArrayList<>();

	public Userorder() {
	}

	public Userorder(String orderid, String customerid, Timestamp createdate, String datedelivery, String paymentmethod,
			Float totalprice, String status, String couponid) {
		this.orderid = orderid;
		this.customerid = customerid;
		this.createdate = createdate;
		this.datedelivery = datedelivery;
		this.paymentmethod = paymentmethod;
		this.totalprice = totalprice;
		this.status = status;
		this.couponid = couponid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
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

	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}

	@Override
	public String toString() {
		return "Userorder [orderid=" + orderid + ", customerid=" + customerid + ", createdate=" + createdate
				+ ", datedelivery=" + datedelivery + ", paymentmethod=" + paymentmethod + ", totalprice=" + totalprice
				+ ", status=" + status + ", couponid=" + couponid + "]";
	}

}
