package nashtech.phucldh.ecommerce.dto.Account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountDTO {

    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    private String userName;

    @NotBlank(message = "Full name is mandatory")
    @Size(min = 5, max = 50, message = "Full name must be between 5 and 50 characters")
    private String fullName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long status;

    private Set<String> roles = new HashSet<>();

}