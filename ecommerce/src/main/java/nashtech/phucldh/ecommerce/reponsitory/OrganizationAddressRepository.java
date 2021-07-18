package nashtech.phucldh.ecommerce.reponsitory;

import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationAddressRepository extends JpaRepository<OrganizationAddress, Long> {

    @Query(
            value = "select * from organization_address where organization_id = ?1",
            nativeQuery = true
    )
    List<OrganizationAddress> getAddressByOrganization(Long id);

    @Query(
            value = "select address from organization_address where organization_id = ?1",
            nativeQuery = true
    )
    List<String> getListAddressOfOrganization(Long organizationId);

}
