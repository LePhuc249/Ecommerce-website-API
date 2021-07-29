package nashtech.phucldh.ecommerce.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import nashtech.phucldh.ecommerce.entity.Account;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountReponsitory extends JpaRepository<Account, Long> {

    Optional<Account> findByUserName(String username);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUserNameAndPassword(String username, String password);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    @Modifying
    @Transactional
    @Query(
            value = "Update account set status = 2 where id = ?1",
            nativeQuery = true
    )
    int updateAccountStatusToActive(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update account set status = 3 where id = ?1",
            nativeQuery = true
    )
    int updateAccountStatusToLocked(Long id);

    @Query(
            value = "Select * from account where username = ?1 and fullname = ?2 and email = ?3 and phone = ?4",
            nativeQuery = true
    )
    Account getForForgotPassword(String username, String fullname, String email, String phone);

}