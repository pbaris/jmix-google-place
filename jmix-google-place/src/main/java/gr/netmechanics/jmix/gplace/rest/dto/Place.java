package gr.netmechanics.jmix.gplace.rest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.netmechanics.jmix.gplace.data.GooglePlace;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@EqualsAndHashCode(of = "id")
public class Place implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("displayName")
    private DisplayName displayName;

    @JsonProperty("formattedAddress")
    private String formattedAddress;

    public GooglePlace toGooglePlace() {
        GooglePlace gp = new GooglePlace();
        gp.setId(id);
        gp.setDisplayName(displayName.getText());
        gp.setAddress(formattedAddress);
        return gp;
    }
}
