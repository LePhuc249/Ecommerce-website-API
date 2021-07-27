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
public class AccountForgotDTO {

    @NotBlank(message = "Username not blank")
    String userName;

    @NotBlank(message = "Full name not blank")
    String fullName;

    @NotBlank(message = "Email not blank")
    String email;

    @NotBlank(message = "Phone not blank")
    String phone;
}
