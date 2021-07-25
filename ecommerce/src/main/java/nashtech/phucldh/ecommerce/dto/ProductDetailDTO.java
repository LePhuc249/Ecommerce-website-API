package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDetailDTO {

    private Long id;

    @NotBlank(message = "Product detail name is mandatory")
    private String name;

    @NotBlank(message = "Product detail short description is mandatory")
    private String shortDescription;

    @NotBlank(message = "Product detail description is mandatory")
    private String description;

    private Float price;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long brandId;

    @Min(value = 0, message = "Quantity should not be less than 0")
    private int quantity;

    private Long categoryId;

    @Min(value = 0, message = "Counter should not be less than 0")
    private int counter;

    private boolean isDeleted;

}