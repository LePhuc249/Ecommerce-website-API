package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.Brand.BrandDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface BrandService {

    Brand getBrand(Long id) throws DataNotFoundException;

    Brand getBrandByName(String name) throws DataNotFoundException;

    Brand checkExistedBrand(String name, Long organizationId);

    Brand checkExistedBrandByIdAndOrganization(Long id, Long organizationId);

    Boolean addNewBrand(BrandDTO brandDTO) throws CreateDataFailException;

    Boolean updateBrand(BrandDTO brandDTO) throws DataNotFoundException, UpdateDataFailException;

    Boolean deleteBrand(Long id) throws DataNotFoundException, DeleteDataFailException;

    BrandDTO getBrandToShow(Long id) throws DataNotFoundException;

    List<BrandDTO> getBrandListToShow(int pageNo, String valueSort);

}