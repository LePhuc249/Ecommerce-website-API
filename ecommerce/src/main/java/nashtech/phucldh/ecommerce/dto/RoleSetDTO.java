package nashtech.phucldh.ecommerce.dto;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class RoleSetDTO {

    @NotNull(message = "Account id not null")
    private Long accountId;

    private Set<RoleDTO> roles = new HashSet<>();
}