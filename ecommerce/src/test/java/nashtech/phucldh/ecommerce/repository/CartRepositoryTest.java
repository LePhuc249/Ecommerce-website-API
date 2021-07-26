package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Cart;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AccountReponsitory accountRepository;

    @Test
    public void addCart() {
        Account account = accountRepository.findById(Long.valueOf("1")).get();
        Cart cart = new Cart();
        cart.setAccount(account);
        Assert.notNull(cartRepository.save(cart));
    }

    @Test
    public void getCartById() {
        Cart cart = cartRepository.findById(Long.valueOf("116")).get();
        boolean result = false;
        if (cart != null) {
            result = true;
        }
        Assert.isTrue(result);
    }

    @Test
    public void getCartByAccount() {
        Account account = accountRepository.findById(Long.valueOf("1")).get();
        Cart cart = cartRepository.findByAccount(account);
        boolean result = false;
        if (cart != null) {
            result = true;
        }
        Assert.isTrue(result);
    }

    @Test
    public void deleteCart() {
        Cart cart = cartRepository.findById(Long.valueOf("122")).get();
        cartRepository.deleteById(cart.getId());
        Optional<Cart> cartAfter = cartRepository.findById(Long.valueOf("122"));
        boolean result = cartAfter.isPresent();
        Assert.isTrue(!result);
    }

}
