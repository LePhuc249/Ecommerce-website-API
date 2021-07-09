package nashtech.phucldh.ecommerce.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Relationship_CategoryDTO {
	
	private String relationshipid;

	private String categoryid1;

	private String categoryid2;
}
