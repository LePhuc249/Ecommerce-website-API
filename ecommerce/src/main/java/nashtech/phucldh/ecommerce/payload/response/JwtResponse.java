package nashtech.phucldh.ecommerce.payload.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private String fullname;
    private String email;
    private List<String> roleid;

    public JwtResponse(String token, String username, String fullname, String email, List<String> roleid) {
        this.token = token;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.roleid = roleid;
    }

}