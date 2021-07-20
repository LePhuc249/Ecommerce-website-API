package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.ImageRepository;
import nashtech.phucldh.ecommerce.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> getAllImage() throws DataNotFoundException {
        List<Image> listAllImage = null;
        try {
            listAllImage = imageRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Can't find all image ");
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        return listAllImage;
    }

    @Override
    public Image getImage(Long id) throws DataNotFoundException {
        Image image = null;
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        return image;
    }

    @Override
    public Image getImageByURL(String url) throws DataNotFoundException {
        Image image = null;
        Optional<Image> imageOptional = imageRepository.findByUrl(url);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by url " + url);
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        return image;
    }


    @Override
    public void addNewImage(Image image) throws CreateDataFailException {
        try {
            imageRepository.save(image);
        } catch (Exception ex) {
            LOGGER.info("Can't create new image ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_IMAGE_FAIL);
        }
    }

    @Override
    public void updateImage(Image image) throws DataNotFoundException, UpdateDataFailException {
        Image tempImage = null;
        Optional<Image> imageOptional = imageRepository.findById(image.getId());
        if (imageOptional.isPresent()) {
            tempImage = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by id " + image.getId());
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        try {
            imageRepository.save(image);
        } catch (Exception ex) {
            LOGGER.info("Can't update image ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
        }
    }

    @Override
    public void deleteImage(Long id) throws DataNotFoundException, DeleteDataFailException {
        Image image = null;
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by id " + image.getId());
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        try {
            imageRepository.deleteImage(id);
        } catch (Exception ex) {
            LOGGER.info("Can't delete image ");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_IMAGE_FAIL);
        }
    }

    @Override
    public void activeImage(Long id) throws DataNotFoundException, DeleteDataFailException {
        Image image = null;
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by id " + image.getId());
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        try {
            imageRepository.unDeleteImage(id);
        } catch (Exception ex) {
            LOGGER.info("Can't update image ");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_IMAGE_FAIL);
        }
    }

    @Override
    public void updateImageUrl(Long id, String url) throws DataNotFoundException, DeleteDataFailException {
        Image image = null;
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by id " + image.getId());
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        try {
            imageRepository.updateImageURL(id, url);
        } catch (Exception ex) {
            LOGGER.info("Can't update image ");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_IMAGE_FAIL);
        }
    }

}