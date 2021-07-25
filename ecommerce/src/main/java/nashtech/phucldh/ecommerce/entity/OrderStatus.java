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
        name = "order_status",
		indexes = {
				@Index(name = "order_status_index", columnList = "id, name")
		}
)
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Status name is mandatory")
    @Size(min = 5, max = 50, message = "Status name must be between 5 and 50 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "isdeleted")
    private boolean isDeleted;

}