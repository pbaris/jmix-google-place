package gr.netmechanics.jmix.gplace.rest.dto;

/**
 * @author Panos Bariamis (pbaris)
 */
public record DetailsResult<T>(String apiKey, String languageCode, String placeId, T place) {

    public boolean isEmpty() {
        return place == null;
    }
}