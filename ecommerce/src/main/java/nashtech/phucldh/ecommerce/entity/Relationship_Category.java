package nashtech.phucldh.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relationship_category")
public class Relationship_Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "category_id1", nullable = false, length = 10)
	private Integer category_id1;

	@Column(name = "category_id2", nullable = false, length = 10)
	private Integer category_id2;

	public Relationship_Category() {
	}

	public Relationship_Category(Integer id, Integer category_id1, Integer category_id2) {
		this.id = id;
		this.category_id1 = category_id1;
		this.category_id2 = category_id2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id1() {
		return category_id1;
	}

	public void setCategory_id1(Integer category_id1) {
		this.category_id1 = category_id1;
	}

	public Integer getCategory_id2() {
		return category_id2;
	}

	public void setCategory_id2(Integer category_id2) {
		this.category_id2 = category_id2;
	}

	@Override
	public String toString() {
		return "Relationship_Category [id=" + id + ", category_id1=" + category_id1 + ", category_id2=" + category_id2
				+ "]";
	}

}
