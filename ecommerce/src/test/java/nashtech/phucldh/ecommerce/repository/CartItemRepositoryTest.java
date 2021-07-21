package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.entity.CartItem;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.reponsitory.CartItemRepository;
import nashtech.phucldh.ecommerce.reponsitory.CartRepository;
import nashtech.phucldh.ecommerce.reponsitory.ProductRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CartItemRepositoryTest {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveCart() {
        Cart cart = cartRepository.findById(Long.valueOf("159")).get();
        Product product = productRepository.findById(Long.valueOf("1")).get();
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setAmount(1);
        item.setPrice(product.getPrice());
        cart.getListItem().add(item);
        item.setCart(cart);
        Assert.notNull(cartRepository.save(cart));
    }

    @Test
    public void updateItem() {
        CartItem item = cartItemRepository.findById(Long.valueOf("")).get();
        item.setAmount(2);
        Assert.notNull(cartItemRepository.save(item));
    }

    @Test
    public void deleteItem() {
        CartItem item = cartItemRepository.findById(Long.valueOf("")).get();
        cartItemRepository.deleteById(item.getId());
        Optional<CartItem> result = cartItemRepository.findById(Long.valueOf(""));
        boolean checkExist = result.isPresent();
        Assert.isTrue(checkExist);
    }
}
