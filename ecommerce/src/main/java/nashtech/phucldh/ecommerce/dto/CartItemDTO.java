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
public class CartItemDTO {

    @NotBlank(message = "Cart item id is mandatory")
    private Long id;

    @NotBlank(message = "Item id is mandatory")
    private Long item_id;

    @Min(value = 0, message = "Amount should not be less than 0")
    private Integer amount;

    @NotBlank(message = "Price id is mandatory")
    private Float price;

}