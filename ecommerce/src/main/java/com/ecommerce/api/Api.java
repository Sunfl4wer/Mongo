package com.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.GenericResponse;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@RestController
public class Api {

  // API for "product" model
  @Autowired
  private ProductService productService;

  @GetMapping("/products")
  public GenericResponse getAllProduct() {

    return productService.getAllProduct();
  }

  @GetMapping("/products/{id}")
  public GenericResponse getProductById(@PathVariable long id) {
    
    return productService.getProductById(id);   
  }

  @GetMapping("/regexProductName")
  public GenericResponse getProductsByRegex(@RequestParam("regex") String regex) {
    
    return productService.getProductsByRegex(regex);
  }

  @GetMapping("/firstByProductName")
  public GenericResponse getFirstProductByName(@RequestParam("productName") String productName) {
    
    return productService.getFirstProductByName(productName);
  }

  @GetMapping("/customByProductName")
  public GenericResponse getCustomProductByName(@RequestParam("productName") String productName) {
    
    return productService.getCustomProductByName(productName);
  }

  @GetMapping("/allWithPage")
  public GenericResponse getPageByName(@RequestParam("pageNo") int pageNo, @RequestParam("noProductsPerPage") int noProductsPerPage) {
    
    return productService.getAllPage(pageNo, noProductsPerPage);
  }

  @PostMapping("/products")
  public GenericResponse createProduct(@RequestBody Product product) {

    return this.productService.createProduct(product);
  }

  @PutMapping("/products/{id}")
  public GenericResponse updateProduct(@PathVariable long id, @RequestBody Product product) {
    
    return this.productService.updateProduct(id, product);
  }

  @DeleteMapping("/products/{id}")
  public GenericResponse deleteProduct(@PathVariable long id) {
    return this.productService.deleteProduct(id);
  }

  // API for "category" model
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/categories")
  public GenericResponse getAllCategory() {

    return categoryService.getAllCategory();
  }

  @GetMapping("/categories/{id}")
  public GenericResponse getCategoryById(@PathVariable long id) {
    
    return categoryService.getCategoryById(id);
  }

  @PostMapping("/categories")
  public GenericResponse createCategory(@RequestBody Category category) {

    return this.categoryService.createCategory(category);
  }

  @PutMapping("/categories/{id}")
  public GenericResponse updateCategory(@PathVariable long id, @RequestBody Category category) {
    
    return this.categoryService.updateCategory(id, category);
  }

  @DeleteMapping("/categories/{id}")
  public GenericResponse deleteCategory(@PathVariable long id) {
    return this.categoryService.deleteCategory(id);
  }
}
