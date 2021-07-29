package nashtech.phucldh.ecommerce.dto.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountStatusDTO {

    private Long id;

    @NotBlank(message = "Status account name is mandatory")
    @Size(min = 5, max = 20, message = "Status name must be between 5 and 20 characters")
    private String status;

}