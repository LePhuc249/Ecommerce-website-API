package nashtech.phucldh.ecommerce.dto;

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
public class CreateProductDTO {

    @NotBlank(message = "Product name is mandatory")
    private String name;

    @NotBlank(message = "Short description is mandatory")
    private String short_description;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Product price is mandatory")
    private Float price;

    @NotBlank(message = "Product brand is mandatory")
    private Long brand;

    @NotBlank(message = "Product quantity is mandatory")
    private int quantity;

    @NotBlank(message = "Product category is mandatory")
    private Long category;

    @NotBlank(message = "Product counter is mandatory")
    private int counter;

    private boolean isDeleted;

}