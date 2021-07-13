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
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true, nullable = false, length = 50)
	private String name;

	@Column(name = "imgurl", unique = true, nullable = false, length = 200)
	private String imgurl;
	
	@Column(name = "short_description", nullable = false, length = 100)
	private String short_description;

	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@Column(name = "price")
	private Float price;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "supplier_id", nullable = false, length = 50)
	private Integer supplier_id;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "category_id", nullable = false, length = 10)
	private Integer category_id;

	@Column(name = "counter")
	private int counter;

	@Column(name = "isDeleted")
	private boolean isDeleted;

}
