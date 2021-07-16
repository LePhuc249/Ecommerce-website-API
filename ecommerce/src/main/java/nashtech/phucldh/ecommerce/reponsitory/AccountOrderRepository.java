package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.AccountOrder;

@Repository
public interface AccountOrderRepository extends JpaRepository<AccountOrder, Long> {

	@Query(value = "select ao from account_order ao where customer_id=:customer_id", nativeQuery = true)
	List<AccountOrder> findByCustomerid(@Param("customer_id") Long customerid);

	@Query(value = "select ao from account_order ao where coupon_id=:coupon_id", nativeQuery = true)
	List<String> findByCouponid(@Param("coupon_id") Long couponid);

	@Query(value = "update account_order SET status=1 where id=:id", nativeQuery = true)
	Boolean updateStatusToCancel(@Param("id") String orderID);
	
	@Query(value = "update account_order SET status=2 where id=:id", nativeQuery = true)
	Boolean updateStatusToConfirm(@Param("id") String orderID);
	
	@Query(value = "update account_order SET status=3 where id=:id", nativeQuery = true)
	Boolean updateStatusToFinish(@Param("id") String orderID);

	@Query(value = "update account_order SET status=4 where id=:id", nativeQuery = true)
	Boolean updateStatusToProcessing(@Param("id") String orderID);

	@Query(value = "select status from account_order where id=:id", nativeQuery = true)
	Long getStatus(@Param("id") String orderID);

}
