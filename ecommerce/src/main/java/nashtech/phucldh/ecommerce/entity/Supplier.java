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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@Column(name = "phone", nullable = false, length = 15)
	private String phone;
	
	@Column(name = "address", nullable = false, length = 150)
	private String address;
	
	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;
	
	@Column(name = "isDeleted")
	private boolean isDeleted;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
	private List<Product> listProduct = new ArrayList<>();

	public Supplier() {
	}

	public Supplier(Integer id, String name, String description, String phone, String address, LocalDateTime createdate,
			LocalDateTime updatedate, boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.phone = phone;
		this.address = address;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.isDeleted = isDeleted;
	}

	public Supplier(Integer id, String name, String description, String phone, String address, LocalDateTime createdate,
			LocalDateTime updatedate, boolean isDeleted, List<Product> listProduct) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.phone = phone;
		this.address = address;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.isDeleted = isDeleted;
		this.listProduct = listProduct;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", description=" + description + ", phone=" + phone
				+ ", address=" + address + ", createdate=" + createdate + ", updatedate=" + updatedate + ", isDeleted="
				+ isDeleted + ", listProduct=" + listProduct + "]";
	}

}
