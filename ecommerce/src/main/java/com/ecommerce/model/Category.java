package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {

  @Id
  private long id;
  
  @Indexed
  private String category;
  
  private long numberOfProducts;
  
  
  public Category() {}
  
  public Category(long id, String category, long numberOfProducts) {
    super();
    this.id = id;
    this.category = category;
    this.numberOfProducts = numberOfProducts;
  }
  
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }
  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public long getNumberOfProducts() {
    return this.numberOfProducts;
  }

  public void setNumberOfProducts(long numberOfProducts) {
    this.numberOfProducts = numberOfProducts;
  }
}
