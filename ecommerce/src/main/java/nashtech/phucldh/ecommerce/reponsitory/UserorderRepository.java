package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Userorder;

@Repository
public interface UserorderRepository extends JpaRepository<Userorder, String>{
	
	List<Userorder> findByCustomerid(String customerid);
	
	@Query("select u.couponid from userorder u")
	List<String> findByCouponid();
	
	@Query("update userorder SET status='Fi' where orderid=:orderid")
	Boolean updateStatusToFinish(@Param("orderid")String orderID);
	
	@Query("update userorder SET status='Co' where orderid=:orderid")
	Boolean updateStatusToConfirm(@Param("orderid")String orderID);
	
	@Query("update userorder SET status='Ca' where orderid=:orderid")
	Boolean updateStatusToCancel(@Param("orderid")String orderID);
}
