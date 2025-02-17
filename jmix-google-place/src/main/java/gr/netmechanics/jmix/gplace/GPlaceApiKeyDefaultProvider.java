package gr.netmechanics.jmix.gplace;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Panos Bariamis (pbaris)
 */
@RequiredArgsConstructor
@Component("gplace_GPlaceApiKeyDefaultProvider")
public class GPlaceApiKeyDefaultProvider implements GPlaceApiKeyProvider {

    private final GPlaceProperties properties;

    public String getApiKey() {
        return properties.getApiKey();
    }
}
