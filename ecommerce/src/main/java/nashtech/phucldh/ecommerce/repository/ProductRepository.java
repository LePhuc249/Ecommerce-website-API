package nashtech.phucldh.ecommerce.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import nashtech.phucldh.ecommerce.entity.Product;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(
            value = "select * from product where name = ?1 and description = ?2 and brand = ?3 ",
            nativeQuery = true
    )
    Product checkExistProduct(String itemName, String description, Long brand);

    @Query(
            value = "select * from product where isdeleted = false and quantity > 0",
            nativeQuery = true
    )
    List<Product> getListForCustomer();

    List<Product> findByIsDeletedAndQuantityGreaterThan(boolean isDeleted, int quantity);

    Page<Product> findByIsDeletedAndQuantityGreaterThan(boolean isDeleted, int quantity, Pageable pageable);

    @Query(
            value = "select * from product where name LIKE ?1 or category = ?2",
            nativeQuery = true
    )
    List<Product> searchByNameOrCategory(String name, Long categoryid);

    @Query(
            value = "select * from product where (name LIKE ?1 or category = ?2) and isdeleted = false and quantity > 0",
            nativeQuery = true
    )
    List<Product> searchByNameOrCategoryForCustomer(String name, Long categoryid);

    @Query(
            value = "select * from product where id = ?1 and isdeleted = false and quantity > 0",
            nativeQuery = true
    )
    Product checkProductToAddToCart(Long id);

    @Query(
            value = "Select * from product where id=?1",
            nativeQuery = true
    )
    Product getProductByID(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update product set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deleteProduct(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update product set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeleteProduct(Long id);

    @Query(
            value = "Select quantity from product where id = ?1",
            nativeQuery = true
    )
    int getQuantityOfProduct(Long id);

    @Query(
            value = "Select counter from product where id = ?1",
            nativeQuery = true
    )
    int getCounterOfProduct(Long id);

    @Query(
            value = "Select name from product where id = ?1",
            nativeQuery = true
    )
    String getNameById(Long id);

    @Query(
            value = "Select quantity from product where id = ?1 and isdeleted = false",
            nativeQuery = true
    )
    int getQuantityOfProductForCustomer(Long id);

    @Query(
            value = "Select brand from product where id = ?1",
            nativeQuery = true
    )
    int getBrandOfProduct(Long id);

    @Query(
            value = "Select category from product where id = ?1",
            nativeQuery = true
    )
    int getCategoryOfProduct(Long id);

}