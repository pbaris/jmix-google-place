package gr.netmechanics.jmix.gplace.data;

import java.io.Serializable;
import java.util.List;

import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@JmixEntity
@EqualsAndHashCode(of = "id")
public class GooglePlaceInfoRef implements Serializable {

    @JmixId
    private String id;

    @InstanceName
    private String displayName;

    private String address;

    private String phoneNumber;

    private Double latitude;

    private Double longitude;

    private String mapUrl;

    private List<String> openingHours;
}
