package gr.netmechanics.jmix.gplace;

import gr.netmechanics.jmix.gplace.component.GooglePlacePicker;
import gr.netmechanics.jmix.gplace.kit.GooglePlacePickerLoader;
import io.jmix.core.annotation.JmixModule;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.flowui.FlowuiConfiguration;
import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@ConfigurationPropertiesScan
@JmixModule(dependsOn = {EclipselinkConfiguration.class, FlowuiConfiguration.class})
@PropertySource(name = "gr.netmechanics.jmix.gplace", value = "classpath:/gr/netmechanics/jmix/gplace/module.properties")
public class GPlaceConfiguration {

    @Bean
    public ComponentRegistration googlePlacePicker() {
        return ComponentRegistrationBuilder.create(GooglePlacePicker.class)
            .withComponentLoader("googlePlacePicker", GooglePlacePickerLoader.class)
            .build();
    }

    @Bean
    @ConditionalOnMissingBean(GPlacePropertiesProvider.class)
    public GPlacePropertiesProvider gplacePropertiesProvider(final GPlaceProperties properties) {
        return new GPlacePropertiesDefaultProvider(properties);
    }
}
