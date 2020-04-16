package com.ecommerce.service;

import com.ecommerce.common.GenericResponse;
import com.ecommerce.model.Category;

public interface CategoryService {

    GenericResponse createCategory(Category category);
    
    GenericResponse updateCategory(long id, Category category);

    GenericResponse getAllCategory();

    GenericResponse getCategoryById(long id);

    GenericResponse deleteCategory(long id);
}
