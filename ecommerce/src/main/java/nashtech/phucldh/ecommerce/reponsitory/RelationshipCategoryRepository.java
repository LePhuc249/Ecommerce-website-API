package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Relationship_Category;

@Repository
public interface RelationshipCategoryRepository extends JpaRepository<Relationship_Category, String>{

}
