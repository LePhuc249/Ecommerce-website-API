package nashtech.phucldh.ecommerce.reponsitory;

import nashtech.phucldh.ecommerce.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Cart;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    @Query(value = "select * from cart c where customer_id = ?1", nativeQuery = true)
    Optional<Cart> getCartById(Long customer_id);

    Optional<Cart> findByAccount(Account customer);

}
