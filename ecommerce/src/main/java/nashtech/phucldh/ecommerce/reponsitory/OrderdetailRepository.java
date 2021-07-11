package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Orderdetail;

@Repository
public interface OrderdetailRepository extends JpaRepository<Orderdetail, String> {

	@Query(value = "select itemproperty from orderdetail where orderid=:orderid", nativeQuery = true)
	List<String> getListItemProperty(@Param("orderid") String orderid);

	Optional<Orderdetail> findByOrderlineid(String orderlineid);
}
