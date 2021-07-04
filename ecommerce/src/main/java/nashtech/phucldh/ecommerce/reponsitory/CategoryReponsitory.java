package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, String> {

}
