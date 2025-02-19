package gr.netmechanics.jmix.gplace.data;

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
@EqualsAndHashCode(of = "name")
public class GooglePlaceReviewRef {

    @JmixId
    private String name;

    private String text;

    private String publishTimeRelative;

    private Double rating;

    @InstanceName
    private String authorName;

    private String mapUrl;
}
