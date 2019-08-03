package com.target.myretail.repository;

import com.target.myretail.domain.Product;
import com.target.myretail.domain.ProductInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {
}
