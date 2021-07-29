package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.ImageConverter;
import nashtech.phucldh.ecommerce.dto.Image.ImageDTO;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.ImageRepository;
import nashtech.phucldh.ecommerce.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageConverter imageConverter;

    @Override
    public List<ImageDTO> getAllImage() throws DataNotFoundException {
        List<ImageDTO> listDTO = null;
        try {
            List<Image> listAllImage = imageRepository.findAll();
            if (listAllImage.size() > 0) {
                listDTO = new ArrayList<>();
                for (Image image : listAllImage) {
                    ImageDTO dto = imageConverter.convertImageToDTO(image);
                    listDTO.add(dto);
                }
            }
        } catch (Exception e) {
            LOGGER.info("Having error when find all image " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        return listDTO;
    }

    @Override
    public ImageDTO getImage(Long id) throws DataNotFoundException {
        Image image;
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        return imageConverter.convertImageToDTO(image);
    }

    @Override
    public ImageDTO getImageByURL(String url) throws DataNotFoundException {
        Image image;
        Optional<Image> imageOptional = imageRepository.findByUrl(url);
        if (imageOptional.isPresent()) {
            image = imageOptional.get();
        } else {
            LOGGER.info("Can't find image by url " + url);
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
        }
        return imageConverter.convertImageToDTO(image);
    }


    @Override
    public Boolean addNewImage(ImageDTO dto) throws CreateDataFailException {
        boolean result;
        try {
            Image image = imageConverter.convertImageToDTO(dto);
            image.setCreateDate(LocalDateTime.now());
            imageRepository.save(image);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when  create new image " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_IMAGE_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateImage(ImageDTO dto) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            Optional<Image> imageOptional = imageRepository.findById(dto.getId());
            if (!imageOptional.isPresent())  {
                LOGGER.info("Can't find image by id " + dto.getId());
                throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
            }
            Image image = imageConverter.convertImageToDTO(dto);
            image.setCreateDate(imageOptional.get().getCreateDate());
            image.setUpdateDate(LocalDateTime.now());
            imageRepository.save(image);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update image " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteImage(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        try {
            Optional<Image> imageOptional = imageRepository.findById(id);
            if (!imageOptional.isPresent()) {
                LOGGER.info("Can't find image by id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
            }
            imageRepository.deleteImage(id);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete image " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_IMAGE_FAIL);
        }
        return result;
    }

    @Override
    public Boolean activeImage(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        try {
            Optional<Image> imageOptional = imageRepository.findById(id);
            if (!imageOptional.isPresent())  {
                LOGGER.info("Can't find image by id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
            }
            imageRepository.unDeleteImage(id);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update image " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_IMAGE_FAIL);
        }
        return result;
    }

}