package gr.netmechanics.jmix.gplace.data;

import java.util.List;

import io.jmix.core.metamodel.annotation.JmixEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@JmixEntity
public class GooglePlaceInfoRef extends GooglePlaceBaseRef {

    private String address;

    private String phoneNumber;

    private Double latitude;

    private Double longitude;

    private List<String> openingHours;
}
