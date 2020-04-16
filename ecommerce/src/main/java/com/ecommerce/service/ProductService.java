package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.common.GenericResponse;
import com.ecommerce.model.Product;

@Service
public interface ProductService {

    GenericResponse createProduct(Product product);
    
    GenericResponse updateProduct(long id, Product product);

    GenericResponse getAllProduct();

    GenericResponse getProductById(long id);

    GenericResponse getProductsByRegex(String regex);

    GenericResponse getFirstProductByName(String productName);

    GenericResponse getCustomProductByName(String productName);

    GenericResponse getAllPage(int pageNo, int noProductsPerPage);

    GenericResponse deleteProduct(long id);
}
