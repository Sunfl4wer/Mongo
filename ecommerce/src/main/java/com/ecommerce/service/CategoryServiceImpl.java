package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.common.ErrorCode;
import com.ecommerce.common.GenericResponse;
import com.ecommerce.common.LogExecutionStatus;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  @LogExecutionStatus
  public GenericResponse createCategory(Category category) {

    if (categoryRepository.findById(category.getId()).isEmpty() == true) {
      
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), categoryRepository.save(category));
    } else {

      return new GenericResponse(ErrorCode.EXISTING_DATA.getCode(), categoryRepository.findById(category.getId()));
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse updateCategory(long id, Category category) {

    Optional<Category> categoryDb = this.categoryRepository.findById(id); 
    if (categoryDb.isPresent() && category.getCategory() != null && id == category.getId()) {

      Category categoryUpdate = categoryDb.get();
      categoryUpdate.setId(category.getId());
      categoryUpdate.setCategory(category.getCategory());
      categoryUpdate.setNumberOfProducts(category.getNumberOfProducts());

      return new GenericResponse(ErrorCode.SUCCESS.getCode(), category);
    } else {

      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), category);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getAllCategory() {
    return new GenericResponse(ErrorCode.SUCCESS.getCode(), this.categoryRepository.findAll());
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getCategoryById(long id) {
    
    Optional<Category> categoryDb = this.categoryRepository.findById(id);

    if (!categoryDb.isEmpty()) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), categoryDb.get());
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), null);
    }
  }

  @Override
  public GenericResponse deleteCategory(long id) {
    
    Optional<Category> categoryDb = this.categoryRepository.findById(id);

    if(categoryDb.isPresent()) {
      this.categoryRepository.delete(categoryDb.get());
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), null);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), null);
    }
  }
}
