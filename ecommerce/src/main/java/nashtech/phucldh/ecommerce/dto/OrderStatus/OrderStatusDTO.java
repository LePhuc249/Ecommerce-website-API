package nashtech.phucldh.ecommerce.dto.OrderStatus;

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
public class OrderStatusDTO {

    private Long id;

    @NotBlank(message = "Order status name is mandatory")
    @Size(min = 5, max = 50, message = "Status name must be between 5 and 50 characters")
    private String name;

    private Long createBy;

    private LocalDateTime createDate;

    private boolean isDeleted;

}