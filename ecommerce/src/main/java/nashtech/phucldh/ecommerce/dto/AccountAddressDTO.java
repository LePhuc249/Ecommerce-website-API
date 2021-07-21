package nashtech.phucldh.ecommerce.dto;

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
public class AccountAddressDTO {

    @NotBlank(message = "Id account is mandatory")
    private Long id;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 10, max = 100, message = "Address must be between 10 and 100 characters")
    private String address;

}