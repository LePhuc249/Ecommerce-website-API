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
public class OrderstatusDTO {
	
	private String orderstatusid;

	private String orderstatusname;
	
	private UUID createby;
	
	private Timestamp createdate;

	private boolean isdeleted;
}
