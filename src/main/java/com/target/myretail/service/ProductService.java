package com.target.myretail.service;

import com.target.myretail.domain.Product;
import com.target.myretail.domain.ProductInformation;
import com.target.myretail.domain.ProductInformationWrapper;
import com.target.myretail.domain.ProductPrice;
import com.target.myretail.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    private RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate){
        this.productRepository = productRepository;
        Product product = new Product();
        ProductPrice productPrice = new ProductPrice();
        productPrice.setCurrencyCode("USD");
        productPrice.setValue(BigDecimal.valueOf(13.99));
        product.setProductPrice(productPrice);
        product.setId(13860428L);
        this.productRepository.save(product);
        this.restTemplate = restTemplate;
    }


    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Product> findOne(String id) {
        log.debug("Request to get ProductInformation : {}", id);
        Optional<Product> product = productRepository.findById(Long.valueOf(id));
        ProductInformation apiResponse = getFromApi(id);
        product.ifPresent(product1 -> product1.setName(apiResponse.getItem().getProductDescription().getTitle()));

        return product;
    }

    /**
     *
     * @param id of the product
     * @return the information of the product sans the wrapper object it came in
     */

    private ProductInformation getFromApi(String id){
        RestTemplate restTemplate = new RestTemplate();
        //TODO externalize this URL and the different URL components
        ResponseEntity<ProductInformationWrapper> responseEntity = restTemplate.getForEntity("https://redsky.target.com/v2/pdp/tcin/"+id+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", ProductInformationWrapper.class, "");
        return responseEntity.getBody().getProductInformation();
    }

    /**
     * Save a productInformation.
     *
     * @param product the entity to save
     * @return the persisted entity
     */

    public Product save(Product product) {
        log.debug("Request to save Product: {}", product.getId());
        return productRepository.save(product);
    }
}
