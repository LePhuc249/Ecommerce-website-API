package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.dto.CouponsDTO;

import nashtech.phucldh.ecommerce.entity.Coupons;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import org.springframework.data.domain.Page;

public interface CouponsService {

    public List<Coupons> findAllCoupons() throws DataNotFoundException;

    public List<Coupons> findByItem(Long itemID) throws DataNotFoundException;

    public List<Coupons> getListCouponByItem(Long itemID) throws DataNotFoundException;

    public Coupons getCouponById(Long id) throws DataNotFoundException;

    public Coupons getCouponByCode(String code) throws DataNotFoundException;

    public Coupons getCouponCanUseByCode(String code) throws DataNotFoundException;

    public Boolean createCoupon(CouponsDTO dtoCoupon) throws CreateDataFailException;

    public Boolean updateCoupon(CouponsDTO dtoCoupon) throws UpdateDataFailException;

    public Boolean deleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException;

    public Boolean unDeleteCoupon(Long id) throws DataNotFoundException, DeleteDataFailException, UpdateDataFailException;

    public Page<Coupons> getPaginationCoupons(int pageNo, String valueSort);

}