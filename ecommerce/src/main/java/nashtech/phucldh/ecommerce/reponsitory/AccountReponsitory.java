package nashtech.phucldh.ecommerce.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Account;

@Repository
public interface AccountReponsitory extends JpaRepository<Account, Long> {

	Optional<Account> findByUsername(String username);

	Optional<Account> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "delete from account where username=:username", nativeQuery = true)
	Boolean deleteByUsername(@Param("username") String username);
}
