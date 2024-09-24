package com.dailycodework.dream_shops.service.category;

import com.dailycodework.dream_shops.Model.Category;
import com.dailycodework.dream_shops.exception.AlreadyExistsException;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category){

        if (categoryRepository.findByName(category.getName())!=null){
            throw new AlreadyExistsException(category.getName()+"Already Exists");
        }
        categoryRepository.save(category);
    }


    @Override
    public Category updateCategory(Category category, Long id) {

        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return  categoryRepository.save(oldCategory);
        }).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public void deleteCategoryById(Long id) {
          categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,()->{
              throw new ResourceNotFoundException("Category Not Found");
          });
    }
}
