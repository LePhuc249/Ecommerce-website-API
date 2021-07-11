package nashtech.phucldh.ecommerce.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
	
	private Integer itemid;
	
	private String itemname;

	private String img;

	private String description;

	private Float price;
	
	private Timestamp createdate;
	
	private Timestamp updatedate;

	private String productname;

	private int quantity;

	private String categoryid;

	private int counter;

	private boolean status;
}
