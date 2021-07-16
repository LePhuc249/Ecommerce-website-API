package nashtech.phucldh.ecommerce.reponsitory;

import java.util.Optional;

import nashtech.phucldh.ecommerce.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByNameAndBrand(String name, Brand brand);

}
