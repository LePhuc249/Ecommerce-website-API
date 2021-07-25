package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.AccountAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long> {

    @Query(
            value = "select address from account_address where account_id = ?1",
            nativeQuery = true
    )
    List<String> findAddressByAccountId(Long id);

}