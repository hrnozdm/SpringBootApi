package com.dailycodework.dream_shops.service.image;

import com.dailycodework.dream_shops.Model.Image;
import com.dailycodework.dream_shops.repository.ImageRepository;
import com.dailycodework.dream_shops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService implements  IImageService{

    private final ImageRepository imageRepository;
    private final IProductService productService;
    @Override
    public Image getImageById(Long id) {
        return null;
    }

    @Override
    public void deleteImageById(Long id) {

    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {

    }
}
