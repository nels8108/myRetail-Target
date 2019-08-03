
package com.target.myretail.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Comes exlcusively from the redsky API
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "price",
    "deep_red_labels",
    "available_to_promise_network",
    "item"
})
public class ProductInformation{

    @JsonProperty("deep_red_labels")
    private DeepRedLabels deepRedLabels;
    @JsonProperty("available_to_promise_network")
    private AvailableToPromiseNetwork availableToPromiseNetwork;
    @JsonProperty("item")
    private Item item;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("deep_red_labels")
    public DeepRedLabels getDeepRedLabels() {
        return deepRedLabels;
    }

    @JsonProperty("deep_red_labels")
    public void setDeepRedLabels(DeepRedLabels deepRedLabels) {
        this.deepRedLabels = deepRedLabels;
    }

    @JsonProperty("available_to_promise_network")
    public AvailableToPromiseNetwork getAvailableToPromiseNetwork() {
        return availableToPromiseNetwork;
    }

    @JsonProperty("available_to_promise_network")
    public void setAvailableToPromiseNetwork(AvailableToPromiseNetwork availableToPromiseNetwork) {
        this.availableToPromiseNetwork = availableToPromiseNetwork;
    }

    @JsonProperty("item")
    public Item getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(Item item) {
        this.item = item;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
