package nashtech.phucldh.ecommerce.dto.Image;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ImageDTO {

    private Long id;

    @NotBlank(message = "Image url is mandatory")
    @Size(min = 5, max = 200, message = "URL must be between 5 and 200 characters")
    private String url;

    @NotBlank(message = "Description image is mandatory")
    @Size(min = 5, max = 200, message = "Description image must be between 5 and 200 characters")
    private String description;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long createBy;

    private boolean isDeleted;

}