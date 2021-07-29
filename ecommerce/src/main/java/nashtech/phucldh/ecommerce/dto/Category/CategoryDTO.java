package nashtech.phucldh.ecommerce.dto.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category name is mandatory")
    @Size(min = 5, max = 20, message = "Category name must be between 5 and 20 characters")
    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long createBy;

    private boolean isDeleted;

}