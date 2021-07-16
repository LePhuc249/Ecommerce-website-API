package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    @Query(value = "update order_status SET isdeleted=true where id=:id", nativeQuery = true)
    Boolean deletePaymentMethod(@Param("id") Long id);

    @Query(value = "update order_status SET isdeleted=false where id=:id", nativeQuery = true)
    Boolean unDeletePaymentMethod(@Param("id") Long id);

}
