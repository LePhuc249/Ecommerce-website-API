package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;

public class category {
	
	private String categoryid;
	
	private String categoryname;
	
	private Timestamp createdate;
	
	private String createaccount;
	
	private boolean isdeleted;

	public category() {}

	public category(String categoryid, String categoryname, Timestamp createdate, String createaccount,
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
