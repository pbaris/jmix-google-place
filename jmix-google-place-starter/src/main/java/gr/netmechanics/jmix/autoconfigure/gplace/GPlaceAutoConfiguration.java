package gr.netmechanics.jmix.autoconfigure.gplace;

import gr.netmechanics.jmix.gplace.GPlaceConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({GPlaceConfiguration.class})
public class GPlaceAutoConfiguration {
}

