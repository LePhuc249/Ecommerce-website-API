package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@Column(name = "categoryid")
	private String categoryid;

	@Column(name = "categoryname")
	private String categoryname;

	@Column(name = "createdate")
	private Timestamp createdate;

	@Column(name = "createaccount")
	private String createaccount;

	@Column(name = "isdeleted")
	private boolean isdeleted;

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
