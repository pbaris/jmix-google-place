package gr.netmechanics.jmix.gplace.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Panos Bariamis (pbaris)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record TextSearchResult(
    @JsonProperty("places")
    List<Place> places) {

}
