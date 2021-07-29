package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "coupons",
		indexes = {
				@Index(name = "coupons_index", columnList = "id, code, product_discount")
		}
)
public class Coupons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Code coupon is mandatory")
    @Size(min = 5, max = 10, message = "Code coupon must be between 5 and 10 characters")
    @Column(name = "code")
    private String code;

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(name = "product_discount")
    private Long productDiscount;

    @Size(min = 5, max = 50, message = "Coupon discription must be between 5 and 50 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "expiration_date")
    @Size(min = 5, max = 15, message = "Coupon expiration date must be between 5 and 15 characters")
    private String expirationDate;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "isdeleted")
    private boolean isDeleted;

}