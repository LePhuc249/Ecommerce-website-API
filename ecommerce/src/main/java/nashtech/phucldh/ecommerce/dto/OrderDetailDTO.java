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
public class OrderDetailDTO {

	private Long id;

	private Long order_id;

	private Long item_id;

	private int amount;

	private Float price;

	private String item_property;
	
}
