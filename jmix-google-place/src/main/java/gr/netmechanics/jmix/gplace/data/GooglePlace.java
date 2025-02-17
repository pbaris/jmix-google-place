package gr.netmechanics.jmix.gplace.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.netmechanics.jmix.gplace.rest.dto.DisplayName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@EqualsAndHashCode(of = "placeId")
public class GooglePlace {

    @JsonAlias("id")
    @JsonProperty("placeId")
    private String placeId;

    private String displayName;

    @JsonProperty("formattedAddress")
    private String address;

    @JsonProperty("displayName")
    private void unpackNested(final DisplayName displayName) {
        this.displayName = displayName.getText();
    }

    @Override
    public String toString() {
        return StringUtils.firstNonBlank(displayName, placeId);
    }
}
