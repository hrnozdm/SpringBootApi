package com.dailycodework.dream_shops.service.image;
import com.dailycodework.dream_shops.Model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    Image saveImage(MultipartFile file,Long productId);
    void updateImage(MultipartFile file,Long imageId);

}

