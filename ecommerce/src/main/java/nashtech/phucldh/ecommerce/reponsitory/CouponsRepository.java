package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Coupons;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons, Long> {

	List<Coupons> findByProductdiscount(Long productdiscount);

	Optional<Coupons> findByCode(String code);

}
