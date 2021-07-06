package nashtech.phucldh.ecommerce.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Account;

@Repository
public interface AccountReponsitory extends JpaRepository<Account, String> {

//	@Query("select u from User u where u.username = :username or u.password = :password")
//	Account checkLogin(@Param("username") String username, @Param("password") String password);

	Optional<Account> findByUsername(String username);
	
	Optional<Account> findByEmail(String email);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
