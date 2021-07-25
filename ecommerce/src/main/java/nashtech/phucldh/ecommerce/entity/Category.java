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
        name = "category",
		indexes = {
				@Index(name = "category_index", columnList = "id, name")
		}
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Category name is mandatory")
    @Size(min = 5, max = 20, message = "Category name must be between 5 and 20 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "category")
    private List<Product> listProduct = new ArrayList<>();

}