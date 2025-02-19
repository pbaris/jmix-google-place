package gr.netmechanics.jmix.gplace.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@EqualsAndHashCode(of = "name")
public class GooglePlaceReviewRef {

    private String name;

    private String text;

    private String publishTimeText;

    private Double rating;

    private String authorName;

    private String mapsUri;
}
