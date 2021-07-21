package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountOrderDTO {

    @NotBlank(message = "Account order id is mandatory")
    private Long id;

    @NotBlank(message = "Account id is mandatory")
    private Long customer_id;

    private LocalDateTime createdate;

    private LocalDateTime updatedate;

    @NotBlank(message = "Date delivery is mandatory")
    @Size(min = 5, max = 20, message = "Date delivery must be between 5 and 20 characters")
    private String date_delivery;

    @NotBlank(message = "Payment method is mandatory")
    private Long payment_method;

    private Float total_price;

    @NotBlank(message = "Status is mandatory")
    private Long status;

    private Long coupon_id;

}