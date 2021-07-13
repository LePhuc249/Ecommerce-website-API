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

	private Integer id;

	private String code;

	private Integer discountamount;

	private Integer productdiscount;

	private String description;

	private String expirationdate;

	private Integer create_by;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	private boolean isDeleted;
	
}
