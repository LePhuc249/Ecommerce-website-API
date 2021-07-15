package nashtech.phucldh.ecommerce.reponsitory;

import nashtech.phucldh.ecommerce.entity.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long> {
}
