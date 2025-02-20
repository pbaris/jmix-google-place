package gr.netmechanics.jmix.gplace.util;

import java.util.Optional;

import com.vaadin.flow.component.html.Div;
import gr.netmechanics.jmix.gplace.data.GooglePlaceBaseRef;
import io.jmix.flowui.component.image.JmixImage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FragmentRenderUtil {

    public static void renderIcon(JmixImage<Object> icon, final GooglePlaceBaseRef ref) {
        String iconUri = ref.getIconMaskBaseUri();
        if (StringUtils.isNotBlank(iconUri)) {
            icon.setSrc(iconUri + ".svg");
            Optional.ofNullable(ref.getIconBackgroundColor())
                .ifPresent(color -> icon.getStyle().setBackgroundColor(color));
        }
    }

    public static void renderStars(final Div container, double rating) {
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
