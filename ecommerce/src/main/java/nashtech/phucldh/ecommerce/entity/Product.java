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
        name = "product",
		indexes = {
				@Index(name = "product_index", columnList = "id, name, brand, category")
		}
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Size(min = 5, max = 50, message = "Product name must be between 5 and 50 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Short description is mandatory")
    @Size(min = 5, max = 50, message = "Short description must be between 5 and 50 characters")
    @Column(name = "short_description")
    private String shortDescription;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 5, max = 100, message = "Description must be between 5 and 100 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "brand")
    private Brand brand;

    @Min(value = 0, message = "Quantity should not be less than 0")
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Min(value = 0, message = "Counter should not be less than 0")
    @Column(name = "counter")
    private int counter;

    @Column(name = "isdeleted")
    private boolean isDeleted;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "product_image",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "image_id")
//    )
//    private List<Image> image = new ArrayList<>();

}