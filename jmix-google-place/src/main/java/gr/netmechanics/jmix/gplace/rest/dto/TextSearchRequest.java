package gr.netmechanics.jmix.gplace.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Panos Bariamis (pbaris)
 */
public record TextSearchRequest(@JsonProperty("textQuery") String term) {

}
