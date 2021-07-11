package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupons {

	@Id
	@Column(name = "couponid")
	private UUID couponid;

	@Column(name = "couponcode", unique = true, nullable = false, columnDefinition = "TEXT", length = 30)
	private String couponcode;

	@Column(name = "discount_amount")
	private int discountamount;

	@Column(name = "product_discount")
	private Integer productdiscount;

	@Column(name = "description", unique = false, nullable = false, length = 30)
	private String description;

	@Column(name = "expiration_date")
	private String expirationdate;

	@Column(name = "createby")
	private UUID createby;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "isdeleted")
	private boolean isdeleted;

	public Coupons() {
	}

	public Coupons(UUID couponid, String couponcode, int discountamount, Integer productdiscount, String description,
			String expirationdate, UUID createby, Timestamp createdate, boolean isdeleted) {
		this.couponid = couponid;
		this.couponcode = couponcode;
		this.discountamount = discountamount;
		this.productdiscount = productdiscount;
		this.description = description;
		this.expirationdate = expirationdate;
		this.createby = createby;
		this.createdate = createdate;
		this.isdeleted = isdeleted;
	}

	public UUID getCouponid() {
		return couponid;
	}

	public void setCouponid(UUID couponid) {
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

	public Integer getProductdiscount() {
		return productdiscount;
	}

	public void setProductdiscount(Integer productdiscount) {
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

	public UUID getCreateby() {
		return createby;
	}

	public void setCreateby(UUID createby) {
		this.createby = createby;
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
				+ expirationdate + ", createby=" + createby + ", createdate=" + createdate + ", isdeleted=" + isdeleted
				+ "]";
	}

}
