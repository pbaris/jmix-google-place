package gr.netmechanics.jmix.gplace.component;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.Target;
import io.jmix.flowui.view.View;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Panos Bariamis (pbaris)
 */
@FragmentDescriptor("google-place-rating-fragment.xml")
public class GooglePlaceRatingFragment extends Fragment<VerticalLayout> {

    @Autowired
    private GPlaceService googlePlaceService;

    @Setter
    private String placeId;

    @Setter
    private String apiKey;

    @Setter
    private String languageCode;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(final View.BeforeShowEvent event) {
        String placeRatings = googlePlaceService.getPlaceRatings(placeId, languageCode, apiKey);
    }
}
