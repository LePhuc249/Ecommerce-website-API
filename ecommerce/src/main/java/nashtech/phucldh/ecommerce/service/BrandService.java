package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface BrandService {

    public List<Brand> getAllBrand() throws DataNotFoundException;

    public Brand getBrand(Long id) throws DataNotFoundException;

    public Brand getBrandByName(String name) throws DataNotFoundException;

    public Brand getBrandByOrganization(Long organizationId) throws DataNotFoundException;

    public Brand checkExistedBrand(String name, Long organizationId);

    public void addNewBrand(Brand brand) throws CreateDataFailException;

    public void updateBrand(Brand brand) throws DataNotFoundException, UpdateDataFailException;

    public void deleteBrand(Long id) throws DataNotFoundException, DeleteDataFailException;

}