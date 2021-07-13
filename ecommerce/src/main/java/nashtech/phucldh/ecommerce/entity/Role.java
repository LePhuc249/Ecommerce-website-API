package nashtech.phucldh.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "rolename", length = 20)
	private ERole rolename;

	public Role() {
	}

	public Role(Integer id, ERole name) {
		this.id = id;
		this.rolename = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return rolename;
	}

	public void setName(ERole name) {
		this.rolename = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + rolename + "]";
	}

}
