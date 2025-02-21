package gr.netmechanics.jmix.gplace.component;

import static gr.netmechanics.jmix.gplace.util.FragmentRenderUtil.renderIcon;
import static gr.netmechanics.jmix.gplace.util.FragmentRenderUtil.renderStars;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRatingRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceReviewRef;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.component.image.JmixImage;
import io.jmix.flowui.component.virtuallist.JmixVirtualList;
import io.jmix.flowui.fragment.Fragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.kit.component.button.JmixButton;
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
@StyleSheet("gr/netmechanics/jmix/gplace/component/google-place-rating-fragment.css")
@FragmentDescriptor("google-place-rating-fragment.xml")
public class GooglePlaceRatingFragment extends Fragment<VerticalLayout> {

    @Autowired
    private GPlaceService googlePlaceService;

    @Setter private String placeId;
    @Setter private String apiKey;
    @Setter private String languageCode;
    @Setter private boolean hideReviews;
    @Setter private boolean useGoogleIcon = true;

    @ViewComponent private Div gprfRating;
    @ViewComponent private Div gprfRatingStars;
    @ViewComponent private JmixButton gprfViewMap;
    @ViewComponent private JmixImage<Object> gprfIcon;
    @ViewComponent private JmixVirtualList<GooglePlaceReviewRef> gprfReviews;
    @ViewComponent private InstanceContainer<GooglePlaceRatingRef> ratingDc;

    private boolean rendered;
    private String mapUrl;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(final View.BeforeShowEvent event) {
        if (rendered) {
            return;
        }

        GooglePlaceRatingRef ref = googlePlaceService.getPlaceRating(placeId, languageCode, apiKey);

        if (ref != null) {
            ratingDc.setItem(ref);
            gprfRating.setText("%.1f".formatted(ref.getRating()));

            if (!useGoogleIcon) {
                renderIcon(gprfIcon, ref);
            }

            // render View on Map button
            Optional.ofNullable(ref.getMapUrl())
                .ifPresentOrElse(url -> mapUrl = url, () -> gprfViewMap.setVisible(false));

            renderStars(gprfRatingStars, ref.getRating());
            renderReviews(ref.getReviews());
            rendered = true;

        } else {
            setVisible(false);
        }
    }

    private void renderReviews(final List<GooglePlaceReviewRef> reviews) {
        if (hideReviews || reviews.isEmpty()) {
            gprfReviews.setVisible(false);

        } else {
            gprfReviews.setItems(reviews);
        }
    }

    @Subscribe(id = "gprfViewMap", subject = "clickListener")
    public void onGprfViewMapClick(final ClickEvent<JmixButton> event) {
        event.getSource().getUI().ifPresent(ui -> ui.getPage().open(mapUrl, "_blank"));
    }
}
