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
    private Integer discountamount;

    @NotBlank(message = "Product discount is mandatory")
    @Column(name = "product_discount")
    private Long productdiscount;

    @Size(min = 5, max = 50, message = "Coupon discription must be between 5 and 50 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "expiration_date")
    private String expirationdate;

    @Column(name = "create_by")
    private Long createby;

    @Column(name = "create_date")
    private LocalDateTime createdate;

    @Column(name = "update_date")
    private LocalDateTime updatedate;

    @Column(name = "isdeleted")
    private boolean isDeleted;

}