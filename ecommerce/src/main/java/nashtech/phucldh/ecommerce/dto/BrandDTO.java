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
public class BrandDTO {

    @NotBlank(message = "Brand id is mandatory")
    private Long id;

    @NotBlank(message = "Brand name is mandatory")
    private String name;

    @NotBlank(message = "Organization of brand is mandatory")
    private Long organization;

}