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
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false, length = 20)
	private String name;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "create_account")
	private Integer create_account;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Product> listProduct = new ArrayList<>();

	public Category() {
	}

	public Category(Integer id, String name, LocalDateTime createdate, LocalDateTime updatedate, Integer create_account,
			boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.create_account = create_account;
		this.isDeleted = isDeleted;
	}

	public Category(Integer id, String name, LocalDateTime createdate, LocalDateTime updatedate, Integer create_account,
			boolean isDeleted, List<Product> listProduct) {
		this.id = id;
		this.name = name;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.create_account = create_account;
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

	public Integer getCreate_account() {
		return create_account;
	}

	public void setCreate_account(Integer create_account) {
		this.create_account = create_account;
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
		return "Category [id=" + id + ", name=" + name + ", createdate=" + createdate + ", updatedate=" + updatedate
				+ ", create_account=" + create_account + ", isDeleted=" + isDeleted + ", listProduct=" + listProduct
				+ "]";
	}

}
