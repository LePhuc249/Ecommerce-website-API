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
public class UserorderDTO {
	
	private UUID orderid;

	private UUID customerid;
	
	private Timestamp createdate;

	private String datedelivery;

	private String paymentmethod;

	private Float totalprice;

	private String status;

	private UUID couponid;
}
