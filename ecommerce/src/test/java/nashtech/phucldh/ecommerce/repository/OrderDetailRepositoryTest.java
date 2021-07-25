package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.entity.OrderDetail;
import nashtech.phucldh.ecommerce.entity.Product;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderdetailrepository;

    @Autowired
    AccountOrderRepository accountOrderRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void addOrderDetail() {
        AccountOrder order = accountOrderRepository.findById(Long.valueOf("128")).get();
        Product product = productRepository.findById(Long.valueOf("1")).get();
        OrderDetail detail = new OrderDetail();
        detail.setAccountOrder(order);
        detail.setAmount(1);
        detail.setItemId(product.getId());
        detail.setPrice(product.getPrice());
        detail.setItemProperty("Detail: " + product.getDescription());
        Assert.notNull(orderdetailrepository.save(detail));
    }

    @Test
    public void updateOrderDetail() {
        OrderDetail detail = orderdetailrepository.findById(Long.valueOf("130")).get();
        detail.setAmount(2);
        Assert.notNull(orderdetailrepository.save(detail));
    }

    @Test
    public void deleteOrderDetail() {
        OrderDetail detail = orderdetailrepository.findById(Long.valueOf("130")).get();
        orderdetailrepository.deleteById(detail.getId());
        Optional<OrderDetail> result = orderdetailrepository.findById(Long.valueOf("130"));
        boolean checkExist = result.isPresent();
        Assert.isTrue(!checkExist);
    }

}
