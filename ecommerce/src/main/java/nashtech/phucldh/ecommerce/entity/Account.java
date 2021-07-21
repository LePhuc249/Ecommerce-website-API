package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "account",
        indexes = {
                @Index(name = "mulitIndex1", columnList = "id, username, password, email")
        }
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 10, max = 100, message = "Password must be between 10 and 100 characters")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Full name is mandatory")
    @Size(min = 5, max = 50, message = "Full name must be between 5 and 50 characters")
    @Column(name = "fullname")
    private String fullname;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    @Size(min = 10, max = 15, message = "Phone must be between 10 and 15 number")
    @Column(name = "phone")
    private String phone;

    @Column(name = "create_date")
    private LocalDateTime createdate;

    @Column(name = "update_date")
    private LocalDateTime updatedate;

    @Column(name = "status")
    private Long status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<AccountAddress> listAddress = new ArrayList<>();

    @OneToOne(mappedBy = "account")
    private Cart cart;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<AccountOrder> listOrder = new ArrayList<>();

}