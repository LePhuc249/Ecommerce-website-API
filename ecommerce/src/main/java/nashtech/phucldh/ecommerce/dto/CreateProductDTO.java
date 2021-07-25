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
    private String shortDescription;

    @NotBlank(message = "Description is mandatory")
    private String description;

    private Float price;

    private Long brand;

    private int quantity;

    private Long category;

    private int counter;

    private boolean isDeleted;

}