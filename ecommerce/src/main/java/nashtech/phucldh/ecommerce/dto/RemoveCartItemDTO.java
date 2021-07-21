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
public class RemoveCartItemDTO {

    @NotBlank(message = "User id is mandatory")
    private Long user_id;

    @NotBlank(message = "Item id is mandatory")
    private Long item_id;

}