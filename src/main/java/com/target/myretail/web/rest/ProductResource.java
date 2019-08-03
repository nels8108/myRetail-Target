package com.target.myretail.web.rest;

import com.target.myretail.domain.Product;
import com.target.myretail.domain.ProductPrice;
import com.target.myretail.service.ProductService;
import com.target.myretail.util.ResponseUtil;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing Products
 */
@RestController
@RequestMapping("/api")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private final ProductService productService;

    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    /**
     * GET  /product/:id : get the "id" product.
     *
     * @param id the id of the product to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the product, or with status 404 (Not Found)
     */
    @GetMapping("/product/{id}")
    @Timed
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        log.debug("REST request to get product : {}", id);

        Optional<Product> product = productService.findOne(id);

        return ResponseUtil.wrapOrNotFound(product);
    }

    /**
     * PUT /product/:id save/update a ProductPrice in the database
     * @param id of the product to save
     * @return the saved productPrice in the database
     */
    @PutMapping("/product/{id}")
    @Timed
    public ResponseEntity<Product> saveProductPrice(@PathVariable String id, @RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to save/update product {}", product.getId());
        Product savedProduct = productService.save(product);
        return ResponseEntity.created(new URI("/api/product/"+savedProduct.getId())).body(savedProduct);
    }

}
