package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUrl(String url);

    @Modifying
    @Transactional
    @Query(
            value = "Update image set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deleteImage(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update image set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeleteImage(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update image set url = ?2 where id = ?1",
            nativeQuery = true
    )
    int updateImageURL(Long id, String url);

    @Query(
            value = "Select * from image where url = ?1 and description = ?2",
            nativeQuery = true
    )
    Image checkExistedImage(String url, String description);

}