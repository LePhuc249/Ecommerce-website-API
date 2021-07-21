package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    @NotBlank(message = "Category id is mandatory")
    private Long id;

    @NotBlank(message = "Category name is mandatory")
    private String name;

    @NotBlank(message = "Category brand is mandatory")
    private Long brandId;

    private Integer parentCategory;

    private LocalDateTime createdate;

    private LocalDateTime updatedate;

    private Long createby;

    private boolean isDeleted;

}