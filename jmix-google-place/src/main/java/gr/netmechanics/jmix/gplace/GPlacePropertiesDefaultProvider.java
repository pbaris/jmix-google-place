package gr.netmechanics.jmix.gplace;

import lombok.RequiredArgsConstructor;

/**
 * @author Panos Bariamis (pbaris)
 */
@RequiredArgsConstructor
public class GPlacePropertiesDefaultProvider implements GPlacePropertiesProvider {

    private final GPlaceProperties properties;

    public String getApiKey() {
        return properties.getApiKey();
    }

    @Override
    public String getLanguageCode() {
        return properties.getLanguageCode();
    }
}
