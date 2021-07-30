package nashtech.phucldh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "Update payment_method set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deletePayment(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update payment_method set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeletePayment(Long id);

    @Query(
            value = "Select * from payment_method where name = ?1",
            nativeQuery = true
    )
    PaymentMethod checkExistedPaymentMethod(String paymentName);

}