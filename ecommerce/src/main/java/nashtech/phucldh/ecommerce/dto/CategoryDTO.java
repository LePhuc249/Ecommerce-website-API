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

    private Long id;

    @NotBlank(message = "Category name is mandatory")
    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long createBy;

    private boolean isDeleted;

}