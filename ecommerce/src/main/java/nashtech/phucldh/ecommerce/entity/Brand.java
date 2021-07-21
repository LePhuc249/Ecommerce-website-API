package nashtech.phucldh.ecommerce.entity;

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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "brand",
        indexes = {
                @Index(name = "brand_index", columnList = "id, name, organization")
        }
)
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Brand name is mandatory")
    @Size(min = 5, max = 50, message = "Brand name must be between 5 and 50 characters")
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization", referencedColumnName = "id")
    private Organization organization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<Category> listCategory = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<Product> listProduct = new ArrayList<>();

}