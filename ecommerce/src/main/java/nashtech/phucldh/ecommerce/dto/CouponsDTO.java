package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponsDTO {
	
	private String couponid;

	private String couponcode;

	private int discountamount;

	private String productdiscount;

	private String description;

	private String expirationdate;

	private boolean isdeleted;
}
