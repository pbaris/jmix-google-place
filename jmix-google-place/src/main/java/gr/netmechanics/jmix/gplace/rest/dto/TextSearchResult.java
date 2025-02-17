package gr.netmechanics.jmix.gplace.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
import lombok.Getter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextSearchResult {

    @JsonProperty("places")
    private List<GooglePlace> places;
}
