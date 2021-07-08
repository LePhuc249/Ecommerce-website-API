package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Query("update product SET status=false where itemid=:itemid")
	Boolean deactiveProduct(@Param("itemid") String itemid);

	@Query("update product SET status=true where itemid=:itemid")
	Boolean activeProduct(@Param("itemid") String itemid);

	@Query("select u from product u where u.itemname=:itemname and u.img=:img and u.description=:description and u.productname=:productname ")
	Product checkExistProduct(@Param("itemname") String itemName, @Param("img") String img,
			@Param("description") String productName, @Param("productname") String description);
	
	@Query("select u from product u where u.status=true and u.quantity > 0")
	List<Product> getListForCustomer();
	
	@Query("select u from product u where u.itemname = :itemname or u.categoryid = :categoryid")
	List<Product> searchByNameOrCategory(@Param("itemname") String itemname, @Param("categoryid") String categoryid);
	
	@Query("select u from product u where u.itemname = :itemname or u.categoryid = :categoryid and u.status=true and u.quantity > 0")
	List<Product> searchByNameOrCategoryForCustomer(@Param("itemname") String itemname, @Param("categoryid") String categoryid);

}
