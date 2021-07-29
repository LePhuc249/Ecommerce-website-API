package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.BrandConverter;
import nashtech.phucldh.ecommerce.dto.Brand.BrandDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.BrandRepository;
import nashtech.phucldh.ecommerce.repository.OrganizationRepository;
import nashtech.phucldh.ecommerce.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    BrandConverter brandConverter;

    @Override
    public Brand getBrand(Long id) throws DataNotFoundException {
        Brand brand = brandRepository.getBrandByIdBrand(id);
        if (brand == null) {
            LOGGER.info("Can't find brand by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return brand;
    }

    @Override
    public Brand getBrandByName(String name) throws DataNotFoundException {
        Brand brand;
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
    public Brand checkExistedBrand(String name, Long organizationId) {
        Brand brand = null;
        if (name != null && organizationId != null) {
            brand = brandRepository.checkExistedBrand(name, organizationId);
        }
        return brand;
    }

    @Override
    public Brand checkExistedBrandByIdAndOrganization(Long id, Long organizationId) {
        Brand brand = null;
        if (id != null && organizationId != null) {
            brand = brandRepository.checkExistedBrandByIdOrOrganization(id, organizationId);
        }
        return brand;
    }

    @Override
    public Boolean addNewBrand(BrandDTO brandDTO) throws CreateDataFailException {
        boolean result;
        try {
            Brand brand = brandConverter.convertBrandDTOToEntity(brandDTO);
            Brand tempBrand = checkExistedBrand(brand.getName(), brand.getOrganization().getId());
            if (tempBrand != null) {
                LOGGER.info("Brand have been existed ");
                throw new DuplicateDataException(ErrorCode.ERR_BRAND_EXISTED);
            }
            brandRepository.save(brand);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create new brand: " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateBrand(BrandDTO brandDTO) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            Brand brand = brandConverter.convertBrandDTOToEntity(brandDTO);
            Brand tempBrand = checkExistedBrandByIdAndOrganization(brand.getId(), brand.getOrganization().getId());
            if (tempBrand == null) {
                LOGGER.info("Can't find brand ");
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            brandRepository.save(brand);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update brand: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_BRAND_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteBrand(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Brand brand;
        try {
            Optional<Brand> brandOptional = brandRepository.findById(id);
            if (brandOptional.isPresent()) {
                brand = brandOptional.get();
            } else {
                LOGGER.info("Can't find brand ");
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            brandRepository.deleteById(brand.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete brand: " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_BRAND_FAIL);
        }
        return result;
    }

    @Override
    public BrandDTO getBrandToShow(Long id) throws DataNotFoundException {
        BrandDTO dto;
        try {
            Brand brand = brandRepository.getBrandByIdBrand(id);
            if (brand == null) {
                LOGGER.info("Can't find brand by id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            dto = brandConverter.convertBrandToDTO(brand);
        } catch (Exception e) {
            LOGGER.info("Having error when load the brand " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_LOADED_FAIL);
        }
        return dto;
    }

    @Override
    public List<BrandDTO> getBrandListToShow(int pageNo, String valueSort) {
        List<BrandDTO> listDTO;
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
            Page<Brand> page = brandRepository.findAll(pageable);
            listDTO = brandConverter.toDTOList(page.getContent());
        } catch (Exception e) {
            LOGGER.info("Having error when load list brand " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

}