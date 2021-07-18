package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.OrderDetail;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	@Query(
			value = "select item_property from order_detail where order_id = ?1",
			nativeQuery = true
	)
	List<String> getListItemProperty(Long orderid);

	@Query(
			value = "select * from order_detail where order_id = ?1",
			nativeQuery = true
	)
	List<OrderDetail> getListItemInOrder(Long orderid);

	@Modifying
	@Transactional
	@Query(
			value = "Update order_detail set amount = ?2 where id = ?1",
			nativeQuery=true
	)
	int updateQuantityItem(Long id, int quantity);
}
