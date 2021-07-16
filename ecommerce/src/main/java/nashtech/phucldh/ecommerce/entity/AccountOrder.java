package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "account_order")
public class AccountOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Account account;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "date_delivery", nullable = false, length = 20)
	private String datedelivery;

	@Column(name = "payment_method")
	private Long paymentmethod;

	@Column(name = "total_price")
	private Float totalprice;
	
	@Column(name = "status")
	private Long status;
	
	@Column(name = "coupon_id")
	private Long couponid;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountOrder")
	private List<OrderDetail> listOrderDetail = new ArrayList<>();

}
