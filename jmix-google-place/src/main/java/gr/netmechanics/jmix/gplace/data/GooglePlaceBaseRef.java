package gr.netmechanics.jmix.gplace.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public abstract class GooglePlaceBaseRef implements Serializable {

    @JmixId
    private String id;

    @JsonIgnore
    private String apiKey;

    @JsonIgnore
    private String languageCode;

    @InstanceName
    private String displayName;

    private String mapUrl;

    private String iconBackgroundColor;

    private String iconMaskBaseUri;
}
