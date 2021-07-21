package nashtech.phucldh.ecommerce.reponsitory;

import nashtech.phucldh.ecommerce.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByName(String name);

    @Modifying
    @Transactional
    @Query(
            value = "Update organization set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deleteOrganization(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update organization set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeleteOrganization(Long id);

    @Query(
            value = "Select isdeleted from organization where id = ?1",
            nativeQuery = true
    )
    Long getStatusOfOrganization(Long organizationId);

    @Query(
            value = "Select * from organization where name = ?1 and image = ?2",
            nativeQuery = true
    )
    Organization checkExistedOrganization(String name, Long imageId);

}