package nashtech.phucldh.ecommerce.reponsitory;

import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrganizationAddressRepository extends JpaRepository<OrganizationAddress, Long> {

    @Query(value = "select oa from organization_address oa where organization_id=:organization_id", nativeQuery = true)
    Optional<OrganizationAddress> getAddressByOrganization(@Param("organization_id") Long id);

}
