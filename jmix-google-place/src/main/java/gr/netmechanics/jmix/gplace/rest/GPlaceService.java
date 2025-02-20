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
import gr.netmechanics.jmix.gplace.util.PlaceMapper;
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

    private static final String FIELDS_BASE = "id,displayName,googleMapsUri,iconBackgroundColor,iconMaskBaseUri";
    private static final String FIELDS_RATING = FIELDS_BASE + ",rating,reviews,userRatingCount";
    private static final String FIELDS_INFO = FIELDS_BASE + ",formattedAddress,internationalPhoneNumber,location,regularOpeningHours";

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
                .map(PlaceMapper::toGooglePlaceRef)
                .toList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public String getPlaceRatingRaw(final String placeId, final String languageCode, final String apiKey) {
        DetailsResult<String> result = fetchPlaceDetails(true, placeId, languageCode, apiKey, FIELDS_RATING);
        return result.place();
    }

    public GooglePlaceRatingRef getPlaceRating(final String placeId, final String languageCode, final String apiKey) {
        DetailsResult<Place> result = fetchPlaceDetails(false, placeId, languageCode, apiKey, FIELDS_RATING);

        Place place = result.place();
        if (place == null) {
            return null;
        }

        return PlaceMapper.toGooglePlaceRatingRef(place, result.apiKey(), result.languageCode());
    }

    public String getPlaceInfoRaw(final String placeId, final String languageCode, final String apiKey) {
        DetailsResult<String> result = fetchPlaceDetails(true, placeId, languageCode, apiKey, FIELDS_INFO);
        return result.place();
    }

    public GooglePlaceInfoRef getPlaceInfo(final String placeId, final String languageCode, final String apiKey) {
        DetailsResult<Place> result = fetchPlaceDetails(false, placeId, languageCode, apiKey, FIELDS_INFO);

        Place place = result.place();
        if (place == null) {
            return null;
        }

        return PlaceMapper.toGooglePlaceInfoRef(place, result.apiKey(), result.languageCode());
    }

    @SuppressWarnings("unchecked")
    private <T> DetailsResult<T> fetchPlaceDetails(final boolean useRaw, final String placeId, final String languageCode,
                                                   final String apiKey, final String fields) {

        var emptyResult = new DetailsResult<T>(null, null, null);
        var actualApiKey = StringUtils.defaultIfBlank(apiKey, props.getApiKey());

        if (StringUtils.isBlank(placeId) || StringUtils.isBlank(actualApiKey)) {
            return emptyResult;
        }

        var actualLanguageCode = StringUtils.defaultIfBlank(languageCode, props.getLanguageCode());
        var headers = Map.of("X-Goog-Api-Key", actualApiKey, "X-Goog-FieldMask", fields);

        try {
            return useRaw
                ? new DetailsResult<>(actualApiKey, actualLanguageCode, (T) restClient.placeDetailsRaw(placeId, actualLanguageCode, headers))
                : new DetailsResult<>(actualApiKey, actualLanguageCode, (T) restClient.placeDetails(placeId, actualLanguageCode, headers));

        } catch (Exception e) {
            return emptyResult;
        }
    }

    private record DetailsResult<T>(String apiKey, String languageCode, T place) {
    }
}
