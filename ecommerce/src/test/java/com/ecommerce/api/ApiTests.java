package com.ecommerce.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;

import com.ecommerce.common.GenericResponse;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;


@SpringBootTest
@PropertySource("classpath:application.properties")
public class ApiTests {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void init() {

    productRepository.deleteAll();
    productRepository.save(new Product(0,"testProduct0", 3.14159265, 0, true));
    productRepository.save(new Product(1,"testduct1", 99, 1, false));
    productRepository.save(new Product(2,"ttoduct2", 65, 2, false));
    productRepository.save(new Product(3,"testProduct3", 0, 2, true));
    productRepository.save(new Product(4,"ttoduct4", 124.032, 4, false));

    categoryRepository.deleteAll();
    categoryRepository.save(new Category(0,"testCategory0", 1));
    categoryRepository.save(new Category(1,"testCategory1", 1));
    categoryRepository.save(new Category(2,"testCategory2", 2));
    categoryRepository.save(new Category(3,"testCategory3", 0));
    categoryRepository.save(new Category(4,"testCategory4", 1));
  }

  @Autowired
  Api api;
  
  /* ******************************
   *      "product" API tests     *
   ********************************/
  
  @DisplayName("deleteProduct test function")
  @Test
  void deleteProductTest() {

    // given
    GenericResponse responseSuccess = api.deleteProduct(0);
    GenericResponse responseNotFoundData = api.deleteProduct(10);
    
    //when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();

    // then
    assertEquals(errorCodeSuccess, 0);
    assertEquals(errorCodeNotFoundData, 2);
  }

  @DisplayName("createProduct test function")
  @Test
  void createProductTest() {
    
    // given
    api.deleteProduct(0);
    GenericResponse responseSuccess = api.createProduct(new Product(0, "testProduct0", 7, 0, true));
    GenericResponse responseExistedData = api.createProduct(new Product(0, "testProduct0", 7, 0, true));
    
    // when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeExistedData = responseExistedData.getErrorCode();
    Product product = (Product) responseSuccess.getData();
    
    // then
    assertEquals(errorCodeSuccess, 0);
    assertEquals(errorCodeExistedData, 1);
    assertEquals(product.getId(), 0);
    assertEquals(product.getProductName(), "testProduct0");
    assertEquals(product.getPricePerUnit(), 7);
    assertEquals(product.getCategoryId(), 0);
    assertEquals(product.isInStock(), true); 
  }

  @DisplayName("getAllProduct test function")
  @Test
  void getAllProductTest() {

    // given
    GenericResponse responseSuccess = api.getAllProduct();
    productRepository.deleteAll();
    GenericResponse responseNotFoundData = api.getAllProduct();
    // when
    List<Product> productsSuccess = (List<Product>) responseSuccess.getData();
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();

    // then 
    assertEquals((errorCodeSuccess), 0);
    assertEquals((productsSuccess.size()), 5);
    assertEquals((errorCodeNotFoundData), 2);
  }

  @DisplayName("getProductById test function")
  @Test
  void getProductByIdTest() {

    // given
    GenericResponse responseSuccess = api.getProductById(1L);
    GenericResponse responseNotFoundData = api.getProductById(10L);

    // when
    Product product = (Product) responseSuccess.getData();
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();

    // then
    assertEquals(errorCodeSuccess, 0);
    assertEquals(errorCodeNotFoundData, 2);
    assertEquals(product.getId(), 1);
    assertEquals(product.getProductName(), "testduct1");
    assertEquals(product.getPricePerUnit(), 99);
    assertEquals(product.getCategoryId(), 1);
    assertEquals(product.isInStock(), false); 
  }

  @DisplayName("getProductsByRegex test function")
  @Test
  void getProductsByRegexTest() {

    // given
    GenericResponse responseSuccess = api.getProductsByRegex("todu");
    GenericResponse responseNotFoundData = api.getProductsByRegex("xxx");

    // when
    List<Product> products = (List<Product>) responseSuccess.getData();
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();

    // then 
    assertEquals((errorCodeSuccess), 0);
    assertEquals((errorCodeNotFoundData), 2);
    assertEquals((products.size()), 2);
  }

  @DisplayName("getFirstProductByName test function")
  @Test
  void getFirstProductByNameTest() {
    // repository.save(new Product(4,"ttoduct4", 124.032, 4, false));
    // given
    GenericResponse responseSuccess = api.getFirstProductByName("ttoduct4");
    GenericResponse responseNotFoundData = api.getFirstProductByName("Ttoduct4");

    // when
    Product product = (Product) responseSuccess.getData();
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();

    // then
    assertEquals(errorCodeSuccess, 0);
    assertEquals(errorCodeNotFoundData, 2);
    assertEquals(product.getId(), 4);
    assertEquals(product.getPricePerUnit(), 124.032);
    assertEquals(product.getCategoryId(), 4);
    assertEquals(product.isInStock(), false); 
  }

