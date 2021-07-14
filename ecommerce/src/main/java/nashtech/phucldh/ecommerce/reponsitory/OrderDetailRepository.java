package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	@Query(value = "select item_property from order_detail where order_id=:order_id", nativeQuery = true)
	List<String> getListItemProperty(@Param("order_id") String orderid);

}
