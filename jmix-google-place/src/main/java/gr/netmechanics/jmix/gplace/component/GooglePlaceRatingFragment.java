package gr.netmechanics.jmix.gplace.component;

import java.util.List;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRatingRef;
import gr.netmechanics.jmix.gplace.data.GooglePlaceReviewRef;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.component.virtuallist.JmixVirtualList;
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
@StyleSheet("gr/netmechanics/jmix/gplace/component/google-place-rating-fragment.css")
@FragmentDescriptor("google-place-rating-fragment.xml")
public class GooglePlaceRatingFragment extends Fragment<VerticalLayout> {

    @Autowired
    private GPlaceService googlePlaceService;

    @Setter private String placeId;
    @Setter private String apiKey;
    @Setter private String languageCode;
    @Setter private boolean hideReviews;

    @ViewComponent private Div gprfRating;
    @ViewComponent private Div gprfRatingStars;
    @ViewComponent private JmixVirtualList<GooglePlaceReviewRef> gprfReviews;
    @ViewComponent private InstanceContainer<GooglePlaceRatingRef> ratingDc;

    private boolean rendered;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(final View.BeforeShowEvent event) {
        if (rendered) {
            return;
        }

        GooglePlaceRatingRef ref = googlePlaceService.getPlaceRating(placeId, languageCode, apiKey);

        if (ref != null) {
            ratingDc.setItem(ref);
            gprfRating.setText("%.1f".formatted(ref.getRating()));
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

    static void renderStars(final Div container, double rating) {
        for (int i = 1; i <= 5; i++) {
            Div star = new Div();
            star.addClassNames("gprf-star", "empty");

            if (rating >= i) {
                star.removeClassName("empty");
                star.addClassName("filled");

            } else if (rating >= i - 0.5) {
                star.addClassName("half");
            }

            container.add(star);
        }
    }
}
