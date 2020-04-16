package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.common.ErrorCode;
import com.ecommerce.common.GenericResponse;
import com.ecommerce.common.LogExecutionStatus;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  @LogExecutionStatus
  public GenericResponse createProduct(Product product) {
    if (productRepository.findById(product.getId()).isEmpty() == true) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), productRepository.save(product));
    } else {
      return new GenericResponse(ErrorCode.EXISTING_DATA.getCode(), productRepository.findById(product.getId()));
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse updateProduct(long id, Product product) {
    Optional<Product> productDb = this.productRepository.findById(id);

    if (productDb.isPresent() && product.getProductName() != null && product.getId() == id) {

      Product productUpdate = productDb.get();
      productUpdate.setCategoryId(product.getCategoryId());
      productUpdate.setId(product.getId());
      productUpdate.setInStock(product.isInStock());
      productUpdate.setId(product.getId());
      productUpdate.setProductName(product.getProductName());
      productUpdate.setPricePerUnit(product.getPricePerUnit());

      return new GenericResponse(ErrorCode.SUCCESS.getCode(), productUpdate);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), product);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getAllProduct() {
    List<Product> products = productRepository.findAll();
    
    if (products.isEmpty()) {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), products);
    } else {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), products);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getProductById(long id) {
    
    Optional<Product> productDb = this.productRepository.findById(id);

    if (productDb.isPresent()) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), productDb.get());
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), null);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse deleteProduct(long id) {
    
    Optional<Product> productDb = this.productRepository.findById(id);

    if(productDb.isPresent()) {
      this.productRepository.deleteById(id);
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), null);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), null);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getProductsByRegex(String regex) {
    List<Product> productDb = this.productRepository.findByRegex(regex);

    if (!productDb.isEmpty()) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), productDb);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), null);
    }
  }


  @Override
  @LogExecutionStatus
  public GenericResponse getFirstProductByName(String productName) {
    Product product = this.productRepository.findFirstByProductName(productName);

    if (product != null) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), product);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), product);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getCustomProductByName(String productName) {
    Product product = this.productRepository.findCustomByProductName(productName);

    if (product != null) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), product);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), product);
    }
  }

  @Override
  @LogExecutionStatus
  public GenericResponse getAllPage(int pageNo, int noProductPerPage) {
    Pageable pageable = PageRequest.of(pageNo, noProductPerPage);
    Page<Product> productPage = this.productRepository.findAll(pageable);

    if (productPage.hasContent()) {
      return new GenericResponse(ErrorCode.SUCCESS.getCode(), productPage);
    } else {
      return new GenericResponse(ErrorCode.NOT_FOUND_DATA.getCode(), productPage);
    }
  }
}
