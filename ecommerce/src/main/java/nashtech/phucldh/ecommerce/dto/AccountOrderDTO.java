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
public class AccountOrderDTO {

	private Long id;

	private Long customer_id;

	private String date_delivery;

	private Long payment_method;

	private Float total_price;

	private Long status;

	private Long coupon_id;

}
