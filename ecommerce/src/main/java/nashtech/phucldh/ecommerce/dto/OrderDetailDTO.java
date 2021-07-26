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
public class OrderDetailDTO {

    private Long id;

    private Long accountOrderId;

    private Long itemId;

    @Min(value = 0, message = "Amount should not be less than 0")
    private int amount;

    private Float price;

    @NotBlank(message = "Order item property is mandatory")
    private String itemProperty;

    private LocalDateTime createDate;

}