package gr.netmechanics.jmix.gplace.rest;

import static gr.netmechanics.jmix.gplace.GPlaceConstants.KEY_CACHE_G_PLACE_INFO;
import static gr.netmechanics.jmix.gplace.GPlaceConstants.KEY_CACHE_G_PLACE_RATING;

import java.util.Map;

import gr.netmechanics.jmix.gplace.rest.dto.TextSearchRequest;
import gr.netmechanics.jmix.gplace.rest.dto.TextSearchResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Panos Bariamis (pbaris)
 */
public interface GPlaceRestClient {

    @Cacheable(KEY_CACHE_G_PLACE_RATING)
    @PostExchange("/v1/places:searchText")
    TextSearchResult searchPlace(@RequestBody final TextSearchRequest textSearchRequest,
                                 @RequestHeader final Map<String, String> headers);

    @Cacheable(KEY_CACHE_G_PLACE_INFO)
    @GetExchange("/v1/places/{placeId}")
    String placeDetails(@PathVariable("placeId") final String placeId,
                        @RequestParam(name = "languageCode") final String languageCode,
                        @RequestHeader final Map<String, String> headers);
}
