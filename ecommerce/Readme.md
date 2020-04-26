# SpringBootEssential-Assignment
## Requirements
Develop the services of a simple e-Commerce site
1. Category & product management with basic information.
2. Provide HTTP GET/POST/PUT/DELETE requests e.g:
   - Retrieve list of predefined categorires.
   - Add new product.
   - Update a specific product.
   - Get list of products with pagination.
   - Search products.
   - View product details.
   - Delete a product.
3. Develop JUnit test suite.
4. Define profiles e.g dev & qc
5. Generate Postman test suit & dev environment.

## Solution
1. Category & product management with basic information
   - Product model (see [Product.java](ecommerce/src/main/java/com/ecommerce/model/Product.java))
     - `{`<br/>
         `"id" : [id of the product - type: long],`<br/>
         `"productName" : [name of the product - type: String],`<br/>
         `"pricePerUnit" : [price per unit - type: double],`<br/>
         `"categoryId" : [the cateogory the product belongs - type: long],`<br/>
         `"inStock" : [tell if the product is still in stock or not - type: boolean]`<br/>
       `}`
       
    - Category model (see [Category.java](ecommerce/src/main/java/com/ecommerce/model/Category.java))
      - `{`<br/>
         `"id" : [id of the category - type: long],`<br/>
         `"category" : [name of the category - type: String],`<br/>
         `"numberOfProducts" : [number of products that belong to this category - type: int],`<br/>
        `}`
 2. Provide HTTP GET/POST/PUT/DELETE requests (see [Api.java](ecommerce/src/main/java/com/ecommerce/api/Api.java))
    - Retrieve list of predefined categorires.
      - `GET https://localhost:9090/ecommerce-demo/categories`
    - Add new product.
      - `POST https://localhost:9090/ecommerce-demo/products`
    - Update a specific product.
      - `PUT https://localhost:9090/ecommerce-demo/products/{id}`
    - Get list of products with pagination.
      - `GET https://localhost:9090/ecommerce-demo/allWithPage?pageNo={pageNumber}&noProductsPerPage={number of products per page}`
    - Search products.
      - `GET https://localhost:9090/ecommerce-demo/regexProductName?regex={regular expression (case insensitive)}`
      - `GET https://localhost:9090/ecommerce-demo/firstByProductName?productName={name of the product (exact and case sensitive)}`
    - View product details
      - `GET https://localhost:9090/ecommerce-demo/products/{id}`
    - Delete a product.
      - `DELETE https://localhost:9090/ecommerce-demo/products/{id}`
3. Develop JUnit test suite.
   - [x] [Database test.](ecommerce/src/test/java/com/ecommerce/repository/DatabaseTest.java)
   - [x] [ApplicationTests.](ecommerce/src/test/java/com/ecommerce/EcommerceApplicationTests.java)
   - [x] [Api tests. All the functions in API are tested.](ecommerce/src/test/java/com/ecommerce/api/ApiTests.java)
4. Define profiles e.g dev & qc
   - [__develoment__ environment properties](ecommerce/src/main/resouces/application.properties)
   - [__test__ environment properties](ecommerce/src/test/resouces/application.properties)
5. Generate Postman test suit & dev environment.
   - Postman test suit is provided in the [_Postman_](Postman) folder of the repository.
