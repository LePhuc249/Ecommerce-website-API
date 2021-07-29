package nashtech.phucldh.ecommerce.dto.Account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountForgotDTO {

    @NotBlank(message = "Username not blank")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    String userName;

    @NotBlank(message = "Full name not blank")
    @Size(min = 5, max = 50, message = "Full name must be between 5 and 50 characters")
    String fullName;

    @NotBlank(message = "Email not blank")
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    String email;

    @NotBlank(message = "Phone not blank")
    @Size(min = 10, max = 15, message = "Phone must be between 10 and 15 number")
    String phone;

}
