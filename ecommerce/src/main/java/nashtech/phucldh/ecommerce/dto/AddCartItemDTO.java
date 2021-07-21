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
public class AddCartItemDTO {

    @NotBlank(message = "Id account is mandatory")
    private Long user_id;

    @NotBlank(message = "Id cart is mandatory")
    private Long cart_id;

    @NotBlank(message = "Id item is mandatory")
    private Long item_id;

}