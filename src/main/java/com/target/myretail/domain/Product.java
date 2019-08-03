package com.target.myretail.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Not to be confused with ProductInformation, in this API, the product entity only contains the ID, Name, and ProductPrice
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "current_price",
})
public class Product {

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    public String getName(){ return name;}

    @JsonProperty("name")
    public void setName(String name){ this.name = name;}

    @JsonProperty("id")
    public Long getId(){ return id;}

    @JsonProperty("id")
    public void setId(Long id){ this.id = id;}

    @JsonProperty("current_price")
    private ProductPrice productPrice;

    @JsonProperty("current_price")
    public ProductPrice getProductPrice(){return productPrice;}

    @JsonProperty("current_price")
    public void setProductPrice(ProductPrice productPrice){this.productPrice = productPrice;}


}