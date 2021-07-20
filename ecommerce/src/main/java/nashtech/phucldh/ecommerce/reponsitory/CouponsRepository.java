package nashtech.phucldh.ecommerce.reponsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Coupons;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons, Long> {

    List<Coupons> findByProductdiscount(Long productdiscount);

    Optional<Coupons> findByCode(String code);

    @Modifying
    @Transactional
    @Query(
            value = "Update coupons set isdeleted = true where id = ?1",
            nativeQuery = true
    )
    int deleteCoupons(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Update coupons set isdeleted = false where id = ?1",
            nativeQuery = true
    )
    int unDeleteCoupons(Long id);

    @Query(
            value = "select * from coupons where code = ?1 and isdeleted = false",
            nativeQuery = true
    )
    Optional<Coupons> canUse(String code);

    @Query(
            value = "select * from coupons where product_discount = ?1 and isdeleted = false",
            nativeQuery = true
    )
    List<Coupons> findCouponsByItem(Long itemID);
}