package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paymentmethod")
public class Paymentmethod {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "methodname")
	private String methodname;
	
	@Column(name = "createdate")
	private Timestamp createdate;
	
	@Column(name = "isdeleted")
	private boolean isdeleted;

	public Paymentmethod() {}

	public Paymentmethod(String id, String methodname, Timestamp createdate, boolean isdeleted) {
		this.id = id;
		this.methodname = methodname;
		this.createdate = createdate;
		this.isdeleted = isdeleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Override
	public String toString() {
		return "Paymentmethod [id=" + id + ", methodname=" + methodname + ", createdate=" + createdate + ", isdeleted="
				+ isdeleted + "]";
	}

}
