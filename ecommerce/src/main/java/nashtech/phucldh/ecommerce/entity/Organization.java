package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
        name = "organization",
        indexes = {
                @Index(name = "organization_index", columnList = "id, name, image")
        }
)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Organization name is mandatory")
    @Size(min = 5, max = 100, message = "Organization name must be between 5 and 100 characters")
    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private Image imageOrganization;

    @Column(name = "create_date")
    private LocalDateTime createdate;

    @Column(name = "update_date")
    private LocalDateTime updatedate;

    @Column(name = "create_by")
    private Long createby;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<OrganizationAddress> listAddress = new ArrayList<>();

}