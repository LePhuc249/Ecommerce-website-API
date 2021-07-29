package nashtech.phucldh.ecommerce.dto.OrganizationAddress;

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
public class OrganizationAddressDTO {

    private Long id;

    private Long organizationId;

    @NotBlank(message = "Organization address is mandatory")
    @Size(min = 5, max = 100, message = "Organization address must be between 5 and 100 characters")
    private String address;

}