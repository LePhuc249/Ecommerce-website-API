package nashtech.phucldh.ecommerce.dto.Coupons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponsDTO {

    private Long id;

    @NotBlank(message = "Coupon code is mandatory")
    @Size(min = 5, max = 10, message = "Code coupon must be between 5 and 10 characters")
    private String code;

    @Min(value = 0, message = "Discount amount aleast 0")
    private Integer discountAmount;

    private Long productDiscount;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 50, message = "Coupon discription must be between 5 and 50 characters")
    private String description;

    @NotBlank(message = "Expiration date is mandatory")
    @Size(min = 5, max = 15, message = "Coupon expiration date must be between 5 and 15 characters")
    private String expirationDate;

    private Long createBy;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private boolean isDeleted;

}