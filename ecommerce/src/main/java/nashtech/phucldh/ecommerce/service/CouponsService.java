package nashtech.phucldh.ecommerce.service;

import java.util.List;
import nashtech.phucldh.ecommerce.dto.Coupons.CouponsDTO;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface CouponsService {

    List<Coupons> getListCouponByItem(Long itemID) throws DataNotFoundException;

    Coupons getCouponById(Long id) throws DataNotFoundException;

    Coupons getCouponByCode(String code) throws DataNotFoundException;

    Coupons getCouponCanUseByCode(String code) throws DataNotFoundException;

    Boolean createCoupon(CouponsDTO dtoCoupon) throws CreateDataFailException;

    Boolean updateCoupon(CouponsDTO dtoCoupon) throws UpdateDataFailException;

    Boolean deleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException;

    Boolean unDeleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException, UpdateDataFailException;

    CouponsDTO getCouponsToShow(Long id) throws DataNotFoundException;

    List<CouponsDTO> getListCouponsToShow(int pageNo, String valueSort);

}