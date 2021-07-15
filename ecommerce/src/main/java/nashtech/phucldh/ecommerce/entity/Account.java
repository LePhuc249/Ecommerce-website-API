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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 30)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "fullname", nullable = false, length = 50)
	private String fullname;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;

	@Column(name = "phone", unique = true, nullable = false, length = 20)
	private String phone;

	@Column(name = "create_date")
	private LocalDateTime createdate;

	@Column(name = "update_date")
	private LocalDateTime updatedate;

	@Column(name = "status")
	private Integer status;

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
