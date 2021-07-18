package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.CartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

    @Query(
            value = "select * from cart_item where cart_id = ?1",
            nativeQuery = true
    )
    List<CartItem> getListItem(Long cartID);

    @Modifying
    @Transactional
    @Query(
            value = "Update cart_item set amount = ?2 where id = ?1",
            nativeQuery=true
    )
    int updateItemQuantity(Long id, int quantity);
}
