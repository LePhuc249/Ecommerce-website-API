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
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "itemid")
	private String itemid;

	@Column(name = "itemname", unique = true, nullable = false, columnDefinition = "TEXT", length = 50)
	private String itemname;

	@Column(name = "img", unique = true, nullable = false, columnDefinition = "TEXT", length = 200)
	private String img;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT", length = 100)
	private String description;

	@Column(name = "price")
	private Float price;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "productname", nullable = false, columnDefinition = "TEXT", length = 50)
	private String productname;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "categoryid", nullable = false, columnDefinition = "TEXT", length = 10)
	private String categoryid;

	@Column(name = "counter")
	private int counter;

	@Column(name = "status")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")
	private Category categoryProduct;
	
	@OneToMany(mappedBy = "productCoupons", cascade = CascadeType.ALL)
	private List<Coupons> listCoupons = new ArrayList<>();
	
	@OneToMany(mappedBy = "productOrderdetail", cascade = CascadeType.ALL)
	private List<Orderdetail> Orderdetail = new ArrayList<>();

	public Product() {
	}

	public Product(String itemid, String itemname, String img, String description, Float price, Timestamp createdate,
			String productname, int quantity, String categoryid, int counter, boolean status) {
		this.itemid = itemid;
		this.itemname = itemname;
		this.img = img;
		this.description = description;
		this.price = price;
		this.createdate = createdate;
		this.productname = productname;
		this.quantity = quantity;
		this.categoryid = categoryid;
		this.counter = counter;
		this.status = status;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
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

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
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

	@Override
	public String toString() {
		return "Product [itemid=" + itemid + ", itemname=" + itemname + ", img=" + img + ", description=" + description
				+ ", price=" + price + ", createdate=" + createdate + ", productname=" + productname + ", quantity="
				+ quantity + ", categoryid=" + categoryid + ", counter=" + counter + ", status=" + status + "]";
	}

}
