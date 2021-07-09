package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderdetailDTO {
	
	private String orderlineid;

	private String orderid;

	private String itemid;

	private int amount;

	private Float price;

	private String itemproperty;
}
