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
@Table(name = "coupons")
public class Coupons {

	@Id
	@Column(name = "couponid")
	private String couponid;

	@Column(name = "couponcode", unique = true, nullable = false, columnDefinition = "TEXT", length = 30)
	private String couponcode;

	@Column(name = "discountamount")
	private int discountamount;

	@Column(name = "productdiscount")
	private String productdiscount;

	@Column(name = "description", unique = false, nullable = false, columnDefinition = "TEXT", length = 30)
	private String description;

	@Column(name = "expirationdate", unique = false, nullable = false, columnDefinition = "TEXT", length = 30)
	private String expirationdate;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "isdeleted")
	private boolean isdeleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemid")
	private Product productCoupons;
	
	@OneToMany(mappedBy = "userorderCoupons", cascade = CascadeType.ALL)
	private List<Userorder> listUserorder = new ArrayList<>();

	public Coupons() {
	}

	public Coupons(String couponid, String couponcode, int discountamount, String productdiscount, String description,
			String expirationdate, Timestamp createdate, boolean isdeleted) {
		this.couponid = couponid;
		this.couponcode = couponcode;
		this.discountamount = discountamount;
		this.productdiscount = productdiscount;
		this.description = description;
		this.expirationdate = expirationdate;
		this.createdate = createdate;
		this.isdeleted = isdeleted;
	}

	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}

	public String getCouponcode() {
		return couponcode;
	}

	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}

	public int getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(int discountamount) {
		this.discountamount = discountamount;
	}

	public String getProductdiscount() {
		return productdiscount;
	}

	public void setProductdiscount(String productdiscount) {
		this.productdiscount = productdiscount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "Coupons [couponid=" + couponid + ", couponcode=" + couponcode + ", discountamount=" + discountamount
				+ ", productdiscount=" + productdiscount + ", description=" + description + ", expirationdate="
				+ expirationdate + ", createdate=" + createdate + ", isdeleted=" + isdeleted + "]";
	}

}
