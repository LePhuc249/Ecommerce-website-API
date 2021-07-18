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

    public void addNewImage(Image image) throws CreateDataFailException;

    public void updateImage(Image image) throws DataNotFoundException, UpdateDataFailException;

    public void deleteImage(Long id) throws DataNotFoundException, DeleteDataFailException;

    public void activeImage(Long id) throws DataNotFoundException, DeleteDataFailException;

    public void updateImageUrl(Long id, String url) throws DataNotFoundException, DeleteDataFailException;

}
