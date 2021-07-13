package nashtech.phucldh.ecommerce.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
public class ProductDTO {

	private Integer id;

	private String name;

	private String imgurl;

	private String short_description;

	private String description;

	private Float price;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	private Integer supplier_id;

	private int quantity;

	private Integer category_id;

	private int counter;

	private boolean isDeleted;

}
