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
public class OrderdetailDTO {
	
	private UUID orderlineid;

	private UUID orderid;

	private Integer itemid;

	private int amount;

	private Float price;

	private String itemproperty;
	
	private Timestamp createdate;
}
