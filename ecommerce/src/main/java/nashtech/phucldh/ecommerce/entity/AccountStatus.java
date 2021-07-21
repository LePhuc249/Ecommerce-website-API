package nashtech.phucldh.ecommerce.entity;

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
        name = "account_status",
        indexes = {
                @Index(name = "Index", columnList = "id")
        }
)
public class AccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Status name is mandatory")
    @Size(min = 5, max = 20, message = "Status name must be between 5 and 20 characters")
    @Column(name = "status")
    private String status;

}