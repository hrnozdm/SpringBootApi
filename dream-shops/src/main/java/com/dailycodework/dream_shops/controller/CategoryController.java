package com.dailycodework.dream_shops.controller;
import com.dailycodework.dream_shops.Model.Category;
import com.dailycodework.dream_shops.response.ApiResponse;
import com.dailycodework.dream_shops.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final ICategoryService categoryService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try{
           List<Category> categories=categoryService.getAllCategories();
            return  ResponseEntity.ok(new ApiResponse("Found!", categories));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
        }
    }


    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){

        try {
            Category categoryExist=categoryService.getCategoryByName(category.getName());

            if (categoryExist != null){
                return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Already Exist",null));
            }

            else {
                categoryService.addCategory(category);
                return ResponseEntity.ok(new ApiResponse("Added Category",null));
            }

        }catch (Exception e){
            ApiResponse apiResponse = new ApiResponse("No category added",null);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){

        try{
            Category category=categoryService.getCategoryById(id);
            if (category != null) {
                categoryService.deleteCategoryById(id);
                return ResponseEntity.ok(new ApiResponse("Category is deleted",category.getName()));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category Not Found",null));
            }
        }catch(Exception e){
            ApiResponse apiResponse = new ApiResponse("Category is not deleted",null);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }

    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
        try{
           Category categoryExist=categoryService.getCategoryById(categoryId);
            if(categoryExist != null) {
              categoryService.updateCategory(category,categoryId);
              return ResponseEntity.ok(new ApiResponse("Updated Category",category));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Category Not Found",null));
            }

        }catch (Exception e){
            ApiResponse apiResponse = new ApiResponse("Category is not updated",null);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }



}
