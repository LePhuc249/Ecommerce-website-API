package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
        name = "account_order",
		indexes = {
				@Index(name = "account_order_index", columnList = "id")
		}
)
public class AccountOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account account;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @NotBlank(message = "Date delivery is mandatory")
    @Size(min = 5, max = 20, message = "Date delivery must be between 5 and 20 characters")
    @Column(name = "date_delivery")
    private String dateDelivery;

    @NotBlank(message = "Payment method is mandatory")
    @Column(name = "payment_method")
    private Long paymentMethod;

    @Column(name = "total_price")
    private Float totalPrice;

    @NotBlank(message = "Status is mandatory")
    @Column(name = "status")
    private Long status;

    @Column(name = "coupon_id")
    private Long couponId;

    @OneToMany(mappedBy = "accountOrder")
    private List<OrderDetail> listOrderDetail = new ArrayList<>();

}