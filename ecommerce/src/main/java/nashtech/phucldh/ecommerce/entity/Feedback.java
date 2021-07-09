package nashtech.phucldh.ecommerce.entity;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@Column(name = "feedbackid")
	private String feedbackid;

	@Column(name = "accountid", unique = false, nullable = false, columnDefinition = "TEXT", length = 30)
	private String accountid;

	@Column(name = "orderid")
	private String orderid;

	@Column(name = "feedbacktime")
	private Timestamp feedbacktime;

	@Column(name = "feedbackcontent", unique = false, nullable = false, columnDefinition = "TEXT", length = 100)
	private String content;

	@Column(name = "counter")
	private int counter;

	@Column(name = "status", unique = false, nullable = false, columnDefinition = "TEXT", length = 10)
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
	private Userorder feedbackUserorder;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
	private Account feedbackAccount;

	public Feedback() {
	}

	public Feedback(String feedbackid, String accountid, String orderid, Timestamp feedbacktime, String content,
			int counter, String status) {
		this.feedbackid = feedbackid;
		this.accountid = accountid;
		this.orderid = orderid;
		this.feedbacktime = feedbacktime;
		this.content = content;
		this.counter = counter;
		this.status = status;
	}

	public String getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
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
