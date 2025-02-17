package gr.netmechanics.jmix.gplace.rest;

import java.util.Map;

import gr.netmechanics.jmix.gplace.rest.dto.TextSearchRequest;
import gr.netmechanics.jmix.gplace.rest.dto.TextSearchResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Panos Bariamis (pbaris)
 */
public interface GPlaceRestClient {

    @PostExchange("/v1/places:searchText")
    TextSearchResult searchPlace(@RequestBody final TextSearchRequest textSearchRequest,
                                 @RequestHeader final Map<String, String> headers);
}
