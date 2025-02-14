package gr.netmechanics.jmix.gplace.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Getter
@Setter
@EqualsAndHashCode(of = "placeId")
public class GooglePlaceRef {

    private String placeId;

    private String displayName;

    public static String toJson(final GooglePlaceRef gpr) {
        try {
            return new ObjectMapper().writeValueAsString(gpr);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot convert to Json", e);
        }
    }

    public static GooglePlaceRef fromJson(final String json) {
        try {
            return  new ObjectMapper().readValue(json, GooglePlaceRef.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot convert from Json", e);
        }
    }
}
