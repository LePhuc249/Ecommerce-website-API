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
@Table(name = "order_detail")
public class Order_Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "order_id")
	private Integer order_id;

	@Column(name = "item_id")
	private Integer item_id;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private Float price;

	@Column(name = "item_property", unique = false, nullable = false, length = 100)
	private String item_property;

	@Column(name = "create_date")
	private LocalDateTime createdate;

}
