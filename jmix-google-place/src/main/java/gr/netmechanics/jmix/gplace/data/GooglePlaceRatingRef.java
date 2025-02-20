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
public class GooglePlaceRatingRef extends GooglePlaceBaseRef {

    private Double rating;

    private Integer ratingCount;

    private List<GooglePlaceReviewRef> reviews;
}
