package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.ImageRepository;
import nashtech.phucldh.ecommerce.reponsitory.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrganizationRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void addOrganization() {
        Organization organization = new Organization();
        organization.setName("Apple");
        organization.setCreateby(1);
        organization.setDeleted(false);
        Assert.notNull(organizationRepository.save(organization));
    }

    @Test
    public void updateOrganization() throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> result = organizationRepository.findById(Long.valueOf("40"));
        if (result.isPresent()){
            organization = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        Image image = null;
        Optional<Image> resultImage = imageRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            image = resultImage.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND); // edit error code
        }
        organization.setImageOrganization(image);
        Assert.notNull(organizationRepository.save(organization));
    }

    @Test
    public void deleteOrganization() throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> result = organizationRepository.findById(Long.valueOf("111"));
        if (result.isPresent()){
            organization = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        organizationRepository.deleteById(organization.getId());
        Optional<Organization> resultAfterDelete = organizationRepository.findById(Long.valueOf("111"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

    @Test
    public void getOrganization() {
        Assert.notNull(organizationRepository.findById(Long.valueOf("1")));
    }

    @Test
    public void getAllOrganizations() {
        List<Organization> list = organizationRepository.findAll();
        boolean result = (list.size() == 39);
        Assert.isTrue(result);
    }
}
