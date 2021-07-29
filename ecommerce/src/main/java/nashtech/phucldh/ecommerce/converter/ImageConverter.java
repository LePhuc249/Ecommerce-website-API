package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.Image.ImageDTO;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ImageRepository imageRepository;

    public ImageDTO convertImageToDTO(Image image) {
        try {
            ImageDTO dtoImage = modelMapper.map(image, ImageDTO.class);
            return dtoImage;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Image to ImageDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Image convertImageToDTO(ImageDTO dtoImage) {
        try {
            Image image = modelMapper.map(dtoImage, Image.class);
            return image;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Image to ImageDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}