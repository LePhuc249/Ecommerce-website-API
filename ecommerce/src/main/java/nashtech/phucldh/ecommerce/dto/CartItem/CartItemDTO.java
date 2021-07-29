package nashtech.phucldh.ecommerce.dto.CartItem;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartItemDTO {

    private Long id;

    private Long itemId;

    @Min(value = 0, message = "Amount should not be less than 0")
    private Integer amount;

    private Float price;

}