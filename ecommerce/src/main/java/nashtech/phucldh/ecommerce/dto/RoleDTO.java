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
public class RoleDTO {

    @NotBlank(message = "Role id is mandatory")
    private Long id;

    @NotBlank(message = "Role name is mandatory")
    private String name;

}