package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {

    @Query(
            value = "Select * from account_status where status = ?1",
            nativeQuery = true
    )
    AccountStatus getStatusByName(String name);

}