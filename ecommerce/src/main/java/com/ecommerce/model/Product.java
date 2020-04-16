package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "products")
public class Product {

  @Id
  private long id;
  
  @Indexed(unique = true)
  private String productName;
  
  private double pricePerUnit;
  private long categoryId;
  private boolean inStock;

  public Product() {}

  public Product(long id, String productName, double pricePerUnit, long categoryId, boolean inStock) {
    super();
    this.id = id;
    this.productName = productName;
    this.pricePerUnit = pricePerUnit;
    this.categoryId = categoryId;
    this.inStock = inStock;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName (String productName) {
    this.productName = productName;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getPricePerUnit() {
    return this.pricePerUnit;
  }

  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  public long getCategoryId() {
    return this.categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public boolean isInStock() {
    return this.inStock;
  }
  
  public void setInStock(boolean inStock) {
    this.inStock = inStock;
  }
}
