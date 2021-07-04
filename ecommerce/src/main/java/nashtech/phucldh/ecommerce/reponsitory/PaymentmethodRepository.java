package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Paymentmethod;

@Repository
public interface PaymentmethodRepository extends JpaRepository<Paymentmethod, String> {

}
