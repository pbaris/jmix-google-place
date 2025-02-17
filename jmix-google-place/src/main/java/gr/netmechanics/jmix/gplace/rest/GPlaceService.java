package gr.netmechanics.jmix.gplace.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import gr.netmechanics.jmix.gplace.GPlaceApiKeyProvider;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
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

    private final GPlaceApiKeyProvider apiKeyProvider;
    private final GPlaceRestClient restClient;

    public List<GooglePlace> searchPlaces(final String term, final String apiKey) throws IllegalStateException {
        var actualApiKey = StringUtils.firstNonBlank(apiKey, apiKeyProvider.getApiKey());

        if (StringUtils.isBlank(term) || StringUtils.isBlank(actualApiKey)) {
            return Collections.emptyList();
        }

        var result = restClient.searchPlace(new TextSearchRequest(term), Map.of(
            "Content-Type", "application/json",
            "X-Goog-Api-Key", actualApiKey,
            "X-Goog-FieldMask", "places.id,places.displayName,places.formattedAddress"
        ));

        return Optional.ofNullable(result)
            .map(r -> Optional.ofNullable(result.getPlaces()).orElseGet(Collections::emptyList))
            .orElseGet(Collections::emptyList);
    }
}
