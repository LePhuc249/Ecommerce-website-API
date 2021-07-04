package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderstatus")
public class Orderstatus {
	
	@Id
	@Column(name = "orderstatusid")
	private String orderstatusid;
	
	@Column(name = "orderstatusname")
	private String orderstatusname;
	
	@Column(name = "createdate")
	private Timestamp createdate;
	
	@Column(name = "isdeleted")
	private boolean isdeleted;

	public Orderstatus() {}

	public Orderstatus(String orderstatusid, String orderstatusname, Timestamp createdate, boolean isdeleted) {
		this.orderstatusid = orderstatusid;
		this.orderstatusname = orderstatusname;
		this.createdate = createdate;
		this.isdeleted = isdeleted;
	}

	public String getOrderstatusid() {
		return orderstatusid;
	}

	public void setOrderstatusid(String orderstatusid) {
		this.orderstatusid = orderstatusid;
	}

	public String getOrderstatusname() {
		return orderstatusname;
	}

	public void setOrderstatusname(String orderstatusname) {
		this.orderstatusname = orderstatusname;
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
		return "Orderstatus [orderstatusid=" + orderstatusid + ", orderstatusname=" + orderstatusname + ", createdate="
				+ createdate + ", isdeleted=" + isdeleted + "]";
	}

}
