package gr.netmechanics.jmix.gplace.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<GooglePlace> searchPlaces(final String term, final Integer pageSize, final String apiKey) throws IllegalStateException {
        var actualApiKey = StringUtils.defaultIfBlank(apiKey, props.getApiKey());

        if (StringUtils.isBlank(term) || StringUtils.isBlank(actualApiKey)) {
            return Collections.emptyList();
        }

        var result = restClient.searchPlace(new TextSearchRequest(term, pageSize, props.getLanguageCode()), Map.of(
            "Content-Type", "application/json",
            "X-Goog-Api-Key", actualApiKey,
            "X-Goog-FieldMask", "places.id,places.displayName,places.formattedAddress"
        ));

        if (result == null) {
            return Collections.emptyList();
        }

        return Optional.ofNullable(result.getPlaces())
            .map(places -> places.stream().map(Place::toGooglePlace).toList())
            .orElseGet(Collections::emptyList);
    }
}
