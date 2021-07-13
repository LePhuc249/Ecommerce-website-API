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
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false, length = 50)
	private String name;

	@Column(name = "imgurl", unique = true, nullable = false, length = 200)
	private String imgurl;
	
	@Column(name = "short_description", nullable = false, length = 100)
	private String shortdescription;

	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@Column(name = "price")
	private Float price;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable = false)
	private Supplier supplier;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Column(name = "counter")
	private int counter;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	public Product() {
	}

	public Product(Integer id, String name, String imgurl, String short_description, String description, Float price,
			LocalDateTime createdate, LocalDateTime updatedate, Supplier supplier, int quantity, Category category,
			int counter, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.imgurl = imgurl;
		this.shortdescription = short_description;
		this.description = description;
		this.price = price;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.supplier = supplier;
		this.quantity = quantity;
		this.category = category;
		this.counter = counter;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getShort_description() {
		return shortdescription;
	}

	public void setShort_description(String short_description) {
		this.shortdescription = short_description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", imgurl=" + imgurl + ", short_description="
				+ shortdescription + ", description=" + description + ", price=" + price + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + ", supplier=" + supplier + ", quantity=" + quantity + ", category="
				+ category + ", counter=" + counter + ", isDeleted=" + isDeleted + "]";
	}

}
