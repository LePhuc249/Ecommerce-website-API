package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.Image.ImageDTO;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface ImageService {

    List<ImageDTO> getAllImage() throws DataNotFoundException;

    ImageDTO getImage(Long id) throws DataNotFoundException;

    ImageDTO getImageByURL(String url) throws DataNotFoundException;

    Boolean addNewImage(ImageDTO image) throws CreateDataFailException;

    Boolean updateImage(ImageDTO image) throws DataNotFoundException, UpdateDataFailException;

    Boolean deleteImage(Long id) throws DataNotFoundException, DeleteDataFailException;

    Boolean activeImage(Long id) throws DataNotFoundException, DeleteDataFailException;

}