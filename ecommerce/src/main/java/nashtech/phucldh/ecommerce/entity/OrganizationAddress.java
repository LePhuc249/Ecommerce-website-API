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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        name = "organization_address",
        indexes = {
                @Index(name = "organization_address_index", columnList = "id, organization_id")
        }
)
public class OrganizationAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @NotBlank(message = "Organization address is mandatory")
    @Size(min = 5, max = 100, message = "Organization address must be between 5 and 100 characters")
    @Column(name = "address")
    private String address;
}