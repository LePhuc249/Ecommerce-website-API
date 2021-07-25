package nashtech.phucldh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.OrderStatus;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "Update order_status set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deleteOrderStatus(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update order_status set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeleteOrderStatus(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update order_status set name = ?2 where id = ?1",
            nativeQuery = true
    )
    int updateNameOrderStatus(Long id, String newName);

}