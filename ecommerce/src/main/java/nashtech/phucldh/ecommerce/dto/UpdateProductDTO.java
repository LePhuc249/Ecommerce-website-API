package nashtech.phucldh.ecommerce.dto;

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
public class UpdateProductDTO {

    @NotBlank(message = "Product id is mandatory")
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Product short description is mandatory")
    private String short_description;

    @NotBlank(message = "Product description is mandatory")
    private String description;

    @NotBlank(message = "Product price is mandatory")
    private Float price;

    @NotBlank(message = "Product brand is mandatory")
    private Long brand;

    @Min(value = 0, message = "Quantity should not be less than 0")
    private int quantity;

    @NotBlank(message = "Product category is mandatory")
    private Long category;

    @Min(value = 0, message = "Counter should not be less than 0")
    private int counter;

    private boolean isDeleted;

}