package nashtech.phucldh.ecommerce.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nashtech.phucldh.ecommerce.entity.Cart_Item;

@Repository
public interface Cart_ItemRepository extends JpaRepository<Cart_Item, Integer>{

}
