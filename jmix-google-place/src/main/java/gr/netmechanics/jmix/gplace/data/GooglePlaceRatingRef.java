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
public class GooglePlaceRatingRef implements Serializable {

    private String id;

    private String displayName;

    private Double rating;

    private Integer ratingCount;

    private String mapsUri;

    private List<GooglePlaceReviewRef> reviews;
}
