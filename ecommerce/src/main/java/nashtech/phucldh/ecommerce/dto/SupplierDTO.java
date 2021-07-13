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
public class SupplierDTO {

	private Integer id;

	private String name;

	private String description;

	private String phone;

	private String address;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	private boolean isDeleted;
	
}
