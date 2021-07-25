package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Coupons;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CouponsRepositoryTest {

    @Autowired
    CouponsRepository couponsRepository;

    @Test
    public void getAllCoupons() {
        List<Coupons> list = couponsRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getCouponById() throws DataNotFoundException {
        Coupons coupons;
        Optional<Coupons> result = couponsRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            coupons = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        Assert.notNull(coupons);
    }

    @Test
    public void testFunctionFindByProductdiscount() throws DataNotFoundException {
        List<Coupons> result = couponsRepository.findByProductDiscount(Long.valueOf("1"));
        Assert.notNull(result);
    }

    @Test
    public void testFunctionFindByCode() throws DataNotFoundException {
        Coupons coupons;
        Optional<Coupons> result = couponsRepository.findByCode("ABC10");
        if (result.isPresent()){
            coupons = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        Assert.notNull(coupons);
    }

    @Test
    public void addCoupon() {
        Coupons coupons = new Coupons();
        coupons.setCode("NEW");
        coupons.setDiscountAmount(15);
        coupons.setProductDiscount(Long.valueOf("1"));
        coupons.setDescription("Demo Description");
        coupons.setExpirationDate("2021-12-31");
        coupons.setCreateBy(Long.valueOf("1"));
        coupons.setDeleted(false);
        Assert.notNull(couponsRepository.save(coupons));
    }

    @Test
    public void updateCoupon() throws DataNotFoundException {
        Coupons coupons;
        Optional<Coupons> result = couponsRepository.findById(Long.valueOf("97"));
        if (result.isPresent()){
            coupons = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        coupons.setDescription("New Description");
        Assert.notNull(couponsRepository.save(coupons));
    }

    @Test
    public void deleteCoupon() throws DataNotFoundException {
        Coupons coupons;
        Optional<Coupons> result = couponsRepository.findById(Long.valueOf("97"));
        if (result.isPresent()){
            coupons = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        couponsRepository.deleteById(coupons.getId());
        Optional<Coupons> resultAfterDelete = couponsRepository.findById(Long.valueOf("97"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

}
