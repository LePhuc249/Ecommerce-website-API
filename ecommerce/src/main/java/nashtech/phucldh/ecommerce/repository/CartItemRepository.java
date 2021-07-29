package nashtech.phucldh.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import nashtech.phucldh.ecommerce.entity.CartItem;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(
            value = "select * from cart_item where cart_id = ?1",
            nativeQuery = true
    )
    List<CartItem> getListItem(Long cartID);

    @Modifying
    @Transactional
    @Query(
            value = "Update cart_item set amount = ?2 where id = ?1",
            nativeQuery = true
    )
    int updateItemQuantity(Long id, int quantity);

    @Query(
            value = "select * from cart_item where cart_id = ?1 and product_id = ?2",
            nativeQuery = true
    )
    CartItem getByCartAndProduct(Long cartID, Long productID);

    @Modifying
    @Transactional
    @Query(
            value = "Delete from cart_item where product_id = ?1",
            nativeQuery = true
    )
    int deleteByItemId(Long id);

    @Query(
            value = "Select * from cart_item where product_id = ?1",
            nativeQuery = true
    )
    CartItem getByItemId(Long id);

    @Modifying
    @Transactional
    @Query(
            value = "Delete from cart_item where cart_id = ?1",
            nativeQuery = true
    )
    int deleteByCartId(Long id);

}