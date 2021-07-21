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
public class ProductDetailDTO {

    @NotBlank(message = "Product detail id is mandatory")
    private Long id;

    @NotBlank(message = "Product detail name is mandatory")
    private String name;

    @NotBlank(message = "Product detail short description is mandatory")
    private String short_description;

    @NotBlank(message = "Product detail description is mandatory")
    private String description;

    @NotBlank(message = "Product detail price is mandatory")
    private Float price;

    @NotBlank(message = "Product detail brand is mandatory")
    private Long brand;

    @Min(value = 0, message = "Quantity should not be less than 0")
    private int quantity;

    @NotBlank(message = "Product detail category is mandatory")
    private Long category;

    @Min(value = 0, message = "Counter should not be less than 0")
    private int counter;

}