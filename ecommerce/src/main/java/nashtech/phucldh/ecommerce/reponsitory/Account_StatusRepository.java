package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Account_Status;

@Repository
public interface Account_StatusRepository extends JpaRepository<Account_Status, Integer>{

}
