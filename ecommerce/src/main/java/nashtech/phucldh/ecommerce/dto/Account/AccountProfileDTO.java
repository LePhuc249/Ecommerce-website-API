package nashtech.phucldh.ecommerce.dto.Account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import nashtech.phucldh.ecommerce.dto.Role.RoleDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountProfileDTO {

    private Long id;

    @NotBlank(message = "User name is mandatory")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    private String userName;

    @Size(min = 5, max = 100, message = "Password must be between 10 and 100 characters")
    private String password;

    @NotBlank(message = "Full name is mandatory")
    @Size(min = 5, max = 50, message = "Full name must be between 5 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    @Size(min = 10, max = 15, message = "Phone must be between 10 and 15 number")
    private String phone;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long status;

    private Set<RoleDTO> roles = new HashSet<>();

}