package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountProfileDTO {

    @NotBlank(message = "Id account is mandatory")
    private Long id;

    @NotBlank(message = "User name is mandatory")
    private String username;

    @NotBlank(message = "Full name is mandatory")
    private String fullname;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    private LocalDateTime createdate;

    private LocalDateTime updatedate;

    private Long status;

    private Set<RoleDTO> roles = new HashSet<>();
}