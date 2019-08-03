package com.target.myretail.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "value",
        "currency_code"
})
public class ProductPrice {
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("value")
    public BigDecimal getValue(){ return value;}

    @JsonProperty("value")
    public void setValue(BigDecimal value){ this.value = value;}

    @JsonProperty("currency_code")
    public String getCurrencyCode(){ return currencyCode;}

    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode){ this.currencyCode = currencyCode;}

}
