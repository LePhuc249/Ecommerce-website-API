package nashtech.phucldh.ecommerce.payload.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SignUpRequest {

    @NotBlank(message = "Username is required.")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]{7,30}$", message = "Invalid username!")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 5, message = "Password invalid")
    private String password;

    private String retypePassword;

    @NotBlank(message = "Full name is required.")
    @Size(min = 1, max = 50, message = "Full name accepts only upto 50 characters and minimum 1 character")
    private String fullname;

    @NotBlank(message = "Email is required.")
    @Pattern(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$", message = "Invalid email!")
    private String email;

    @Pattern(regexp = "[0-9]{7,12}", message = "Invalid phone number!")
    private String phoneNumber;

}