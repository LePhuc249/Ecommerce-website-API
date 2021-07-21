package nashtech.phucldh.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

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
public class CartDTO {

    @NotBlank(message = "Cart id is mandatory")
    private Long id;

    @NotBlank(message = "Customer id is mandatory")
    private Long customer_id;

    private List<CartItemDTO> cartItems = new ArrayList<>();

}