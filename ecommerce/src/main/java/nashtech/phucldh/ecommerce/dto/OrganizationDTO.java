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

    @NotBlank(message = "Organization id is mandatory")
    private Long id;

    @NotBlank(message = "Organization name is mandatory")
    private String name;

    private Long imageId;

    private LocalDateTime createdate;

    private LocalDateTime updatedate;

    private Long createby;

    private boolean isDeleted;

}