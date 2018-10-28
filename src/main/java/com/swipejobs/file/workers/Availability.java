
package com.swipejobs.file.workers;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "dayIndex"
})
public class Availability {

    @JsonProperty("title")
    private String title;
    @JsonProperty("dayIndex")
    private Integer dayIndex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Availability() {
    }

    /**
     * 
     * @param dayIndex
     * @param title
     */
    public Availability(String title, Integer dayIndex) {
        super();
        this.title = title;
        this.dayIndex = dayIndex;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("dayIndex")
    public Integer getDayIndex() {
        return dayIndex;
    }

    @JsonProperty("dayIndex")
    public void setDayIndex(Integer dayIndex) {
        this.dayIndex = dayIndex;
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
