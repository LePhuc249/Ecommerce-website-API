package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "coupons")
public class Coupons {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", unique = true, nullable = false, length = 10)
	private String code;

	@Column(name = "discount_amount")
	private Integer discountamount;

	@Column(name = "product_discount")
	private Long productdiscount;

	@Column(name = "description", unique = false, nullable = false, length = 30)
	private String description;

	@Column(name = "expiration_date")
	private String expirationdate;

	@Column(name = "create_by")
	private Long createby;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "isdeleted")
	private boolean isDeleted;

}
