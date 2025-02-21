package gr.netmechanics.jmix.gplace;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jmix.gplace")
public class GPlaceProperties {

    private String apiKey;

    private String languageCode;

    public GPlaceProperties(
        final String apiKey,
        @DefaultValue("en") final String languageCode) {

        this.apiKey = apiKey;
        this.languageCode = languageCode;
    }
}
