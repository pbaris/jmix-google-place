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
public class GooglePlaceRatingRef implements Serializable {

    @JmixId
    private String id;

    @InstanceName
    private String displayName;

    private Double rating;

    private Integer ratingCount;

    private String mapUrl;

    private List<GooglePlaceReviewRef> reviews;
}