  @DisplayName("getCustomProductByName test function")
  @Test
  void getCustomProductByNameTest() {
    // repository.save(new Product(4,"ttoduct4", 124.032, 4, false));
    // given
    GenericResponse response = api.getCustomProductByName("ttoduct4");

    // when
    Product product = (Product) response.getData();
    int errorCode = response.getErrorCode();

    // then
    assertEquals(errorCode, 0);
    assertEquals(product.getId(), 4);
    assertEquals(product.getPricePerUnit(), 124.032);
    assertEquals(product.getCategoryId(), 4);
    assertEquals(product.isInStock(), false); 
  }

  @DisplayName("getPageByName test function")
  @Test
  void getPageByNameTest() {
    // repository.save(new Product(4,"ttoduct4", 124.032, 4, false));
    // given
    GenericResponse responseSuccess = api.getPageByName(0,2);
    GenericResponse responseNotFoundData = api.getPageByName(4,2);

    // when
    Page<Product> page = (Page<Product>) responseSuccess.getData();
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();

    // then
    assertEquals(errorCodeSuccess, 0);
    assertEquals(errorCodeNotFoundData, 2);
    assertEquals(page.getSize(), 2); 
    assertEquals(page.getNumber(), 0);
  }

  @DisplayName("updateProduct test function")
  @Test
  void updateProductTest() {
    
    // given
    GenericResponse responseSuccess = api.updateProduct(0, new Product(0, "test0", 10, 0, false));
    GenericResponse responseNotFoundData = api.updateProduct(0, new Product(10, "tesuct0", 7, 0, true));
    
    // when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFoundData = responseNotFoundData.getErrorCode();
    Product product = (Product) responseSuccess.getData();
    
    // then
    assertEquals(errorCodeSuccess, 0);
    assertEquals(errorCodeNotFoundData, 2);
    assertEquals(product.getId(), 0);
    assertEquals(product.getProductName(), "test0");
    assertEquals(product.getPricePerUnit(), 10);
    assertEquals(product.getCategoryId(), 0);
    assertEquals(product.isInStock(), false); 
  }
  /* ******************************
   *      "Category" API tests     *
   ********************************/
  
  @DisplayName("deleteCategory test function")
  @Test
  void deleteCategory() {

    // given
    GenericResponse responseSuccess = api.deleteCategory(0L);
    GenericResponse responseNotFound = api.deleteCategory(10L);

    // when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFound = responseNotFound.getErrorCode();

    // then 
    assertEquals((errorCodeSuccess), 0);
    assertEquals((errorCodeNotFound), 2);
  }

  @DisplayName("updateCategory test function")
  @Test
  void updateCategory() {

    // given
    GenericResponse responseSuccess = api.updateCategory(0L, new Category(0, "testCategoryUpdate0", 1));
    GenericResponse responseNotFound = api.updateCategory(19L, new Category(19, "testCategoryUpdate0", 1));

    // when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeNotFound = responseNotFound.getErrorCode();

    // then 
    assertEquals((errorCodeSuccess), 0);
    assertEquals((errorCodeNotFound), 2);
  }

  @DisplayName("createCategory test function")
  @Test
  void createCategory() {

    // given
    api.deleteCategory(0);
    GenericResponse responseSuccess = api.createCategory(new Category(0, "testCategoryUpdate0", 1));
    GenericResponse responseExistingData = api.createCategory(new Category(0, "testCategoryUpdate0", 1));

    // when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    int errorCodeExistingData = responseExistingData.getErrorCode();

    // then 
    assertEquals((errorCodeSuccess), 0);
    assertEquals((errorCodeExistingData), 1);
  }

  @DisplayName("getAllCategory test function")
  @Test
  void getAllCategoryTest() {

    // given
    GenericResponse response = api.getAllCategory();

    // when
    List<Category> categories = (List<Category>) response.getData();
    int errorCode = response.getErrorCode();

    // then 
    assertEquals((errorCode), 0);
    assertEquals((categories.size()), 5);
  }

  @DisplayName("getCategoryById test function")
  @Test
  void getCategoryById() {

    // given
    GenericResponse responseSuccess = api.getCategoryById(0);
    GenericResponse responseNotFound = api.getCategoryById(19);

    // when
    int errorCodeSuccess = responseSuccess.getErrorCode();
    Category category = (Category) responseSuccess.getData();
    int errorCodeNotFound = responseNotFound.getErrorCode();

    // then 
    assertEquals((errorCodeSuccess), 0);
    assertEquals(category.getCategory(), "testCategory0");
    assertEquals(category.getNumberOfProducts(), 1);
    assertEquals((errorCodeNotFound), 2);
  }

}
