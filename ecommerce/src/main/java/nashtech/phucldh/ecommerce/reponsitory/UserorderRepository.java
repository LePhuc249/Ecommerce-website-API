package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Userorder;

@Repository
public interface UserorderRepository extends JpaRepository<Userorder, String> {

	List<Userorder> findByCustomerid(String customerid);

	List<String> findByCouponid(String id);

	@Query(value = "update userorder SET status='Fi' where orderid=:orderid", nativeQuery = true)
	Boolean updateStatusToFinish(@Param("orderid") String orderID);

	@Query(value = "update userorder SET status='Co' where orderid=:orderid", nativeQuery = true)
	Boolean updateStatusToConfirm(@Param("orderid") String orderID);

	@Query(value = "update userorder SET status='Ca' where orderid=:orderid", nativeQuery = true)
	Boolean updateStatusToCancel(@Param("orderid") String orderID);
}
