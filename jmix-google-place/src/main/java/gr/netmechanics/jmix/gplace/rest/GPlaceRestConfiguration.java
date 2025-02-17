package gr.netmechanics.jmix.gplace.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author Panos Bariamis (pbaris)
 */
@Configuration("gplace_GPlaceRestConfiguration")
public class GPlaceRestConfiguration {

    @Bean(name = "gplace_GPlaceRestClient")
    public GPlaceRestClient googlePlaceRestClient() {
        RestClient client = RestClient.builder()
            .baseUrl("https://places.googleapis.com")
            .build();

        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client))
            .build()
            .createClient(GPlaceRestClient.class);
    }
}
