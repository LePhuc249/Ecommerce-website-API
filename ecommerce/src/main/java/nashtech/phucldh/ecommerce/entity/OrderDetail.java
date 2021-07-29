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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "order_detail",
		indexes = {
				@Index(name = "order_detail_index", columnList = "id, order_id, item_id")
		}
)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private AccountOrder accountOrder;

    @Column(name = "item_id")
    private Long itemId;

    @Min(value = 0, message = "Amount should not be less than 0")
    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private Float price;

    @NotBlank(message = "Item id is mandatory")
    @Size(min = 5, max = 100, message = "Item property must be between 5 and 100 characters")
    @Column(name = "item_property")
    private String itemProperty;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}