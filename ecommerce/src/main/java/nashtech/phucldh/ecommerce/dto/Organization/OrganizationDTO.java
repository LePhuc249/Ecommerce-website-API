package nashtech.phucldh.ecommerce.dto.Organization;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrganizationDTO {

    private Long id;

    @NotBlank(message = "Organization name is mandatory")
    @Size(min = 5, max = 100, message = "Organization name must be between 5 and 100 characters")
    private String name;

    private Long imageOrganizationId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long createBy;

    private boolean isDeleted;

}