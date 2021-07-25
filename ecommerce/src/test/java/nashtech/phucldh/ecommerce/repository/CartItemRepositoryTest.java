package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.entity.CartItem;
import nashtech.phucldh.ecommerce.entity.Product;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void saveCart() {
        Cart cart = cartRepository.findById(Long.valueOf("77")).get();
        Product product = productRepository.findById(Long.valueOf("1")).get();
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setAmount(1);
        item.setPrice(product.getPrice());
        item.setCart(cart);
        Assert.notNull(cartItemRepository.save(item));
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
