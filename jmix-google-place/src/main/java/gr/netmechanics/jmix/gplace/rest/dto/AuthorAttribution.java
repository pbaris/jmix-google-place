package gr.netmechanics.jmix.gplace.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Panos Bariamis (pbaris)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorAttribution(
    @JsonProperty("displayName")
    String displayName,

    @JsonProperty("uri")
    String uri,

    @JsonProperty("photoUri")
    String photoUri) {

}
