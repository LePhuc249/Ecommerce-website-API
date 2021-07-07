package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Orderdetail;

@Repository
public interface OrderdetailRepository extends JpaRepository<Orderdetail, String> {

	
	@Query("select u.itemproperty from orderdetail u where u.orderid = :orderid")
	List<String> getListItemProperty(@Param("orderid") String orderid);
}
