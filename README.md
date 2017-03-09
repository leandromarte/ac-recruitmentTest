# Avenue code Aplication

This is my implementation of coding test for Avenue Code's Java developer. Since the scenario does not specifies all tecnologies i should use, i tried to keep it as simple as possible, but no simpler. My focus was to use the java specs such JAX-RS instead of springboot.

## How to test, compile and run 

All commands should be runned inside the app directory. 

### To run tests

`
/Products/ mvn test
`

### To build and run

```
/Products/ mvn clean compile assembly:single
/Products/target/ java -jar products-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

### Examples of calls

**__VERB__** http://localhost:8080/...
```
{}
```

**GET** http://localhost:8080/product
Returns all products


**GET** http://localhost:8080/product/{id}
Returns a specific product


**GET** http://localhost:8080/product/{id}/subproducts
Returns all subproducts from a specific product


**GET** http://localhost:8080/product/{id}/images
Returns all images from a specific product


**GET** http://localhost:8080/product/with/?\[images=true\]\[&subproducts=true\]
Return all products with images and/or subproducts. Parameters are optional


**GET** http://localhost:8080/product/{id}/with/?\[images=true\]\[&subproducts=true\]
Return a specific product with images and/or subproducts. Parameters are optional


**POST** http://localhost:8080/product
```
{
	"name": "name of product",
	"description": "description for product"
}
```

**PUT** http://localhost:8080/product/{id}
```
{
	"name": "updated name of product",
	"description": "updated description for product"
}

{
	"name": "updated name of product"
}

{
	"description": "updated description for product"
}
```

**DELETE** http://localhost:8080/product/{id}



## Additional comments

### Should be revisited in future:
* Dependency injection 
* Parameterized queries 
* Increase test coverage by testing controller methods
