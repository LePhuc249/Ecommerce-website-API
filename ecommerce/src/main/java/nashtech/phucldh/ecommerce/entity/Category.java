package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	private UUID createaccount;

	@Column(name = "isdeleted")
	private boolean isdeleted;

	public Category() {
	}

	public Category(String categoryid, String categoryname, Timestamp createdate, UUID createaccount,
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

	public UUID getCreateaccount() {
		return createaccount;
	}

	public void setCreateaccount(UUID createaccount) {
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
