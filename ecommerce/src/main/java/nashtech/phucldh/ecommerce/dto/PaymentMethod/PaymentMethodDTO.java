package nashtech.phucldh.ecommerce.dto.PaymentMethod;

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
public class PaymentMethodDTO {

    private Long id;

    @NotBlank(message = "Payment method name is mandatory")
    @Size(min = 5, max = 50, message = "Payment method name must be between 5 and 50 characters")
    private String name;

    private Long createBy;

    private LocalDateTime createDate;

    private boolean isDeleted;

}