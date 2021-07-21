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
public class OrderDetailDTO {

    @NotBlank(message = "Order Detail id is mandatory")
    private Long id;

    @NotBlank(message = "Order id is mandatory")
    private Long order_id;

    @NotBlank(message = "Order Detail item id is mandatory")
    private Long item_id;

    @Min(value = 0, message = "Amount should not be less than 0")
    private int amount;

    @NotBlank(message = "Order item price is mandatory")
    private Float price;

    @NotBlank(message = "Order item property is mandatory")
    private String item_property;

}