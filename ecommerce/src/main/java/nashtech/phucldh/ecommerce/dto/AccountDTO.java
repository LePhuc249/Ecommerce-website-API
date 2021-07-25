package nashtech.phucldh.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
public class AccountDTO {

    private Long id;

    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Long status;

    private Set<String> roles = new HashSet<>();

}