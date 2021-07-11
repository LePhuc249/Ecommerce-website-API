package nashtech.phucldh.ecommerce.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponsDTO {
	
	private UUID couponid;

	private String couponcode;

	private int discountamount;

	private Integer productdiscount;

	private String description;

	private String expirationdate;
	
	private UUID createby;
	
	private Timestamp createdate;

	private boolean isdeleted;
}
