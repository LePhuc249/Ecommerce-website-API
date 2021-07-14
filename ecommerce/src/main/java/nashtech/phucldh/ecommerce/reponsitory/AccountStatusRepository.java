package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.AccountStatus;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Integer>{

}
