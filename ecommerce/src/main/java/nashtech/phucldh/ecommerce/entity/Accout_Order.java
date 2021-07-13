package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "account_order")
public class Accout_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "customer_id", nullable = false, length = 10)
	private Integer customer_id;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "date_delivery", nullable = false, length = 20)
	private String date_delivery;

	@Column(name = "payment_method", nullable = false, length = 10)
	private Integer payment_method;

	@Column(name = "total_price")
	private Float total_price;

	@Column(name = "status", nullable = false, length = 10)
	private Integer status;

	@Column(name = "coupon_id")
	private Integer coupon_id;

}
