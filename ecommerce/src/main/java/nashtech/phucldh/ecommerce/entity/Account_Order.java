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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account_order")
public class Account_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "customer_id", nullable = false, length = 10)
	private Integer customer_id;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "date_delivery", nullable = false, length = 20)
	private String date_delivery;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_method", nullable = false)
	private Payment_Method payment_method;

	@Column(name = "total_price")
	private Float total_price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", nullable = false)
	private Order_Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_id", nullable = false)
	private Coupons coupon;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account_order")
	private List<Order_Detail> listOrder_Detail = new ArrayList<>();

	public Account_Order() {
	}

	public Account_Order(Integer id, Integer customer_id, LocalDateTime createdate, LocalDateTime updatedate,
			String date_delivery, Payment_Method payment_method, Float total_price, Order_Status status,
			Coupons coupon) {
		this.id = id;
		this.customer_id = customer_id;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.date_delivery = date_delivery;
		this.payment_method = payment_method;
		this.total_price = total_price;
		this.status = status;
		this.coupon = coupon;
	}

	public Account_Order(Integer id, Integer customer_id, LocalDateTime createdate, LocalDateTime updatedate,
			String date_delivery, Payment_Method payment_method, Float total_price, Order_Status status, Coupons coupon,
			List<Order_Detail> listOrder_Detail) {
		this.id = id;
		this.customer_id = customer_id;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.date_delivery = date_delivery;
		this.payment_method = payment_method;
		this.total_price = total_price;
		this.status = status;
		this.coupon = coupon;
		this.listOrder_Detail = listOrder_Detail;
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
		return date_delivery;
	}

	public void setDate_delivery(String date_delivery) {
		this.date_delivery = date_delivery;
	}

	public Payment_Method getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(Payment_Method payment_method) {
		this.payment_method = payment_method;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Float total_price) {
		this.total_price = total_price;
	}

	public Order_Status getStatus() {
		return status;
	}

	public void setStatus(Order_Status status) {
		this.status = status;
	}

	public Coupons getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupons coupon) {
		this.coupon = coupon;
	}

	public List<Order_Detail> getListOrder_Detail() {
		return listOrder_Detail;
	}

	public void setListOrder_Detail(List<Order_Detail> listOrder_Detail) {
		this.listOrder_Detail = listOrder_Detail;
	}

	@Override
	public String toString() {
		return "Account_Order [id=" + id + ", customer_id=" + customer_id + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + ", date_delivery=" + date_delivery + ", payment_method="
				+ payment_method + ", total_price=" + total_price + ", status=" + status + ", coupon=" + coupon
				+ ", listOrder_Detail=" + listOrder_Detail + "]";
	}

}
