package com.dailycodework.dream_shops.controller;
import com.dailycodework.dream_shops.Model.Image;
import com.dailycodework.dream_shops.dto.ImageDto;
import com.dailycodework.dream_shops.response.ApiResponse;
import com.dailycodework.dream_shops.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images")
public class ImageController {

    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files,@RequestParam Long productId){
        try {
            List<ImageDto> imagesDto = imageService.saveImages(files,productId);
            return ResponseEntity.ok(new ApiResponse("Upload Success",imagesDto));
        }catch (Exception e){
            ApiResponse apiResponse=new ApiResponse("Upload Failed",e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {

        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId,@RequestBody MultipartFile file){

        try{
            Image image=imageService.getImageById(imageId);
            if (image != null){
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Update success",null));
            }

        }catch (Exception e){
           ApiResponse apiResponse=new ApiResponse(e.getMessage(),null);
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Updated Failed",null));

    }


    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        try {
            Image image=imageService.getImageById(imageId);
            if(image != null) {
                imageService.deleteImageById(imageId);
              return ResponseEntity.ok(new ApiResponse("Delete success",null));
            }

        }catch (Exception e){
             ApiResponse apiResponse=new ApiResponse(e.getMessage(),null);
             return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Deleted Failed",null));
    }




}
