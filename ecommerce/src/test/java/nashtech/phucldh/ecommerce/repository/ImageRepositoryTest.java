package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.ImageRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void getAllImage() {
        List<Image> list = imageRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getImage() throws DataNotFoundException {
        Image image = null;
        Optional<Image> result = imageRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            image = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND); // add image to error code
        }
        Assert.notNull(image);
    }

    @Test
    public void addImage() {
        Image image = new Image();
        image.setUrl("Test url");
        image.setDescription("Test description");
        image.setCreateby(1);
        image.setDeleted(false);
        Assert.notNull(imageRepository.save(image));
    }

    @Test
    public void updateImage() throws DataNotFoundException {
        Image image = null;
        Optional<Image> result = imageRepository.findById(Long.valueOf("93"));
        if (result.isPresent()){
            image = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND); // add image to error code
        }
        image.setDescription("Updated Description");
        Assert.notNull(imageRepository.save(image));
    }

    @Test
    public void deleteImage() throws DataNotFoundException {
        Image image = null;
        Optional<Image> result = imageRepository.findById(Long.valueOf("93"));
        if (result.isPresent()){
            image = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND); // add image to error code
        }
        imageRepository.deleteById(image.getId());
        Optional<Image> resultAfterDelete = imageRepository.findById(Long.valueOf("93"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }
}
