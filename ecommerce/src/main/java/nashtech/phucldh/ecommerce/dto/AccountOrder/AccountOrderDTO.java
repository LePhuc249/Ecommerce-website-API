package nashtech.phucldh.ecommerce.dto.AccountOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountOrderDTO {

    private Long id;

    private Long accountId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Size(min = 5, max = 20, message = "Date delivery must be between 5 and 20 characters")
    private String dateDelivery;

    private Long paymentMethod;

    private Float totalPrice;

    private Long status;

    private Long couponId;

}