package nashtech.phucldh.ecommerce.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

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
public class Payment_MethodDTO {
	
	private Integer id;
	
	private String name;
	
	private Integer createby;
	
	private LocalDateTime createdate;

	private boolean isdeleted;
}
