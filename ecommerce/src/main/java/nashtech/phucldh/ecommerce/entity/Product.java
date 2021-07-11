package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "itemid")
	private Integer itemid;

	@Column(name = "itemname", unique = true, nullable = false, length = 50)
	private String itemname;

	@Column(name = "img", unique = true, nullable = false, length = 200)
	private String img;

	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@Column(name = "price")
	private Float price;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "updatedate")
	private Timestamp updatedate;

	@Column(name = "productname", nullable = false, length = 50)
	private String productname;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "categoryid", nullable = false, length = 10)
	private String categoryCode;

	@Column(name = "counter")
	private int counter;

	@Column(name = "status")
	private boolean status;

	public Product() {
	}

	public Product(Integer itemid, String itemname, String img, String description, Float price, String productname,
			int quantity, String categoryCode) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.img = img;
		this.description = description;
		this.price = price;
		this.productname = productname;
		this.quantity = quantity;
		this.categoryCode = categoryCode;
	}

	public Product(Integer itemid, String itemname, String img, String description, Float price, Timestamp createdate,
			String productname, int quantity, String categoryCode, int counter, boolean status) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.img = img;
		this.description = description;
		this.price = price;
		this.createdate = createdate;
		this.productname = productname;
		this.quantity = quantity;
		this.categoryCode = categoryCode;
		this.counter = counter;
		this.status = status;
	}

	public Product(Integer itemid, String itemname, String img, String description, Float price, Timestamp createdate,
			Timestamp updatedate, String productname, int quantity, String categoryCode, int counter, boolean status) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.img = img;
		this.description = description;
		this.price = price;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.productname = productname;
		this.quantity = quantity;
		this.categoryCode = categoryCode;
		this.counter = counter;
		this.status = status;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "Product [itemid=" + itemid + ", itemname=" + itemname + ", img=" + img + ", description=" + description
				+ ", price=" + price + ", createdate=" + createdate + ", updatedate=" + updatedate + ", productname="
				+ productname + ", quantity=" + quantity + ", categoryCode=" + categoryCode + ", counter=" + counter
				+ ", status=" + status + "]";
	}

}
