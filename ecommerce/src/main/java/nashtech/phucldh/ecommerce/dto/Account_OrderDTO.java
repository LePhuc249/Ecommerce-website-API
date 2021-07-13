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
public class Account_OrderDTO {

	private Integer id;

	private Integer customer_id;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	private String date_delivery;

	private Integer payment_method;

	private Float total_price;

	private Integer status;

	private Integer coupon_id;

}
