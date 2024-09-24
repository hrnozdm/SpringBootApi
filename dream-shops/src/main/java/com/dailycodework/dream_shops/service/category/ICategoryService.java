package com.dailycodework.dream_shops.service.category;

import com.dailycodework.dream_shops.Model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
   void addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id);
}
