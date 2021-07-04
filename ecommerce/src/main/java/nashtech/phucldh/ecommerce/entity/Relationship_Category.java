package nashtech.phucldh.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relationship_category")
public class Relationship_Category {

	@Id
	@Column(name = "relationshipid")
	private String relationshipid;

	@Column(name = "categoryid1")
	private String categoryid1;

	@Column(name = "categoryid2")
	private String categoryid2;

	public Relationship_Category() {
	}

	public Relationship_Category(String relationshipid, String categoryid1, String categoryid2) {
		this.relationshipid = relationshipid;
		this.categoryid1 = categoryid1;
		this.categoryid2 = categoryid2;
	}

	public String getRelationshipid() {
		return relationshipid;
	}

	public void setRelationshipid(String relationshipid) {
		this.relationshipid = relationshipid;
	}

	public String getCategoryid1() {
		return categoryid1;
	}

	public void setCategoryid1(String categoryid1) {
		this.categoryid1 = categoryid1;
	}

	public String getCategoryid2() {
		return categoryid2;
	}

	public void setCategoryid2(String categoryid2) {
		this.categoryid2 = categoryid2;
	}

	@Override
	public String toString() {
		return "Relationship_Category [relationshipid=" + relationshipid + ", categoryid1=" + categoryid1
				+ ", categoryid2=" + categoryid2 + "]";
	}

}
