
package com.target.myretail.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "is_po_box_prohibited",
    "box_percent_filled_by_volume",
    "box_percent_filled_by_weight",
    "box_percent_filled_display"
})
public class Fulfillment {

    @JsonProperty("is_po_box_prohibited")
    private Boolean isPoBoxProhibited;
    @JsonProperty("box_percent_filled_by_volume")
    private Double boxPercentFilledByVolume;
    @JsonProperty("box_percent_filled_by_weight")
    private Double boxPercentFilledByWeight;
    @JsonProperty("box_percent_filled_display")
    private Double boxPercentFilledDisplay;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("is_po_box_prohibited")
    public Boolean getIsPoBoxProhibited() {
        return isPoBoxProhibited;
    }

    @JsonProperty("is_po_box_prohibited")
    public void setIsPoBoxProhibited(Boolean isPoBoxProhibited) {
        this.isPoBoxProhibited = isPoBoxProhibited;
    }

    @JsonProperty("box_percent_filled_by_volume")
    public Double getBoxPercentFilledByVolume() {
        return boxPercentFilledByVolume;
    }

    @JsonProperty("box_percent_filled_by_volume")
    public void setBoxPercentFilledByVolume(Double boxPercentFilledByVolume) {
        this.boxPercentFilledByVolume = boxPercentFilledByVolume;
    }

    @JsonProperty("box_percent_filled_by_weight")
    public Double getBoxPercentFilledByWeight() {
        return boxPercentFilledByWeight;
    }

    @JsonProperty("box_percent_filled_by_weight")
    public void setBoxPercentFilledByWeight(Double boxPercentFilledByWeight) {
        this.boxPercentFilledByWeight = boxPercentFilledByWeight;
    }

    @JsonProperty("box_percent_filled_display")
    public Double getBoxPercentFilledDisplay() {
        return boxPercentFilledDisplay;
    }

    @JsonProperty("box_percent_filled_display")
    public void setBoxPercentFilledDisplay(Double boxPercentFilledDisplay) {
        this.boxPercentFilledDisplay = boxPercentFilledDisplay;
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
