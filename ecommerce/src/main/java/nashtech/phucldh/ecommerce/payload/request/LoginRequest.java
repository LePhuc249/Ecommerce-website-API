package nashtech.phucldh.ecommerce.payload.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9\\.]{4,30}", message = "Invalid username!")
    private String username;

    @NotBlank(message = "Password can't blank")
    private String password;

}