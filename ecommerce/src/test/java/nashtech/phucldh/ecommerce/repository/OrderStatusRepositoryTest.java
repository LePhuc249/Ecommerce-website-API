package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.OrderStatus;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.OrderStatusRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderStatusRepositoryTest {

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Test
    public void getAllStatus() {
        List<OrderStatus> list = orderStatusRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getStatusById() throws DataNotFoundException {
        OrderStatus status = null;
        Optional<OrderStatus> result = orderStatusRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            status = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        Assert.notNull(status);
    }

    @Test
    public void addStatus() {
        OrderStatus status = new OrderStatus();
        status.setName("Payment Third Party");
        status.setCreateby(Long.valueOf("1"));
        status.setDeleted(false);
        Assert.notNull(orderStatusRepository.save(status));
    }

    @Test
    public void updateStatus() throws DataNotFoundException {
        OrderStatus status = null;
        Optional<OrderStatus> result = orderStatusRepository.findById(Long.valueOf("94"));
        if (result.isPresent()){
            status = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        status.setName("New Status Name");
        Assert.notNull(orderStatusRepository.save(status));
    }

    @Test
    public void deleteStatus() throws DataNotFoundException {
        OrderStatus status = null;
        Optional<OrderStatus> result = orderStatusRepository.findById(Long.valueOf("94"));
        if (result.isPresent()){
            status = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORDER_STATUS_NOT_FOUND);
        }
        orderStatusRepository.deleteById(status.getId());
        Optional<OrderStatus> resultAfterDelete = orderStatusRepository.findById(Long.valueOf("94"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }
}
