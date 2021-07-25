package nashtech.phucldh.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrganizationDTO {

    private Long id;

    @NotBlank(message = "Organization name is mandatory")
    private String name;

    private Long imageOrganizationId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long createBy;

    private boolean isDeleted;

}