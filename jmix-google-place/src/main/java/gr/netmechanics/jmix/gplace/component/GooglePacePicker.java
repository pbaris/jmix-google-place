package gr.netmechanics.jmix.gplace.component;

import java.util.stream.Stream;

import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;
import io.jmix.flowui.component.combobox.JmixComboBox;

/**
 * @author Panos Bariamis (pbaris)
 */
public class GooglePacePicker extends JmixComboBox<GooglePlaceRef> {

    public GooglePacePicker() {
        setItemsFetchCallback(query -> {
            return Stream.empty();
        });
    }
}
