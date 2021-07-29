package nashtech.phucldh.ecommerce.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import nashtech.phucldh.ecommerce.entity.Category;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

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
            value = "Select isdeleted from category where id = ?1",
            nativeQuery = true
    )
    Boolean checkStatusOfCategery(Long id);

    @Query(
            value = "Select * from category where name = ?1",
            nativeQuery = true
    )
    Category checkExistCategory(String name);

    @Query(
            value = "Select * from category where id = ?1",
            nativeQuery = true
    )
    Category checkExistCategoryById(Long id);

}