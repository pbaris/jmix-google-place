package gr.netmechanics.jmix.gplace.component;

import java.util.List;

import com.vaadin.flow.data.renderer.LitRenderer;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.component.combobox.JmixComboBox;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
public class GooglePlacePicker extends JmixComboBox<GooglePlace> {
    private GPlaceService gpService;

    private String searchTerm;
    private List<GooglePlace> searchResults;

    @Override
    protected void initComponent() {
        super.initComponent();
        this.gpService = applicationContext.getBean(GPlaceService.class);

        //TODO maybe get apikey declaratively also ???
        setItemsFetchCallback(query -> {
            var queryTerm = query.getFilter().orElse("");

            if (StringUtils.isBlank(searchTerm) || !searchTerm.equalsIgnoreCase(queryTerm)) {
                searchTerm = queryTerm;
                searchResults = gpService.searchPlaces(searchTerm, null);
            }

            return searchResults.stream()
                .skip(Math.min(0, query.getPage()))
                .limit(Math.max(100, query.getLimit()));
        });

        setItemLabelGenerator(GooglePlace::toString);
        setRenderer(LitRenderer.<GooglePlace>of("""
                <span style="font-weight: bold;">${item.name}</span> <small style="font-size:0.9em;color:#565477">${item.address}</small>
                """)
            .withProperty("name", GooglePlace::toString)
            .withProperty("address", GooglePlace::getAddress)
        );
    }
}
