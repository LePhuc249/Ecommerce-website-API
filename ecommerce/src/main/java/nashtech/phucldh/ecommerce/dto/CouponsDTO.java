package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponsDTO {

    @NotBlank(message = "Coupon id is mandatory")
    private Long id;

    @NotBlank(message = "Coupon code is mandatory")
    private String code;

    @NotBlank(message = "Discount amount is mandatory")
    private Integer discountamount;

    @NotBlank(message = "Product discount is mandatory")
    private Long productdiscount;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Expiration date is mandatory")
    private String expirationdate;

    private Long createby;

    private LocalDateTime createdate;

    private LocalDateTime updatedate;

    private boolean isDeleted;

}