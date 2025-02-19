package gr.netmechanics.jmix.gplace.component;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import gr.netmechanics.jmix.gplace.data.GooglePlaceInfoRef;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import io.jmix.flowui.view.ViewComponent;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Panos Bariamis (pbaris)
 */
@StyleSheet("gr/netmechanics/jmix/gplace/component/google-place-info-fragment.css")
@FragmentDescriptor("google-place-info-fragment.xml")
public class GooglePlaceInfoFragment extends Fragment<VerticalLayout> {

    @Autowired
    private GPlaceService googlePlaceService;

    @Setter private String placeId;
    @Setter private String apiKey;
    @Setter private String languageCode;

    @ViewComponent private Div gpifOpeningHours;
    @ViewComponent private InstanceContainer<GooglePlaceInfoRef> infoDc;

    private boolean rendered;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(final View.BeforeShowEvent event) {
        if (rendered) {
            return;
        }

        GooglePlaceInfoRef ref = googlePlaceService.getPlaceInfo(placeId, languageCode, apiKey);

        if (ref != null) {
            infoDc.setItem(ref);
            ref.getOpeningHours().forEach(oh -> gpifOpeningHours.add(new Div(oh)));

            rendered = true;

        } else {
            setVisible(false);
        }
    }
}