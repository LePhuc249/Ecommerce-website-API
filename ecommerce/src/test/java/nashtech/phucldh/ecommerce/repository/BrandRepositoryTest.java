package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Organization;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BrandRepositoryTest {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    public void getAll() {
        List<Brand> list = brandRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getBrandById() throws DataNotFoundException {
        Brand brand;
        Optional<Brand> result = brandRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            brand = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        Assert.notNull(brand);
    }

    @Test
    public void getBrandByName() throws DataNotFoundException {
        Brand brand;
        Optional<Brand> result = brandRepository.findByName("Fres");
        if (result.isPresent()){
            brand = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        Assert.notNull(brand);
    }

    @Test
    public void getBrandByOrganization() throws DataNotFoundException {
        Organization organization;
        Optional<Organization> result = organizationRepository.findById(Long.valueOf("2"));
        if (result.isPresent()) {
            organization = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        Brand brand;
        Optional<Brand> brandResult = brandRepository.findByOrganization(organization);
        if (brandResult.isPresent()) {
            brand = brandResult.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        Assert.notNull(brand);
    }

    @Test
    public void addBrand() throws DataNotFoundException {
        Organization organization = organizationRepository.findById(Long.valueOf("111")).get();
        Brand brand = new Brand();
        brand.setName("New Brand");
        brand.setOrganization(organization);
        Assert.notNull(brandRepository.save(brand));
    }

    @Test
    public void updateBrand() throws DataNotFoundException {
        Brand brand;
        Optional<Brand> result = brandRepository.findById(Long.valueOf("109"));
        if (result.isPresent()) {
            brand = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        brand.setName("New Name");
        Assert.notNull(brandRepository.save(brand));
    }

    @Test
    public void deleteBrand() throws DataNotFoundException {
        Brand brand;
        Optional<Brand> result = brandRepository.findById(Long.valueOf("112"));
        if (result.isPresent()) {
            brand = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        brandRepository.deleteById(brand.getId());
        Optional<Brand> resultAfterDelete = brandRepository.findById(Long.valueOf("110"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

}
