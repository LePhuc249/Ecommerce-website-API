package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ImageDTO {

    @NotBlank(message = "Image id is mandatory")
    private Long id;

    @NotBlank(message = "Image url is mandatory")
    private String urlImage;

    private boolean isdeleted;

}