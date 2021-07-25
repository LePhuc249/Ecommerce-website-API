package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponsDTO {

    private Long id;

    @NotBlank(message = "Coupon code is mandatory")
    private String code;

    @Min(value = 0, message = "Discount amount aleast 0")
    private Integer discountAmount;

    private Long productDiscount;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Expiration date is mandatory")
    private String expirationDate;

    private Long createBy;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private boolean isDeleted;

}