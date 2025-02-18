package gr.netmechanics.jmix.gplace.component;

import com.vaadin.flow.data.renderer.LitRenderer;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.component.combobox.JmixComboBox;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Setter
public class GooglePlacePicker extends JmixComboBox<GooglePlace> {

    private String apiKey;

    @Override
    protected void initComponent() {
        super.initComponent();
        GPlaceService gpService = applicationContext.getBean(GPlaceService.class);

        setItemsFetchCallback(new GooglePlaceFetchCallback(gpService, apiKey));
        setItemLabelGenerator(GooglePlace::toString);
        setRenderer(LitRenderer.<GooglePlace>of("""
                <span style="font-weight: bold;">${item.name}</span> <small style="font-size:0.9em;color:#565477">${item.address}</small>
                """)
            .withProperty("name", GooglePlace::toString)
            .withProperty("address", GooglePlace::getAddress));
    }
}
