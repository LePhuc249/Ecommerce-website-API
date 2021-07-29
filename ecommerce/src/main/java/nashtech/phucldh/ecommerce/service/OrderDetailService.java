package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.OrderDetail;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;

public interface OrderDetailService {

    Boolean createOrderdetail(OrderDetail theCoupon) throws CreateDataFailException;;

}