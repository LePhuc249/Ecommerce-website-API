package nashtech.phucldh.ecommerce.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponsDTO {

	private Long id;

	private String code;

	private Integer discountamount;

	private Long productdiscount;

	private String description;

	private String expirationdate;

	private boolean isDeleted;
	
}
