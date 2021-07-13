package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Payment_Method;

@Repository
public interface Payment_MethodRepository extends JpaRepository<Payment_Method, Integer> {

}
