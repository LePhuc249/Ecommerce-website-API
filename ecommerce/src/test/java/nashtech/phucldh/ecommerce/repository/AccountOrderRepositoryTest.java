package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.entity.OrderStatus;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.reponsitory.AccountOrderRepository;
import nashtech.phucldh.ecommerce.reponsitory.AccountReponsitory;
import nashtech.phucldh.ecommerce.reponsitory.OrderStatusRepository;
import nashtech.phucldh.ecommerce.reponsitory.PaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AccountOrderRepositoryTest {

    @Autowired
    AccountOrderRepository accountOrderRepository;

    @Autowired
    AccountReponsitory accountRepository;

    @Autowired
    PaymentMethodRepository paymentmethodRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Test
    public void createOrder() {
        Account account = accountRepository.findById(Long.valueOf("1")).get();
        PaymentMethod payment = paymentmethodRepository.findById(Long.valueOf("1")).get();
        OrderStatus status = orderStatusRepository.findById(Long.valueOf("1")).get();
        AccountOrder order = new AccountOrder();
        order.setAccount(account);
        order.setDatedelivery("2021-07-30");
        order.setPaymentmethod(payment.getId());
        order.setStatus(status.getId());
        order.setTotalprice(Float.valueOf(100));
        Assert.notNull(accountOrderRepository.save(order));
    }

    @Test
    public void updateOrder() {
        AccountOrder order = accountOrderRepository.findById(Long.valueOf("127")).get();
        OrderStatus status = orderStatusRepository.findById(Long.valueOf("2")).get();
        order.setStatus(status.getId());
        Assert.notNull(accountOrderRepository.save(order));
    }

    @Test
    public void deleteOrder() {
        AccountOrder order = accountOrderRepository.findById(Long.valueOf("128")).get();
        accountOrderRepository.deleteById(order.getId());
        Optional<AccountOrder> result = accountOrderRepository.findById(Long.valueOf("128"));
        boolean checkExist = result.isPresent();
        Assert.isTrue(!checkExist);
    }
}
