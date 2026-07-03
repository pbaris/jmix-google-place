package gr.netmechanics.jmix.gplace.util;

import java.util.Optional;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.server.streams.DownloadHandler;
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
    private static final String G_ICON_PATH = "/META-INF/resources/glogo.svg";

    public static void renderIcon(final boolean useGoogleIcon, final JmixImage<Object> icon, final GooglePlaceBaseRef ref) {
        if (useGoogleIcon) {
            icon.setSrc(DownloadHandler.forClassResource(FragmentRenderUtil.class, G_ICON_PATH));

        } else {
            String iconUri = ref.getIconMaskBaseUri();
            if (StringUtils.isNotBlank(iconUri)) {
                icon.setSrc(iconUri + ".svg");
                Optional.ofNullable(ref.getIconBackgroundColor())
                    .ifPresent(color -> icon.getStyle().setBackgroundColor(color));
            }
        }
    }

    public static void renderStars(final Div container, double rating) {
        for (int i = 1; i <= 5; i++) {
            Icon star;

            if (rating >= i) {
                star = VaadinIcon.STAR.create();

            } else if (rating >= i - 0.5) {
                star = VaadinIcon.STAR_HALF_LEFT_O.create();

            } else {
                star = VaadinIcon.STAR_O.create();
            }

            star.addClassNames("gprf-star");
            container.add(star);
        }
    }
}
