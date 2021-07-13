package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "account_id", unique = false, nullable = false, length = 30)
	private Integer accountid;

	@Column(name = "order_id")
	private Integer orderid;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "feedback_content", unique = false, nullable = false, length = 200)
	private String content;

	@Column(name = "counter")
	private Integer counter;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	public Feedback() {
	}

	public Feedback(Integer id, Integer account_id, Integer order_id, LocalDateTime createdate,
			LocalDateTime updatedate, String content, Integer counter, boolean isDeleted) {
		this.id = id;
		this.accountid = account_id;
		this.orderid = order_id;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.content = content;
		this.counter = counter;
		this.isDeleted = isDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccount_id() {
		return accountid;
	}

	public void setAccount_id(Integer account_id) {
		this.accountid = account_id;
	}

	public Integer getOrder_id() {
		return orderid;
	}

	public void setOrder_id(Integer order_id) {
		this.orderid = order_id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", account_id=" + accountid + ", order_id=" + orderid + ", createdate="
				+ createdate + ", updatedate=" + updatedate + ", content=" + content + ", counter=" + counter
				+ ", isDeleted=" + isDeleted + "]";
	}

}
