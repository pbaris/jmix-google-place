package gr.netmechanics.jmix.gplace.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import gr.netmechanics.jmix.gplace.GPlacePropertiesProvider;
import gr.netmechanics.jmix.gplace.data.GooglePlaceInfoRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRatingRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;
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

    public List<GooglePlaceRef> searchPlaces(final String term, final String languageCode, final Integer pageSize, final String apiKey) {
        var actualApiKey = StringUtils.defaultIfBlank(apiKey, props.getApiKey());

        if (StringUtils.isBlank(term) || StringUtils.isBlank(actualApiKey)) {
            return Collections.emptyList();
        }

        var actualLanguageCode = StringUtils.defaultIfBlank(languageCode, props.getLanguageCode());

        try {
            var result = restClient.searchPlace(new TextSearchRequest(term, pageSize, actualLanguageCode), Map.of(
                "Content-Type", "application/json",
                "X-Goog-Api-Key", actualApiKey,
                "X-Goog-FieldMask", "places.id,places.displayName,places.formattedAddress,places.location"));

            return result.places().stream()
                .map(Place::toGooglePlaceRef)
                .toList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public String getPlaceRatingRaw(final String placeId, final String languageCode, final String apiKey) {
        return fetchPlaceDetails(true, placeId, languageCode, apiKey,
            "id,displayName,rating,googleMapsUri,reviews,userRatingCount");
    }

    public GooglePlaceRatingRef getPlaceRating(final String placeId, final String languageCode, final String apiKey) {
        Place place = fetchPlaceDetails(false, placeId, languageCode, apiKey,
            "id,displayName,rating,googleMapsUri,reviews,userRatingCount");

        return place != null ? place.toGooglePlaceRatingRef() : null;
    }

    public String getPlaceInfoRaw(final String placeId, final String languageCode, final String apiKey) {
        return fetchPlaceDetails(true, placeId, languageCode, apiKey,
            "id,displayName,formattedAddress,internationalPhoneNumber,location,googleMapsUri,regularOpeningHours");
    }

    public GooglePlaceInfoRef getPlaceInfo(final String placeId, final String languageCode, final String apiKey) {
        Place place = fetchPlaceDetails(false, placeId, languageCode, apiKey,
            "id,displayName,formattedAddress,internationalPhoneNumber,location,googleMapsUri,regularOpeningHours");

        return place != null ? place.toGooglePlaceInfoRef() : null;
    }

    @SuppressWarnings("unchecked")
    private <T> T fetchPlaceDetails(final boolean useRaw, final String placeId, final String languageCode,
                                     final String apiKey, final String fields) {

        var actualApiKey = StringUtils.defaultIfBlank(apiKey, props.getApiKey());

        if (StringUtils.isBlank(placeId) || StringUtils.isBlank(actualApiKey)) {
            return null;
        }

        var actualLanguageCode = StringUtils.defaultIfBlank(languageCode, props.getLanguageCode());
        var headers = Map.of("X-Goog-Api-Key", actualApiKey, "X-Goog-FieldMask", fields);

        try {
            return useRaw
                ? (T) restClient.placeDetailsRaw(placeId, actualLanguageCode, headers)
                : (T) restClient.placeDetails(placeId, actualLanguageCode, headers);

        } catch (Exception e) {
            return null;
        }
    }
}
