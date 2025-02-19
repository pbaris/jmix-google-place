package gr.netmechanics.jmix.gplace.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.netmechanics.jmix.gplace.data.GooglePlaceRef;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Panos Bariamis (pbaris)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static String toJson(final GooglePlaceRef gpr) {
        try {
            return new ObjectMapper().writeValueAsString(gpr);

        } catch (Exception e) {
            return null;
        }
    }

    public static GooglePlaceRef fromJson(final String json) {
        try {
            return  new ObjectMapper().readValue(json, GooglePlaceRef.class);

        } catch (Exception e) {
            return null;
        }
    }
}
