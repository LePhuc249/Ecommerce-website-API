package nashtech.phucldh.ecommerce.dto.Cart;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import nashtech.phucldh.ecommerce.dto.CartItem.CartItemDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartDTO {

    private Long id;

    private Long customerId;

    private List<CartItemDTO> cartItems = new ArrayList<>();

}