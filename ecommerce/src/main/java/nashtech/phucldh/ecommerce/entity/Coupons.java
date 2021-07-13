package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupons {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "code", unique = true, nullable = false, length = 10)
	private String code;

	@Column(name = "discount_amount")
	private Integer discountamount;

	@Column(name = "product_discount")
	private Integer productdiscount;

	@Column(name = "description", unique = false, nullable = false, length = 30)
	private String description;

	@Column(name = "expiration_date")
	private String expirationdate;

	@Column(name = "create_by")
	private Integer createby;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	public Coupons() {
	}

	public Coupons(Integer id, String code, Integer discountamount, Integer productdiscount, String description,
			String expirationdate, Integer create_by, LocalDateTime createdate, LocalDateTime updatedate,
			boolean isDeleted) {
		this.id = id;
		this.code = code;
		this.discountamount = discountamount;
		this.productdiscount = productdiscount;
		this.description = description;
		this.expirationdate = expirationdate;
		this.createby = create_by;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDiscountamount() {
		return discountamount;
	}

	public void setDiscountamount(Integer discountamount) {
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

	public Integer getCreate_by() {
		return createby;
	}

	public void setCreate_by(Integer create_by) {
		this.createby = create_by;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Coupons [id=" + id + ", code=" + code + ", discountamount=" + discountamount + ", productdiscount="
				+ productdiscount + ", description=" + description + ", expirationdate=" + expirationdate
				+ ", create_by=" + createby + ", createdate=" + createdate + ", updatedate=" + updatedate
				+ ", isDeleted=" + isDeleted + "]";
	}

}
