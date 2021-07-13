package nashtech.phucldh.ecommerce.dto;

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
public class Order_DetailDTO {
	
	private Integer id;

	private Integer order_id;

	private Integer item_id;

	private int amount;

	private Float price;

	private String item_property;
	
	private LocalDateTime createdate;
}
