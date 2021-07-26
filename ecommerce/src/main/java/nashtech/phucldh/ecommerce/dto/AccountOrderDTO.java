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

    private Long id;

    private Long accountId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String dateDelivery;

    private Long paymentMethod;

    private Float totalPrice;

    private Long status;

    private Long couponId;

}