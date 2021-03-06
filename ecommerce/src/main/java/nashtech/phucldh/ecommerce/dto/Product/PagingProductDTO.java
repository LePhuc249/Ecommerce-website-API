package nashtech.phucldh.ecommerce.dto.Product;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PagingProductDTO {

    private Long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Product short description is mandatory")
    private String shortDescription;

    private Float price;

    private Long brand;

    @Min(value = 0, message = "Quantity should not be less than 0")
    private int quantity;

    private Long category;

    @Min(value = 0, message = "Counter should not be less than 0")
    private int counter;

}