package nashtech.phucldh.ecommerce.reponsitory;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    Optional<Brand> findByOrganization(Organization organ);

    @Query(
            value = "Select * from brand where name = ?1 and organization = ?2",
            nativeQuery = true
    )
    Brand checkExistedBrand(String name, Long organizationId);

}