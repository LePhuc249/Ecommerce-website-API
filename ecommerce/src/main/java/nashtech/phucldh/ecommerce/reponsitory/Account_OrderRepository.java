package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Account_Order;

@Repository
public interface Account_OrderRepository extends JpaRepository<Account_Order, Integer> {

	List<Account_Order> findByCustomerid(String customerid);

	List<String> findByCouponid(String id);

	@Query(value = "update userorder SET status='Fi' where id=:id", nativeQuery = true)
	Boolean updateStatusToFinish(@Param("id") String orderID);

	@Query(value = "update userorder SET status='Co' where id=:id", nativeQuery = true)
	Boolean updateStatusToConfirm(@Param("id") String orderID);

	@Query(value = "update userorder SET status='Ca' where id=:id", nativeQuery = true)
	Boolean updateStatusToCancel(@Param("id") String orderID);
}
