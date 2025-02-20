package gr.netmechanics.jmix.gplace.component;

import static gr.netmechanics.jmix.gplace.util.FragmentRenderUtil.renderStars;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import gr.netmechanics.jmix.gplace.data.GooglePlaceReviewRef;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.FragmentRenderer;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.view.ViewComponent;
import org.springframework.lang.NonNull;

/**
 * @author Panos Bariamis (pbaris)
 */
@RendererItemContainer("reviewDc")
@FragmentDescriptor("google-place-review-fragment.xml")
public class GooglePlaceReviewFragment extends FragmentRenderer<VerticalLayout, GooglePlaceReviewRef> {

    @ViewComponent private Div gprfReviewRating;
    @ViewComponent private Div gprfReviewRatingStars;

    private boolean rendered;

    @Override
    public void setItem(@NonNull final GooglePlaceReviewRef item) {
        super.setItem(item);

        if (!rendered) {
            gprfReviewRating.setText("%.1f".formatted(item.getRating()));
            renderStars(gprfReviewRatingStars, item.getRating());
            rendered = true;
        }
    }
}
