package gr.netmechanics.jmix.gplace.component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.vaadin.flow.data.provider.Query;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.component.SupportsItemsFetchCallback;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
@RequiredArgsConstructor
class GooglePlacePickerFetchCallback implements SupportsItemsFetchCallback.FetchCallback<GooglePlaceRef, String> {
    private final GPlaceService gpService;
    private final String languageCode;
    private final String apiKey;

    private String searchTerm;
    private List<GooglePlaceRef> searchResults = Collections.emptyList();

    @NonNull
    @Override
    public Stream<GooglePlaceRef> fetch(final Query<GooglePlaceRef, String> query) {
        var queryTerm = query.getFilter().orElse(null);

        if (StringUtils.isNotBlank(queryTerm) && !queryTerm.equalsIgnoreCase(searchTerm)) {
            searchTerm = queryTerm;
            searchResults = gpService.searchPlaces(searchTerm, languageCode, query.getLimit(), apiKey);
        }

        return searchResults.stream()
            .skip(Math.min(0, query.getPage()));
    }
}
