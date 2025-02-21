package gr.netmechanics.jmix.gplace.component;

import static gr.netmechanics.jmix.gplace.util.FragmentRenderUtil.renderIcon;

import java.util.Optional;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import gr.netmechanics.jmix.gplace.data.GooglePlaceInfoRef;
import gr.netmechanics.jmix.gplace.rest.GPlaceService;
import io.jmix.flowui.component.image.JmixImage;
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
@StyleSheet("gr/netmechanics/jmix/gplace/component/google-place-info-fragment.css")
@FragmentDescriptor("google-place-info-fragment.xml")
public class GooglePlaceInfoFragment extends Fragment<Div> {

    @Autowired
    private GPlaceService googlePlaceService;

    @Setter private String placeId;
    @Setter private String apiKey;
    @Setter private String languageCode;
    @Setter private boolean hideMap;
    @Setter private boolean hideOpeningHours;
    @Setter private boolean useDefaultIcon = true;
    @Setter private int zoom = 14;
    @Setter private String mapType = "roadmap";

    @ViewComponent private IFrame gpifMap;
    @ViewComponent private Div gpifOpeningHours;
    @ViewComponent private JmixButton gpifViewMap;
    @ViewComponent private JmixImage<Object> gpifIcon;
    @ViewComponent private FormLayout.FormItem gpifOpeningHoursLabel;
    @ViewComponent private InstanceContainer<GooglePlaceInfoRef> infoDc;

    private boolean rendered;
    private String mapUrl;

    @Subscribe(target = Target.HOST_CONTROLLER)
    public void onHostBeforeShow(final View.BeforeShowEvent event) {
        if (rendered) {
            return;
        }

        GooglePlaceInfoRef ref = googlePlaceService.getPlaceInfo(placeId, languageCode, apiKey);

        if (ref != null) {
            infoDc.setItem(ref);

            // render Opening Hours
            if (hideOpeningHours) {
                gpifOpeningHoursLabel.setVisible(false);

            } else {
                ref.getOpeningHours().forEach(oh -> gpifOpeningHours.add(new Div(oh)));
            }

            // render View on Map button
            Optional.ofNullable(ref.getMapUrl())
                .ifPresentOrElse(url -> mapUrl = url, () -> gpifViewMap.setVisible(false));

            if (!useDefaultIcon) {
                renderIcon(gpifIcon, ref);
            }

            renderMap(ref);
            rendered = true;

        } else {
            setVisible(false);
        }
    }

    private void renderMap(final GooglePlaceInfoRef ref) {
        if (hideMap) {
            gpifMap.setVisible(false);
            return;
        }

        gpifMap.setSrc("https://www.google.com/maps/embed/v1/place?key=%s&q=place_id:%s&language=%s&zoom=%d&maptype=%s"
            .formatted(ref.getApiKey(), ref.getId(), ref.getLanguageCode(), zoom, mapType));

        gpifMap.getElement()
            .setAttribute("referrerpolicy", "no-referrer-when-downgrade")
            .setAttribute("allowfullscreen", true)
            .setAttribute("frameborder", "0");
    }

    @Subscribe(id = "gpifViewMap", subject = "clickListener")
    public void onGpifViewMapClick(final ClickEvent<JmixButton> event) {
        event.getSource().getUI().ifPresent(ui -> ui.getPage().open(mapUrl, "_blank"));
    }
}