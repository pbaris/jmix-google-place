package gr.netmechanics.jmix.gplace.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import gr.netmechanics.jmix.gplace.GPlacePropertiesProvider;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
import gr.netmechanics.jmix.gplace.rest.dto.Place;
import gr.netmechanics.jmix.gplace.rest.dto.TextSearchRequest;
import io.jmix.core.entity.annotation.SystemLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Panos Bariamis (pbaris)
 */
@SystemLevel
@RequiredArgsConstructor
@Service("gplace_GPlaceService")
public class GPlaceService {

    private final GPlacePropertiesProvider props;
    private final GPlaceRestClient restClient;

    public List<GooglePlace> searchPlaces(final String term, final String languageCode, final Integer pageSize, final String apiKey) {
        var actualApiKey = StringUtils.defaultIfBlank(apiKey, props.getApiKey());

        if (StringUtils.isBlank(term) || StringUtils.isBlank(actualApiKey)) {
            return Collections.emptyList();
        }

        var actualLanguageCode = StringUtils.defaultIfBlank(languageCode, props.getLanguageCode());

        try {
            var result = restClient.searchPlace(new TextSearchRequest(term, pageSize, actualLanguageCode), Map.of(
                "Content-Type", "application/json",
                "X-Goog-Api-Key", actualApiKey,
                "X-Goog-FieldMask", "places.id,places.displayName,places.formattedAddress"));

            return result.getPlaces().stream()
                .map(Place::toGooglePlace)
                .toList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public String getPlaceRatings(final String placeId, final String languageCode, final String apiKey) {
        return fetchPlaceDetails(placeId, languageCode, apiKey,
            "id,displayName,rating,googleMapsUri,reviews,userRatingCount");
    }

    public String getPlaceInfo(final String placeId, final String languageCode, final String apiKey) {
        return fetchPlaceDetails(placeId, languageCode, apiKey,
            "id,displayName,formattedAddress,internationalPhoneNumber,location,googleMapsUri,regularOpeningHours");
    }

    private String fetchPlaceDetails(final String placeId, final String languageCode, final String apiKey, final String fields) {
        var actualApiKey = StringUtils.defaultIfBlank(apiKey, props.getApiKey());

        if (StringUtils.isBlank(placeId) || StringUtils.isBlank(actualApiKey)) {
            return null;
        }

        var actualLanguageCode = StringUtils.defaultIfBlank(languageCode, props.getLanguageCode());

        try {
            return restClient.placeDetails(placeId, actualLanguageCode, Map.of(
                "X-Goog-Api-Key", actualApiKey,
                "X-Goog-FieldMask", fields));

        } catch (Exception e) {
            return null;
        }
    }
}
