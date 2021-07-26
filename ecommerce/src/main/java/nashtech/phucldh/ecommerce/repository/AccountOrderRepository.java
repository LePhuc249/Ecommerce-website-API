package nashtech.phucldh.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.AccountOrder;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountOrderRepository extends JpaRepository<AccountOrder, Long> {

    @Query(
            value = "select * from account_order where customer_id = ?1",
            nativeQuery = true
    )
    List<AccountOrder> findByCustomerid(Long customerid);

    @Query(
            value = "select * from account_order where coupon_id = ?1",
            nativeQuery = true
    )
    List<String> findByCouponid(Long couponid);

    @Modifying
    @Transactional
    @Query(
            value = "update account_order SET status=1 where id = ?1",
            nativeQuery = true
    )
    int updateStatusToCancel(Long orderID);

    @Modifying
    @Transactional
    @Query(
            value = "update account_order SET status=2 where id = ?1",
            nativeQuery = true
    )
    int updateStatusToConfirm(Long orderID);

    @Modifying
    @Transactional
    @Query(
            value = "update account_order SET status=3 where id = ?1",
            nativeQuery = true
    )
    int updateStatusToFinish(Long orderID);

    @Modifying
    @Transactional
    @Query(
            value = "update account_order SET status=4 where id = ?1",
            nativeQuery = true
    )
    int updateStatusToProcessing(Long orderID);

    @Query(
            value = "select status from account_order where id = ?1",
            nativeQuery = true
    )
    Long getStatus(Long orderID);

    @Query(
            value = "select * from account_order where customer_id = ?1 and total_price = ?2",
            nativeQuery = true
    )
    AccountOrder getAccountOrderByAccountAndTotalPrice(Long customerID, int totalPrice);

}
