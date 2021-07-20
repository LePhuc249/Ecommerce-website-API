package nashtech.phucldh.ecommerce.reponsitory;

import java.util.Optional;

import nashtech.phucldh.ecommerce.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Category;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndBrand(String name, Brand brand);

    Optional<Category> findByBrand(Brand brand);

    @Modifying
    @Transactional
    @Query(
            value = "Update category set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deleteCategory(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update category set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeleteCategory(Long id);

    @Query(
            value = "Select isdeleted from category c where c.id = ?1",
            nativeQuery = true
    )
    Boolean checkStatusOfCategery(Long id);
}