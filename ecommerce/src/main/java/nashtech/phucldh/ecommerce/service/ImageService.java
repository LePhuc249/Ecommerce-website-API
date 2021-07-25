package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Image;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface ImageService {

    public List<Image> getAllImage() throws DataNotFoundException;

    public Image getImage(Long id) throws DataNotFoundException;

    public Image getImageByURL(String url) throws DataNotFoundException;

    public Boolean addNewImage(Image image) throws CreateDataFailException;

    public Boolean updateImage(Image image) throws DataNotFoundException, UpdateDataFailException;

    public Boolean deleteImage(Long id) throws DataNotFoundException, DeleteDataFailException;

    public Boolean activeImage(Long id) throws DataNotFoundException, DeleteDataFailException;

    public Boolean updateImageUrl(Long id, String url) throws DataNotFoundException, DeleteDataFailException;

}