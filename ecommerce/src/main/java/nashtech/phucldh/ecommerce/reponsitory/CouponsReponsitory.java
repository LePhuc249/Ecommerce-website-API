package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Coupons;

@Repository
public interface CouponsReponsitory extends JpaRepository<Coupons, UUID> {

	List<Coupons> findByProductdiscount(Integer productdiscount);

	Optional<Coupons> findByCouponcode(String code);

	@Query(value = "delete from coupons where couponcode=:couponcode", nativeQuery = true)
	Boolean deleteCouponByCode(@Param("couponcode") String couponsCode);
}
