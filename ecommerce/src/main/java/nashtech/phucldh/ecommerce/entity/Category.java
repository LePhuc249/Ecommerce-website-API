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
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "categoryid", unique = false, nullable = false, columnDefinition = "TEXT", length = 10)
	private String categoryid;

	@Column(name = "categoryname", unique = false, nullable = false, columnDefinition = "TEXT", length = 20)
	private String categoryname;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "createaccount")
	private String createaccount;

	@Column(name = "isdeleted")
	private boolean isdeleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
	private Account account;
	
	@OneToMany(mappedBy = "categoryRelationship", cascade = CascadeType.ALL)
	private List<Relationship_Category> listRelationship = new ArrayList<>();
	
	@OneToMany(mappedBy = "categoryProduct", cascade = CascadeType.ALL)
	private List<Product> listProduct = new ArrayList<>();

	public Category() {
	}

	public Category(String categoryid, String categoryname, Timestamp createdate, String createaccount,
			boolean isdeleted) {
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.createdate = createdate;
		this.createaccount = createaccount;
		this.isdeleted = isdeleted;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public String getCreateaccount() {
		return createaccount;
	}

	public void setCreateaccount(String createaccount) {
		this.createaccount = createaccount;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "category [categoryid=" + categoryid + ", categoryname=" + categoryname + ", createdate=" + createdate
				+ ", createaccount=" + createaccount + ", isdeleted=" + isdeleted + "]";
	}

}
