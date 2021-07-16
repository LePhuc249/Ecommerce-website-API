package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "select * from product u where u.name = ?1 and u.description = ?2 and u.brand = ?3", nativeQuery = true)
	Product checkExistProduct(String itemName, String description, Long brand);

	@Query(value = "select * from product u where u.isdeleted = false and u.quantity > 0", nativeQuery = true)
	List<Product> getListForCustomer();

	@Query(value = "select * from product u where u.name LIKE ?1 or u.category = ?2", nativeQuery = true)
	List<Product> searchByNameOrCategory(String name, Long categoryid);

	@Query(value = "select * from product u where u.name = ?1 or u.category = ?2 and u.isdeleted=false and u.quantity > 0", nativeQuery = true)
	List<Product> searchByNameOrCategoryForCustomer(String name, Long categoryid);

}
