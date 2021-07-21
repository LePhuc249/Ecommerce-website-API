package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.CouponsDTO;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.reponsitory.CouponsRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponsConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponsConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CouponsRepository couponsRepository;

    public CouponsDTO convertCouponsToDTO(Coupons coupon) {
        try {
            CouponsDTO couponsDTO = modelMapper.map(coupon, CouponsDTO.class);
            return couponsDTO;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Coupons to CouponsDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Coupons convertCouponsDTOToEntity(CouponsDTO dtoCoupon) {
        try {
            Coupons coupon = modelMapper.map(dtoCoupon, Coupons.class);
            return coupon;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert CouponsDTO to Coupons");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }
}