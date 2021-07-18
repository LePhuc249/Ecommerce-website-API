package nashtech.phucldh.ecommerce.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Account;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface AccountReponsitory extends JpaRepository<Account, Long> {

	Optional<Account> findByUsername(String username);

	Optional<Account> findByEmail(String email);

	Optional<Account> findByUsernameAndPassword(String username, String password);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Modifying
	@Transactional
	@Query(
			value = "Update account set status = 2 where id = ?1",
			nativeQuery=true
	)
	int updateAccountStatusToActive(Long id);

	@Modifying
	@Transactional
	@Query(
			value = "Update account set status = 3 where id = ?1",
			nativeQuery=true
	)
	int updateAccountStatusToLocked(Long id);


}
