package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.PaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PaymentMethodRepositoryTest {

    @Autowired
    PaymentMethodRepository paymentmethodRepository;


    @Test
    public void getAllPaymentMethods() {
        List<PaymentMethod> list = paymentmethodRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getPaymentById() throws DataNotFoundException {
        PaymentMethod paymentMethod = null;
        Optional<PaymentMethod> result = paymentmethodRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            paymentMethod = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        Assert.notNull(paymentMethod);
    }

    @Test
    public void addPayment() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName("Payment Third Party");
        paymentMethod.setCreateby(Long.valueOf("1"));
        paymentMethod.setDeleted(false);
        Assert.notNull(paymentmethodRepository.save(paymentMethod));
    }

    @Test
    public void updatePayment() throws DataNotFoundException {
        PaymentMethod paymentMethod = null;
        Optional<PaymentMethod> result = paymentmethodRepository.findById(Long.valueOf("95"));
        if (result.isPresent()){
            paymentMethod = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        paymentMethod.setName("Pay On Delivery");
        Assert.notNull(paymentmethodRepository.save(paymentMethod));
    }

    @Test
    public void deletePayment() throws DataNotFoundException {
        PaymentMethod paymentMethod = null;
        Optional<PaymentMethod> result = paymentmethodRepository.findById(Long.valueOf("95"));
        if (result.isPresent()){
            paymentMethod = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_PAYMENT_METHOD_NOT_FOUND);
        }
        paymentmethodRepository.deleteById(paymentMethod.getId());
        Optional<PaymentMethod> resultAfterDelete = paymentmethodRepository.findById(Long.valueOf("95"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }
}
