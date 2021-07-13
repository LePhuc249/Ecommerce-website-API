package nashtech.phucldh.ecommerce.dto;

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
public class Cart_ItemDTO {
	
	private Integer id;
	
	private Integer cart_id;
	
	private Integer item_id;
	
	private Integer amount;
	
	private Float price;
}
