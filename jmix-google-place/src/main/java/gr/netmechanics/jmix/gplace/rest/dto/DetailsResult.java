package gr.netmechanics.jmix.gplace.rest.dto;

/**
 * @author Panos Bariamis (pbaris)
 */
public record DetailsResult<T>(String apiKey, String languageCode, T place) {
}