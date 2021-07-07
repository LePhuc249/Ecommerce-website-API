package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Coupons;

@Repository
public interface CouponsReponsitory extends JpaRepository<Coupons,String>{
	
	List<Coupons> findByProductdiscount(String productdiscount);
}
