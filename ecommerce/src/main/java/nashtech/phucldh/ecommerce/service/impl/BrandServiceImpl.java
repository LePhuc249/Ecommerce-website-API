package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.BrandRepository;
import nashtech.phucldh.ecommerce.reponsitory.OrganizationRepository;
import nashtech.phucldh.ecommerce.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public List<Brand> getAllBrand() throws DataNotFoundException {
        List<Brand> brandList = null;
        try {
            brandList = brandRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Can't find all brand");
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return brandList;
    }

    @Override
    public Brand getBrand(Long id) throws DataNotFoundException {
        Brand brand = null;
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            brand = brandOptional.get();
        } else {
            LOGGER.info("Can't find brand by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return brand;
    }

    @Override
    public Brand getBrandByName(String name) throws DataNotFoundException {
        Brand brand = null;
        Optional<Brand> brandOptional = brandRepository.findByName(name);
        if (brandOptional.isPresent()) {
            brand = brandOptional.get();
        } else {
            LOGGER.info("Can't find brand by name: " + name);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return brand;
    }

    @Override
    public Brand getBrandByOrganization(Long organizationId) throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> organizationOptional = organizationRepository.findById(organizationId);
        if (organizationOptional.isPresent()) {
            organization = organizationOptional.get();
        } else {
            LOGGER.info("Can't find organization by id: " + organizationId);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        Brand brand = null;
        Optional<Brand> brandOptional = brandRepository.findByOrganization(organization);
        if (brandOptional.isPresent()) {
            brand = brandOptional.get();
        } else {
            LOGGER.info("Can't find brand by organization id: " + organizationId);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return brand;
    }

    @Override
    public void addNewBrand(Brand brand) throws CreateDataFailException {
        try {
            brandRepository.save(brand);
        } catch (Exception ex) {
            LOGGER.info("Can't add new brand ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }
    }

    @Override
    public void updateBrand(Brand brand) throws DataNotFoundException, UpdateDataFailException {
        Brand temp = null;
        Optional<Brand> brandOptional = brandRepository.findById(brand.getId());
        if (brandOptional.isPresent()) {
            temp = brandOptional.get();
        } else {
            LOGGER.info("Can't find brand ");
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        try {
            brandRepository.save(brand);
        } catch (Exception ex) {
            LOGGER.info("Can't update brand");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_BRAND_FAIL);
        }
    }

    @Override
    public void deleteBrand(Long id) throws DataNotFoundException, DeleteDataFailException {
        Brand brand = null;
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            brand = brandOptional.get();
        } else {
            LOGGER.info("Can't find brand ");
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        try {
            brandRepository.deleteById(brand.getId());
        } catch (Exception ex) {
            LOGGER.info("Can't delete brand");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_BRAND_FAIL);
        }
    }

}