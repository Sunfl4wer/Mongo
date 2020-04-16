package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {
  
  @Query("{ productName: { $regex : ?0, $options:'i' } }")
  List<Product> findByRegex(String regex);

  @Query("{ productName : '?0' }")
  Product findCustomByProductName(String productName);

  Product findFirstByProductName(String productName);
}
