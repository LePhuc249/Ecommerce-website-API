package nashtech.phucldh.ecommerce.dto.Product;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UpdateProductDTO {

    private Long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Product short description is mandatory")
    private String shortDescription;

    @NotBlank(message = "Product description is mandatory")
    private String description;

    private Float price;

    private Long brand;

    private int quantity;

    private Long category;

    private int counter;

    private boolean isDeleted;

}