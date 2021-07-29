package nashtech.phucldh.ecommerce.dto.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleSetDTO {

    private Long accountId;

    private Set<RoleDTO> roles = new HashSet<>();
}