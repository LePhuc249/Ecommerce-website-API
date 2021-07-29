package nashtech.phucldh.ecommerce.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import nashtech.phucldh.ecommerce.entity.ERole;
import nashtech.phucldh.ecommerce.entity.Role;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    @Modifying
    @Transactional
    @Query(
            value = "insert into account_role(account_id, role_id) values( ?1 , ?2 )",
            nativeQuery = true
    )
    int addNewAccountRole(Long accountID, Long roleID);

    @Modifying
    @Transactional
    @Query(
            value = "delete from account_role where account_id = ?1 and role_id = ?2",
            nativeQuery = true
    )
    int deleteAccountRole(Long accountID, Long roleID);

}