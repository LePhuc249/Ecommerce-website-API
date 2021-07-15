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
public class ProductDTO {

	private Long id;

	private String name;

	private String short_description;

	private String description;

	private Float price;

	private Long brand;

	private int quantity;

	private Long category;

	private int counter;

	private boolean isDeleted;

}
