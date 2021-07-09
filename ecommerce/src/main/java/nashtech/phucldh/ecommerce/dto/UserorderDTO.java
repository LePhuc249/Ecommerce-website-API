package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserorderDTO {
	
	private String orderid;

	private String customerid;

	private String datedelivery;

	private String paymentmethod;

	private Float totalprice;

	private String status;

	private String couponid;
}
