package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long> {

    @Query(
            value = "select * from account_address where account_id = ?1",
            nativeQuery = true
    )
    List<AccountAddress> findAddressByAccountId(Long id);

    @Query(
            value = "select * from account_address where account_id = ?1 and address = ?2",
            nativeQuery = true
    )
    AccountAddress checkExistedAccountAddress(Long accountId, String address);

}