package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.Coupons.CouponsDTO;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CouponsConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponsConverter.class);

    @Autowired
    private ModelMapper modelMapper;

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

    public CouponsDTO toDTO(Coupons entity) {
        CouponsDTO dto = new CouponsDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setProductDiscount(entity.getProductDiscount());
        dto.setDescription(entity.getDescription());
        dto.setCreateBy(entity.getCreateBy());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setCreateBy(entity.getCreateBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public List<CouponsDTO> toDTOList(List<Coupons> entityList) {
        List<CouponsDTO> dtoList = entityList.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return dtoList;
    }

}