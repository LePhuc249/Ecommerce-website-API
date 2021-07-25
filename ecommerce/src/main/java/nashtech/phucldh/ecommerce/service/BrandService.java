package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.BrandDTO;

import nashtech.phucldh.ecommerce.entity.Brand;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {

    public List<Brand> getAllBrand() throws DataNotFoundException;

    public Brand getBrand(Long id) throws DataNotFoundException;

    public Brand getBrandByName(String name) throws DataNotFoundException;

    public Brand getBrandByOrganization(Long organizationId) throws DataNotFoundException;

    public Brand checkExistedBrand(String name, Long organizationId);

    public Brand checkExistedBrandByIdAndOrganization(Long id, Long organizationId);

    public Boolean addNewBrand(BrandDTO brandDTO) throws CreateDataFailException;

    public Boolean updateBrand(BrandDTO brandDTO) throws DataNotFoundException, UpdateDataFailException;

    public Boolean deleteBrand(Long id) throws DataNotFoundException, DeleteDataFailException;

    public Page<Brand> getPaginationBrand(int pageNo, String valueSort);

}