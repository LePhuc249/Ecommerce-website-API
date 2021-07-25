package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.OrganizationAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    @Query(
            value = "select * from organization_address where organization_id = ?1 and address = ?2",
            nativeQuery = true
    )
    OrganizationAddress getOrganizationAddress(Long organizationId, String address);

    @Query(
            value = "select * from organization_address where id = ?1",
            nativeQuery = true
    )
    OrganizationAddress getOrganizationAddressById(Long id);

    @Query(
            value = "select * from organization_address",
            nativeQuery = true
    )
    List<OrganizationAddress> getListAllOrganizationAddress();

}