package gr.netmechanics.jmix.gplace.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplayName {

    @JsonProperty("text")
    private String text;
}
