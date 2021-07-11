package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@Column(name = "feedbackid")
	private UUID feedbackid;

	@Column(name = "accountid", unique = false, nullable = false, columnDefinition = "TEXT", length = 30)
	private UUID accountid;

	@Column(name = "orderid")
	private UUID orderid;

	@Column(name = "feedbacktime")
	private Timestamp feedbacktime;

	@Column(name = "feedbackcontent", unique = false, nullable = false, columnDefinition = "TEXT", length = 100)
	private String content;

	@Column(name = "counter")
	private int counter;

	@Column(name = "status", unique = false, nullable = false, columnDefinition = "TEXT", length = 10)
	private String status;

	public Feedback() {
	}

	public Feedback(UUID feedbackid, UUID accountid, UUID orderid, Timestamp feedbacktime, String content, int counter,
			String status) {
		this.feedbackid = feedbackid;
		this.accountid = accountid;
		this.orderid = orderid;
		this.feedbacktime = feedbacktime;
		this.content = content;
		this.counter = counter;
		this.status = status;
	}

	public UUID getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(UUID feedbackid) {
		this.feedbackid = feedbackid;
	}

	public UUID getAccountid() {
		return accountid;
	}

	public void setAccountid(UUID accountid) {
		this.accountid = accountid;
	}

	public UUID getOrderid() {
		return orderid;
	}

	public void setOrderid(UUID orderid) {
		this.orderid = orderid;
	}

	public Timestamp getFeedbacktime() {
		return feedbacktime;
	}

	public void setFeedbacktime(Timestamp feedbacktime) {
		this.feedbacktime = feedbacktime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackid=" + feedbackid + ", accountid=" + accountid + ", orderid=" + orderid
				+ ", feedbacktime=" + feedbacktime + ", content=" + content + ", counter=" + counter + ", status="
				+ status + "]";
	}

}
