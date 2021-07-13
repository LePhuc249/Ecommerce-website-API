package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Order_Detail;

@Repository
public interface Order_DetailRepository extends JpaRepository<Order_Detail, Integer> {

	@Query(value = "select item_property from order_detail where order_id=:order_id", nativeQuery = true)
	List<String> getListItemProperty(@Param("order_id") String orderid);

}
