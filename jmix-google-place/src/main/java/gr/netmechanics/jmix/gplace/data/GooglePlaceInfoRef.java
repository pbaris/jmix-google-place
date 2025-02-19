package gr.netmechanics.jmix.gplace.data;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class GooglePlaceInfoRef implements Serializable {

    private String id;

    private String displayName;

    private String address;

    private String phoneNumber;

    private Double latitude;

    private Double longitude;

    private String mapsUri;

    private List<String> openingHours;
}
