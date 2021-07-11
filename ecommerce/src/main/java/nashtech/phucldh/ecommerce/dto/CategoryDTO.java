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
public class CategoryDTO {
	
	private String categoryid;

	private String categoryname;
	
	private Timestamp createdate;
	
	private UUID createaccount;
	
	private boolean isdeleted;

}
