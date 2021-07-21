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
public class OrganizationAddressDTO {

    @NotBlank(message = "Organization address id is mandatory")
    private Long id;

    @NotBlank(message = "Organization id is mandatory")
    private Long organization;

    @NotBlank(message = "Organization address is mandatory")
    private String address;

}