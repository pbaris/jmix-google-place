package gr.netmechanics.jmix.gplace;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "gplace")
public class GPlaceProperties {

    private String apiKey;

}
