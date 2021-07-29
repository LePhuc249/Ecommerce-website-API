package nashtech.phucldh.ecommerce.dto.Brand;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BrandDTO {

    private Long id;

    @NotBlank(message = "Brand name is mandatory")
    @Size(min = 5, max = 50, message = "Brand name must be between 5 and 50 characters")
    private String name;

    private Long organizationId;

}